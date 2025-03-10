package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.user.CreateUserAccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/super/update-account")
public class AdminSuperUpdateAccountController {
    @GetMapping
    public String adminUpdateAccountForm(@ModelAttribute("userAccountDTO") CreateUserAccountDTO userAccountDTO) {
        return "";
    }

    @PostMapping
    public String adminUpdateAccount(@ModelAttribute("userAccountDTO") CreateUserAccountDTO userAccountDTO) {
        return "";
    }
}
