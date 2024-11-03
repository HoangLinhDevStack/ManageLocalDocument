package com.manager.doc.dto.user.account;

import com.manager.doc.model.user.UserRoles;

public class UserAccountDTO {
    private Integer idUserAcc;
    private String username;
    private String password;
    private byte enable;
    private UserRoles role;

    public UserAccountDTO() {}

    public Integer getIdUserAcc() {
        return idUserAcc;
    }

    public void setIdUserAcc(Integer idUserAcc) {
        this.idUserAcc = idUserAcc;
    }

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

    public byte getEnable() {
        return enable;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
