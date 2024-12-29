package com.manager.doc.dao.user.information.fetch;

import com.manager.doc.dao.interact.InteractDao;
import net.sf.jsqlparser.JSQLParserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> fetchUserRole() throws JSQLParserException {
        String sql = "Select IDUserRole, KeyRoles from user_roles";
        return interactDao.keyAndValueRowByRow(sql);
    }
}











