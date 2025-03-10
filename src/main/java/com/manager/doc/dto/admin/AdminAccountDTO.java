package com.manager.doc.dto.admin;

import java.util.Date;

public class AdminAccountDTO {
    private String userName;
    private int adminPassword;
    private int numberAccuracy;
    private String roles;
    private Date historyLogin;
    private String descriptions;



    public int getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(int adminPassword) {
        this.adminPassword = adminPassword;
    }

    public int getNumberAccuracy() {
        return numberAccuracy;
    }

    public void setNumberAccuracy(int numberAccuracy) {
        this.numberAccuracy = numberAccuracy;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getHistoryLogin() {
        return historyLogin;
    }

    public void setHistoryLogin(Date historyLogin) {
        this.historyLogin = historyLogin;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
}
