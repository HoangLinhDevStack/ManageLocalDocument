package com.manager.doc.dao.document;

import com.manager.doc.dao.interact.InteractDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentStoreDaoImpl implements DocumentStoreDao {

    @Autowired
    @Qualifier("interactDaoImpl")
    private InteractDao interactDao;

    @Override
    public Map<Integer, String> getIDAndNameDocumentStore() throws JSQLParserException {
        String sql = "SELECT IDDoccumentStore, NameStore FROM doccument_store";
        return interactDao.keyAndValueRowByRow(sql);
    }
}
