package com.manager.doc.dao.department;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface DepartmentDao {

    Map<Integer, String> fetchDepartment() throws JSQLParserException;

}
