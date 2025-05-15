package com.manager.doc.dao.user.account.update;

import com.manager.doc.model.user.*;

import java.sql.SQLException;

public interface UpdateUserAccountDao {
    boolean update();
    boolean updateUser(User user) throws SQLException;
    boolean updateUserAccountRole(Integer IDUserRole, Integer IDUser) throws SQLException;
//    * Update inf user begin --------------------
    boolean updateUserEducation(UserEducation education, Integer IDUser) throws SQLException;
    boolean updateUserAddress(UserAddress address, Integer IDUser) throws SQLException;
    boolean updateUserSkill(UserSkill skill, Integer IDUser) throws SQLException;

//    * Update inf user end --------------------


//    * insert inf user begin --------------------

    boolean insertUserEducation(UserEducation education, Integer IDUser) throws SQLException;
    boolean insertUserAddress(UserAddress address, Integer IDUser) throws SQLException;
    boolean insertUserSkill(UserSkill skill, Integer IDUser) throws SQLException;

//    * insert ind user end --------------------
    boolean updatePassword(Integer IDUser, String newPassword) throws SQLException;
    UserAddress findUserAddressById(Integer userId, Integer addressId) throws SQLException;
    UserEducation findUserEducationById(Integer userId, Integer educationId) throws SQLException;
    UserSkill findUserSkillById(Integer userId, Integer skillId) throws SQLException;
    User findUserByID(Integer id);
}
