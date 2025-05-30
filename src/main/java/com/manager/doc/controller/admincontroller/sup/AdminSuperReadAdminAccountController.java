package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.admin.CreateAdminAccountDTO;
import com.manager.doc.model.admin.AdminAccount;
import com.manager.doc.service.admin.account.AdminReadUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.department.FetchDepartment;
import com.manager.doc.service.office.FetchOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/super/list-account-admin")
public class AdminSuperReadAdminAccountController {

    @Autowired
    private AdminInformationService adminInformationService;

    @Autowired
    private FetchDepartment fetchDepartment;

    @Autowired
    private FetchOffice fetchOffice;

    @Autowired
    private AdminReadUserAccountService adminReadUserAccountService;

    @GetMapping
    public String adminReadAdminAccountForm(Model model) {
        try {
            List<AdminAccount> accounts = adminReadUserAccountService.getAdminsAccount();
            
            model.addAttribute("roleAdmin", adminInformationService.fetchAdminRole());
            model.addAttribute("DepartmentKeyAndValue", fetchDepartment.choiceDepartment());
            model.addAttribute("OfficeKeyAndValue", fetchOffice.choiceOffices());
            model.addAttribute("adminAccount", accounts);
            model.addAttribute("allAdminInformation", adminInformationService.getAllAdminInformation());
            model.addAttribute("departmentWork", fetchDepartment.fetchFullDepartmentWork());

            return "admin/build_account/read_admin_account";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi tải danh sách tài khoản admin: " + e.getMessage());
            return "admin/build_account/read_admin_account";
        }
    }

//    @PostMapping
//    public String adminReadAdminAccount(@ModelAttribute("adminAccountDTO") CreateAdminAccountDTO createAdminAccountDTO, Model model) {
//        // This method is not used currently
//        return "redirect:/ManagerBook/admin/super/list-account";
//    }

}
