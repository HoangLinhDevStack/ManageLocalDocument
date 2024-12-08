package com.manager.doc.dao.admin.account.list;

import com.manager.doc.model.user.UserAccount;

import java.util.List;

public interface ListUserAccountDao {
    List<UserAccount> getList(List<UserAccount> userAccounts);
    List<UserAccount> getDisableList(List<UserAccount> userAccounts);
    List<UserAccount> getEnableList(List<UserAccount> userAccounts);
}
