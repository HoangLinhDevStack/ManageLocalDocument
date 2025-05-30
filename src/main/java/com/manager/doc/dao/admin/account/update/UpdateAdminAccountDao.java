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
    boolean updatePassword(Integer IDAdmin, String newPassword) throws SQLException;
    AdminAddress findAdminAddressById(Integer adminId, Integer addressId) throws SQLException;
    AdminEducation findAdminEducationById(Integer adminId, Integer educationId) throws SQLException;
    AdminSkill findAdminSkillById(Integer adminId, Integer skillId) throws SQLException;
    Admin findAdminByID(Integer id);
    boolean toggleAdminAccountStatus(int adminId) throws SQLException;

}
