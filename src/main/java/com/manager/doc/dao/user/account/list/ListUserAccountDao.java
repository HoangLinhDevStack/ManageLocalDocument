package com.manager.doc.dao.user.account.list;

import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAccount;

import java.util.List;

public interface ListUserAccountDao {
    List<UserAccount> getList(); // * Get list user account
    List<UserAccount> getDisableList(); // * Get list user disable account
    List<UserAccount> getEnableList(); // * Get list user enable account
}
