package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.configuration.configformat.ManagerDocFormatConfig;
import com.manager.doc.dto.document.DocumentForm;
import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.document.DocumentStore;
import com.manager.doc.model.document.Genres;
import com.manager.doc.model.user.User;
import com.manager.doc.service.document.DocumentService;
import com.manager.doc.service.document.GenresService;
import com.manager.doc.service.format.FormatTextUTF_8;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin/super/up-document")
@PropertySource("classpath:application.properties")
public class AdminSuperUpDocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private GenresService genresService;

    @Autowired
    @Qualifier("format_to_UTF_8")
    private FormatTextUTF_8 formatTextUTF_8;

    @Value("${upload.dir}")
    private String uploadDir;

    // Hiển thị form upload tài liệu
    @GetMapping
    public String showUploadForm(Model model) throws JSQLParserException {

        // Tạo DocumentForm và thiết lập adminId
        DocumentForm documentForm = new DocumentForm();
        model.addAttribute("documentForm", documentForm);


        model.addAttribute("genres", genresService.listGenres());
        model.addAttribute("documentStores", documentService.getIDAndNameDocumentStore());

        model.addAttribute("statusList", StatusDocument.values()); // Truyền Enum vào JSP
        return "admin/document/up_document";
    }

    // Xử lý tải lên tài liệu
    @PostMapping("/upload")
    public String uploadDocument(@RequestParam("title") String title,
                               @RequestParam("author") String author,
                               @RequestParam("status") String status,
                               @RequestParam("documentStoreId") int documentStoreId,
                               @RequestParam(value = "genreIdsStr", required = false) String genreIdsStr,
                               @RequestParam("fileData") MultipartFile fileData,
                               RedirectAttributes redirectAttributes) throws NoSuchAlgorithmException {
        try {

            // Lấy thông tin người dùng từ Spring Security
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;

            // Kiểm tra kiểu dữ liệu của principal
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                // Đây là đối tượng User của Spring Security
                username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            } else {
                // Nếu không phải User, có thể là String hoặc kiểu khác
                username = principal.toString();
            }

            // Lấy IDAdmin từ username
            Integer adminId = documentService.getAdminIdByUsername(username);

            // Kiểm tra nếu không tìm thấy admin
            if (adminId == null) {
                redirectAttributes.addFlashAttribute("uploadError", "Không tìm thấy ID người dùng");
                return "redirect:/ManagerBook/admin/super/up-document";
            }

            System.out.println("ID Admin được sử dụng: " + adminId);

            // Tạo đối tượng DocumentForm từ các tham số
            DocumentForm form = new DocumentForm();
            form.setTitle(title);
            form.setAuthor(author);
            form.setStatus(status);
            form.setDocumentStoreId(documentStoreId);
            form.setGenreIdsStr(genreIdsStr);
            form.setFileData(fileData);
            form.setAdminId(adminId);
            
            System.out.println("ID Admin được truyền vào: " + adminId);
            
            MultipartFile uploadedFile = fileData;

            System.out.println("Hển thị tên file: " + uploadedFile);

            System.out.println("Hiển thị còn lại: " + form.getAuthor() +  form.getStatus());

            if (uploadedFile == null || uploadedFile.isEmpty()) {
                redirectAttributes.addFlashAttribute("uploadError", "Vui lòng chọn một tệp để tải lên.");
                return "redirect:/ManagerBook/admin/super/up-document";
            }

            // Validate enum status
            StatusDocument statusDefine;
            try {
                statusDefine = StatusDocument.valueOf(form.getStatus());
            } catch (IllegalArgumentException e) {
                redirectAttributes.addFlashAttribute("uploadError", "Trạng thái không hợp lệ.");
                return "redirect:/ManagerBook/admin/super/up-document";
            }

            // ===== 📁 Xây đường dẫn lưu file theo ngày
            String originalFileName = formatTextUTF_8.decodeValue(uploadedFile.getOriginalFilename());
            String safeFileName = documentService.generateFileName(Objects.requireNonNull(originalFileName), uploadedFile.getBytes());
            String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            Path folderPath = Paths.get(uploadDir, today);
            Files.createDirectories(folderPath);  // Tạo thư mục nếu chưa có

            // ===== 🔐 Kiểm tra trùng tên file và sinh lại tên nếu trùng
            Path finalFilePath = folderPath.resolve(safeFileName);
            if (Files.exists(finalFilePath)) {
                // Sinh lại tên file nếu trùng
                safeFileName = documentService.generateFileName(originalFileName, uploadedFile.getBytes());
                finalFilePath = folderPath.resolve(safeFileName);
            }

            // Lưu file vào ổ đĩa
            Files.write(finalFilePath, uploadedFile.getBytes());

            // ===== Đổ vào Document entity
            Document document = new Document();
            document.setTitle(form.getTitle());
            document.setAuthor(form.getAuthor());
            document.setFilePath(finalFilePath.toString());
            document.setFileSize((int) uploadedFile.getSize());
            document.setStatus(StatusDocument.valueOf(status));

            // Thiết lập Admin ID
            Admin admin = new Admin(); 
            admin.setId(form.getAdminId());
            document.setAdmin(admin);

            System.err.println("Đây là id admin cho vào document: " + document.getAdmin().getId());


            if (form.getGenreIdsStr() != null) {
                List<Genres> genres = Arrays.stream(form.getGenreIdsStr().split(","))
                        .map(String::trim) // * Loại bỏ khoảng trắng ở đầu/cuối
                        .map(Integer::parseInt) // * Chuyển từ String sang Integer
                        .map(id -> { // * Tạo đối tượng Genres từ ID
                            Genres g = new Genres();
                            g.setId(id);
                            return g;
                        })
                        .collect(Collectors.toList()); // * Thu thập về danh sách genres
                document.getGenres().addAll(genres);  // * Thêm vào danh sách genres của document
            }

            documentService.uploadDocument(document, form.getDocumentStoreId());
            redirectAttributes.addFlashAttribute("uploadSuccess", "Tải lên thành công.");
            return "redirect:/ManagerBook/admin/super/list-document";

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("uploadError", "Lỗi khi ghi tệp: " + e.getMessage());
            return "redirect:/ManagerBook/admin/super/up-document";
        }
    }




}
