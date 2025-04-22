package com.manager.doc.dao.user.account.update;

import com.manager.doc.model.user.*;

import java.sql.SQLException;

public interface UpdateUserAccountDao {
    boolean update();
    boolean updateUser(User user) throws SQLException;
    boolean updateUserAccountRole(Integer IDUserRole, Integer IDUser) throws SQLException;
    boolean updateUserEducation(UserEducation education, Integer IDUser) throws SQLException;
    boolean updateUserAddress(UserAddress address, Integer IDUser) throws SQLException;
    boolean updateUserSkill(UserSkill skill, Integer IDUser) throws SQLException;
    boolean updatePassword(Integer IDUser, String newPassword) throws SQLException;
    User findUserByID(Integer id);
}
