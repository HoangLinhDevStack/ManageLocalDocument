package com.manager.doc.model.user;

public class UserAccount {
    private Integer id;
    private String username;
    private String password;
    private final User user;
    private byte enable;
    private UserRoles role;

    public UserAccount() {
        this.user = new User();
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

    public User getUser() {
        return user;
    }

    public byte getEnable() {
        return enable;
    }

    public UserRoles getRole() {
        return role;
    }
}
