package com.manager.doc.model.user;

public class UserAccount {
    private Integer id;
    private String username;
    private String password;
    private byte enable;
    private UserRoles role;

    public UserAccount() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnable(byte enable) {
        this.enable = enable;
    }

    public void setRole(UserRoles role) {
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

    public UserRoles getRole() {
        return role;
    }
}
