package com.manager.doc.controller;


import com.manager.doc.model.user.UserAccount;
import com.manager.doc.properties.admin.AdminCreateAccountUserProperties;
import com.manager.doc.service.serviceauth.MgDocAppAuthenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminManagerBookController {

    @Autowired
    private MgDocAppAuthenticate mgDocAppAuthenticate;

    @Autowired
    private AdminCreateAccountUserProperties adminCreateAccountUserProperties;

    @RequestMapping // space working admin page
    public String adminRedirect() {

        return "admin/homepage";
    }

    @GetMapping("/login") // login admin account
    public String adminLogin() {
        String loginPage = "admin/Login_out/login";
        String setAdminAuthority = "ADMIN";
        String homePageAdminRedirect = "ManagerBook/admin";

        return mgDocAppAuthenticate.redirectAuthenticateAlreadyLogin(setAdminAuthority, loginPage, homePageAdminRedirect);
    }


    @GetMapping("/create-account") // space create account admin page
    public String adminCreateAccount(@ModelAttribute("userAccount") UserAccount userAccount,
                                     Model model) {

//        model.addAllAttributes()

        return "admin/build_account/create_account";
    }
}
