package com.manager.doc.dao.interact;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface InteractDao {
    Map<Integer, String> keyAndValueRowByRow(String sql) throws JSQLParserException;
}
