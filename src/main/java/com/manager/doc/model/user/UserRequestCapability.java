package com.manager.doc.model.user;

import com.manager.doc.enumeration.user.RolesUser;

public class UserRequestCapability {
    private int idUserRequestCapability;
    private RolesUser keyCapability;
    private String description;

    public UserRequestCapability() {}

    public void setIdUserRequestCapability(int idUserRequestCapability) {
        this.idUserRequestCapability = idUserRequestCapability;
    }

    public void setKeyCapability(RolesUser keyCapability) {
        this.keyCapability = keyCapability;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdUserRequestCapability() {
        return idUserRequestCapability;
    }

    public RolesUser getKeyCapability() {
        return keyCapability;
    }

    public String getDescription() {
        return description;
    }
}
