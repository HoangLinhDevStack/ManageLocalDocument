package com.manager.doc.model.office;

import java.util.HashSet;
import java.util.Set;

public class Office {
    private final int idOffice;
    private final String name;
    private String Manager;
    private int numberOfMember;
    private final Set<OfficeWork> work;

    public Office(int idOffice,
                  String name) {
        this.idOffice = idOffice;
        this.name = name;
        this.work = new HashSet<>();
    }

    public void setManager(String manager) {
        Manager = manager;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public int getIdOffice() {
        return idOffice;
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
