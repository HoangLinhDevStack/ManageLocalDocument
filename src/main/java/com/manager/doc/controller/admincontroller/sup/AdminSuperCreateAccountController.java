package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.admin.CreateAdminAccountDTO;
import com.manager.doc.dto.user.CreateUserAccountDTO;
import com.manager.doc.service.admin.account.AdminCreateUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.sex.FetchSex;
import com.manager.doc.service.user.inf.UserInformationService;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "admin/super/create-account", produces = "application/x-www-form-urlencoded;charset:UTF-8")
public class AdminSuperCreateAccountController {

    @Autowired
    private AdminCreateUserAccountService adminCreateUserAccountService;

    @Autowired
    private FetchSex fetchSex;

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private AdminInformationService adminInformationService;

    @GetMapping
    public String adminChoiceFormAccount() {
        return "admin/build_account/choice_form";
    }

    @GetMapping("/user")
    public String adminCreateUserAccountForm(@ModelAttribute("createUserAccountDTO") CreateUserAccountDTO createUserAccountDTO, 
                                           Model model,
                                           @ModelAttribute("error") String error) throws JSQLParserException {
        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> roleUsers = userInformationService.fetchUserRole();
        List<String> existingUsernames = adminCreateUserAccountService.getAllUserUsernames();

        model.addAttribute("sexData", sexData);
        model.addAttribute("roleUser", roleUsers);
        model.addAttribute("existingUsernames", existingUsernames);
        
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        return "admin/build_account/create_user_account";
    }

    @GetMapping("/admin")
    public String adminCreateAdminAccountForm(@ModelAttribute("createAdminAccountDTO") CreateAdminAccountDTO createAdminAccountDTO, 
                                            Model model,
                                            @ModelAttribute("error") String error) throws JSQLParserException {
        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> roleAdmins = adminInformationService.fetchAdminRole();
        List<String> existingUsernames = adminCreateUserAccountService.getAllAdminUsernames();

        model.addAttribute("sexData", sexData);
        model.addAttribute("roleAdmin", roleAdmins);
        model.addAttribute("existingUsernames", existingUsernames);
        
        if (error != null && !error.isEmpty()) {
            model.addAttribute("error", error);
        }

        return "admin/build_account/create_admin_account";
    }

    @PostMapping(value = "/user-list", produces = "application/x-www-form-urlencoded;charset:UTF-8")
    public String adminCreateUserAccount(@ModelAttribute("CreateUserAccountDTO") CreateUserAccountDTO createUserAccountDTO,
                                       RedirectAttributes redirectAttributes) {
        // Kiểm tra username đã tồn tại chưa
        if (adminCreateUserAccountService.isUserUsernameExists(createUserAccountDTO.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Tên tài khoản này đã được sử dụng");
            return "redirect:/ManagerBook/admin/super/create-account/user";
        }
        
        adminCreateUserAccountService.createUserAccount(createUserAccountDTO);
        return "redirect:/ManagerBook/admin/super/list-account";
    }

    @PostMapping(value = "/admin-list", produces = "application/x-www-form-urlencoded;charset:UTF-8")
    public String adminCreateAdminAccount(@ModelAttribute("createAdminAccountDTO") CreateAdminAccountDTO createAdminAccountDTO,
                                        RedirectAttributes redirectAttributes) {
        // Kiểm tra username đã tồn tại chưa
        if (adminCreateUserAccountService.isAdminUsernameExists(createAdminAccountDTO.getUsername())) {
            redirectAttributes.addFlashAttribute("error", "Tên tài khoản này đã được sử dụng");
            return "redirect:/ManagerBook/admin/super/create-account/admin";
        }
        
        adminCreateUserAccountService.createAdminAccount(createAdminAccountDTO);
        return "redirect:/ManagerBook/admin/super/list-account-admin";
    }
}
