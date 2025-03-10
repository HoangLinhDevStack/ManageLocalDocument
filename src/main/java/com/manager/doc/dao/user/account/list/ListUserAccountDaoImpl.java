package com.manager.doc.dao.user.account.list;

import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ListUserAccountDaoImpl implements ListUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<UserAccount> userAccountRowMapper = new RowMapper<UserAccount>() { // Utilize method to map col account
        @Override
        public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

            final UserAccount userAccount = new UserAccount();

            userAccount.setUsername(rs.getString("Username"));
            userAccount.setPassword(rs.getString("Password"));
            userAccount.setEnable(rs.getByte("Enabled"));
            return userAccount;
        }
    };

    @Override
    public List<UserAccount> getList() {

        final String sql = "select Username, Password, Enabled from user_account";

        return jdbcTemplate.query(sql, userAccountRowMapper);
    }

    @Override
    public List<UserAccount> getDisableList() {

        final String sql = "select Username, Password, Enabled from user_account where Enabled = ?";

        return jdbcTemplate.query(sql, new Object[]{0}, userAccountRowMapper);
    }

    @Override
    public List<UserAccount> getEnableList() {

        final String sql = "select Username, Password, Enabled from user_account  where Enabled = ?";

        return jdbcTemplate.query(sql, new Object[]{1}, userAccountRowMapper);
    }
}
