package com.manager.doc.service.serviceauth;

public class UserAuthenticate implements Authenticate{
    private final String DIRECT_USER_AUTHOR = "ManagerBook/user/author";
    private final String DIRECT_USER_MANAGER = "ManagerBook/user/manager";
    private final String DIRECT_USER_STUDENT = "ManagerBook/user/student";
    private final String USER_LOGIN_PAGE = "";


    @Override
    public String redirectAuthenticateAlreadyLogin(String authority) {
        return null;
    }
}
