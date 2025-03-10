package com.manager.doc.dto.user;

import com.manager.doc.model.user.User;

import java.io.Serializable;

public class UserWithInformationDTO implements Serializable {
    //  * user table
    private User user;

    //  * address user table
    private String streetName;
    private String city;
    private String province;

    //  * skill user table
    private String skill;

    //  * school user table
    private String school;

    public UserWithInformationDTO() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}
