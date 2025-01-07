package com.manager.doc.model.user;

import com.manager.doc.enumeration.user.RolesUser;

public class UserRoles {
    private Integer id;
    private RolesUser keyRoles;

    public UserRoles() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RolesUser getKeyRoles() {
        return keyRoles;
    }

    public void setKeyRoles(RolesUser keyRoles) {
        this.keyRoles = keyRoles;
    }
}
