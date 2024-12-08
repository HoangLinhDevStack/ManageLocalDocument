package com.manager.doc.dao.admin.account.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UpdateUserAccountDaoImpl implements UpdateUserAccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean update() {
        return false;
    }
}
