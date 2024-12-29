package com.manager.doc.dao.user.information.fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<Integer, String> fetchAdminRole() {
        String sql = "Select IDUserRole, KeyRoles from user_roles";

        List<Map.Entry<Integer, String>> rolesResultList = jdbcTemplate.query(sql, new RowMapper<Map.Entry<Integer, String>>() {
            @Override
            public Map.Entry<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                int id = rs.getInt("IDUserRole");
                String role = rs.getString("KeyRoles");
                return new AbstractMap.SimpleEntry<>(id, role);
            }
        });

        Map<Integer, String> roles = new HashMap<>();
        for (Map.Entry<Integer, String> dataRole : rolesResultList) {
            roles.put(dataRole.getKey(), dataRole.getValue());
        }

        return roles;
    }
}











