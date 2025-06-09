package com.manager.doc.dao.user.account.delete;

import com.manager.doc.model.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeleteUserAccountDaoImpl implements DeleteUserAccountDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void deleteAdmin(Integer idAdmin) {

        // Xóa dữ liệu từ bảng con trước

        String sqlDeleteFromSkills = "DELETE FROM admin_skill WHERE IDAdmin = ?";
        jdbcTemplate.update(sqlDeleteFromSkills, idAdmin);

        String sqlDeleteFromEducation = "DELETE FROM admin_education WHERE IDAdmin = ?";
        jdbcTemplate.update(sqlDeleteFromEducation, idAdmin);

        String sqlDeleteFromAddress = "DELETE FROM admin_address WHERE IDAdmin = ?";
        jdbcTemplate.update(sqlDeleteFromAddress, idAdmin);

        String sqlDeleteFromAccount = "DELETE FROM admin_account WHERE IDAdmin = ?";
        jdbcTemplate.update(sqlDeleteFromAccount, idAdmin);

        // Cuối cùng xóa người dùng từ bảng chính
        String sqlDeleteFromAdmin = "DELETE FROM admin WHERE IDAdmin = ?";
        jdbcTemplate.update(sqlDeleteFromAdmin, idAdmin);


    }

    @Override
    public void deleteUser(Integer idUser) {

        String sqlDeleteFromSkills = "DELETE FROM user_skill WHERE IDUser = ?";
        jdbcTemplate.update(sqlDeleteFromSkills, idUser);

        String sqlDeleteFromEducation = "DELETE FROM user_education WHERE IDUser = ?";
        jdbcTemplate.update(sqlDeleteFromEducation, idUser);

        String sqlDeleteFromAddress = "DELETE FROM user_address WHERE IDUser = ?";
        jdbcTemplate.update(sqlDeleteFromAddress, idUser);

        String sqlDeleteFromAccount = "DELETE FROM user_account WHERE IDUser = ?";
        jdbcTemplate.update(sqlDeleteFromAccount, idUser);

        // Cuối cùng xóa người dùng từ bảng chính
        String sqlDeleteFromUser = "DELETE FROM user WHERE IDUser = ?";
        jdbcTemplate.update(sqlDeleteFromUser, idUser);

    }

    @Override
    public boolean deleteList(List<UserAccount> userAccounts) {
        return false;
    }
}
