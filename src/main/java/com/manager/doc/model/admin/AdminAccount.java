package com.manager.doc.model.admin;

public class AdminAccount {
    private final int idAdminAcc;
    private final String username;
    private String password;
    private byte enable;
    private final Admin admin;
    private AdminRoles role;

    public AdminAccount(int idAdminAcc,
                        String username,
                        Admin admin) {
        this.idAdminAcc = idAdminAcc;
        this.username = username;
        this.admin = admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public void setRole(AdminRoles role) {
        this.role = role;
    }

    public int getIdAdminAcc() {
        return idAdminAcc;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public byte getEnable() {
        return enable;
    }

    public Admin getAdmin() {
        return admin;
    }

    public AdminRoles getRole() {
        return role;
    }
}
