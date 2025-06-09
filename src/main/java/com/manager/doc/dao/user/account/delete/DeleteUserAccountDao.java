package com.manager.doc.dao.user.account.delete;

import com.manager.doc.model.user.UserAccount;

import java.util.List;

public interface DeleteUserAccountDao {
    void deleteAdmin(Integer idAdmin);
    void deleteUser(Integer idUser);
    boolean deleteList(List<UserAccount> userAccounts);
}
