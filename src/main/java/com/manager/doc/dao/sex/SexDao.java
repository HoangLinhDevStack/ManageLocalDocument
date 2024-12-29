package com.manager.doc.dao.sex;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface SexDao {
    Map<Integer, String> fetchSex() throws JSQLParserException;
}
