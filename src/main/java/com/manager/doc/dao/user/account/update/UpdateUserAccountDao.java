package com.manager.doc.dao.user.account.update;

import com.manager.doc.model.user.*;

import java.sql.SQLException;
import java.util.List;

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



//    * delete inf user begin --------------------
    boolean deleteUserEducation(Integer userId, Integer educationId) throws SQLException;
    boolean deleteUserAddress(Integer userId, Integer addressId) throws SQLException;
    boolean deleteUserSkill(Integer userId, Integer skillId) throws SQLException;
//    * delete inf user end --------------------


    boolean updatePassword(Integer IDUser, String newPassword) throws SQLException;

    // * find inf user begin --------------------
    UserAddress findUserAddressById(Integer userId, Integer addressId) throws SQLException;
    UserEducation findUserEducationById(Integer userId, Integer educationId) throws SQLException;
    UserSkill findUserSkillById(Integer userId, Integer skillId) throws SQLException;

    // * find inf user end --------------------

    // * get all inf user begin --------------------
    List<UserAddress> getAllUserAddressById(Integer userId) throws SQLException;
    List<UserEducation> getAllUserEducationById(Integer userId) throws SQLException;
    List<UserSkill> getAllUserSkillById(Integer userId) throws SQLException;

    // * get all inf user end --------------------

    User findUserByID(Integer id);
    boolean toggleAccountStatus(int userId) throws SQLException;
}
