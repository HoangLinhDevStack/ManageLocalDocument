package com.manager.doc.dao.office;

import com.manager.doc.dao.interact.InteractDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class OfficeDaoImpl implements OfficeDao {

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> fetchOffice() throws JSQLParserException {

        final String sql = "SELECT IDOffice, Name FROM Office";   // SQL query to fetch data from the 'department' table
        return interactDao.keyAndValueRowByRow(sql); // Return the final map containing id and value (name office)
    }
}
