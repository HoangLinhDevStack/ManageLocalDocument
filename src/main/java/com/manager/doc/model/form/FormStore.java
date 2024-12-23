package com.manager.doc.model.form;

import java.util.HashSet;
import java.util.Set;

public class FormStore {
    private Integer id;
    private String nameStore;
    private String description;
    private final Set<Form> forms;

    public FormStore() {
        this.forms = new HashSet<>();
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

    public Set<Form> getForms() {
        return forms;
    }
}
