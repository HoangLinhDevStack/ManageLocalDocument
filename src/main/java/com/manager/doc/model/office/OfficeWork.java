package com.manager.doc.model.office;

public class OfficeWork {
    private final int idOfficeWork;
    private final String position;

    public OfficeWork(int idOfficeWork, String position) {
        this.idOfficeWork = idOfficeWork;
        this.position = position;
    }

    public int getIdOfficeWork() {
        return idOfficeWork;
    }

    public String getPosition() {
        return position;
    }
}
