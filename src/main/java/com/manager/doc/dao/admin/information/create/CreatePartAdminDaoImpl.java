package com.manager.doc.dao.admin.information.create;

import com.manager.doc.model.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class CreatePartAdminDaoImpl implements CreateAdminDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(Admin admin) {

        System.out.println("Dao layer: " + admin.getName());

        final String sql = "insert into `admin` (Name, IDSex, DOB) value(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        // * Tells the driver you want the auto-generated key after the insert.
                        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, admin.getName());
                        preparedStatement.setInt(2, admin.getSex().getId());

                        // Chuyển từ java.util.Date sang java.sql.Date
                        java.sql.Date sqlDob = new java.sql.Date(admin.getDateOfBirth().getTime());
                        preparedStatement.setDate(3, sqlDob);
                        return preparedStatement;
                    }
                },
                keyHolder // * captures the generated primary key after the insert.
        );


        return Integer.parseInt(Objects.requireNonNull(keyHolder.getKey()).toString()); // * return id of user
    }
}
