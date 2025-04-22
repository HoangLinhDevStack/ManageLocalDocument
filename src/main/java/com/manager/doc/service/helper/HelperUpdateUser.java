package com.manager.doc.service.helper;

import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.model.user.UserSkill;
import com.manager.doc.service.admin.account.AdminUpdateUserAccountService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

public class HelperUpdateUser {
    public static boolean updateRole(Integer roleId, Integer userId, AdminUpdateUserAccountService adminUpdateUserAccountService) throws SQLException {
        return adminUpdateUserAccountService.updateUserAccountRole(roleId, userId);
    }

    public static boolean updateEducation(UserEducation education, Integer userId, AdminUpdateUserAccountService adminUpdateUserAccountService) throws SQLException {
        UserEducation educationNew = new UserEducation();
        educationNew.setSchool(education.getSchool());
        return adminUpdateUserAccountService.updateUserEducation(educationNew, userId);
    }

    public static boolean updateAddress(UserAddress userAddress, Integer userId, AdminUpdateUserAccountService adminUpdateUserAccountService) throws SQLException {
        UserAddress address = new UserAddress();
        address.setStreetName(userAddress.getStreetName());
        address.setCity(userAddress.getCity());
        address.setProvince(userAddress.getProvince());
        return adminUpdateUserAccountService.updateUserAddress(address, userId);
    }

    public static boolean updateSkill(String skillDescription, Integer userId, AdminUpdateUserAccountService adminUpdateUserAccountService) throws SQLException {
        UserSkill skill = new UserSkill();
        skill.setDescriptions(skillDescription);
        return adminUpdateUserAccountService.updateUserSkill(skill, userId);
    }
}
