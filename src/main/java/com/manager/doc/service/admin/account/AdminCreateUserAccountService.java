package com.manager.doc.service.admin.account;

import com.manager.doc.dao.admin.account.CreateUserAccountDao;
import com.manager.doc.dto.user.account.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
public class AdminCreateUserAccountService {

    @Autowired
    @Qualifier("createUserAccountDaoImpl")
    private CreateUserAccountDao createUserAccountDao;


    public void createUserAccount(UserAccountDTO userAccountDTO) {

    }
}
