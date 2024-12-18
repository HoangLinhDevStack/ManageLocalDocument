package com.manager.doc.dao.user.account.delete;

import com.manager.doc.model.user.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeleteUserAccountDaoImpl implements DeleteUserAccountDao{

    @Override
    public boolean delete(UserAccount userAccount) {
        return false;
    }

    @Override
    public boolean deleteList(List<UserAccount> userAccounts) {
        return false;
    }
}
