package com.manager.doc.service.parsesql;

import net.sf.jsqlparser.JSQLParserException;

public interface IBodyColumnSQL {
    String[] keyAndValue(String sql) throws JSQLParserException;
}
