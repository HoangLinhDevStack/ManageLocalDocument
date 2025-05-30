package com.manager.doc.dao.admin;

import java.util.List;

public interface AdminAccountDao {
    /**
     * Lấy mật khẩu hiện tại của admin dựa trên username
     * @param username Tên đăng nhập của admin
     * @return Mật khẩu đã mã hóa
     */
    String getCurrentPassword(String username);
    
    /**
     * Cập nhật mật khẩu mới cho admin
     * @param username Tên đăng nhập của admin
     * @param newPassword Mật khẩu mới đã mã hóa
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    boolean updatePassword(String username, String newPassword);
    
    /**
     * Lấy ID của admin dựa trên username
     * @param username Tên đăng nhập của admin
     * @return ID của admin
     */
    Integer getAdminIdByUsername(String username);

    /**
     * Kiểm tra xem username đã tồn tại trong hệ thống chưa
     * @param username Tên đăng nhập cần kiểm tra
     * @return true nếu username đã tồn tại, false nếu chưa
     */
    boolean isUsernameExists(String username);

    /**
     * Lấy danh sách tất cả username của admin
     * @return Danh sách username
     */
    List<String> getAllUsernames();
}