package com.manager.doc.dao.user.information.fetch.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserIDDaoImpl implements UserIDDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer fetchId(String username) {

        String sql = "select IDUser from user where Username = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
    }
}
