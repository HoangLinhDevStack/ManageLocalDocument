package com.manager.doc.model.document;

import java.util.HashSet;
import java.util.Set;

public class DocumentStore {
    private final int idDocumentStore;
    private String nameStore;
    private String description;
    private final Set<Document> documents;

    public DocumentStore(int idDocumentStore) {
        this.idDocumentStore = idDocumentStore;
        this.documents = new HashSet<>();
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdDocumentStore() {
        return idDocumentStore;
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
