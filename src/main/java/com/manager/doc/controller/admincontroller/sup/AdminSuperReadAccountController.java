package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dto.user.CreateUserAccountDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/super/list-account")
public class AdminSuperReadAccountController {

    @GetMapping
    public String adminReadAccountForm(@ModelAttribute("userAccountDTO") CreateUserAccountDTO createUserAccountDTO) {
        return "admin/build_account/read_user_account";
    }

    @PostMapping
    public String adminReadAccount(@ModelAttribute("userAccountDTO") CreateUserAccountDTO createUserAccountDTO) {
        return "";
    }

}
