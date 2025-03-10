package com.manager.doc.service.admin.inf;

import com.manager.doc.dao.user.information.fetch.full.FetchFullUserDao;
import com.manager.doc.dto.user.UserWithInformationDTO;
import com.manager.doc.model.user.User;
import jakarta.interceptor.AroundInvoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminInformationService {

    @Autowired
    @Qualifier("fetchFullUserDaoImpl")
    private FetchFullUserDao fetchFullUserDao;

    public List<User> getFullUserAndAccount() {
        return fetchFullUserDao.getFullUserAndAccount();
    } // * inf user and account

    public List<UserWithInformationDTO> getMultipleInfUser () {
        return fetchFullUserDao.getFullUserInformation();
    } // * multiple inf

    public List<UserWithInformationDTO> getFullPrivateInformationUser() {
        return fetchFullUserDao.getFullPrivateUserInformation();
    } // * private inf



}
