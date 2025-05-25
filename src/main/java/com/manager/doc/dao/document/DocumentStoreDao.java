package com.manager.doc.dao.document;

import com.manager.doc.model.document.DocumentStore;
import net.sf.jsqlparser.JSQLParserException;

import java.util.Map;

public interface DocumentStoreDao {

    Map<Integer, String> getIDAndNameDocumentStore() throws JSQLParserException;
    DocumentStore getDocumentStoreById(int id);

}
