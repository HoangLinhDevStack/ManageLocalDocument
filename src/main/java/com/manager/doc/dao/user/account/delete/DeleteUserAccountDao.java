package com.manager.doc.dao.user.account.delete;

import com.manager.doc.model.user.UserAccount;

import java.util.List;

public interface DeleteUserAccountDao {
    boolean delete(UserAccount userAccount);
    boolean deleteList(List<UserAccount> userAccounts);
}
