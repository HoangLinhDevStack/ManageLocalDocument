package com.manager.doc.dao.admin.information.fetch.id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminIDDaoImpl implements AdminIDDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer fetchId(String username) {

        String sql = "select IDAdmin from admin where Username = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
    }
}
