package com.manager.doc.dao.office;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface OfficeDao {

    Map<Integer, String> fetchOffice() throws JSQLParserException;

}
