package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.user.account.UserAccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/supper/delete-account")
public class AdminSupperDeleteUserAccountController {
    @GetMapping
    public String adminDeleteAccountForm(@ModelAttribute("userAccountDTO") UserAccountDTO userAccountDTO) {
        return "";
    }

    @PostMapping
    public String adminDeleteAccount(@ModelAttribute("userAccountDTO") UserAccountDTO userAccountDTO) {
        return "";
    }
}
