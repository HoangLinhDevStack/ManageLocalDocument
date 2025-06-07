package com.manager.doc.dao.admin.information.fetch.full;

import com.manager.doc.enumeration.admin.RolesAdmin;
import com.manager.doc.enumeration.user.RolesUser;
import com.manager.doc.model.admin.*;
import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.form.Form;
import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FetchFullAdminDaoImpl implements FetchFullAdminDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    // * Method scan map row user and inf user
    private static void scanAdminInformation(Admin admin, ResultSet rs, int rowNum) throws SQLException {

        // * fetch and get inf for user
        admin.setId(rs.getInt("IDAdmin"));
        admin.setName(rs.getString("Name"));
        admin.setNickname(rs.getString("NickName"));
        admin.setDateOfBirth(rs.getDate("DOB"));
        admin.setNation(rs.getString("Nation"));
        admin.setPicture(rs.getBytes("Picture"));
        admin.setDescription(rs.getString("Description"));

        // * Initialize related objects before setting values
        Sex sex = new Sex();
        sex.setId(rs.getInt("IDSex"));
        sex.setSex(rs.getString("Sex"));
        admin.setSex(sex);

        DepartmentWork departmentWork = new DepartmentWork();
        departmentWork.setId(rs.getInt("IDDepartmentWork"));
        departmentWork.setPosition(rs.getString("Position"));
        admin.setDepartmentWork(departmentWork);

        Form form = new Form();
        form.setId(rs.getInt("IDForm"));
        form.setName(rs.getString("FormName"));
        admin.setForm(form);

    }

    // * Method scan user Account by id
    private static void scanAdminAccountByID(AdminAccount adminAccount, ResultSet rs) throws SQLException {

        while (rs.next()) {

            Admin admin = new Admin();
            admin.setId(rs.getInt("IDAdmin"));
            admin.getAdminAccount().setId(rs.getInt("IDAdminAcc"));
            admin.getAdminAccount().setUsername(rs.getString("Username"));
            admin.getAdminAccount().setPassword(rs.getString("Password"));
            admin.getAdminAccount().setEnable(rs.getByte("Enabled"));

            // * fetch and get role of account
            AdminRoles adminRole = new AdminRoles();
            adminRole.setId(rs.getInt("IDAdminRole"));
            adminRole.setKeyRoles(RolesAdmin.valueOf(rs.getString("KeyRoles")));
            admin.getAdminAccount().setRole(adminRole);

            adminAccount.setUsername(admin.getAdminAccount().getUsername()); // * get data account by id
//            userAccount.setPassword(user.getUserAccount().getPassword());
            adminAccount.setEnable(admin.getAdminAccount().getEnable());
            adminAccount.setRole(admin.getAdminAccount().getRole());
        }



    }


    private static List<AdminAddress> scanAdminAddresses(ResultSet rs) throws SQLException {

        List<AdminAddress> addressList = new ArrayList<>();

        while (rs.next()) {
            //            * User_Address
            Integer id = rs.getInt("IDAddress");
            String city = rs.getString("City");
            String province = rs.getString("Province");
            String streetName = rs.getString("StreetName");

            Object[] addresses = new Object[]{city, province, streetName};

            AdminAddress adminAddress = new AdminAddress(); // * add user address

            for (Object address : addresses) { // * loop to render address

                if (address != null) {
                    adminAddress.setId(id);
                    adminAddress.setCity(city);
                    adminAddress.setProvince(province);
                    adminAddress.setStreetName(streetName);
                    addressList.add(adminAddress);

                }
            }
        }

        return addressList;
    }

    private static List<AdminSkill> scanAdminSkills(ResultSet rs) throws SQLException {

        List<AdminSkill> skillList = new ArrayList<>();

        while (rs.next()) {
            //            * User_Skills

            Integer id = rs.getInt("IDSkill");
            String skill = rs.getString("Skill");
            Object[] skills = new Object[]{skill};

            AdminSkill adminSkill = new AdminSkill(); // * add user skills

            for (Object skill_loop : skills) { // * loop to render education

                if (skill_loop != null) {
                    adminSkill.setId(id);
                    adminSkill.setDescription(skill);
                    skillList.add(adminSkill);
                }
            }
        }

        return skillList;
    }

    private static List<AdminEducation> scanAdminEducation(ResultSet rs) throws SQLException {

        List<AdminEducation> educationList = new ArrayList<>();

        while (rs.next()) {
            //            * User_Education
            Integer id = rs.getInt("IDEducation");
            String school = rs.getString("School");
            Object[] educations = new Object[]{school};

            AdminEducation adminEducation = new AdminEducation(); // * add user education

            for (Object education : educations) { // * loop to render education

                if (education != null) {
                    adminEducation.setId(id);
                    adminEducation.setSchool(school);
                    educationList.add(adminEducation);
                }
            }

        }

        return educationList;
    }

    private final ResultSetExtractor<List<AdminAddress>> adminAddresses = new ResultSetExtractor<List<AdminAddress>>() {
        @Override
        public List<AdminAddress> extractData(ResultSet rs) throws SQLException {
            return scanAdminAddresses(rs); // * iterate address of user by id
        }
    };

    private final ResultSetExtractor<List<AdminSkill>> adminSkills = new ResultSetExtractor<List<AdminSkill>>() {
        @Override
        public List<AdminSkill> extractData(ResultSet rs) throws SQLException {
            return scanAdminSkills(rs);  // * iterate skill of user by id
        }
    };

    private final ResultSetExtractor<List<AdminEducation>> adminEducations = new ResultSetExtractor<List<AdminEducation>>() {
        @Override
        public List<AdminEducation> extractData(ResultSet rs) throws SQLException {
            return scanAdminEducation(rs);  // * iterate education of user by id
        }
    };

    private final ResultSetExtractor<AdminAccount> adminAccountExtractor = new ResultSetExtractor<AdminAccount>() {
        @Override
        public AdminAccount extractData(ResultSet rs) throws SQLException {
            final AdminAccount adminAccount = new AdminAccount(); // * initialize user account for get data form user

            scanAdminAccountByID(adminAccount, rs); // * method process retrieve user by id

            return adminAccount;
        }
    };

    // * Method get data user and information user utilize
    private final RowMapper<Admin> adminWithInformationRowMapper = new RowMapper<Admin>() {
        @Override
        public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {

            final Admin admin = new Admin();
            scanAdminInformation(admin, rs, rowNum);
            return admin;

        }
    };

    @Override
    public List<Admin> getAdminInformation() { // * not fetch user account
        String sql = "SELECT " +
                "u.IDAdmin, u.Name, u.NickName, u.DOB, u.Nation, u.Picture, u.Description, " +
                "u.IDSex, s.Sex, " +
                "u.IDDepartmentWork, dw.Position, " +
                "u.IDForm, f.Name AS FormName " +
                "FROM admin u " +
                "LEFT JOIN sex s ON u.IDSex = s.IDSex " +  //  Proper JOIN
                "LEFT JOIN department_work dw ON u.IDDepartmentWork = dw.IDDepartmentWork " +
                "LEFT JOIN form f ON u.IDForm = f.IDForm ";

        return jdbcTemplate.query(sql, adminWithInformationRowMapper);
    }

    @Override
    public List<AdminAddress> getAdminAddresses(Integer IDAdmin) { // * fetch addresses of user
        String sql = "SELECT uad.IDAddress, uad.StreetName, uad.City, uad.Province " +
                "FROM admin_address uad " +
                "WHERE uad.IDAdmin = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDAdmin}, adminAddresses);
    }

    @Override
    public List<AdminSkill> getAdminSkills(Integer IDAdmin) { // * fetch skills of user
        String sql = "SELECT us.IDSkill, us.Description AS Skill " +
                "FROM admin_skill us " +
                "WHERE us.IDAdmin = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDAdmin}, adminSkills);
    }

    @Override
    public List<AdminEducation> getAdminEducations(Integer IDAdmin) { // * fetch education of user
        String sql = "SELECT ue.IDEducation, ue.School " +
                "FROM admin_education ue " +
                "WHERE ue.IDAdmin = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDAdmin}, adminEducations);
    }

    @Override
    public AdminAccount getAdminAccountByID(Integer IDAdmin) {
        String sql = "SELECT IDAdminAcc, Username, Password, Enabled, ua.IDAdminRole, KeyRoles, IDAdmin " +
                "FROM admin_account ua " +
                "LEFT JOIN admin_roles ur ON ua.IDAdminRole = ur.IDAdminRole " +
                "WHERE IDAdmin = ?; ";

        return jdbcTemplate.query(sql, new Object[]{IDAdmin}, adminAccountExtractor);
    }

    @Override
    public AdminAccount getAdminAccountByUsername(String username) {
        String sql = "SELECT IDAdminAcc, Username, Password, Enabled, IDAdminRole, IDAdmin FROM admin_account WHERE Username = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                AdminAccount adminAccount = new AdminAccount();
                adminAccount.setId(rs.getInt("IDAdminAcc"));
                adminAccount.setUsername(rs.getString("Username"));
                adminAccount.setEnable((byte) (rs.getBoolean("Enabled") ? 1 : 0));
                
                AdminRoles role = new AdminRoles();
                int roleId = rs.getInt("IDAdminRole");
                role.setId(roleId);
                
                // Map role ID to enum value
                switch (roleId) {
                    case 1:
                        role.setKeyRoles(RolesAdmin.Super);
                        break;
                    case 2:
                        role.setKeyRoles(RolesAdmin.Manager);
                        break;
                    case 3:
                        role.setKeyRoles(RolesAdmin.Dev);
                        break;
                }
                
                adminAccount.setRole(role);
                
                return adminAccount;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Admin getAdminInformationById(Integer id) {
        String sql = "SELECT * FROM admin WHERE IDAdmin = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
                Admin admin = new Admin();
                admin.setId(rs.getInt("IDAdmin"));
                admin.setName(rs.getString("Name"));
                admin.setNickname(rs.getString("NickName"));
                admin.setDateOfBirth(rs.getDate("DOB"));
                admin.setNation(rs.getString("Nation"));
                admin.setPicture(rs.getBytes("Picture"));
                admin.setDescription(rs.getString("Description"));
                
                Sex sex = new Sex();
                sex.setId(rs.getInt("IDSex"));
                admin.setSex(sex);
                
                return admin;
            });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
