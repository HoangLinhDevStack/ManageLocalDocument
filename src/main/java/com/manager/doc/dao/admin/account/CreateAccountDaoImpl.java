package com.manager.doc.dao.admin.account;

import com.manager.doc.dto.user.account.UserAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateAccountDaoImpl implements CreateAccountDao {

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;


    @Override
    public void SaveUser(UserAccountDTO userAccountDTO) {

        String sql = "insert into `user_account` (IDUserAcc, Password) value(?,?)";

        jdbcTemplate.update(sql);

    }
}
