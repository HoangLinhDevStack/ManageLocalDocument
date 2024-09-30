package com.manager.doc.model.user;



public class UserAccount {
    private final int idUserAcc;
    private final String username;
    private String password;
    private final User user;
    private byte enable;
    private UserRoles role;

    public UserAccount(int idUserAcc,
                       String username,
                       User user) {
        this.idUserAcc = idUserAcc;
        this.username = username;
        this.user = user;
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

    public int getIdUserAcc() {
        return idUserAcc;
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
