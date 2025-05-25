package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.document.DocumentForm;
import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.document.Genres;
import com.manager.doc.service.document.DocumentService;
import com.manager.doc.service.document.GenresService;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/super/update-document")
public class AdminSuperUpdateDocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private GenresService genresService;

    @GetMapping("/{documentId}")
    public String showUpdateForm(@PathVariable int documentId, Model model) throws JSQLParserException {
        Document document = documentService.getDocumentById(documentId);
        if (document == null) {
            return "redirect:/ManagerBook/admin/super/list-document";
        }

        // Lấy danh sách genres của document
        List<Genres> documentGenres = documentService.getDocumentGenres(documentId);
        for (Genres genre : documentGenres) {
            document.getGenres().add(genre);
        }

        model.addAttribute("document", document);
        model.addAttribute("genres", genresService.listGenres());
        model.addAttribute("documentStores", documentService.getIDAndNameDocumentStore());
        model.addAttribute("statusList", StatusDocument.values());

        return "admin/document/update_document";
    }

    @PostMapping("/{documentId}")
    public String updateDocument(@PathVariable int documentId,
                                 @RequestParam("documentStoreId") int documentStoreId,
                                 @RequestParam(value = "genreIdsStr", required = false) String genreIdsStr,
                                 @RequestParam("status") String status,
                                 @RequestParam("author") String author,
                                 RedirectAttributes redirectAttributes) {
        try {
            Document document = documentService.getDocumentById(documentId);
            if (document == null) {
                redirectAttributes.addFlashAttribute("uploadError", "Không tìm thấy tài liệu");
                return "redirect:/ManagerBook/admin/super/list-document";
            }

            System.out.println("==== Form Submission Debug Info ====");
            System.out.println("documentId: " + documentId);
            System.out.println("documentStoreId: " + documentStoreId);
            System.out.println("genreIdsStr: " + genreIdsStr);
            System.out.println("status: " + status);
            System.out.println("author: " + author);
            System.out.println("====================================");

            // Gộp logic input vào một DocumentForm
            DocumentForm form = new DocumentForm();
            form.setDocumentStoreId(documentStoreId);
            form.setGenreIdsStr(genreIdsStr);
            form.setStatus(status);
            form.setAuthor(author);

            boolean hasChanges = false;

            // So sánh document store
//            if (document.getDocumentStore().getId() != form.getDocumentStoreId()) {
//                hasChanges = true;
//            }

            // So sánh status
            if (!document.getStatus().name().equals(form.getStatus())) {
                hasChanges = true;
            }

            // So sánh author
            if (!document.getAuthor().equals(form.getAuthor())) {
                hasChanges = true;
            }

            // So sánh genres (có thể rỗng)
            if (form.getGenreIdsStr() != null && !form.getGenreIdsStr().trim().isEmpty()) {
                List<Integer> newGenreIds = Arrays.stream(form.getGenreIdsStr().split(","))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

                List<Integer> currentGenreIds = document.getGenres().stream()
                        .map(Genres::getId)
                        .collect(Collectors.toList());

                if (!newGenreIds.equals(currentGenreIds)) {
                    hasChanges = true;
                }
            }

            if (!hasChanges) {
                redirectAttributes.addFlashAttribute("uploadSuccess", "Không có thay đổi nào được thực hiện.");
                return "redirect:/ManagerBook/admin/super/list-document";
            }

            // Validate và convert status sang enum
            StatusDocument statusDefine;
            try {
                statusDefine = StatusDocument.valueOf(form.getStatus());
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("uploadError", "Trạng thái không hợp lệ.");
                return "redirect:/ManagerBook/admin/super/update-document/" + documentId;
            }

            // Chuẩn bị danh sách genreIds
            List<Integer> genreIds = (form.getGenreIdsStr() != null && !form.getGenreIdsStr().trim().isEmpty())
                    ? Arrays.stream(form.getGenreIdsStr().split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList())
                    : document.getGenres().stream()
                    .map(Genres::getId)
                    .collect(Collectors.toList());

            // Gọi cập nhật
            boolean updateResult = documentService.updateDocument(
                    documentId,
                    form.getDocumentStoreId(),
                    genreIds,
                    statusDefine,
                    form.getAuthor()
            );

            if (updateResult) {
                redirectAttributes.addFlashAttribute("uploadSuccess", "Cập nhật tài liệu thành công.");
            } else {
                redirectAttributes.addFlashAttribute("uploadError", "Cập nhật tài liệu thất bại.");
            }

            return "redirect:/ManagerBook/admin/super/list-document";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("uploadError", "Lỗi khi cập nhật: " + e.getMessage());
            return "redirect:/ManagerBook/admin/super/update-document/" + documentId;
        }
    }

} 