package com.manager.doc.model.sex;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.User;

import java.util.HashSet;
import java.util.Set;

public class Sex {
    private Integer id;
    private String sex;

    public Sex() {
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

}
