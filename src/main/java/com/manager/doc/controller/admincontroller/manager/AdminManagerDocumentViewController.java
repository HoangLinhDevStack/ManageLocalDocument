package com.manager.doc.controller.admincontroller.manager;

import com.manager.doc.controller.admincontroller.sup.AdminSuperDocumentViewController;
import com.manager.doc.model.document.Document;
import com.manager.doc.service.document.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("admin/manager/document")
public class AdminManagerDocumentViewController {

    private static final Logger logger = LoggerFactory.getLogger(AdminSuperDocumentViewController.class);
    private static final int BUFFER_SIZE = 8192; // 8KB buffer
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final int INITIAL_RETRY_DELAY = 100; // milliseconds

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @GetMapping("/view/{id}")
    public void viewDocument(@PathVariable("id") int documentId, HttpServletResponse response) {
        logger.info("Bắt đầu xử lý yêu cầu xem tài liệu ID: {}", documentId);

        try {
            // 1. Validate input
            if (documentId <= 0) {
                logger.warn("ID tài liệu không hợp lệ: {}", documentId);
                sendErrorSafe(response, HttpServletResponse.SC_BAD_REQUEST, "ID tài liệu không hợp lệ");
                return;
            }

            // 2. Lấy thông tin document với retry logic
            Document document = getDocumentWithRetry(documentId);
            if (document == null) {
                logger.warn("Không tìm thấy tài liệu với ID: {}", documentId);
                sendErrorSafe(response, HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy tài liệu");
                return;
            }

            // 3. Validate file path
            String filePath = document.getFilePath();
            if (filePath == null || filePath.trim().isEmpty()) {
                logger.error("Đường dẫn file không hợp lệ cho tài liệu ID: {}", documentId);
                sendErrorSafe(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đường dẫn file không hợp lệ");
                return;
            }

            Path path = Paths.get(filePath.trim());
            if (!Files.exists(path)) {
                logger.error("File không tồn tại: {} cho tài liệu ID: {}", filePath, documentId);
                sendErrorSafe(response, HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy file");
                return;
            }

            if (!Files.isReadable(path)) {
                logger.error("File không thể đọc: {} cho tài liệu ID: {}", filePath, documentId);
                sendErrorSafe(response, HttpServletResponse.SC_FORBIDDEN, "Không có quyền đọc file");
                return;
            }

            // 4. Kiểm tra kích thước file
            long fileSize = Files.size(path);
            if (fileSize == 0) {
                logger.error("File rỗng: {} cho tài liệu ID: {}", filePath, documentId);
                sendErrorSafe(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File rỗng");
                return;
            }

            logger.info("Bắt đầu stream file: {} (size: {} bytes) cho tài liệu ID: {}", filePath, fileSize, documentId);

            // 5. Thiết lập headers
            setupResponseHeaders(response, path, fileSize);

            // 6. Stream file
            streamFileToResponse(response, path);

            logger.info("Hoàn thành stream file cho tài liệu ID: {}", documentId);

        } catch (AdminManagerDocumentViewController.DocumentNotFoundException e) {
            logger.error("Không tìm thấy tài liệu ID: {} - {}", documentId, e.getMessage());
            sendErrorSafe(response, HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (IOException e) {
            logger.error("Lỗi I/O khi xử lý tài liệu ID: {} - {}", documentId, e.getMessage(), e);
            sendErrorSafe(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống khi đọc file");
        } catch (Exception e) {
            logger.error("Lỗi không mong muốn khi xử lý tài liệu ID: {} - {}", documentId, e.getMessage(), e);
            sendErrorSafe(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi hệ thống");
        }
    }

    private void setupResponseHeaders(HttpServletResponse response, Path filePath, long fileSize) throws IOException {
        // Xác định content type
        String contentType = determineContentType(filePath);

        // Thiết lập headers
        response.setContentType(contentType);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("Content-Disposition", "inline; filename=\"" + encodeFileName(filePath) + "\"");
        response.setHeader("Content-Length", String.valueOf(fileSize));
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Cache-Control", "private, max-age=3600"); // Cache 1 giờ
        response.setHeader("X-Frame-Options", "SAMEORIGIN");

        logger.debug("Thiết lập headers: Content-Type={}, Content-Length={}, filename={}",
                contentType, fileSize, filePath.getFileName());
    }

    private String determineContentType(Path filePath) {
        String fileName = filePath.getFileName().toString().toLowerCase();
        String contentType = null;

        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            logger.warn("Không thể xác định content type từ file: {}", e.getMessage());
        }

        // Fallback dựa trên extension
        if (contentType == null) {
            if (fileName.endsWith(".pdf")) {
                contentType = MediaType.APPLICATION_PDF_VALUE;
            } else if (fileName.endsWith(".doc") || fileName.endsWith(".docx")) {
                contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            } else if (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) {
                contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            } else if (fileName.endsWith(".ppt") || fileName.endsWith(".pptx")) {
                contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            } else {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }
        }

        return contentType;
    }

    private Document getDocumentWithRetry(int documentId) {
        int retryDelay = INITIAL_RETRY_DELAY;

        for (int attempt = 1; attempt <= MAX_RETRY_ATTEMPTS; attempt++) {
            try {
                Document document = documentService.getDocumentById(documentId);
                if (document != null) {
                    return document;
                }
                logger.warn("Attempt {}: Document null for ID {}", attempt, documentId);
            } catch (Exception e) {
                logger.warn("Attempt {}: Lỗi khi lấy document ID {} - {}", attempt, documentId, e.getMessage());
                if (attempt == MAX_RETRY_ATTEMPTS) {
                    logger.error("Đã thử {} lần nhưng không thể lấy document ID {}", MAX_RETRY_ATTEMPTS, documentId);
                    throw new AdminManagerDocumentViewController.DocumentNotFoundException("Không thể lấy thông tin tài liệu sau " + MAX_RETRY_ATTEMPTS + " lần thử");
                }
            }

            // Wait before retry
            try {
                Thread.sleep(retryDelay);
                retryDelay *= 2; // Exponential backoff
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new AdminManagerDocumentViewController.DocumentNotFoundException("Bị gián đoạn khi thử lại lấy document");
            }
        }

        return null;
    }

    private String encodeFileName(Path filePath) {
        String fileName = filePath.getFileName().toString();
        try {
            // Mã hóa tên file an toàn cho URL
            return URLEncoder.encode(fileName, StandardCharsets.UTF_8.name())
                    .replace("+", "%20"); // Thay thế '+' bằng '%20' để chuẩn URL
        } catch (UnsupportedEncodingException e) {
            // UTF-8 luôn được hỗ trợ, trường hợp này không bao giờ xảy ra
            logger.warn("Lỗi encode filename: {}", e.getMessage());
            return fileName;
        }
    }

    private void streamFileToResponse(HttpServletResponse response, Path filePath) throws IOException {
        long totalBytesRead = 0;
        long fileSize = Files.size(filePath);

        try (BufferedInputStream inStream = new BufferedInputStream(Files.newInputStream(filePath), BUFFER_SIZE);
             ServletOutputStream outStream = response.getOutputStream()) {

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = inStream.read(buffer)) != -1) {
                try {
                    // Kiểm tra xem client có còn kết nối không
                    if (response.isCommitted()) {
                        outStream.write(buffer, 0, bytesRead);
                        outStream.flush();
                        totalBytesRead += bytesRead;
                    } else {
                        outStream.write(buffer, 0, bytesRead);
                        totalBytesRead += bytesRead;
                    }

                    // Log progress cho file lớn (> 10MB)
                    if (fileSize > 10 * 1024 * 1024 && totalBytesRead % (1024 * 1024) == 0) {
                        logger.debug("Đã stream {}/{} bytes ({}%)",
                                totalBytesRead, fileSize, (totalBytesRead * 100 / fileSize));
                    }
                } catch (IOException e) {
                    logger.warn("Client đã ngắt kết nối khi đang stream file. Đã stream {}/{} bytes",
                            totalBytesRead, fileSize);
                    throw e;
                }
            }

            // Final flush
            outStream.flush();
            logger.debug("Hoàn thành stream file: {}/{} bytes", totalBytesRead, fileSize);

        } catch (IOException e) {
            logger.error("Lỗi khi stream file {} - đã stream {}/{} bytes: {}",
                    filePath.getFileName(), totalBytesRead, fileSize, e.getMessage());
            throw e;
        }
    }

    private void sendErrorSafe(HttpServletResponse response, int status, String message) {
        try {
            if (!response.isCommitted()) {
                response.reset();
                response.setStatus(status);
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().write(message);
                response.getWriter().flush();
                logger.debug("Đã gửi error response: status={}, message={}", status, message);
            } else {
                logger.warn("Không thể gửi error response vì response đã committed: status={}, message={}", status, message);
            }
        } catch (IOException ex) {
            logger.error("Lỗi khi gửi phản hồi lỗi (status={}, message={}): {}", status, message, ex.getMessage(), ex);
        }
    }

    // Custom exception để xử lý riêng trường hợp không tìm thấy document
    private static class DocumentNotFoundException extends RuntimeException {
        public DocumentNotFoundException(String message) {
            super(message);
        }
    }

}
