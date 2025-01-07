package com.manager.doc.dao.user.account.create;

import com.manager.doc.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CreateUserAccountDaoImpl implements CreateUserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(User user) {

        String sql = "insert into `user_account` (Username, Password, IDUserRole, IDUser) value(?,?,?,?)";

        Object[] objects = {
                user.getUserAccount().getUsername(),
                user.getUserAccount().getPassword(),
                user.getUserAccount().getRole().getId(),
                user.getId()
        };
         return jdbcTemplate.update(sql, objects);
    }
}











