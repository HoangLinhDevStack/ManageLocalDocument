package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.user.CreateUserAccountDTO;
import com.manager.doc.service.admin.account.AdminCreateUserAccountService;
import com.manager.doc.service.sex.FetchSex;
import com.manager.doc.service.user.inf.UserInformationService;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public String adminChoiceFormAccount() {
        return "admin/build_account/choice_form";
    }

    @GetMapping("/user")
    public String adminCreateUserAccountForm(@ModelAttribute("createUserAccountDTO") CreateUserAccountDTO createUserAccountDTO, Model model) throws JSQLParserException {

        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> roleUsers = userInformationService.fetchUserRole();

        model.addAttribute("sexData", sexData);
        model.addAttribute("roleUser", roleUsers);
        System.out.println(roleUsers);

        return "admin/build_account/create_user_account";
    }

    @GetMapping("/admin")
    public String adminCreateAdminAccountForm() {

        return "admin/build_account";
    }

    @PostMapping(value = "/user-list", produces = "application/x-www-form-urlencoded;charset:UTF-8")
    public String adminCreateUserAccount(@ModelAttribute("CreateUserAccountDTO") CreateUserAccountDTO createUserAccountDTO) {

        System.out.println("Controller layer: " + createUserAccountDTO.getUser().getName());

        adminCreateUserAccountService.createUserAccount(createUserAccountDTO);

        return "admin/build_account/read_user_account";
    }

    @PostMapping("/admin")
    public String adminCreateAdminAccount() {
        return "admin/homepage";
    }


}
