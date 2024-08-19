package com.manager.doc.service.serviceauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MgDocAppAuthenticate {


    //Method user already login in login page and if user again search link login page,
    // it automatically redirected your desire file jsp
    public String redirectAuthenticateAlreadyLogin(String authority, String login, String redirect) {
        // Authority: check authenticated user, admin, ....
        // Login: link login file jsp page
        // redirect: if user already login, it's automatically desire redirect page you want
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()) { // get an authority granted to an Authentication object.
                if(grantedAuthority.getAuthority().equals(authority)) {
                    return "redirect:/" + redirect;
                }
            }
        }
        return login;
    }

}
