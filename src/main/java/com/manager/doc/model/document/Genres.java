package com.manager.doc.model.document;

import java.util.ArrayList;
import java.util.List;

public class Genres {
    private int id;
    private String genresName;
    private String description;
    private final List<Document> documents;

    public Genres() {
        this.documents = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenresName(String genresName) {
        this.genresName = genresName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenresName() {
        return genresName;
    }

    public String getDescription() {
        return description;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}
