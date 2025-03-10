package com.manager.doc.controller.admincontroller;


import com.manager.doc.service.serviceauth.Authenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired // * process issues authenticate
    @Qualifier("adminAuthenticate")
    private Authenticate adminAuthenticate; // service authenticate folder #[[com.manager.doc.service.serviceauth]]

    private GrantedAuthority grantedAuthority;

    @RequestMapping // ** Space working admin page
    public String adminRedirect() {

        return "admin/homepage";
    }

    @GetMapping("/login")
    public String adminLogin() {

        return "admin/login_out/login";
    }

    @PostMapping("/login")
    public String adminLoginPost() {
        String adminAuthority = grantedAuthority.getAuthority();

        return adminAuthenticate.redirectAuthenticateAlreadyLogin(adminAuthority);
    }

}
