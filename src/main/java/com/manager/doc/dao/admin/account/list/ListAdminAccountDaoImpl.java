package com.manager.doc.dao.admin.account.list;

import com.manager.doc.model.admin.AdminAccount;
import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ListAdminAccountDaoImpl implements ListAdminAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<AdminAccount> adminAccountRowMapper = new RowMapper<AdminAccount>() { // Utilize method to map col account
        @Override
        public AdminAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

            final AdminAccount adminAccount = new AdminAccount();

            adminAccount.setUsername(rs.getString("Username"));
            adminAccount.setPassword(rs.getString("Password"));
            adminAccount.setEnable(rs.getByte("Enabled"));
            return adminAccount;
        }
    };

    @Override
    public List<AdminAccount> getList() {

        final String sql = "select Username, Password, Enabled from admin_account";

        return jdbcTemplate.query(sql, adminAccountRowMapper);
    }

    @Override
    public List<AdminAccount> getDisableList() {

        final String sql = "select Username, Password, Enabled from admin_account where Enabled = ?";

        return jdbcTemplate.query(sql, new Object[]{0}, adminAccountRowMapper);
    }

    @Override
    public List<AdminAccount> getEnableList() {

        final String sql = "select Username, Password, Enabled from admin_account  where Enabled = ?";

        return jdbcTemplate.query(sql, new Object[]{1}, adminAccountRowMapper);
    }
}
