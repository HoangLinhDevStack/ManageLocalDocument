package com.manager.doc.dao.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminAccountDaoImpl")
public class AdminAccountDaoImpl implements AdminAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String getCurrentPassword(String username) {
        try {
            String sql = "SELECT Password FROM admin_account WHERE Username = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        try {
            String sql = "UPDATE admin_account SET Password = ? WHERE Username = ?";
            int rowsAffected = jdbcTemplate.update(sql, newPassword, username);
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Integer getAdminIdByUsername(String username) {
        try {
            String sql = "SELECT IDAdmin FROM admin_account WHERE Username = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean isUsernameExists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM admin_account WHERE Username = ?";
            int count = jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<String> getAllUsernames() {
        String sql = "SELECT Username FROM admin_account";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}