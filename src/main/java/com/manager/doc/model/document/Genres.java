package com.manager.doc.model.document;

import java.util.List;

public class Genres {
    private final int idGenres;
    private final String genresName;
    private String description;
    private final List<Document> documents;

    public Genres(int idGenres,
                  String genresName,
                  List<Document> documents) {
        this.idGenres = idGenres;
        this.genresName = genresName;
        this.documents = documents;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdGenres() {
        return idGenres;
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
