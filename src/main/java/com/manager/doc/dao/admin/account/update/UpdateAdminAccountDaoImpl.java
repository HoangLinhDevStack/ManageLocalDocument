package com.manager.doc.dao.admin.account.update;

import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.admin.AdminAddress;
import com.manager.doc.model.admin.AdminEducation;
import com.manager.doc.model.admin.AdminSkill;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.model.user.UserSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UpdateAdminAccountDaoImpl implements UpdateAdminAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static void scanAdminByID(ResultSet rs, Admin admin) throws SQLException {

        while (rs.next()) {
            admin.setId(rs.getInt("IDAdmin"));
        }
    }

    private static void scanAdminAddressByAdminID(ResultSet rs, int rowNum, AdminAddress adminAddress) throws SQLException {
        adminAddress.setId(rs.getInt("IDAddress"));
        adminAddress.setStreetName(rs.getString("StreetName"));
        adminAddress.setCity(rs.getString("City"));
        adminAddress.setProvince(rs.getString("Province"));
    }

    private static void scanAdminEducationByAdminID(ResultSet rs, int rowNum, AdminEducation adminEducation) throws SQLException {
        adminEducation.setId(rs.getInt("IDEducation"));
        adminEducation.setSchool(rs.getString("School"));
    }

    private static void scanAdminSkillByAdminID(ResultSet rs, int rowNum, AdminSkill adminSkill) throws SQLException {
        adminSkill.setId(rs.getInt("IDSkill"));
        adminSkill.setDescription(rs.getString("Description"));
    }

    private static final ResultSetExtractor<Admin> findAdminByIDRowMapper = new ResultSetExtractor<Admin>() {
        @Override
        public Admin extractData(ResultSet rs) throws SQLException, DataAccessException {

            final Admin admin = new Admin();

            scanAdminByID(rs, admin);

            return admin;
        }
    };

    private static final RowMapper<AdminAddress> findAdminAddressByIDRowMapper = new RowMapper<AdminAddress>() {
        @Override
        public AdminAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AdminAddress adminAddress = new AdminAddress();

            scanAdminAddressByAdminID(rs, rowNum, adminAddress);

            return adminAddress;
        }
    };

    private static final RowMapper<AdminEducation> findAdminEducationByIDRowMapper = new RowMapper<AdminEducation>() {
        @Override
        public AdminEducation mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AdminEducation adminEducation = new AdminEducation();

            scanAdminEducationByAdminID(rs, rowNum, adminEducation);

            return adminEducation;
        }
    };

    private static final RowMapper<AdminSkill> findAdminSkillByIDRowMapper = new RowMapper<AdminSkill>() {
        @Override
        public AdminSkill mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AdminSkill adminSkill = new AdminSkill();

            scanAdminSkillByAdminID(rs, rowNum, adminSkill);

            return adminSkill;
        }
    };

    @Override
    public boolean update() {

        String sql = "";

        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws SQLException {

        System.out.println("Tầng DAO: " + admin.getNation());

        try {
            int rowsAffected;
            // If sex is not null, update including the sex field
            String sql = "UPDATE admin SET Name = ?, NickName = ?, Nation = ?, DOB = ?, Picture = ?, Description = ?, IDSex = ? WHERE IDAdmin = ?";

            // Use jdbcTemplate to execute the update query with parameters from the Admin object
            rowsAffected = jdbcTemplate.update(sql,
                    admin.getName(),
                    admin.getNickname(),
                    admin.getNation(),
                    admin.getDateOfBirth(),
                    admin.getPicture(),
                    admin.getDescription(),
                    admin.getSex().getId(),

                    // * Where clause:
                    admin.getId()              // Set IDAdmin (admin's ID)
            );

            boolean result = rowsAffected > 0;

            // Return true if rows are affected (i.e., update was successful)
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - ERROR ====");
            System.err.println("Error updating admin: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - ERROR END ====");
            throw new SQLException("Error updating admin: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean updateAdminAccountRole(Integer IDAdminRole, Integer IDAdmin) throws SQLException {

        String sql = "UPDATE admin_account SET IDAdminRole = ? WHERE IDAdmin = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, IDAdminRole, IDAdmin);
            boolean result = rowsAffected > 0;
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateAdminAccountRole - ERROR ====");
            System.err.println("Error updating admin role: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateAdminAccountRole - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updateAdminEducation(AdminEducation education, Integer IDAdmin) throws SQLException {

        String sql = "UPDATE admin_education SET School = ? WHERE IDAdmin = ? AND IDEducation = ?";
        try {
            // Update existing record

            return jdbcTemplate.update(sql, education.getSchool(), IDAdmin, education.getId()) > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateAdminEducation - ERROR ====");
            System.err.println("Error updating admin education: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateAdminEducation - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updateAdminAddress(AdminAddress address, Integer IDAdmin) throws SQLException {

        String sql = "UPDATE admin_address SET StreetName = ?, City = ?, Province = ? WHERE IDAdmin = ? AND IDAddress = ?";
        try {

            return jdbcTemplate.update(sql, address.getStreetName(), address.getCity(), address.getProvince(), IDAdmin, address.getId()) > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateAdminAddress - ERROR ====");
            System.err.println("Error updating admin address: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateAdminAddress - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updateAdminSkill(AdminSkill skill, Integer IDAdmin) throws SQLException {

        String sql = "UPDATE admin_skill SET Description = ? WHERE IDAdmin = ? AND IDSkill = ?";
        try {
            // Update existing record
            return jdbcTemplate.update(sql, skill.getDescription(), IDAdmin, skill.getId()) > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateAdminSkill - ERROR ====");
            System.err.println("Error updating admin skill: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateAdminSkill - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean insertAdminEducation(AdminEducation education, Integer IDAdmin) throws SQLException {
        System.out.println(education.getSchool() + " đây là tầng dao ADMIN school");
        String sql = "INSERT INTO admin_education (IDAdmin, School) VALUES (?, ?)";

        try {
            // Thực thi câu lệnh INSERT với các tham số
            int rowsInserted = jdbcTemplate.update(sql,
                    IDAdmin,
                    education.getSchool());
            return rowsInserted > 0; // Trả về true nếu có ít nhất một dòng được chèn
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }

    @Override
    public boolean insertAdminAddress(AdminAddress address, Integer IDAdmin) throws SQLException {
        String sql = "INSERT INTO admin_address (IDAdmin, StreetName, City, Province) VALUES (?, ?, ?, ?)";

        try {
            // Thực thi câu lệnh INSERT với các tham số
            int rowsInserted = jdbcTemplate.update(sql,
                    IDAdmin,
                    address.getStreetName(),
                    address.getCity(),
                    address.getProvince());

            return rowsInserted > 0; // Trả về true nếu có ít nhất một dòng được chèn
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }


    @Override
    public boolean insertAdminSkill(AdminSkill skill, Integer IDAdmin) throws SQLException {
        String sql = "INSERT INTO admin_skill (IDAdmin, Description) VALUES (?, ?)";

        try {
            // Thực thi câu lệnh INSERT với các tham số
            int rowsInserted = jdbcTemplate.update(sql,
                    IDAdmin,
                    skill.getDescription());

            return rowsInserted > 0; // Trả về true nếu có ít nhất một dòng được chèn
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }

    @Override
    public boolean deleteAdminEducation(Integer adminId, Integer educationId) throws SQLException {
        String sql = "DELETE FROM admin_education WHERE IDAdmin = ? AND IDEducation = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, adminId, educationId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - deleteAdminEducation - ERROR ====");
            System.err.println("Error deleting admin education: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - deleteAdminEducation - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean deleteAdminAddress(Integer adminId, Integer addressId) throws SQLException {
        String sql = "DELETE FROM admin_address WHERE IDAdmin = ? AND IDAddress = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, adminId, addressId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - deleteAdminAddress - ERROR ====");
            System.err.println("Error deleting admin address: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - deleteAdminAddress - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean deleteAdminSkill(Integer adminId, Integer skillId) throws SQLException {
        String sql = "DELETE FROM admin_skill WHERE IDAdmin = ? AND IDSkill = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, adminId, skillId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - deleteAdminSkill - ERROR ====");
            System.err.println("Error deleting admin skill: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - deleteAdminSkill - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updatePassword(Integer IDAdmin, String newPassword) throws SQLException {

        String sql = "UPDATE admin_account SET Password = ? WHERE IDAdmin = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, newPassword, IDAdmin);
            boolean result = rowsAffected > 0;
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updatePassword - ERROR ====");
            System.err.println("Error updating admin password: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updatePassword - ERROR END ====");
            throw e;
        }
    }

    @Override
    public AdminAddress findAdminAddressById(Integer adminId, Integer addressId) throws SQLException {
        String sql = "SELECT IDAddress, StreetName, City, Province FROM admin_address WHERE IDAdmin = ? AND IDAddress = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{adminId, addressId}, findAdminAddressByIDRowMapper);
    }

    @Override
    public AdminEducation findAdminEducationById(Integer adminId, Integer educationId) throws SQLException {
        String sql = "SELECT IDEducation, School FROM admin_education WHERE IDAdmin = ? AND IDEducation = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{adminId, educationId}, findAdminEducationByIDRowMapper);
    }

    @Override
    public AdminSkill findAdminSkillById(Integer adminId, Integer skillId) throws SQLException {
        String sql = "SELECT IDSkill, Description FROM admin_skill WHERE IDAdmin = ? AND IDSkill = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{adminId, skillId}, findAdminSkillByIDRowMapper);
    }

    @Override
    public List<AdminAddress> getAllAdminAddressById(Integer adminId) throws SQLException {
        String sql = "SELECT IDAddress, StreetName, City, Province FROM admin_address WHERE IDAdmin = ?";
        return jdbcTemplate.query(sql, findAdminAddressByIDRowMapper, adminId);
    }

    @Override
    public List<AdminEducation> getAllAdminEducationById(Integer adminId) throws SQLException {
        String sql = "SELECT IDEducation, School FROM admin_education WHERE IDAdmin = ?";
        return jdbcTemplate.query(sql, findAdminEducationByIDRowMapper, adminId);
    }

    @Override
    public List<AdminSkill> getAllAdminSkillById(Integer adminId) throws SQLException {
        String sql = "SELECT IDSkill, Description FROM admin_skill WHERE IDAdmin = ?";
        return jdbcTemplate.query(sql, findAdminSkillByIDRowMapper, adminId);
    }

    @Override
    public Admin findAdminByID(Integer id) {

        String sql = "SELECT IDAdmin FROM admin WHERE IDAdmin = ?";

        try {
            Admin admin = jdbcTemplate.query(sql, findAdminByIDRowMapper, id);
            return admin;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - findAdminByID - ERROR ====");
            System.err.println("Error finding admin by ID: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - findAdminByID - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean toggleAdminAccountStatus(int adminId) throws SQLException {
        String sql = "UPDATE admin_account SET Enabled = NOT Enabled WHERE IDAdmin = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, adminId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - toggleAccountStatus - ERROR ====");
            System.err.println("Error toggling account status: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - toggleAccountStatus - ERROR END ====");
            throw e;
        }
    }


}
