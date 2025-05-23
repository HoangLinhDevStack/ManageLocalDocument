package com.manager.doc.dao.document;

import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.document.Genres;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void saveDocument(Document document, int documentStoreId) {

        System.out.println("Saving document id: " + document.getAdmin().getId());

        // Lưu vào bảng doccument
        String sql = "INSERT INTO doccument (IDDoccumentStore, Title, Author, FilePath, FileSize, Status, IDAdmin, IDUser) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                documentStoreId,
                document.getTitle(),
                document.getAuthor(),
                document.getFilePath(),
                document.getFileSize(),
                document.getStatus().name(),
                document.getAdmin().getId(),
                null // Hoặc document.getUser() != null ? document.getUser().getId() : null
        );

        // Lấy ID mới insert
        Integer newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        document.setId(newId);

        // Lưu genres (document_has_genres)
        if (newId != null) {
            for (Genres genre : document.getGenres()) {
                jdbcTemplate.update(
                        "INSERT INTO doccument_has_genres (IDDoccument, IDDoccumentStore, IDGenres) VALUES (?, ?, ?)",
                        newId, documentStoreId, genre.getId()
                );
            }
        } else {
            throw new IllegalStateException("Document record not found for FK insertion.");
        }
    }

    @Override
    public Integer getAdminIdByUsername(String username) {
        try {
            String sql = "SELECT IDAdmin FROM admin_account WHERE Username = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            // Không tìm thấy admin với username này
            return null;
        }
    }


    @Override
    public List<Document> getAllDocuments() {
        String sql = "SELECT * FROM doccument";
        // Thực hiện truy vấn và ánh xạ kết quả vào danh sách Document
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Document document = new Document();
            document.setId(rs.getInt("IDDoccument"));
            document.setTitle(rs.getString("Title"));
            document.setAuthor(rs.getString("Author"));
            document.setFileSize(rs.getInt("FileSize"));
            document.setFilePath(rs.getString("FilePath"));
            document.setStatus(StatusDocument.valueOf(rs.getString("Status")));
            return document;
        });
    }
    
    @Override
    public Document getDocumentById(int documentId) {
        try {
            String sql = "SELECT * FROM doccument WHERE IDDoccument = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{documentId}, (rs, rowNum) -> {
                Document document = new Document();
                document.setId(rs.getInt("IDDoccument"));
                document.setTitle(rs.getString("Title"));
                document.setAuthor(rs.getString("Author"));
                document.setFileSize(rs.getInt("FileSize"));
                document.setFilePath(rs.getString("FilePath"));
                document.setStatus(StatusDocument.valueOf(rs.getString("Status")));
                return document;
            });
        } catch (Exception e) {
            System.err.println("Không tìm thấy tài liệu với ID: " + documentId);
            return null;
        }
    }
}
