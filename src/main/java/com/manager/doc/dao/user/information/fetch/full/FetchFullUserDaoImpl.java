package com.manager.doc.dao.user.information.fetch.full;

import com.manager.doc.dto.user.UserWithInformationDTO;
import com.manager.doc.enumeration.user.RolesUser;
import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.form.Form;
import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class FetchFullUserDaoImpl implements FetchFullUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // * Method scan map row user and inf user
    private static void scanUserInformation(User user, ResultSet rs, int rowNum) throws SQLException {

        // * fetch and get inf for user
        user.setId(rs.getInt("IDUser"));
        user.setName(rs.getString("Name"));
        user.setNickName(rs.getString("NickName"));
        user.setDateOfBirth(rs.getDate("DOB"));
        user.setNation(rs.getString("Nation"));
        user.setPicture(rs.getBytes("Picture"));
        user.setDescription(rs.getString("Description"));

        // * Initialize related objects before setting values
        Sex sex = new Sex();
        sex.setId(rs.getInt("IDSex"));
        sex.setSex(rs.getString("Sex"));
        user.setSex(sex);

        DepartmentWork departmentWork = new DepartmentWork();
        departmentWork.setId(rs.getInt("IDDepartmentWork"));
        departmentWork.setPosition(rs.getString("Position"));
        user.setDepartmentWork(departmentWork);

        Form form = new Form();
        form.setId(rs.getInt("IDForm"));
        form.setName(rs.getString("Name"));
        user.setForm(form);
    }


    // * Method scan map row user and inf private user
    private static void scanPrivateUserInformation(User user, ResultSet rs, int rowNum) throws SQLException {

        // * fetch and get user_account and role of account
        UserRoles userRole = new UserRoles();
        userRole.setId(rs.getInt("IDUserRole"));
        userRole.setKeyRoles(RolesUser.valueOf(rs.getString("KeyRoles")));

        user.getUserAccount().setId(rs.getInt("IDUserAcc"));
        user.getUserAccount().setUsername(rs.getString("Username"));
        user.getUserAccount().setPassword(rs.getString("Password"));
        user.getUserAccount().setEnable(rs.getByte("Enabled"));
        user.getUserAccount().setRole(userRole);

    }

    // * Method scan map row user and multiple inf user
    private static UserWithInformationDTO scanMultipleUserInformation(User user, ResultSet rs, int rowNum) throws SQLException {

        final UserWithInformationDTO userWithInformationDTO = new UserWithInformationDTO();

        userWithInformationDTO.setUser(user); // ** get what user ? // need line code

        // * fetch and get multiple inf this user
        userWithInformationDTO.setStreetName(rs.getString("StreetName"));
        userWithInformationDTO.setCity(rs.getString("City"));
        userWithInformationDTO.setProvince(rs.getString("Province"));
        userWithInformationDTO.setSkill(rs.getString("Skill"));
        userWithInformationDTO.setSchool(rs.getString("School"));

        return userWithInformationDTO;

    }

    // * Method get data user and user_account utilize
    private static final RowMapper<User> userWithAccountRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            final User user = new User(); // * initialize object user

            scanUserInformation(user, rs, rowNum); // * fetch and get inf for user

            scanPrivateUserInformation(user, rs, rowNum); // * fetch and get user_account and role of account


            return user;
        }
    };

    // * Method get data user and information user utilize
    private static final RowMapper<UserWithInformationDTO> userWithInformationRowMapper = new RowMapper<UserWithInformationDTO>() {
        @Override
        public UserWithInformationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            final User user = new User();

            // * fetch and get inf for user
            scanUserInformation(user, rs, rowNum);

            return scanMultipleUserInformation(user, rs, rowNum);
        }
    };

    // * Method get data user and private information user for admin utilize
    private static final RowMapper<UserWithInformationDTO> userWithPrivateInformationRowMapper = new RowMapper<UserWithInformationDTO>() {
        @Override
        public UserWithInformationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            final User user = new User();

            scanUserInformation(user, rs, rowNum); // * fetch and get inf for user

            scanPrivateUserInformation(user, rs, rowNum); // * fetch and get user_account and role of account

            return scanMultipleUserInformation(user, rs, rowNum);
        }
    };

    @Override
    public List<UserWithInformationDTO> getFullUserInformation() { // * not fetch user account
        String sql = "SELECT " +
                "u.IDUser, u.Name, u.NickName, u.DOB, u.Nation, u.Picture, u.Description, " +
                "u.IDSex, s.Sex, " +
                "u.IDDepartmentWork, dw.Position, " +
                "u.IDForm, f.Name AS FormName, " +
                "ua.StreetName, ua.City, ua.Province, " +
                "us.Description AS Skill, " +
                "ue.School " +
                "FROM user u " +
                "LEFT JOIN sex s ON u.IDSex = s.IDSex " +  //  Proper JOIN
                "LEFT JOIN department_work dw ON u.IDDepartmentWork = dw.IDDepartmentWork " +
                "LEFT JOIN form f ON u.IDForm = f.IDForm " +
                "LEFT JOIN user_address ua ON u.IDUser = ua.IDUser " +
                "LEFT JOIN user_skill us ON u.IDUser = us.IDUser " +
                "LEFT JOIN user_education ue ON u.IDUser = ue.IDUser";

        try {
            return jdbcTemplate.query(sql, userWithInformationRowMapper);
        } catch (DataAccessException e) {
            // Handle database exceptions
            throw new RuntimeException("Failed to retrieve user multiple information", e);
        }
    }

    @Override
    public List<UserWithInformationDTO> getFullPrivateUserInformation() {
        String sql = "SELECT " +
                "u.IDUser, u.Name, u.NickName, u.DOB, u.Nation, u.Picture, u.Description, " +
                "u.IDSex, s.Sex, " +
                "u.IDDepartmentWork, dw.Position, " +
                "u.IDForm, f.Name AS FormName, " +
                "uad.StreetName, uad.City, uad.Province, " +
                "us.Description AS Skill, " +
                "ue.School, " +
                "ua.IDUserAcc, ua.Username, ua.Password, ua.Enabled, " +
                "ur.IDUserRole, ur.KeyRoles " +
                "FROM user u " +
                "LEFT JOIN user_account ua ON u.IDUser = ua.IDUser " +
                "LEFT JOIN user_roles ur ON ua.IDUserRole = ur.IDUserRole " +
                "LEFT JOIN sex s ON u.IDSex = s.IDSex " +
                "LEFT JOIN department_work dw ON u.IDDepartmentWork = dw.IDDepartmentWork " +
                "LEFT JOIN form f ON u.IDForm = f.IDForm " +
                "LEFT JOIN user_address uad ON u.IDUser = uad.IDUser " +
                "LEFT JOIN user_skill us ON u.IDUser = us.IDUser " +
                "LEFT JOIN user_education ue ON u.IDUser = ue.IDUser";

        try {
            return jdbcTemplate.query(sql, userWithPrivateInformationRowMapper);
        } catch (DataAccessException e) {
            // Handle database exceptions
            throw new RuntimeException("Failed to retrieve private user multiple information", e);
        }
    }


    @Override
    public List<User> getFullUserAndAccount() {
        String sql = "SELECT " +
                "u.IDUser, u.Name, u.NickName, u.DOB, u.Nation, u.Picture, u.Description, " +
                "u.IDSex, u.IDDepartmentWork, u.IDForm, " +
                "ua.IDUserAcc, ua.Username, ua.Password, ua.Enabled, ua.IDUserRole " +
                "FROM user u " +
                "JOIN user_account ua ON u.IDUser = ua.IDUser";

        try {
            return jdbcTemplate.query(sql, userWithAccountRowMapper);
        } catch (DataAccessException e) {
            // Handle database exceptions
            throw new RuntimeException("Failed to retrieve user and user account information", e);
        }
    }
}
