package com.manager.doc.service.serviceauth;

import com.manager.doc.enumeration.admin.RolesAdmin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminAuthenticate implements Authenticate {

    private final String DIRECT_ADMIN_SUPER = "ManagerBook/admin/super";
    private final String DIRECT_ADMIN_MANAGER = "ManagerBook/admin/manager";
    private final String DIRECT_ADMIN_DEV = "ManagerBook/admin/dev";
    private final String ADMIN_LOGIN_PAGE = "admin/login_out/login";
    private final String DIRECT_USER_AUTHOR = "ManagerBook/user/author";
    private final String DIRECT_USER_MANAGER = "ManagerBook/user/manager";
    private final String DIRECT_USER_STUDENT = "ManagerBook/user/student";



    //Method user already login in login page and if user again search link login page,
    // it automatically redirected your desire file jsp
    @Override
    public String redirectAuthenticateAlreadyLogin(String authority) {
        // Authority: check authenticated user, admin, ....
        // Login: link login file jsp page
        // redirect: if user already login, it's automatically desire redirect page you want
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) { // get an authority granted to an Authentication object.

                switch (grantedAuthority.getAuthority()) {
                    case "Super":
                        return "redirect:/" + DIRECT_ADMIN_SUPER;
                    case "Manager":
                        return "redirect:/" + DIRECT_ADMIN_MANAGER;
                    case "Dev":
                        return "redirect:/" + DIRECT_ADMIN_DEV;
                    default:
                        throw new IllegalStateException("Unexpected value: " + grantedAuthority.getAuthority());
                }

            }
        }
        return ADMIN_LOGIN_PAGE;
    }


}
