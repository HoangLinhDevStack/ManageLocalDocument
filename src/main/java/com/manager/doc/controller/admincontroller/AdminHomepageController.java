package com.manager.doc.controller.admincontroller;

import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminHomepageController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    public String showHomepage(Model model) {
        List<Document> documents = documentService.listDocuments();

        // Lọc ra các tài liệu không bị xóa (status khác Rejected)
//        documents = documents.stream()
//                .filter(doc -> doc.getStatus() != StatusDocument.Rejected)
//                .collect(Collectors.toList());

        model.addAttribute("documents", documents);
        model.addAttribute("documentService", documentService);


        return "admin/homepage";
    }
} 