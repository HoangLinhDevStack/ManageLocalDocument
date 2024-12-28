package com.manager.doc.dao.user.information.create;

import com.manager.doc.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreatePartUserDaoImpl implements CreateUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {

        String sql = "insert into `user` (Name, IDSex) value(?,?)";

        return jdbcTemplate.update(sql, user.getName(), user.get);
    }
}
