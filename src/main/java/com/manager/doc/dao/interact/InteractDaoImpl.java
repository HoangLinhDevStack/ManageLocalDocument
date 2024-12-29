package com.manager.doc.dao.interact;

import com.manager.doc.service.parsesql.IBodyColumnSQL;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class InteractDaoImpl implements InteractDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("bodyColumnSQLImpl")
    private IBodyColumnSQL parseKeyAndValueSQL;

    @Override
    public Map<Integer, String> keyAndValueRowByRow(String sql) throws JSQLParserException {

        final String[] keyAndValueColumnSQL = parseKeyAndValueSQL.keyAndValue(sql); // * Get name id key and value from table

        // * Use entry to scan row by row in table
        // * Use List to keep data and then take one by one inside loop
        List<Map.Entry<Integer,String>> resultSet = jdbcTemplate.query(sql, new RowMapper<Map.Entry<Integer, String>>() {
            @Override
            public Map.Entry<Integer, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
                // * Mapping with table to get data
                int id = rs.getInt(keyAndValueColumnSQL[0]);
                String value = rs.getString(keyAndValueColumnSQL[1]);

                return new AbstractMap.SimpleEntry<>(id, value); // * Simple to present set key and value
            }
        });

        Map<Integer, String> result = new HashMap<>(); // * Loop value and return result
        for (Map.Entry<Integer, String> data : resultSet) {
            result.put(data.getKey(), data.getValue());
        }

        return result;
    }
}
