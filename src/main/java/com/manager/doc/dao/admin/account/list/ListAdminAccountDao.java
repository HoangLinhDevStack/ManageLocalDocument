package com.manager.doc.dao.admin.account.list;

import com.manager.doc.model.admin.AdminAccount;
import com.manager.doc.model.user.UserAccount;

import java.util.List;

public interface ListAdminAccountDao {

    List<AdminAccount> getList(); // * Get list admin account
    List<AdminAccount> getDisableList(); // * Get list admin disable account
    List<AdminAccount> getEnableList(); // * Get list admin enable account

}
