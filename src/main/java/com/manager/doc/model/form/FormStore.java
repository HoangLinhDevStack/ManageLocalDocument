package com.manager.doc.model.form;

import java.util.Set;

public class FormStore {

    private final int idDocumentStore;
    private String nameStore;
    private String description;
    private final Set<Form> forms;

    public FormStore(int idDocumentStore, Set<Form> forms) {
        this.idDocumentStore = idDocumentStore;
        this.forms = forms;
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

    public Set<Form> getForms() {
        return forms;
    }
}
