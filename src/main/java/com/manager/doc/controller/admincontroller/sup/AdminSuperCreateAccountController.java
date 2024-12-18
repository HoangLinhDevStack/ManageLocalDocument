package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.user.account.UserAccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/super/create-account")
public class AdminSuperCreateAccountController {

    @GetMapping
    public String adminChoiceFormAccount() {
        return "admin/build_account/choice_form";
    }

    @GetMapping("/user")
    public String adminCreateUserAccountForm(@ModelAttribute("userAccountDTO") UserAccountDTO userAccountDTO) {

        return "admin/build_account/create_user_account";
    }

    @GetMapping("/admin")
    public String adminCreateAdminAccountForm() {

        return "admin/build_account/";
    }

    @PostMapping("/user")
    public String adminCreateUserAccount() {
        return "admin/homepage";
    }

    @PostMapping("/admin")
    public String adminCreateAdminAccount() {
        return "admin/homepage";
    }


}
