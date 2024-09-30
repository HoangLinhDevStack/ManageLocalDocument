package com.manager.doc.model.admin;

import com.manager.doc.enumeration.admin.RolesAdmin;

public class AdminRoles {
    private final int idAdminRole;
    private final RolesAdmin keyRoles;

    public AdminRoles(int idAdminRole, RolesAdmin keyRoles) {
        this.idAdminRole = idAdminRole;
        this.keyRoles = keyRoles;
    }

    public int getIdAdminRole() {
        return idAdminRole;
    }

    public RolesAdmin getKeyRoles() {
        return keyRoles;
    }
}
