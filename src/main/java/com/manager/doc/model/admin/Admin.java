package com.manager.doc.model.admin;


import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.form.Form;
import com.manager.doc.model.office.OfficeWork;
import com.manager.doc.model.user.UserRequestCapability;

import java.sql.Date;
import java.util.Set;

public class Admin {
    private int idAdmin;
    private String name;
    private Date dateOfBirth;
    private byte[] picture;
    private String description;
    private Set<UserRequestCapability> processAuth;
    private Set<AdminEducation> educations;
    private Set<AdminAddress> addresses;
    private Set<AdminSkill> skills;
    private Set<Document> documents;
    private Form form;
    private OfficeWork officeWork;
    private DepartmentWork departmentWork;

    public Admin(int idAdmin,
                 String name,
                 Date dateOfBirth,
                 byte[] picture,
                 String description,
                 Set<UserRequestCapability> processAuth,
                 Set<AdminEducation> educations,
                 Set<AdminAddress> addresses,
                 Set<AdminSkill> skills,
                 Set<Document> documents,
                 Form form,
                 OfficeWork officeWork,
                 DepartmentWork departmentWork) {
        this.idAdmin = idAdmin;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.description = description;
        this.processAuth = processAuth;
        this.educations = educations;
        this.addresses = addresses;
        this.skills = skills;
        this.documents = documents;
        this.form = form;
        this.officeWork = officeWork;
        this.departmentWork = departmentWork;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserRequestCapability> getProcessAuth() {
        return processAuth;
    }

    public void setProcessAuth(Set<UserRequestCapability> processAuth) {
        this.processAuth = processAuth;
    }

    public Set<AdminEducation> getEducations() {
        return educations;
    }

    public void setEducations(Set<AdminEducation> educations) {
        this.educations = educations;
    }

    public Set<AdminAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<AdminAddress> addresses) {
        this.addresses = addresses;
    }

    public Set<AdminSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<AdminSkill> skills) {
        this.skills = skills;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public OfficeWork getOfficeWork() {
        return officeWork;
    }

    public void setOfficeWork(OfficeWork officeWork) {
        this.officeWork = officeWork;
    }

    public DepartmentWork getDepartmentWork() {
        return departmentWork;
    }

    public void setDepartmentWork(DepartmentWork departmentWork) {
        this.departmentWork = departmentWork;
    }
}
