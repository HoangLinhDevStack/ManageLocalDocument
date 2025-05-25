package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

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
        
        // Lọc ra các tài liệu không bị xóa (status khác Rejected)
        documents = documents.stream()
                .filter(doc -> doc.getStatus() != StatusDocument.Rejected)
                .collect(Collectors.toList());

        // Truyền vào model để hiển thị trong JSP
        model.addAttribute("documents", documents);
        model.addAttribute("documentService", documentService);

        return "admin/document/list_document";
    }

    @GetMapping("/deleted")
    public String listDeletedDocuments(Model model) {
        // Lấy danh sách tài liệu từ DAO
        List<Document> documents = documentService.listDocuments();
        
        // Lọc ra các tài liệu đã bị xóa (status là Rejected)
        documents = documents.stream()
                .filter(doc -> doc.getStatus() == StatusDocument.Rejected)
                .collect(Collectors.toList());

        // Truyền vào model để hiển thị trong JSP
        model.addAttribute("documents", documents);
        model.addAttribute("documentService", documentService);

        return "admin/document/list_deleted_document";
    }

    @PostMapping("/delete/{id}")
    public String deleteDocument(@PathVariable("id") int documentId, RedirectAttributes redirectAttributes) {
        boolean success = documentService.deleteDocument(documentId);
        if (success) {
            redirectAttributes.addFlashAttribute("uploadSuccess", "Tài liệu đã được chuyển vào thùng rác!");
        } else {
            redirectAttributes.addFlashAttribute("uploadError", "Không thể xóa tài liệu. Vui lòng thử lại sau.");
        }
        return "redirect:/ManagerBook/admin/super/list-document";
    }

    @PostMapping("/restore/{id}")
    public String restoreDocument(@PathVariable("id") int documentId, RedirectAttributes redirectAttributes) {
        boolean success = documentService.updateDocumentStatus(documentId, StatusDocument.Pending);
        if (success) {
            redirectAttributes.addFlashAttribute("uploadSuccess", "Tài liệu đã được khôi phục thành công!");
        } else {
            redirectAttributes.addFlashAttribute("uploadError", "Không thể khôi phục tài liệu. Vui lòng thử lại sau.");
        }
        return "redirect:/ManagerBook/admin/super/list-document/deleted";
    }
}
