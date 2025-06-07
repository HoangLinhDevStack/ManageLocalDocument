package com.manager.doc.dao.admin.account.update;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.admin.AdminAddress;
import com.manager.doc.model.admin.AdminEducation;
import com.manager.doc.model.admin.AdminSkill;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.model.user.UserSkill;

import java.sql.SQLException;
import java.util.List;

public interface UpdateAdminAccountDao {

    boolean update();
    boolean updateAdmin(Admin admin) throws SQLException;
    boolean updateAdminAccountRole(Integer IDAdminRole, Integer IDAdmin) throws SQLException;
    //    * Update inf admin begin --------------------
    boolean updateAdminEducation(AdminEducation education, Integer IDAdmin) throws SQLException;
    boolean updateAdminAddress(AdminAddress address, Integer IDAdmin) throws SQLException;
    boolean updateAdminSkill(AdminSkill skill, Integer IDAdmin) throws SQLException;

//    * Update inf admin end --------------------


//    * insert inf admin begin --------------------

    boolean insertAdminEducation(AdminEducation education, Integer IDAdmin) throws SQLException;
    boolean insertAdminAddress(AdminAddress address, Integer IDAdmin) throws SQLException;
    boolean insertAdminSkill(AdminSkill skill, Integer IDAdmin) throws SQLException;

    //    * insert ind admin end --------------------


    //    * delete inf admin begin --------------------
    boolean deleteAdminEducation(Integer adminId, Integer educationId) throws SQLException;
    boolean deleteAdminAddress(Integer adminId, Integer addressId) throws SQLException;
    boolean deleteAdminSkill(Integer adminId, Integer skillId) throws SQLException;
    //    * delete inf admin end --------------------


    boolean updatePassword(Integer IDAdmin, String newPassword) throws SQLException;

    // * find inf admin begin --------------------

    AdminAddress findAdminAddressById(Integer adminId, Integer addressId) throws SQLException;
    AdminEducation findAdminEducationById(Integer adminId, Integer educationId) throws SQLException;
    AdminSkill findAdminSkillById(Integer adminId, Integer skillId) throws SQLException;

    // * find inf admin end --------------------

    // * get all inf admin begin --------------------
    List<AdminAddress> getAllAdminAddressById(Integer adminId) throws SQLException;
    List<AdminEducation> getAllAdminEducationById(Integer adminId) throws SQLException;
    List<AdminSkill> getAllAdminSkillById(Integer adminId) throws SQLException;

    // * get all inf admin end --------------------

    Admin findAdminByID(Integer id);
    boolean toggleAdminAccountStatus(int adminId) throws SQLException;

}
