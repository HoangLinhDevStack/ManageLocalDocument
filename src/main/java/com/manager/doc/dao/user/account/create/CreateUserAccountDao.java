package com.manager.doc.dao.user.account.create;

import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAccount;
import java.util.List;

public interface CreateUserAccountDao {

    int save(User user);

    boolean isUsernameExists(String username);

    List<String> getAllUsernames();

}
