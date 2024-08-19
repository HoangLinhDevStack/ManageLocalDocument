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
    public void saveUser(UserAccountDTO userAccountDTO) {

        String sql = "insert into `useraccount_infor` (IDUserAccount, Passwords, Roles) value(?,?,?)";

        jdbcTemplate.update(sql);

    }
}
