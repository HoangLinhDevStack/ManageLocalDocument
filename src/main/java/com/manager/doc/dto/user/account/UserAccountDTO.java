package com.manager.doc.dto.user.account;

import java.util.Date;

public class UserAccountDTO {

    private int IDUserAccount;
    private String Passwords;
    private int AccuracyCode;
    private String Roles;
    private Date HistoryLogin;
    private String Descriptions;

    public int getIDUserAccount() {
        return IDUserAccount;
    }

    public void setIDUserAccount(int IDUserAccount) {
        this.IDUserAccount = IDUserAccount;
    }

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String passwords) {
        Passwords = passwords;
    }

    public int getAccuracyCode() {
        return AccuracyCode;
    }

    public void setAccuracyCode(int accuracyCode) {
        AccuracyCode = accuracyCode;
    }

    public String getRoles() {
        return Roles;
    }

    public void setRoles(String roles) {
        Roles = roles;
    }

    public Date getHistoryLogin() {
        return HistoryLogin;
    }

    public void setHistoryLogin(Date historyLogin) {
        HistoryLogin = historyLogin;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }
}
