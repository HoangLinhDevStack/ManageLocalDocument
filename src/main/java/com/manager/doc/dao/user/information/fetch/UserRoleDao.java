package com.manager.doc.dao.user.information.fetch;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface UserRoleDao {
    Map<Integer, String> fetchUserRole() throws JSQLParserException;
}
