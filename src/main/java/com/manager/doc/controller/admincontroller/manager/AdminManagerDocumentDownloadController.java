package com.manager.doc.controller.admincontroller.manager;

import com.manager.doc.model.document.Document;
import com.manager.doc.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
@RequestMapping("admin/manager/document")
public class AdminManagerDocumentDownloadController {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @GetMapping("/download/{id}")
    public void downloadDocument(@PathVariable("id") int documentId, HttpServletResponse response) {
        try {
            Document document = documentService.getDocumentById(documentId);
            if (document == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy tài liệu");
                return;
            }

            Path filePath = Paths.get(document.getFilePath());
            if (!Files.exists(filePath)) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy file");
                return;
            }

            File file = filePath.toFile();
            String originalFilename = Optional.ofNullable(document.getTitle())
                    .filter(name -> !name.trim().isEmpty())
                    .orElseGet(() -> filePath.getFileName().toString());

            if (!originalFilename.contains(".")) {
                int lastDotIndex = filePath.getFileName().toString().lastIndexOf(".");
                if (lastDotIndex > 0) {
                    String extension = filePath.getFileName().toString().substring(lastDotIndex);
                    originalFilename += extension;
                }
            }

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            response.reset();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment; filename=\"" +
                    URLEncoder.encode(originalFilename, StandardCharsets.UTF_8.name()) + "\"");

            // Đọc file và stream ra response thay vì dùng setContentLength để tránh giới hạn 2GB
            try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
                 ServletOutputStream outStream = response.getOutputStream()) {

                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                outStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (!response.isCommitted()) {
                try {
                    response.reset();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi tải xuống file");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}
