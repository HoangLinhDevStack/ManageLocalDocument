package com.manager.doc.controller.admincontroller;

import com.manager.doc.dto.user.account.UserAccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/create-account")
public class AdminCreateUserAccountController {

    @GetMapping
    public String adminCreateAccountForm(@ModelAttribute("userAccountDTO") UserAccountDTO userAccountDTO) {


        return "admin/build_account/create_account";
    }

    @PostMapping
    public String adminCreateAccount() {
        return "admin/homepage";
    }
}
