package com.manager.doc.dao.admin.information.fetch.roles;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface AdminRoleDao {

    Map<Integer, String> fetchAdminRole() throws JSQLParserException;

}
