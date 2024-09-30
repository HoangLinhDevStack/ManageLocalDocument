package com.manager.doc.model.department;

public class DepartmentWork {
    private final int idDepartmentWork;
    private final String position;

    public DepartmentWork(int idDepartmentWork, String position) {
        this.idDepartmentWork = idDepartmentWork;
        this.position = position;
    }

    public int getIdDepartmentWork() {
        return idDepartmentWork;
    }

    public String getPosition() {
        return position;
    }
}
