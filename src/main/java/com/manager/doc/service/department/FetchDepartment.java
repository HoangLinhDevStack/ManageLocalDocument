package com.manager.doc.service.department;

import com.manager.doc.dao.department.DepartmentDao;
import com.manager.doc.dao.department.DepartmentDaoImpl;
import com.manager.doc.model.department.Department;
import com.manager.doc.model.department.DepartmentWork;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.taglibs.standard.lang.jstl.IntegerLiteral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FetchDepartment {

    @Autowired
    @Qualifier("departmentDaoImpl")
    private DepartmentDao fetchDepartment;

    private Set<DepartmentWork> departmentWorkSet() {

        return fetchDepartment.getFullDepartmentWork(); // * fetch information department work
    }

    private Set<Department> departmentSet() {

        return fetchDepartment.getFullDepartment(); // * fetch information department
    }

    private Department getDepartmentByID(Integer IDDepartment) {
        return fetchDepartment.getDepartmentWorkByIDDepartment(IDDepartment);
    }

    public Map<Integer, String> choiceDepartment() throws JSQLParserException {

        return fetchDepartment.fetchDepartment();
    }

    public Set<Department> fetchFullDepartmentWork() { // * fetch full if department include department work

        Department departmentNew;
        Set<Department> departmentSet = new HashSet<>();

        for (Department department: departmentSet()) {
            departmentNew = getDepartmentByID(department.getId());
            if(departmentNew.getId() != null) {
                departmentSet.add(departmentNew);
            }

        }

        return departmentSet;
    }


}
