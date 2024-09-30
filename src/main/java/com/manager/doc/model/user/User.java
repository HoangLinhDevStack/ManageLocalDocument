package com.manager.doc.model.user;

import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.document.Document;
import com.manager.doc.model.form.Form;
import com.manager.doc.model.office.OfficeWork;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
    private final int idUser;
    private final String name;
    private String nickName;
    private Date dateOfBirth;
    private byte[] picture;
    private String description;
    private final Set<UserEducation> educations;
    private final Set<UserAddress> addresses;
    private final Set<UserSkill> skills;
    private UserRequestCapability requestRoleUser;
    private OfficeWork officeWork;
    private DepartmentWork departmentWork;
    private Form form;
    private Set<Document> documents;

    public User(int idUser,
                String name) {
        this.idUser = idUser;
        this.name = name;
        this.educations = new HashSet<>();
        this.addresses = new HashSet<>();
        this.skills = new HashSet<>();
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public void setRequestRoleUser(UserRequestCapability requestRoleUser) {
        this.requestRoleUser = requestRoleUser;
    }

    public void setOfficeWork(OfficeWork officeWork) {
        this.officeWork = officeWork;
    }

    public void setDepartmentWork(DepartmentWork departmentWork) {
        this.departmentWork = departmentWork;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
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

    public Set<UserEducation> getEducations() {
        return educations;
    }

    public Set<UserAddress> getAddresses() {
        return addresses;
    }

    public Set<UserSkill> getSkills() {
        return skills;
    }

    public UserRequestCapability getRequestRoleUser() {
        return requestRoleUser;
    }

    public OfficeWork getOfficeWork() {
        return officeWork;
    }

    public DepartmentWork getDepartmentWork() {
        return departmentWork;
    }

    public Form getForm() {
        return form;
    }

    public Set<Document> getDocuments() {
        return documents;
    }
}
