package com.manager.doc.dao.department;

import com.manager.doc.model.department.Department;
import com.manager.doc.model.department.DepartmentWork;
import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;
import java.util.Set;

public interface DepartmentDao {

    Map<Integer, String> fetchDepartment() throws JSQLParserException;
    Set<Department> getFullDepartment();
    Set<DepartmentWork> getFullDepartmentWork();

    Department getDepartmentWorkByIDDepartment(Integer IDDepartment); // ! Get department by id do tomorrow

}
