package com.manager.doc.dao.user.information.fetch.full;

import com.manager.doc.enumeration.user.RolesUser;
import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.form.Form;
import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            form.setName(rs.getString("FormName"));
            user.setForm(form);

    }

// * Method scan user Account by id
    private static void scanUserAccountByID(UserAccount userAccount, ResultSet rs) throws SQLException {

        while (rs.next()) {

            User user = new User();
            user.setId(rs.getInt("IDUser"));
            user.getUserAccount().setId(rs.getInt("IDUserAcc"));
            user.getUserAccount().setUsername(rs.getString("Username"));
            user.getUserAccount().setPassword(rs.getString("Password"));
            user.getUserAccount().setEnable(rs.getByte("Enabled"));

            // * fetch and get role of account
            UserRoles userRole = new UserRoles();
            userRole.setId(rs.getInt("IDUserRole"));
            userRole.setKeyRoles(RolesUser.valueOf(rs.getString("KeyRoles")));
            user.getUserAccount().setRole(userRole);

            userAccount.setUsername(user.getUserAccount().getUsername()); // * get data account by id
//            userAccount.setPassword(user.getUserAccount().getPassword());
            userAccount.setEnable(user.getUserAccount().getEnable());
            userAccount.setRole(user.getUserAccount().getRole());
        }



    }


    private static List<UserAddress> scanUserAddresses(ResultSet rs) throws SQLException {

        List<UserAddress> addressList = new ArrayList<>();

        while (rs.next()) {
            //            * User_Address
            String city = rs.getString("City");
            String province = rs.getString("Province");
            String streetName = rs.getString("StreetName");

            Object[] addresses = new Object[]{city, province, streetName};

            UserAddress userAddress = new UserAddress(); // * add user address

            for (Object address : addresses) { // * loop to render address

                if (address != null) {

                    userAddress.setCity(city);
                    userAddress.setProvince(province);
                    userAddress.setStreetName(streetName);
                    addressList.add(userAddress);

                }
            }
        }

        return addressList;
    }

    private static List<UserSkill> scanUserSkills(ResultSet rs) throws SQLException {

        List<UserSkill> skillList = new ArrayList<>();

        while (rs.next()) {
            //            * User_Skills
            String skill = rs.getString("Skill");
            Object[] skills = new Object[]{skill};

            UserSkill userSkill = new UserSkill(); // * add user skills

            for (Object skill_loop : skills) { // * loop to render education

                if (skill_loop != null) {
                    userSkill.setDescriptions(skill);
                    skillList.add(userSkill);
                }
            }
        }

        return skillList;
    }

    private static List<UserEducation> scanUserEducation(ResultSet rs) throws SQLException {

        List<UserEducation> educationList = new ArrayList<>();

        while (rs.next()) {
            //            * User_Education
            Integer id = rs.getInt("IDEducation");
            String school = rs.getString("School");
            Object[] educations = new Object[]{school};

            UserEducation userEducation = new UserEducation(); // * add user education

            for (Object education : educations) { // * loop to render education

                if (education != null) {
                    userEducation.setId(id);
                    userEducation.setSchool(school);
                    educationList.add(userEducation);
                }
            }

        }

        return educationList;
    }

    private final ResultSetExtractor<List<UserAddress>> userAddresses = new ResultSetExtractor<List<UserAddress>>() {
        @Override
        public List<UserAddress> extractData(ResultSet rs) throws SQLException {
            return scanUserAddresses(rs); // * iterate address of user by id
        }
    };

    private final ResultSetExtractor<List<UserSkill>> userSkills = new ResultSetExtractor<List<UserSkill>>() {
        @Override
        public List<UserSkill> extractData(ResultSet rs) throws SQLException {
            return scanUserSkills(rs);  // * iterate skill of user by id
        }
    };

    private final ResultSetExtractor<List<UserEducation>> userEducations = new ResultSetExtractor<List<UserEducation>>() {
        @Override
        public List<UserEducation> extractData(ResultSet rs) throws SQLException {
            return scanUserEducation(rs);  // * iterate education of user by id
        }
    };

    private final ResultSetExtractor<UserAccount> userAccountExtractor = new ResultSetExtractor<UserAccount>() {
        @Override
        public UserAccount extractData(ResultSet rs) throws SQLException {
            final UserAccount userAccount = new UserAccount(); // * initialize user account for get data form user

            scanUserAccountByID(userAccount, rs); // * method process retrieve user by id

            return userAccount;
        }
    };

    // * Method get data user and information user utilize
    private final RowMapper<User> userWithInformationRowMapper = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            final User user = new User();
            scanUserInformation(user, rs, rowNum);
            return user;

        }
    };

    @Override
    public List<User> getUserInformation() { // * not fetch user account
        String sql = "SELECT " +
                "u.IDUser, u.Name, u.NickName, u.DOB, u.Nation, u.Picture, u.Description, " +
                "u.IDSex, s.Sex, " +
                "u.IDDepartmentWork, dw.Position, " +
                "u.IDForm, f.Name AS FormName " +
                "FROM user u " +
                "LEFT JOIN sex s ON u.IDSex = s.IDSex " +  //  Proper JOIN
                "LEFT JOIN department_work dw ON u.IDDepartmentWork = dw.IDDepartmentWork " +
                "LEFT JOIN form f ON u.IDForm = f.IDForm ";

            return jdbcTemplate.query(sql, userWithInformationRowMapper);
    }

    @Override
    public List<UserAddress> getUserAddresses(Integer IDUser) { // * fetch addresses of user
        String sql = "SELECT uad.StreetName, uad.City, uad.Province " +
                "FROM user_address uad " +
                "WHERE uad.IDUser = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDUser}, userAddresses);
    }

    @Override
    public List<UserSkill> getUserSkills(Integer IDUser) { // * fetch skills of user
        String sql = "SELECT us.Description AS Skill " +
                "FROM user_skill us " +
                "WHERE us.IDUser = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDUser}, userSkills);
    }

    @Override
    public List<UserEducation> getUserEducations(Integer IDUser) { // * fetch education of user
        String sql = "SELECT ue.IDEducation, ue.School " +
                "FROM user_education ue " +
                "WHERE ue.IDUser = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDUser}, userEducations);
    }

    @Override
    public UserAccount getUserAccountByID(Integer IDUser) {
        String sql = "SELECT IDUserAcc, Username, Password, Enabled, ua.IDUserRole, KeyRoles, IDUser " +
                "FROM user_account ua " +
                "LEFT JOIN user_roles ur ON ua.IDUserRole = ur.IDUserRole " +
                "WHERE IDUser = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDUser}, userAccountExtractor);
    }

}
