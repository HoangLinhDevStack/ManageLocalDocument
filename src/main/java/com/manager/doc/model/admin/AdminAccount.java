package com.manager.doc.model.admin;

public class AdminAccount {
    private Integer id;
    private String username;
    private String password;
    private byte enable;
    private AdminRoles role;

    public AdminAccount() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public void setRole(AdminRoles role) {
        this.role = role;
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

    public AdminRoles getRole() {
        return role;
    }
}
