package com.manager.doc.model.sex;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.User;

import java.util.HashSet;
import java.util.Set;

public class Sex {
    private Integer id;
    private String sex;
    private final Set<Admin> adminSex;
    private final Set<User> userSex;

    public Sex() {
        this.adminSex = new HashSet<>();
        this.userSex = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public Set<Admin> getAdminSex() {
        return adminSex;
    }

    public Set<User> getUserSex() {
        return userSex;
    }
}
