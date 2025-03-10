package com.manager.doc.service.department;

import com.manager.doc.dao.department.DepartmentDao;
import com.manager.doc.dao.department.DepartmentDaoImpl;
import com.manager.doc.model.department.Department;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FetchDepartment {

    @Autowired
    @Qualifier("departmentDaoImpl")
    private DepartmentDao fetchDepartment;

    public Map<Integer, String> choiceDepartment() throws JSQLParserException {

        return fetchDepartment.fetchDepartment();
    }
}
