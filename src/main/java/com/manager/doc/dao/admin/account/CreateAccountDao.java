package com.manager.doc.dao.admin.account;

import com.manager.doc.model.user.UserAccount;

public interface CreateAccountDao {

    void SaveUser(UserAccount userAccount);

    int SelectAdmin();

}
