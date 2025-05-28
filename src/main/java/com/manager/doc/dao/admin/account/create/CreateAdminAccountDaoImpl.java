package com.manager.doc.dao.admin.account.create;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateAdminAccountDaoImpl implements CreateAdminAccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Admin admin) {

        String sql = "insert into `admin_account` (Username, Password, IDAdminRole, IDAdmin) value(?,?,?,?)";

        Object[] objects = {
                admin.getAdminAccount().getUsername(),
                admin.getAdminAccount().getPassword(),
                admin.getAdminAccount().getRole().getId(),
                admin.getId()
        };
        return jdbcTemplate.update(sql, objects);
    }
}
