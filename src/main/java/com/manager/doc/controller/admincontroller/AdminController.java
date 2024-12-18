package com.manager.doc.controller.admincontroller;


import com.manager.doc.dto.user.account.UserAccountDTO;
import com.manager.doc.service.serviceauth.MgDocAppAuthenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired // * process issues authenticate
    private MgDocAppAuthenticate mgDocAppAuthenticate; // service authenticate folder #[[com.manager.doc.service.serviceauth]]
    private GrantedAuthority grantedAuthority;

    @RequestMapping // space working admin page
    public String adminRedirect() {

        return "admin/homepage";
    }

    @GetMapping("/login") //! Write down logic for roles admin
    public String adminLogin() {
        String loginPage = "admin/login_out/login";
        String setAdminAuthority = grantedAuthority.getAuthority();
        String homePageAdminRedirect = "ManagerBook/admin";


        return mgDocAppAuthenticate.redirectAuthenticateAlreadyLogin(setAdminAuthority, loginPage, homePageAdminRedirect);
    }

}
