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

@Repository
public class UpdateUserAccountDaoImpl implements UpdateUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static void scanUserByID(ResultSet rs, User user) throws SQLException {

        while (rs.next()) {
            user.setId(rs.getInt("IDUser"));
        }
    }

    private final ResultSetExtractor<User> findUserByIDRowMapper = new ResultSetExtractor<User>() {
        @Override
        public User extractData(ResultSet rs) throws SQLException, DataAccessException {

            final User user = new User();

            scanUserByID(rs, user);

            return user;
        }
    };

    @Override
    public boolean update() {

        String sql = "";

        return false;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        System.out.println("==== DAO LAYER - START ====");

        // Validate user object
        if (user == null) {
            System.err.println("User object is null");
            throw new SQLException("User object cannot be null");
        }

        if (user.getId() == null) {
            System.err.println("User ID is null");
            throw new SQLException("User ID cannot be null");
        }

        System.out.println("Updating user in DAO with ID: " + user.getId());

        // Check if required fields are null and provide default values
        String name = (user.getName() != null) ? user.getName() : "";
        String nickName = (user.getNickName() != null) ? user.getNickName() : "";
        String nation = (user.getNation() != null) ? user.getNation() : "";
        String description = (user.getDescription() != null) ? user.getDescription() : "";

        // Check if user.getSex() is null
        Integer sexId = (user.getSex() != null) ? user.getSex().getId() : null;


        try {
            int rowsAffected;

            // Get the date of birth
            Date dob = user.getDateOfBirth();
            System.out.println("  - DOB: " + dob);


            // If sex is not null, update including the sex field
            String sql = "UPDATE user SET Name = ?, NickName = ?, Nation = ?, DOB = ?, nation = ?, picture = ?, Description = ?, IDSex = ? WHERE IDUser = ?";

            System.out.println("Executing SQL (with sex): " + sql);
            System.out.println("Parameters: [" + name + ", " + nickName + ", " + nation + ", " + description + ", " + dob + ", " + sexId + ", " + user.getId() + "]");

            // Use jdbcTemplate to execute the update query with parameters from the User object
            rowsAffected = jdbcTemplate.update(sql,
                    user.getName(),
                    user.getNickName(),
                    user.getNation(),
                    user.getDateOfBirth(),
                    user.getPicture(),
                    user.getDescription(),
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

        try {
            // First check if a record exists for this user
            String checkSql = "SELECT COUNT(*) FROM user_education WHERE IDUser = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, IDUser);
            boolean recordExists = count != null && count > 0;

            int rowsAffected;
            if (recordExists) {
                // Update existing record
                String updateSql = "UPDATE user_education SET School = ? WHERE IDUser = ? AND IDEducation = ?";
                rowsAffected = jdbcTemplate.update(updateSql, education.getSchool(), IDUser, education.getId());
            } else {
                // Insert new record
                String insertSql = "INSERT INTO user_education (School, IDUser) VALUES (?, ?)";
                rowsAffected = jdbcTemplate.update(insertSql, education.getSchool(), IDUser);
            }

            boolean result = rowsAffected > 0;
            return result;
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

        try {
            // First check if a record exists for this user
            String checkSql = "SELECT COUNT(*) FROM user_address WHERE IDUser = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, IDUser);
            boolean recordExists = count != null && count > 0;

            int rowsAffected;
            if (recordExists) {
                // Update existing record
                String updateSql = "UPDATE user_address SET StreetName = ?, City = ?, Province = ? WHERE IDUser = ?";
                rowsAffected = jdbcTemplate.update(updateSql,
                        address.getStreetName(),
                        address.getCity(),
                        address.getProvince(),
                        IDUser);
            } else {
                // Insert new record
                String insertSql = "INSERT INTO user_address (StreetName, City, Province, IDUser) VALUES (?, ?, ?, ?)";

                rowsAffected = jdbcTemplate.update(insertSql,
                        address.getStreetName(),
                        address.getCity(),
                        address.getProvince(),
                        IDUser);
            }

            boolean result = rowsAffected > 0;
            return result;
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

        try {
            // First check if a record exists for this user
            String checkSql = "SELECT COUNT(*) FROM user_skill WHERE IDUser = ?";
            Integer count = jdbcTemplate.queryForObject(checkSql, Integer.class, IDUser);
            boolean recordExists = count != null && count > 0;

            int rowsAffected;
            if (recordExists) {
                // Update existing record
                String updateSql = "UPDATE user_skill SET Description = ? WHERE IDUser = ?";
                rowsAffected = jdbcTemplate.update(updateSql, skill.getDescriptions(), IDUser);
            } else {
                // Insert new record
                String insertSql = "INSERT INTO user_skill (Description, IDUser) VALUES (?, ?)";
                rowsAffected = jdbcTemplate.update(insertSql, skill.getDescriptions(), IDUser);
            }

            boolean result = rowsAffected > 0;
            return result;
        } catch (Exception e) {
            System.err.println("==== DAO LAYER - updateUserSkill - ERROR ====");
            System.err.println("Error updating user skill: " + e.getMessage());
            e.printStackTrace();
            System.err.println("==== DAO LAYER - updateUserSkill - ERROR END ====");
            throw e;
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

}


