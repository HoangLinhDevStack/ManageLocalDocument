package com.manager.doc.dao.sex;

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
public class SexDaoImpl implements SexDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<Integer, String> fetchSex() {
        String sql = "SELECT IDSex, Sex FROM sex";  // SQL query to fetch data from the 'sex' table

        // Using JdbcTemplate's query method with a RowMapper to process the result set
        List<Map.Entry<Integer, String>> resultList = jdbcTemplate.query(sql, new RowMapper<Map.Entry<Integer, String>>() {
            @Override
            public Map.Entry<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                // Create a map entry for each row to map id to sex
                int id = rs.getInt("IDSex");
                String sex = rs.getString("Sex");
                return new AbstractMap.SimpleEntry<>(id, sex);  // Return the entry instead of a map
            }
        });

        // Convert the list of entries into a map
        Map<Integer, String> resultMap = new HashMap<>();
        for (Map.Entry<Integer, String> entry : resultList) {
            resultMap.put(entry.getKey(), entry.getValue());
        }

        return resultMap;  // Return the final map containing all results

    }

}
