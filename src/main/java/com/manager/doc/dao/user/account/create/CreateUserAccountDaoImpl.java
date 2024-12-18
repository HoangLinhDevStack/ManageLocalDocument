package com.manager.doc.dao.user.account.create;

import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateUserAccountDaoImpl implements CreateUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(UserAccount userAccount) {

        String sql = "insert into `user_account` (Username, Password, IDUser) value(?,?,?)";

         int i = jdbcTemplate.update(sql);

         return i;

    }



}
