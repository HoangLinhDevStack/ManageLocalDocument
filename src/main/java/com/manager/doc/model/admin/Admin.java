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
    private Integer id;
    private String name;
    private String nickname;
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


    public Admin() {
        this.processAuth = new HashSet<>();
        this.educations = new HashSet<>();
        this.addresses = new HashSet<>();
        this.skills = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
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
