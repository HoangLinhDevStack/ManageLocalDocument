package com.manager.doc.service.admin.account;

import com.manager.doc.dao.admin.account.list.ListAdminAccountDao;
import com.manager.doc.dao.user.account.list.ListUserAccountDao;
import com.manager.doc.model.admin.AdminAccount;
import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminReadUserAccountService {

    @Autowired
    @Qualifier("listUserAccountDaoImpl")
    private ListUserAccountDao listUserDao;

    @Autowired
    @Qualifier("listAdminAccountDaoImpl")
    private ListAdminAccountDao listAdminDao;

// * ------------------ FOR USER ACCOUNT -----------------------------------

    public List<UserAccount> getUsersAccount() {
        return listUserDao.getList();
    }

    public List<UserAccount> getUsersAccountDisable() { return listUserDao.getDisableList(); }

    public List<UserAccount> getUsersAccountEnable() {
        return listUserDao.getEnableList();
    }


// * ----------------- FOR ADMIN ACCOUNT -----------------------------------

    public List<AdminAccount> getAdminsAccount(){
        return listAdminDao.getList();
    }

}
