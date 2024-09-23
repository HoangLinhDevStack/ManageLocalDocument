package com.manager.doc.model.user.account;

import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;


public class UserAccount {

    private int idUserAccount;
    private String username;
    private String password;
    private TinyIntTypeDescriptor enable;
    private int idUserRole;

    public int getIdUserAccount() {
        return idUserAccount;
    }

    public void setIdUserAccount(int idUserAccount) {
        this.idUserAccount = idUserAccount;
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

    public TinyIntTypeDescriptor getEnable() {
        return enable;
    }

    public void setEnable(TinyIntTypeDescriptor enable) {
        this.enable = enable;
    }

    public int getIDUserRole() {
        return idUserRole;
    }

    public void setIDUserRole(int IDUserRole) {
        this.idUserRole = IDUserRole;
    }


}
