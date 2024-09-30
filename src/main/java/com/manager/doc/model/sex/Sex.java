package com.manager.doc.model.sex;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.User;

import java.util.Set;

public class Sex {
    private final int idSex;
    private final String sex;
    private final Set<Admin> adminSex;
    private final Set<User> userSex;

    public Sex(int idSex, String sex, Set<Admin> adminSex, Set<User> userSex) {
        this.idSex = idSex;
        this.sex = sex;
        this.adminSex = adminSex;
        this.userSex = userSex;
    }

    public int getIdSex() {
        return idSex;
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
