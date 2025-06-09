package com.manager.doc.controller.admincontroller.manager;

import com.manager.doc.dto.user.CreateUserAccountDTO;
import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.UserAccount;
import com.manager.doc.service.admin.account.AdminReadUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.department.FetchDepartment;
import com.manager.doc.service.office.FetchOffice;
import com.manager.doc.service.user.inf.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/manager/list-account")
public class AdminManagerReadAccountController {


    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private AdminInformationService adminInformationService;

    @Autowired
    private FetchDepartment fetchDepartment;

    @Autowired
    private FetchOffice fetchOffice;

    @Autowired
    private AdminReadUserAccountService adminReadUserAccountService;

    @GetMapping
    public String adminReadAccountForm(@ModelAttribute("userAccountDTO") CreateUserAccountDTO createUserAccountDTO, Model model) {
        try {
            List<UserAccount> accounts = adminReadUserAccountService.getUsersAccount();

            model.addAttribute("roleUser", userInformationService.fetchUserRole());
            model.addAttribute("DepartmentKeyAndValue", fetchDepartment.choiceDepartment());
            model.addAttribute("OfficeKeyAndValue", fetchOffice.choiceOffices());
            model.addAttribute("userAccount", accounts);
            model.addAttribute("allUserInformation", adminInformationService.getAllUserInformation());
            model.addAttribute("departmentWork", fetchDepartment.fetchFullDepartmentWork());

            return "admin/build_account/read_user_account";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while loading user accounts: " + e.getMessage());
            return "admin/build_account/read_user_account";
        }
    }

    @PostMapping
    public String adminReadAccount(@ModelAttribute("userAccountDTO") CreateUserAccountDTO createUserAccountDTO, Model model) {
        // This method is not used currently
        return "redirect:/ManagerBook/admin/manager/list-account";
    }

    @GetMapping("/read-yourself")
    public String readYourself(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin admin = adminInformationService.getCurrentAdminInformation(username);
        if (admin != null) {
            model.addAttribute("admin", admin);
        }
        return "admin/build_information/read-yourself";
    }


}
