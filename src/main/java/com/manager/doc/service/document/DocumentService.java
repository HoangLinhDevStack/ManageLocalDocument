package com.manager.doc.service.document;

import com.manager.doc.dao.document.DocumentDao;
import com.manager.doc.dao.document.DocumentStoreDao;
import com.manager.doc.model.document.Document;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DocumentService {
    @Autowired
    @Qualifier("documentDaoImpl")
    private DocumentDao documentDao;

    @Autowired
    @Qualifier("documentStoreDaoImpl")
    private DocumentStoreDao documentStoreDao;

    // Lấy IDAdmin từ tên người dùng (username)
    public Integer getAdminIdByUsername(String username) {
        // Giả sử có một phương thức tìm Admin ID dựa vào username
        return documentDao.getAdminIdByUsername(username);
    }


    public void uploadDocument(Document document, int documentStoreId) {
        documentDao.saveDocument(document, documentStoreId);
    }

    public Map<Integer, String> getIDAndNameDocumentStore() throws JSQLParserException { // * get name store
        return documentStoreDao.getIDAndNameDocumentStore();
    }


    public List<Document> listDocuments() {
        return documentDao.getAllDocuments();
    }

    public String generateFileName(String originalFileName, byte[] fileData) throws NoSuchAlgorithmException {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + "_" + DigestUtils.sha256Hex(fileData) + fileExtension;
    }


}
