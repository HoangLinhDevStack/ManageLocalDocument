package com.manager.doc.model.form;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.user.User;

import java.util.Set;

public class Form {
    private final int idForm;
    private String name;
    private String type;
    private String language;
    private int fileSize;
    private byte[] fileData;
    private final Set<User> users;
    private final Set<Admin> admins;

    public Form(int idForm,
                Set<User> users,
                Set<Admin> admins) {
        this.idForm = idForm;
        this.users = users;
        this.admins = admins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    public int getIdForm() {
        return idForm;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }

    public int getFileSize() {
        return fileSize;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Admin> getAdmins() {
        return admins;
    }
}
