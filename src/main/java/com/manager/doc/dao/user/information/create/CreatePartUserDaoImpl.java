package com.manager.doc.dao.user.information.create;

import com.manager.doc.model.user.User;
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
public class CreatePartUserDaoImpl implements CreateUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer save(final User user) {

        System.out.println("Dao layer: " + user.getName());

        final String sql = "insert into `user` (Name, IDSex, DOB) value(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                        // * Tells the driver you want the auto-generated key after the insert.
                        PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        preparedStatement.setString(1, user.getName());
                        preparedStatement.setInt(2, user.getSex().getId());

                        // Chuyển từ java.util.Date sang java.sql.Date
                        java.sql.Date sqlDob = new java.sql.Date(user.getDateOfBirth().getTime());
                        preparedStatement.setDate(3, sqlDob);
                        return preparedStatement;
                    }
                },
                keyHolder // * captures the generated primary key after the insert.
        );


        return Integer.parseInt(Objects.requireNonNull(keyHolder.getKey()).toString()); // * return id of user
    }
}
