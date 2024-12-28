package com.manager.doc.dto.user.account;

import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserRoles;

public class UserAccountDTO {
// ** UserAccount table
    private String username;
    private String password;
    private UserRoles role;

// ** User table
    private User user;
    private Sex sex;

    public UserAccountDTO() {}

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

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
