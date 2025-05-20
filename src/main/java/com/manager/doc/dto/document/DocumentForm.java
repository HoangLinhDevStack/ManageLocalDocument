package com.manager.doc.dto.document;

import org.springframework.web.multipart.MultipartFile;

public class DocumentForm {

    private String title;
    private String author;
    private int fileSize;
    private MultipartFile fileData;
    private int adminId;
    private int userId;
    private int documentStoreId;
    private String genreIdsStr;
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public MultipartFile getFileData() {
        return fileData;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDocumentStoreId() {
        return documentStoreId;
    }

    public void setDocumentStoreId(int documentStoreId) {
        this.documentStoreId = documentStoreId;
    }

    public String getGenreIdsStr() {
        return genreIdsStr;
    }

    public void setGenreIdsStr(String genreIdsStr) {
        this.genreIdsStr = genreIdsStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
