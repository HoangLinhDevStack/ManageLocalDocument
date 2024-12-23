package com.manager.doc.model.admin;

import com.manager.doc.enumeration.admin.RolesAdmin;

public class AdminRoles {
    private Integer id;
    private RolesAdmin keyRoles;

    public AdminRoles() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setKeyRoles(RolesAdmin keyRoles) {
        this.keyRoles = keyRoles;
    }

    public RolesAdmin getKeyRoles() {
        return keyRoles;
    }
}
