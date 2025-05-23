package com.manager.doc.controller.admincontroller;

import com.manager.doc.service.admin.account.AdminPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/password")
public class AdminPasswordController {

    @Autowired
    private AdminPasswordService adminPasswordService;

    @PostMapping("/change")
    public String changePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes,
            Model model) {

        // Lấy thông tin người dùng hiện tại từ Spring Security
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Kiểm tra xác nhận mật khẩu
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("passwordError", "Mật khẩu mới và xác nhận mật khẩu không khớp");
            return "redirect:/ManagerBook/admin/super/setting";
        }

        // Kiểm tra mật khẩu hiện tại
        if (!adminPasswordService.checkCurrentPassword(username, currentPassword)) {
            redirectAttributes.addFlashAttribute("passwordError", "Mật khẩu hiện tại không đúng");
            return "redirect:/ManagerBook/admin/super/setting";
        }

        // Kiểm tra định dạng mật khẩu
        String error = adminPasswordService.validatePassword(newPassword);
        if (error != null) {
            redirectAttributes.addFlashAttribute("passwordError", error);
            return "redirect:/ManagerBook/admin/super/setting";
        }

        // Cập nhật mật khẩu mới
        boolean updated = adminPasswordService.updatePassword(username, newPassword);
        if (updated) {
            redirectAttributes.addFlashAttribute("passwordSuccess", "Đổi mật khẩu thành công");
        } else {
            redirectAttributes.addFlashAttribute("passwordError", "Đổi mật khẩu thất bại. Vui lòng thử lại sau");
        }

        return "redirect:/ManagerBook/admin/super/setting";
    }
}