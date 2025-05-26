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
        Integer idDocumentStoreOfIDDocument = documentService.getDocumentStoreIdByDocumentId(documentId);
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
        model.addAttribute("idDocumentStoreOfIDDocument", idDocumentStoreOfIDDocument);
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
            Integer idDocumentStoreOfIDDocument = documentService.getDocumentStoreIdByDocumentId(documentId);
            if (document == null) {
                redirectAttributes.addFlashAttribute("uploadError", "Không tìm thấy tài liệu");
                return "redirect:/ManagerBook/admin/super/list-document";
            }

            // Gộp logic input vào một DocumentForm
            DocumentForm form = new DocumentForm();
            form.setDocumentStoreId(documentStoreId);
            form.setGenreIdsStr(genreIdsStr);
            form.setStatus(status);
            form.setAuthor(author);

            boolean hasChanges = false;

            // So sánh document store
            if (idDocumentStoreOfIDDocument == null || idDocumentStoreOfIDDocument != form.getDocumentStoreId()) {
                hasChanges = true;
                // Cập nhật document store nếu có thay đổi
                documentService.updateDocumentStoreForDocument(documentId, form.getDocumentStoreId());
            }


            // So sánh status
            if (!document.getStatus().name().equals(form.getStatus())) {
                hasChanges = true;
            }

            // So sánh author
            if (!document.getAuthor().equals(form.getAuthor())) {
                hasChanges = true;
            }

//            // Kiểm tra xem form có gửi lên danh sách genres không
//            // form.getGenreIdsStr() có thể là null hoặc chuỗi rỗng
            if (form.getGenreIdsStr() != null && !form.getGenreIdsStr().trim().isEmpty()) { // * Kiểm tra xem form có gửi lên chuỗi genre IDs không
                System.out.println("=== CONTROLLER LAYER ===");
                System.out.println("Form genreIdsStr: " + form.getGenreIdsStr());

                // Chuyển đổi chuỗi genres từ form thành List<Integer>
                List<Integer> newGenreIds = Arrays.stream(form.getGenreIdsStr().split(",")) // * Chuyển đổi chuỗi thành danh sách số
                        .map(String::trim)        // Loại bỏ khoảng trắng thừa
                        .map(Integer::parseInt)   // Chuyển String thành Integer
                        .collect(Collectors.toList()); // Gộp thành List

                System.out.println("New genre IDs: " + newGenreIds);

                List<Genres> documentGenres = documentService.getDocumentGenres(documentId);
                for (Genres genre : documentGenres) {
                    document.getGenres().add(genre);
                } // * Thêm genres vào danh sách genres của document


                List<Integer> currentGenreIds = document.getGenres().stream() // * Lấy danh sách ID của genres hiện tại từ document
                        .map(Genres::getId)       // Lấy id của mỗi genre
                        .collect(Collectors.toList()); // Gộp thành List

                System.out.println("Current genre IDs: " + currentGenreIds);

                // So sánh 2 danh sách genres
                if (!newGenreIds.equals(currentGenreIds)) { // * So sánh 2 danh sách genres new và current
                    hasChanges = true;


                    List<Integer> genresToDelete = currentGenreIds.stream() // * Tìm các genres cần xóa (có trong currentGenreIds nhưng không có trong newGenreIds)
                            .filter(id -> !newGenreIds.contains(id))
                            .collect(Collectors.toList());

                    System.out.println("Genres to delete: " + genresToDelete);

                    // Nếu có genres cần xóa
                    if (!genresToDelete.isEmpty()) { // * thực thi xóa
                        // Xóa các genres không còn trong danh sách mới
                        for (Integer genreId : genresToDelete) {
                            System.out.println("Calling service to delete genre ID: " + genreId);
                            documentService.deleteGenreFromDocument(documentId, genreId);
                        }
                    }
                }
            }

//            if (form.getGenreIdsStr() != null && !form.getGenreIdsStr().trim().isEmpty()) {
//                documentService.handleGenreUpdates(document, form, hasChanges);
//            }

            if (!hasChanges) {
                redirectAttributes.addFlashAttribute("uploadSuccess", "Không có thay đổi nào được thực hiện.");
                return "redirect:/ManagerBook/admin/super/update-document/" + documentId;
            }

            // Validate và convert status sang enum
            StatusDocument statusDefine;
            try {
                statusDefine = StatusDocument.valueOf(form.getStatus());
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("uploadError", "Trạng thái không hợp lệ.");
                return "redirect:/ManagerBook/admin/super/update-document/" + documentId;
            }

            // Chuẩn bị danh sách genreIds cuối cùng để cập nhật
            // Sử dụng toán tử điều kiện (ternary operator) để quyết định lấy danh sách từ đâu
            List<Integer> genreIds = (form.getGenreIdsStr() != null && !form.getGenreIdsStr().trim().isEmpty())
                    ? // Nếu form có gửi lên genres mới
                    Arrays.stream(form.getGenreIdsStr().split(","))
                            .map(String::trim)        // Loại bỏ khoảng trắng thừa
                            .map(Integer::parseInt)   // Chuyển String thành Integer
                            .collect(Collectors.toList()) // Gộp thành List
                    : // Nếu form không gửi lên genres mới
                    document.getGenres().stream()
                            .map(Genres::getId)       // Lấy id của mỗi genre
                            .collect(Collectors.toList()); // Gộp thành List

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