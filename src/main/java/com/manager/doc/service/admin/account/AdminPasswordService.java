package com.manager.doc.service.admin.account;

import com.manager.doc.dao.admin.AdminAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class AdminPasswordService {

    @Autowired
    @Qualifier("adminAccountDaoImpl")
    private AdminAccountDao adminAccountDao;

    @Autowired
    @Qualifier("getPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile("[\\-\\_\\*\\/\\\\\\#\\&\\@\\^\\~\\+\\%\\=]");

    public boolean isPasswordValid(String password) {
        if (password == null || password.trim().isEmpty()) return false;
        // Add more rules here (length, uppercase, etc.)
        return true;
    }

    /**
     * Kiểm tra mật khẩu hiện tại có đúng không
     * @param username Tên đăng nhập của admin
     * @param currentPassword Mật khẩu hiện tại (chưa mã hóa)
     * @return true nếu mật khẩu đúng, false nếu sai
     */
    public boolean checkCurrentPassword(String username, String currentPassword) {
        String storedPassword = adminAccountDao.getCurrentPassword(username);
        if (storedPassword == null) {
            return false;
        }
        return passwordEncoder.matches(currentPassword, storedPassword);
    }

    public boolean containsSpecifiedSpecialCharacters(String password) {
        return password != null && SPECIAL_CHAR_PATTERN.matcher(password).find();
    }

    public String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Mật khẩu không được để trống.";
        }
        if (!containsSpecifiedSpecialCharacters(password)) {
            return "Mật khẩu phải chứa ít nhất một ký tự đặc biệt trong danh sách: - _ * / \\ # & @ ^ ~ + % =";
        }
        return null; // hợp lệ
    }

    /**
     * Cập nhật mật khẩu mới cho admin
     * @param username Tên đăng nhập của admin
     * @param newPassword Mật khẩu mới (chưa mã hóa)
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    @Transactional
    public boolean updatePassword(String username, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        return adminAccountDao.updatePassword(username, encodedPassword);
    }

    /**
     * Lấy ID của admin dựa trên username
     * @param username Tên đăng nhập của admin
     * @return ID của admin
     */
    public Integer getAdminIdByUsername(String username) {
        return adminAccountDao.getAdminIdByUsername(username);
    }
}