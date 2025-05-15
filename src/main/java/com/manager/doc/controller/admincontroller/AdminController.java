package com.manager.doc.controller.admincontroller;


import com.manager.doc.service.serviceauth.Authenticate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired // * process issues authenticate
    @Qualifier("adminAuthenticate")
    private Authenticate adminAuthenticate; // service authenticate folder #[[com.manager.doc.service.serviceauth]]

    private GrantedAuthority grantedAuthority;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @RequestMapping // ** Space working admin page
    public String adminRedirect() {

        return "admin/homepage";
    }

    @GetMapping("/login")
    public String adminLogin() {
        // Lấy thông tin người dùng hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Nếu người dùng đã đăng nhập, chuyển hướng đến trang chính của admin
        if (authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken)) {
            // Nếu người dùng đã đăng nhập, tránh redirect vòng lặp
            return "redirect:/ManagerBook/admin"; // Hoặc trang chính của admin
        }

        // Nếu người dùng chưa đăng nhập, trả về trang login
        return "admin/login_out/login";
    }


    @GetMapping("/logout")
    public RedirectView logout() {
        // Logic tùy chỉnh trước khi đăng xuất (nếu cần)
        SecurityContextHolder.clearContext(); // Xóa Context Security

        // Sau khi logout, chuyển hướng đến trang login với tham số logout
        return new RedirectView("QLy_Mg_Doc_Binh/ManagerBook/admin/logout");
    }

    @PostMapping("/process-login")
    public String adminLoginPost() {

        String adminAuthority = grantedAuthority.getAuthority();
        System.out.println("Đây là quyền admin này: " + adminAuthority);

        // Gọi service để kiểm tra và chuyển hướng
        return adminAuthenticate.redirectAuthenticateAlreadyLogin(adminAuthority);


        // Nếu không có quyền hợp lệ, chuyển hướng về trang login với thông báo lỗi
//        return "redirect:/admin/login?error=true";
    }


}


