package com.manager.doc.dao.user.account.update;

import com.manager.doc.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;

@Repository("updateUserAccountDaoImpl")
public class UpdateUserAccountDaoImpl implements UpdateUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static void scanUserByID(ResultSet rs, User user) throws SQLException {

        while (rs.next()) {
            user.setId(rs.getInt("IDUser"));
        }
    }

    private static void scanUserAddressByUserID(ResultSet rs, int rowNum, UserAddress userAddress) throws SQLException {
        userAddress.setId(rs.getInt("IDAddress"));
        userAddress.setStreetName(rs.getString("StreetName"));
        userAddress.setCity(rs.getString("City"));
        userAddress.setProvince(rs.getString("Province"));
    }

    private static void scanUserEducationByUserID(ResultSet rs, int rowNum, UserEducation userEducation) throws SQLException {
        userEducation.setId(rs.getInt("IDEducation"));
        userEducation.setSchool(rs.getString("School"));
    }

    private static void scanUserSkillByUserID(ResultSet rs, int rowNum, UserSkill userSkill) throws SQLException {
        userSkill.setId(rs.getInt("IDSkill"));
        userSkill.setDescriptions(rs.getString("Description"));
    }

    private static final ResultSetExtractor<User> findUserByIDRowMapper = new ResultSetExtractor<User>() {
        @Override
        public User extractData(ResultSet rs) throws SQLException, DataAccessException {

            final User user = new User();

            scanUserByID(rs, user);

            return user;
        }
    };

    private static final RowMapper<UserAddress> findUserAddressByIDRowMapper = new RowMapper<UserAddress>() {
        @Override
        public UserAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
            final UserAddress userAddress = new UserAddress();

            scanUserAddressByUserID(rs, rowNum, userAddress);

            return userAddress;
        }
    };

    private static final RowMapper<UserEducation> findUserEducationByIDRowMapper = new RowMapper<UserEducation>() {
        @Override
        public UserEducation mapRow(ResultSet rs, int rowNum) throws SQLException {
            final UserEducation userEducation = new UserEducation();

            scanUserEducationByUserID(rs, rowNum, userEducation);

            return userEducation;
        }
    };

    private static final RowMapper<UserSkill> findUserSkillByIDRowMapper = new RowMapper<UserSkill>() {
        @Override
        public UserSkill mapRow(ResultSet rs, int rowNum) throws SQLException {
            final UserSkill userSkill = new UserSkill();

            scanUserSkillByUserID(rs, rowNum, userSkill);

            return userSkill;
        }
    };

    @Override
    public boolean update() {

        String sql = "";

        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {

        System.out.println("Tầng DAO: " + user.getNation());

        try {
            int rowsAffected;
            // If sex is not null, update including the sex field
            String sql = "UPDATE user SET Name = ?, NickName = ?, Nation = ?, DOB = ?, picture = ?, Description = ?, IDSex = ? WHERE IDUser = ?";

            // Use jdbcTemplate to execute the update query with parameters from the User object
            rowsAffected = jdbcTemplate.update(sql,
                    user.getName(),
                    user.getNickName(),
                    user.getNation(),
                    user.getDateOfBirth(),
                    user.getPicture(),
                    user.getDescription(),
                    user.getSex().getId(),

                    // * Where clause:
                    user.getId()              // Set IDUser (user's ID)
            );

            boolean result = rowsAffected > 0;

            // Return true if rows are affected (i.e., update was successful)
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - ERROR ====");
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - ERROR END ====");
            throw new SQLException("Error updating user: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean updateUserAccountRole(Integer IDUserRole, Integer IDUser) throws SQLException {

        String sql = "UPDATE user_account SET IDUserRole = ? WHERE IDUser = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, IDUserRole, IDUser);
            boolean result = rowsAffected > 0;
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateUserAccountRole - ERROR ====");
            System.err.println("Error updating user role: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateUserAccountRole - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updateUserEducation(UserEducation education, Integer IDUser) throws SQLException {

        String sql = "UPDATE user_education SET School = ? WHERE IDUser = ? AND IDEducation = ?";
        try {
            // Update existing record

            return jdbcTemplate.update(sql, education.getSchool(), IDUser, education.getId()) > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateUserEducation - ERROR ====");
            System.err.println("Error updating user education: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateUserEducation - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updateUserAddress(UserAddress address, Integer IDUser) throws SQLException {

        String sql = "UPDATE user_address SET StreetName = ?, City = ?, Province = ? WHERE IDUser = ? AND IDAddress = ?";
        try {

            return jdbcTemplate.update(sql, address.getStreetName(), address.getCity(), address.getProvince(), IDUser, address.getId()) > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateUserAddress - ERROR ====");
            System.err.println("Error updating user address: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateUserAddress - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean updateUserSkill(UserSkill skill, Integer IDUser) throws SQLException {

        String sql = "UPDATE user_skill SET Description = ? WHERE IDUser = ? AND IDSkill = ?";
        try {
            // Update existing record
            return jdbcTemplate.update(sql, skill.getDescriptions(), IDUser, skill.getId()) > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateUserSkill - ERROR ====");
            System.err.println("Error updating user skill: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateUserSkill - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean insertUserEducation(UserEducation education, Integer IDUser) throws SQLException {
        String sql = "INSERT INTO user_education (IDUser, School) VALUES (?, ?)";

        try {
            // Thực thi câu lệnh INSERT với các tham số
            int rowsInserted = jdbcTemplate.update(sql,
                    IDUser,
                    education.getSchool());

            return rowsInserted > 0; // Trả về true nếu có ít nhất một dòng được chèn
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }


    @Override
    public boolean insertUserAddress(UserAddress address, Integer IDUser) throws SQLException {
        String sql = "INSERT INTO user_address (IDUser, StreetName, City, Province) VALUES (?, ?, ?, ?)";

        try {
            // Thực thi câu lệnh INSERT với các tham số
            int rowsInserted = jdbcTemplate.update(sql,
                    IDUser,
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
    public boolean insertUserSkill(UserSkill skill, Integer IDUser) throws SQLException {
        String sql = "INSERT INTO user_skill (IDUser, Description) VALUES (?, ?)";

        try {
            // Thực thi câu lệnh INSERT với các tham số
            int rowsInserted = jdbcTemplate.update(sql,
                    IDUser,
                    skill.getDescriptions());

            return rowsInserted > 0; // Trả về true nếu có ít nhất một dòng được chèn
        } catch (DataAccessException e) {
            e.printStackTrace();
            return false; // Nếu có lỗi, trả về false
        }
    }


    @Override
    public boolean updatePassword(Integer IDUser, String newPassword) throws SQLException {

        String sql = "UPDATE user_account SET Password = ? WHERE IDUser = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql, newPassword, IDUser);
            boolean result = rowsAffected > 0;
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updatePassword - ERROR ====");
            System.err.println("Error updating user password: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updatePassword - ERROR END ====");
            throw e;
        }
    }

    @Override
    public UserAddress findUserAddressById(Integer userId, Integer addressId) throws SQLException {
        String sql = "SELECT IDAddress, StreetName, City, Province FROM user_address WHERE IDUser = ? AND IDAddress = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId, addressId}, findUserAddressByIDRowMapper);
    }

    @Override
    public UserEducation findUserEducationById(Integer userId, Integer educationId) throws SQLException {
        String sql = "SELECT IDEducation, School FROM user_education WHERE IDUser = ? AND IDEducation = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, educationId}, findUserEducationByIDRowMapper);
    }

    @Override
    public UserSkill findUserSkillById(Integer userId, Integer skillId) throws SQLException {
        String sql = "SELECT IDSkill, Description FROM user_skill WHERE IDUser = ? AND IDSkill = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId, skillId}, findUserSkillByIDRowMapper);
    }

    @Override
    public List<UserAddress> getAllUserAddressById(Integer userId) throws SQLException {
        String sql = "SELECT IDAddress, StreetName, City, Province FROM user_address WHERE IDUser = ?";
        return jdbcTemplate.query(sql, findUserAddressByIDRowMapper, userId);
    }

    @Override
    public List<UserEducation> getAllUserEducationById(Integer userId) throws SQLException {
        String sql = "SELECT IDEducation, School FROM user_education WHERE IDUser = ?";
        return jdbcTemplate.query(sql, findUserEducationByIDRowMapper, userId);
    }

    @Override
    public List<UserSkill> getAllUserSkillById(Integer userId) throws SQLException {
        String sql = "SELECT IDSkill, Description FROM user_skill WHERE IDUser = ?";
        return jdbcTemplate.query(sql, findUserSkillByIDRowMapper, userId);
    }

    @Override
    public User findUserByID(Integer id) {

        String sql = "SELECT IDUser FROM user WHERE IDUser = ?";

        try {
            User user = jdbcTemplate.query(sql, findUserByIDRowMapper, id);
            return user;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - findUserByID - ERROR ====");
            System.err.println("Error finding user by ID: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - findUserByID - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean toggleAccountStatus(int userId) throws SQLException {
        String sql = "UPDATE user_account SET Enabled = NOT Enabled WHERE IDUser = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, userId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - toggleAccountStatus - ERROR ====");
            System.err.println("Error toggling account status: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - toggleAccountStatus - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean deleteUserEducation(Integer userId, Integer educationId) throws SQLException {
        String sql = "DELETE FROM user_education WHERE IDUser = ? AND IDEducation = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, userId, educationId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - deleteUserEducation - ERROR ====");
            System.err.println("Error deleting user education: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - deleteUserEducation - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean deleteUserAddress(Integer userId, Integer addressId) throws SQLException {
        String sql = "DELETE FROM user_address WHERE IDUser = ? AND IDAddress = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, userId, addressId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - deleteUserAddress - ERROR ====");
            System.err.println("Error deleting user address: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - deleteUserAddress - ERROR END ====");
            throw e;
        }
    }

    @Override
    public boolean deleteUserSkill(Integer userId, Integer skillId) throws SQLException {
        String sql = "DELETE FROM user_skill WHERE IDUser = ? AND IDSkill = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, userId, skillId);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - deleteUserSkill - ERROR ====");
            System.err.println("Error deleting user skill: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - deleteUserSkill - ERROR END ====");
            throw e;
        }
    }

}


