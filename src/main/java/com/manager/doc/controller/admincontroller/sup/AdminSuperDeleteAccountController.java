package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.user.CreateUserAccountDTO;
import com.manager.doc.service.admin.account.AdminDeleteUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/super/delete-account")
public class AdminSuperDeleteAccountController {


    @Autowired
    private AdminDeleteUserAccountService adminDeleteUserAccountService;
    @GetMapping("/user/{id}")
    public String adminDeleteAccountForm(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        try {

            System.out.println("ID: " + id);

            adminDeleteUserAccountService.deleteUserAccount(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting user.");
        }
        return "redirect:/ManagerBook/admin/super/list-account"; // Chuyển hướng về trang danh sách người dùng

    }

//    @PostMapping
//    public String adminDeleteAccount(@ModelAttribute("userAccountDTO") CreateUserAccountDTO userAccountDTO) {
//        return "";
//    }

    @GetMapping("/admin/{id}")
    public String adminDeleteAdminAccountForm(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            adminDeleteUserAccountService.deleteAdminAccount(id);
            redirectAttributes.addFlashAttribute("successMessage", "admin deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting admin.");
        }
        return "redirect:/ManagerBook/admin/super/list-account-admin"; // Chuyển hướng về trang danh sách người dùng
    }

//    @PostMapping
//    public String adminDeleteAdminAccount(@ModelAttribute("userAccountDTO") CreateUserAccountDTO userAccountDTO) {
//        return "";
//    }


}
