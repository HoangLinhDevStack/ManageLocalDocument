package com.manager.doc.model.office;

import java.util.HashSet;
import java.util.Set;

public class Office {
    private Integer id;
    private String name;
    private String Manager;
    private int numberOfMember;
    private final Set<OfficeWork> work;

    public Office() {
        this.work = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setManager(String manager) {
        Manager = manager;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return Manager;
    }

    public int getNumberOfMember() {
        return numberOfMember;
    }

    public Set<OfficeWork> getWork() {
        return work;
    }
}
