package com.manager.doc.model.department;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private Integer id;
    private String name;
    private String manager;
    private int numberOfMember;
    private final Set<DepartmentWork> work;

    public Department() {
        this.work = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
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
