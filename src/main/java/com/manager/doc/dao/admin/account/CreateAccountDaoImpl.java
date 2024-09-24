package com.manager.doc.dao.admin.account;

import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateAccountDaoImpl implements CreateAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void SaveUser(UserAccount userAccount) {

        String sql = "insert into `user_account` (IDUserAcc, Password) value(?,?)";

        jdbcTemplate.update(sql);

    }

    @Override
    public int SelectAdmin() {

        System.out.println(jdbcTemplate);

        String sql = "SELECT IDAdminAcc FROM admin_account WHERE IDAdminAcc = 343041975";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(result + " Select admin success");
        return result != null ? result : 0;
    }


}
