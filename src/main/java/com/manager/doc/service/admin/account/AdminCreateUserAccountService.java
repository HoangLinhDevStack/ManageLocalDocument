package com.manager.doc.service.admin.account;

import com.manager.doc.dao.user.account.create.CreateUserAccountDao;
import com.manager.doc.dao.user.information.create.CreateUserDao;
import com.manager.doc.dto.user.account.UserAccountDTO;
import com.manager.doc.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
public class AdminCreateUserAccountService {

    @Autowired
    @Qualifier("createUserAccountDaoImpl")
    private CreateUserAccountDao createUserAccountDao;

    @Autowired
    @Qualifier("createPartUserDaoImpl")
    private CreateUserDao createPartUserDao;

    @Transactional
    public void createUserAccount(UserAccountDTO userAccountDTO) {

        User user = new User(); // * Create user information
        user.setName(userAccountDTO.getUsername());
        user.setSex(userAccountDTO.getSex());

        // * set value for user account
        user.getUserAccount().setUsername(userAccountDTO.getUsername());
        user.getUserAccount().setPassword(userAccountDTO.getPassword());

        createPartUserDao.save(user);
        createUserAccountDao.save(user);
    }
}
