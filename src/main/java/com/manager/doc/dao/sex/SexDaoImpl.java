package com.manager.doc.dao.sex;

import com.manager.doc.dao.interact.InteractDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SexDaoImpl implements SexDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> fetchSex() throws JSQLParserException {
        String sql = "SELECT IDSex, Sex FROM sex";  // SQL query to fetch data from the 'sex' table
        return interactDao.keyAndValueRowByRow(sql);  // Return the final map containing id and value

    }

}
