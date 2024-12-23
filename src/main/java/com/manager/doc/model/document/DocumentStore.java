package com.manager.doc.model.document;

import java.util.HashSet;
import java.util.Set;

public class DocumentStore {
    private Integer id;
    private String nameStore;
    private String description;
    private final Set<Document> documents;

    public DocumentStore() {
        this.documents = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameStore() {
        return nameStore;
    }

    public String getDescription() {
        return description;
    }

    public Set<Document> getDocuments() {
        return documents;
    }
}
