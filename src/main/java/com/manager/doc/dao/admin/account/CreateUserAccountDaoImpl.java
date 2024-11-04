package com.manager.doc.dao.admin.account;

import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateUserAccountDaoImpl implements CreateUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserAccount userAccount) {

        String sql = "insert into `user_account` (Username, Password, IDUser) value(?,?,?)";

        jdbcTemplate.update(sql);

    }



}
