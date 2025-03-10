package com.manager.doc.dao.department;

import com.manager.doc.dao.interact.InteractDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> fetchDepartment() throws JSQLParserException {
        final String sql = "SELECT IDDepartment, Name FROM Department";  // SQL query to fetch data from the 'department' table
        return interactDao.keyAndValueRowByRow(sql);  // Return the final map containing id and value (name department)
    }
}
