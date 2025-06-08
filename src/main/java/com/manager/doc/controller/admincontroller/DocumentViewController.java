package com.manager.doc.controller.admincontroller;

import com.manager.doc.model.document.Document;
import com.manager.doc.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/ManagerBook/admin/super/document")
public class DocumentViewController {
    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @GetMapping("/view/{id}")
    public void viewDocument(@PathVariable("id") int documentId, HttpServletResponse response) {
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

            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            response.reset();
            response.setContentType(contentType);
            // KHÔNG set Content-Disposition: attachment để browser tự quyết định xem/tải

            try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(filePath.toFile()));
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
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi đọc file");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
} 