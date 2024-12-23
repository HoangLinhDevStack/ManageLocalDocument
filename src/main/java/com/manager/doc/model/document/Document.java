package com.manager.doc.model.document;

import com.manager.doc.enumeration.document.StatusDocument;
import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.User;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private Integer id;
    private String title;
    private String author;
    private StatusDocument status;
    private int fileSize;
    private byte[] fileData;
    private final List<Genres> genres;
    private Admin admin;
    private User user;

    public Document() {
        this.genres = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStatus(StatusDocument status) {
        this.status = status;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public StatusDocument getStatus() {
        return status;
    }

    public int getFileSize() {
        return fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public Admin getAdmin() {
        return admin;
    }

    public User getUser() {
        return user;
    }
}
