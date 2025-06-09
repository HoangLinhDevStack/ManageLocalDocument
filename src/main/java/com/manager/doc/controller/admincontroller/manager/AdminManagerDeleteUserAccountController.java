package com.manager.doc.controller.admincontroller.manager;


import com.manager.doc.service.admin.account.AdminDeleteUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("admin/manager/delete-account")
public class AdminManagerDeleteUserAccountController {

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
        return "redirect:/ManagerBook/admin/manager/list-account"; // Chuyển hướng về trang danh sách người dùng

    }

}
