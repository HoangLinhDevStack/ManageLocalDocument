package com.manager.doc.configuration.configsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class MgDocSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("datasource")
    private DataSource dataSource;

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder())
                .usersByUsernameQuery("SELECT Username, Password, Enabled FROM admin_account WHERE Username = ?") // enabled this account to access login
                .authoritiesByUsernameQuery(
                        "SELECT admin_account.Username AS Username, admin_roles.KeyRoles AS authority FROM admin_account " +
                        "JOIN admin_roles ON admin_account.IDAdminRole = admin_roles.IDAdminRole " +
                        "WHERE admin_account.Username = ?"); // check authorized
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .headers()
                .frameOptions().sameOrigin() // Cho phép iframe từ cùng một origin
                .and()


                .csrf().disable() // Tạm thời vô hiệu hóa CSRF để kiểm tra vấn đề upload file
                .authorizeRequests()
                // Allow access to the login page and login processing without authentication
                .antMatchers("/ManagerBook/admin/login", "/ManagerBook/admin/process-login").permitAll()

                // Restrict access to specific sections based on roles
                .antMatchers("/ManagerBook/admin/super/**").hasAuthority("Super")
                .antMatchers("/ManagerBook/admin/super/document/view/**").authenticated()


                .antMatchers("/ManagerBook/admin/manager/**").hasAuthority("Manager")
                .antMatchers("/ManagerBook/admin/dev/**").hasAuthority("Dev")

                // Allow access to the main admin dashboard for authenticated users
                .antMatchers("/ManagerBook/admin").authenticated()

                // Any other admin URLs require authentication
                .antMatchers("/ManagerBook/admin/**").authenticated()

                .and()
                .formLogin()
                .loginPage("/ManagerBook/admin/login")
                .loginProcessingUrl("/ManagerBook/admin/process-login") // Handles login POST requests
                .defaultSuccessUrl("/ManagerBook/admin", true) // Redirect to admin dashboard on success
                .failureUrl("/ManagerBook/admin/login?error=true") // Redirect on login failure
                .permitAll()

                .and()
                .logout()
                .logoutUrl("/admin/logout") // Đảm bảo URL đăng xuất trùng với controller
                .logoutSuccessUrl("/ManagerBook/admin/logout") // Chuyển hướng về trang login sau khi logout
                .clearAuthentication(true) // Xóa thông tin xác thực khi đăng xuất
                .invalidateHttpSession(true) // Vô hiệu hóa session
                .deleteCookies("JSESSIONID") // Xóa cookie phiên
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler()); // Custom handler for access denied exceptions
    }


}

































