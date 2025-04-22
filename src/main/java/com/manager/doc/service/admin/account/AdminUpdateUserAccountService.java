package com.manager.doc.service.admin.account;

import com.manager.doc.dao.user.account.update.UpdateUserAccountDao;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.model.user.UserSkill;
import com.manager.doc.service.admin.inf.AdminInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.sql.SQLException;
import java.util.List;

@Service
public class AdminUpdateUserAccountService {

    @Autowired
    @Qualifier("updateUserAccountDaoImpl")
    private UpdateUserAccountDao updateUserAccountDao;

    @Autowired
    @Qualifier("adminInformationService")
    private AdminInformationService adminInformationService;

    @Autowired
    private PasswordEncoder encoder;

    public User findUserAccountFullInformation(Integer id) {

        User userNew = null;
        List<User> users = adminInformationService.getAllUserInformation();
        int idUserFind = updateUserAccountDao.findUserByID(id).getId();

        for (User user : users) {
            int userInListByID = user.getId();

            if (userInListByID == idUserFind) { // * Check id equal and then take this user
                userNew = user;
            }
        }

        return userNew;
    }

    @Transactional
    public boolean updatePassword(Integer id, String rawPassword) throws SQLException { // * update password of current user
        Integer IDUser = updateUserAccountDao.findUserByID(id).getId();
        String hashed = encoder.encode(rawPassword);

        boolean rows = updateUserAccountDao.updatePassword(IDUser, hashed);
        if (!rows) {
            throw new IllegalStateException("Password update failed for user " + IDUser);
        }
        return true;
    }

    @Transactional
    public boolean updateUser(User user) throws SQLException {
        // * update all information of current user
        System.out.println("==== SERVICE LAYER - START ====");
        System.out.println("Updating user with ID: " + user.getId());
        System.out.println("User details in service:");
        System.out.println("  - Name: " + user.getName());
        System.out.println("  - NickName: " + user.getNickName());
        System.out.println("  - Nation: " + user.getNation());
        System.out.println("  - DateOfBirth: " + user.getDateOfBirth());
        System.out.println("  - Description: " + user.getDescription());
        System.out.println("  - Picture: " + (user.getPicture() != null ? "Not null" : "null"));
        System.out.println("  - Sex: " + (user.getSex() != null ? user.getSex().getId() : "null"));
        
        boolean result = updateUserAccountDao.updateUser(user);
        System.out.println("Update result: " + result);
        System.out.println("==== SERVICE LAYER - END ====");
        return result;
    }

    @Transactional
    public boolean updateUserAccountRole(Integer IDUserRole, Integer IDUser) throws SQLException {
        // * update role of current user
        return updateUserAccountDao.updateUserAccountRole(IDUserRole, IDUser);
    }

    @Transactional
    public boolean updateUserEducation(UserEducation userEducation, Integer IDUser) throws SQLException {
        // * update education of current user
        return updateUserAccountDao.updateUserEducation(userEducation, IDUser);
    }

    @Transactional
    public boolean updateUserAddress(UserAddress userAddress, Integer IDUser) throws SQLException {
        // * update address of current user
        return updateUserAccountDao.updateUserAddress(userAddress, IDUser);
    }

    @Transactional
    public boolean updateUserSkill(UserSkill userSkill, Integer IDUser) throws SQLException {
        // * update skill of current user
        return updateUserAccountDao.updateUserSkill(userSkill, IDUser);
    }
}
