package com.manager.doc.dto.admin;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.admin.AdminRoles;
import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserRoles;

import java.util.Date;

public class CreateAdminAccountDTO {
    // ** UserAccount table
    private String username;
    private String password;
    private AdminRoles role;

    // ** User table
    private Admin admin;
    private Sex sex;

    public CreateAdminAccountDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminRoles getRole() {
        return role;
    }

    public void setRole(AdminRoles role) {
        this.role = role;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
