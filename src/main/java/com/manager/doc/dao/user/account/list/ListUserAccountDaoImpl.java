package com.manager.doc.dao.user.account.list;

import com.manager.doc.model.user.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListUserAccountDaoImpl implements ListUserAccountDao{
    @Override
    public List<UserAccount> getList(List<UserAccount> userAccounts) {
        return null;
    }

    @Override
    public List<UserAccount> getDisableList(List<UserAccount> userAccounts) {
        return null;
    }

    @Override
    public List<UserAccount> getEnableList(List<UserAccount> userAccounts) {
        return null;
    }
}
