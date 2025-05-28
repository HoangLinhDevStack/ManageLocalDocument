package com.manager.doc.dao.admin.information.fetch.roles;

import com.manager.doc.dao.interact.InteractDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AdminRoleDaoImpl implements AdminRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> fetchAdminRole() throws JSQLParserException {
        String sql = "Select IDAdminRole, KeyRoles from admin_roles";
        return interactDao.keyAndValueRowByRow(sql);
    }

}
