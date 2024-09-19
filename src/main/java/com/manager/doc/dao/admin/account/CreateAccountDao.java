package com.manager.doc.dao.admin.account;

import com.manager.doc.dto.user.account.UserAccount;

public interface CreateAccountDao {

    void SaveUser(UserAccount userAccount);

    int SelectAdmin();

}
