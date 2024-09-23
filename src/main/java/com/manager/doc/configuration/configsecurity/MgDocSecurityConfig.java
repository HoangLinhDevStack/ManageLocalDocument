package com.manager.doc.configuration.configsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
                .usersByUsernameQuery("SELECT Username, Password, Enabled FROM admin_account WHERE IDAdminAcc = ?") // enabled this account to access login
                .authoritiesByUsernameQuery("SELECT admin_account.IDAdminAcc AS Username, admin_roles.KeyRoles AS authority FROM admin_account JOIN admin_roles ON admin_account.IDAdminRole = admin_roles.IDAdminRole WHERE admin_account.IDAdminAcc = ?"); // check authorized

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ManagerBook/admin").authenticated()
                .antMatchers("/ManagerBook/admin/login", "/ManagerBook/admin/process-login").authenticated()
                .antMatchers("/ManagerBook/admin/**").hasAuthority("Supper")
                .and()
                .formLogin().loginPage("/ManagerBook/admin/login")
                .loginProcessingUrl("/ManagerBook/admin/process-login") // take user and password
                .defaultSuccessUrl("/ManagerBook/admin", true)
                .failureUrl("/ManagerBook/admin/login?error=true")
                .permitAll()
                .and()
                .logout().logoutUrl("/ManagerBook/admin/logout").logoutSuccessUrl("/ManagerBook/admin/login?logout")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }

}

































