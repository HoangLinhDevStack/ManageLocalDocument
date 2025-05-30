package com.manager.doc.dao.user.account.create;

import com.manager.doc.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

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

    @Override
    public boolean isUsernameExists(String username) {
        String sql = "SELECT COUNT(*) FROM user_account WHERE Username = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count > 0;
    }

    @Override
    public List<String> getAllUsernames() {
        String sql = "SELECT Username FROM user_account";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}











