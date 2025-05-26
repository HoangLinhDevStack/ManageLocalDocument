package com.manager.doc.service.document;

import com.manager.doc.dao.document.DocumentDao;
import com.manager.doc.dao.document.DocumentStoreDao;
import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.document.DocumentStore;
import com.manager.doc.model.document.Genres;
import net.sf.jsqlparser.JSQLParserException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional
    public void uploadDocument(Document document, int documentStoreId) {
        documentDao.saveDocument(document, documentStoreId);
    }

    public Map<Integer, String> getIDAndNameDocumentStore() throws JSQLParserException { // * get name store
        return documentStoreDao.getIDAndNameDocumentStore();
    }


    public List<Document> listDocuments() {
        return documentDao.getAllDocuments();
    }
    
    public Document getDocumentById(int documentId) {
        return documentDao.getDocumentById(documentId);
    }

    @Transactional
    public boolean deleteDocument(int documentId) {
        Document document = documentDao.getDocumentById(documentId);
        if (document == null) {
            return false;
        }
        
        // Update status to Rejected instead of deleting
        return documentDao.updateDocumentStatus(documentId, StatusDocument.Rejected);
    }

    @Transactional
    public boolean updateDocumentStatus(int documentId, StatusDocument status) {
        return documentDao.updateDocumentStatus(documentId, status);
    }

    @Transactional
    public boolean permanentDeleteDocument(int documentId) {
        Document document = documentDao.getDocumentById(documentId);
        if (document == null) {
            return false;
        }
        
        // Delete physical file
        try {
            java.nio.file.Path filePath = java.nio.file.Paths.get(document.getFilePath());
            java.nio.file.Files.deleteIfExists(filePath);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        // Delete from database
        return documentDao.deleteDocument(documentId);
    }

    public String generateFileName(String originalFileName, byte[] fileData) throws NoSuchAlgorithmException {
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        return UUID.randomUUID().toString() + "_" + DigestUtils.sha256Hex(fileData) + fileExtension;
    }

    public String formatFileSize(long sizeInBytes) {
        if (sizeInBytes >= 1024 * 1024 * 1024) {
            return String.format("%.2f GB", (double) sizeInBytes / (1024 * 1024 * 1024));
        } else if (sizeInBytes >= 1024 * 1024) {
            return String.format("%.2f MB", (double) sizeInBytes / (1024 * 1024));
        } else if (sizeInBytes >= 1024) {
            return String.format("%.2f KB", (double) sizeInBytes / 1024);
        } else {
            return sizeInBytes + " bytes";
        }
    }

    public Map<Integer, String> getIDAndNameGenres() throws JSQLParserException {
        return documentDao.getIDAndNameGenres();
    }

    @Transactional
    public boolean updateDocument(int documentId, int documentStoreId, List<Integer> genreIds, StatusDocument status, String author) {
        Document document = documentDao.getDocumentById(documentId);
        if (document == null) {
            return false;
        }

        // Update document store
        DocumentStore documentStore = documentStoreDao.getDocumentStoreById(documentStoreId);
        if (documentStore != null) {
            // Update document store in database
            documentDao.updateDocumentStoreForDocument(documentId, documentStoreId);
        }

        // Update status and author
        document.setStatus(status);
        document.setAuthor(author);
        
        // Update genres
        List<Genres> genres = document.getGenres();
        genres.clear();
        for (Integer genreId : genreIds) {
            Genres genre = documentDao.getGenreById(genreId);
            if (genre != null) {
                genres.add(genre);
            }
        }

        return documentDao.updateDocument(document);
    }

    public List<Genres> getDocumentGenres(int documentId) {
        return documentDao.getDocumentGenres(documentId);
    }

    public Integer getDocumentStoreIdByDocumentId(int documentId) {
        return documentDao.getDocumentStoreIdByDocumentId(documentId);
    }

    @Transactional
    public boolean updateDocumentStoreForDocument(int documentId, int documentStoreId) {
        return documentDao.updateDocumentStoreForDocument(documentId, documentStoreId);
    }

}
