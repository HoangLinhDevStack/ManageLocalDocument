package com.manager.doc.model.department;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private final int idDepartment;
    private final String name;
    private String manager;
    private int numberOfMember;
    private final Set<DepartmentWork> work;

    public Department(int idDepartment,
                      String name) {
        this.idDepartment = idDepartment;
        this.name = name;
        this.work = new HashSet<>();
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public int getNumberOfMember() {
        return numberOfMember;
    }

    public Set<DepartmentWork> getWork() {
        return work;
    }
}
