package com.manager.doc.model.admin;


import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.form.Form;
import com.manager.doc.model.office.OfficeWork;
import com.manager.doc.model.user.UserRequestCapability;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Admin {
    private final int idAdmin;
    private final String name;
    private Date dateOfBirth;
    private byte[] picture;
    private String description;
    private final Set<UserRequestCapability> processAuth;
    private final Set<AdminEducation> educations;
    private final Set<AdminAddress> addresses;
    private final Set<AdminSkill> skills;
    private Set<Document> documents;
    private Form form;
    private OfficeWork officeWork;
    private DepartmentWork departmentWork;

    public Admin(int idAdmin,
                 String name,
                 Date dateOfBirth,
                 byte[] picture,
                 String description,
                 Set<Document> documents,
                 Form form,
                 OfficeWork officeWork,
                 DepartmentWork departmentWork) {
        this.idAdmin = idAdmin;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.description = description;
        this.documents = documents;
        this.form = form;
        this.officeWork = officeWork;
        this.departmentWork = departmentWork;
        this.processAuth = new HashSet<>();
        this.educations = new HashSet<>();
        this.addresses = new HashSet<>();
        this.skills = new HashSet<>();
    }

    public Admin(int idAdmin, String name) {
        this.idAdmin = idAdmin;
        this.name = name;
        this.processAuth = new HashSet<>();
        this.educations = new HashSet<>();
        this.addresses = new HashSet<>();
        this.skills = new HashSet<>();
    }


    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setOfficeWork(OfficeWork officeWork) {
        this.officeWork = officeWork;
    }

    public void setDepartmentWork(DepartmentWork departmentWork) {
        this.departmentWork = departmentWork;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public Set<UserRequestCapability> getProcessAuth() {
        return processAuth;
    }

    public Set<AdminEducation> getEducations() {
        return educations;
    }

    public Set<AdminAddress> getAddresses() {
        return addresses;
    }

    public Set<AdminSkill> getSkills() {
        return skills;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Form getForm() {
        return form;
    }

    public OfficeWork getOfficeWork() {
        return officeWork;
    }

    public DepartmentWork getDepartmentWork() {
        return departmentWork;
    }
}
