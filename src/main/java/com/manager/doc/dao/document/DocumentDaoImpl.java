package com.manager.doc.dao.document;

import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.document.Genres;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentDaoImpl implements DocumentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveDocument(Document document, int documentStoreId) {
        // Lưu vào bảng document
        String sql = "INSERT INTO doccument (IDDoccumentStore, Title, Author, FilePath, FileSize, Status, IDAdmin, IDUser) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                documentStoreId,
                document.getTitle(),
                document.getAuthor(),
                document.getFilePath(),        // ✅ lưu đường dẫn file
                document.getFileSize(),
                document.getStatus().name(),   // enum → String
                document.getAdmin().getId(),
                document.getUser().getId()
        );

        // Lấy ID mới insert
        Integer newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        document.setId(newId);

        // Lưu genres (document_has_genres)
        for (Genres genre : document.getGenres()) {
            jdbcTemplate.update(
                    "INSERT INTO doccument_has_genres (IDDoccument, IDDoccumentStore, IDGenres) VALUES (?, ?, ?)",
                    newId, documentStoreId, genre.getId()
            );
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
            document.setId(rs.getInt("IDDocument"));
            document.setTitle(rs.getString("Title"));
            document.setAuthor(rs.getString("Author"));
            document.setFileSize(rs.getInt("FileSize"));
            document.setFilePath(rs.getString("FilePath"));
            document.setStatus(StatusDocument.valueOf(rs.getString("Status")));
            return document;
        });
    }
}
