package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.model.document.Document;
import com.manager.doc.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/super/list-document")
public class AdminSuperListDocumentController {

    @Autowired
    @Qualifier("documentService")
    private DocumentService documentService;

    @GetMapping
    public String adminListDocument(Model model) {

        // Lấy danh sách tài liệu từ DAO
        List<Document> documents = documentService.listDocuments();

        for (Document document : documents) {
            System.out.println(document.getTitle());
        }

        // Truyền vào model để hiển thị trong JSP
        model.addAttribute("documents", documents);
        model.addAttribute("documentService", documentService);

        return "admin/document/list_document";
    }

}
