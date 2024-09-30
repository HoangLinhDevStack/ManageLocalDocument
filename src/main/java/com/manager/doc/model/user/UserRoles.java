package com.manager.doc.model.user;

import com.manager.doc.enumeration.user.RolesUser;

public class UserRoles {
    private final int idUserRole;
    private final RolesUser keyRoles;

    public UserRoles(int idUserRole, RolesUser keyRoles) {
        this.idUserRole = idUserRole;
        this.keyRoles = keyRoles;
    }

    public int getIdUserRole() {
        return idUserRole;
    }

    public RolesUser getKeyRoles() {
        return keyRoles;
    }
}
