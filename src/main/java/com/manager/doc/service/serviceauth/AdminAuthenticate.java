package com.manager.doc.service.serviceauth;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;



@Service
public class AdminAuthenticate implements Authenticate {

    private static final String DIRECT_ADMIN_SUPER = "ManagerBook/admin/super";
    private static final String DIRECT_ADMIN_MANAGER = "ManagerBook/admin/manager";
    private static final String DIRECT_ADMIN_DEV = "ManagerBook/admin/dev";
    private static final String ADMIN_LOGIN_PAGE = "admin/login_out/login";

    // Method to redirect after successful login based on authority
    @Override
    public String redirectAuthenticateAlreadyLogin(String authority) {
        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                // Check the authority and return corresponding redirect URL
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
        // If not authenticated or no matching authority, return login page
        return "redirect:/" + ADMIN_LOGIN_PAGE;
    }

}
