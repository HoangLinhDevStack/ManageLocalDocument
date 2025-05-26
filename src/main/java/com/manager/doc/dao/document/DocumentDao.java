package com.manager.doc.dao.document;

import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.document.Genres;
import net.sf.jsqlparser.JSQLParserException;

import java.util.List;
import java.util.Map;

public interface DocumentDao {
    void saveDocument(Document document, int documentStoreId);
    Integer getAdminIdByUsername(String username); // Lấy IDAdmin từ username
    List<Document> getAllDocuments(); // Lấy danh sách tài liệu
    Document getDocumentById(int documentId); // Lấy thông tin tài liệu theo ID
    boolean deleteDocument(int documentId); // Xóa tài liệu theo ID
    boolean updateDocumentStatus(int documentId, StatusDocument status); // Cập nhật trạng thái tài liệu
    Map<Integer, String> getIDAndNameGenres() throws JSQLParserException;
    Genres getGenreById(Integer genreId);
    boolean updateDocument(Document document);
    boolean updateDocumentStoreForDocument(int documentId, int documentStoreId);
    List<Genres> getDocumentGenres(int documentId);
    Integer getDocumentStoreIdByDocumentId(int documentId);
}
