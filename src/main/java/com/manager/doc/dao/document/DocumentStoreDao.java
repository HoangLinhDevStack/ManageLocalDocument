package com.manager.doc.dao.document;

import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface DocumentStoreDao {

    Map<Integer, String> getIDAndNameDocumentStore() throws JSQLParserException;

}
