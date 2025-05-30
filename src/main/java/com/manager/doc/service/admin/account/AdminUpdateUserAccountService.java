package com.manager.doc.service.admin.account;

import com.fasterxml.jackson.databind.JsonNode;
import com.manager.doc.dao.admin.account.update.UpdateAdminAccountDao;
import com.manager.doc.dao.user.account.update.UpdateUserAccountDao;
import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.admin.AdminAddress;
import com.manager.doc.model.admin.AdminEducation;
import com.manager.doc.model.admin.AdminSkill;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.model.user.UserSkill;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.format.FormatTextUTF_8;
import com.manager.doc.service.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

@Service
public class AdminUpdateUserAccountService {

    @Autowired
    @Qualifier("updateUserAccountDaoImpl")
    private UpdateUserAccountDao updateUserAccountDao;

    @Autowired
    @Qualifier("updateAdminAccountDaoImpl")
    private UpdateAdminAccountDao updateAdminAccountDao;

    @Autowired
    @Qualifier("adminInformationService")
    private AdminInformationService adminInformationService;

    @Autowired
    @Qualifier("format_to_UTF_8")
    private FormatTextUTF_8 formatTextUTF_8;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private DataSource dataSource;

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
    public boolean updateUserPassword(Integer id, String rawPassword) throws SQLException { // * update password of current user
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
        // *
        System.out.println("Tầng service: " + user.getNation());
        boolean result = updateUserAccountDao.updateUser(user);
        System.out.println("Update result: " + result);
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

    @Transactional
    public void processUserChangeSet(JsonNode changes, User user) throws SQLException {
        // Extract data from changes
        Integer userId = user.getId();

        // Update user in the database
        updateUser(user);

        // Handle related data like education, address, and skills
        JsonNode educations = changes.get("educations");
        JsonNode addresses = changes.get("addresses");
        JsonNode skills = changes.get("skills");

        for (JsonNode edu : educations) {
            JsonNode idNode = edu.get("id");
            JsonNode valueNode = edu.get("value");

            // Kiểm tra nếu valueNode hợp lệ (ít nhất có 1 giá trị)
            if (valueNode == null || valueNode.size() < 1) continue;

            String school = formatTextUTF_8.decodeValue(valueNode.get(0).asText());

            // Kiểm tra nếu idNode là null hoặc missing node (thực hiện INSERT)
            if (idNode == null || idNode.isNull()) {

                System.out.println("Đã vào null");

                // * CASE: INSERT NEW EDUCATION
                UserEducation newEducation = new UserEducation();
                newEducation.setSchool(school);
                updateUserAccountDao.insertUserEducation(newEducation, userId);
            } else {
                // * CASE: POSSIBLE UPDATE
                int educationId = idNode.asInt();
                UserEducation existingEducation = updateUserAccountDao.findUserEducationById(userId, educationId);

                boolean changed = !Helper.safeEquals(existingEducation.getSchool(), school);

                if (changed) {
                    existingEducation.setSchool(school);
                    updateUserAccountDao.updateUserEducation(existingEducation, userId); // UPDATE EXISTING EDUCATION
                } else {
                    continue;
                }
            }
        }




        for (JsonNode addrNode : addresses) {
            // Read id and value
            JsonNode idNode = addrNode.get("id");
            JsonNode valueNode = addrNode.get("value");

            System.out.println("Dcm đây là Size của thằng cha address: " + valueNode.size());

            if (valueNode == null || valueNode.size() < 3) continue; // Ensure structure

            String street = formatTextUTF_8.decodeValue(valueNode.get(0).asText());
            String city = formatTextUTF_8.decodeValue(valueNode.get(1).asText());
            String province = formatTextUTF_8.decodeValue(valueNode.get(2).asText());

            if (idNode == null || idNode.isNull()) {

                System.out.println(idNode + "Neu id null");

                // * CASE: INSERT NEW ADDRESS
                UserAddress newAddress = new UserAddress();
                newAddress.setStreetName(street);
                newAddress.setCity(city);
                newAddress.setProvince(province);
                updateUserAccountDao.insertUserAddress(newAddress, userId); // Insert path

                System.out.println("Chèn dữ liệu oke rồi đấy");
            } else {
                // * CASE: POSSIBLE UPDATE
                int addressId = idNode.asInt();
                UserAddress existing = updateUserAccountDao.findUserAddressById(userId, addressId);

                boolean changed = !Helper.safeEquals(existing.getStreetName(), street) ||
                                  !Helper.safeEquals(existing.getCity(), city) ||
                                  !Helper.safeEquals(existing.getProvince(), province);

                if (changed) {
                    existing.setStreetName(street);
                    existing.setCity(city);
                    existing.setProvince(province);
                    updateUserAccountDao.updateUserAddress(existing, userId); // Update path
                } else {
                    // Skip update
                    continue;
                }
            }
        }

        for (JsonNode skill : skills) {
            JsonNode idNode = skill.get("id");
            JsonNode valueNode = skill.get("value");

            System.out.println("id của skills: " + idNode);
            System.out.println("Giá trị: " + valueNode);
            System.out.println("Kích thước trong mảng Skill: " + valueNode.size());

            // Kiểm tra valueNode có null hoặc không có ít nhất 1 giá trị hợp lệ
            if (valueNode == null || valueNode.size() < 1) continue;

            String skillName = formatTextUTF_8.decodeValue(valueNode.get(0).asText());

            // Kiểm tra idNode có null hoặc missing (insert case)
            if (idNode == null || idNode.isNull()) {
                // CASE: INSERT NEW SKILL
                UserSkill newSkill = new UserSkill();
                newSkill.setDescriptions(skillName);
                updateUserAccountDao.insertUserSkill(newSkill, userId);
            } else {
                // CASE: POSSIBLE UPDATE
                int skillId = idNode.asInt();
                UserSkill existingSkill = updateUserAccountDao.findUserSkillById(userId, skillId);

                boolean changed = !Helper.safeEquals(existingSkill.getDescriptions(), skillName);

                if (changed) {
                    existingSkill.setDescriptions(skillName);
                    updateUserAccountDao.updateUserSkill(existingSkill, userId); // Update existing skill
                } else {
                    continue;
                }
            }
        }


        // Optionally, handle the user role update as well
        updateUserAccountRole(user.getUserAccount().getRole().getId(), userId);
    }

    @Transactional
    public boolean toggleAccountStatus(int userId) throws SQLException {
        return updateUserAccountDao.toggleAccountStatus(userId);
    }


    // *  ------------------update admin account---------------------------------


    public Admin findAdminAccountFullInformation(Integer id) {

        Admin adminNew = null;
        List<Admin> admins = adminInformationService.getAllAdminInformation();
        int idAdminFind = updateAdminAccountDao.findAdminByID(id).getId();

        for (Admin admin : admins) {
            int adminInListByID = admin.getId();

            if (adminInListByID == idAdminFind) { // * Check id equal and then take this user
                adminNew = admin;
            }
        }

        return adminNew;
    }


    @Transactional
    public boolean updateAdminPassword(Integer id, String rawPassword) throws SQLException { // * update password of current user
        Integer IDAdmin = updateAdminAccountDao.findAdminByID(id).getId();
        String hashed = encoder.encode(rawPassword);

        boolean rows = updateAdminAccountDao.updatePassword(IDAdmin, hashed);
        if (!rows) {
            throw new IllegalStateException("Password update failed for user " + IDAdmin);
        }
        return true;
    }

    @Transactional
    public boolean updateAdmin(Admin admin) throws SQLException {
        // * update all information of current user
        // *
        System.out.println("Tầng service: " + admin.getNation());
        boolean result = updateAdminAccountDao.updateAdmin(admin);
        System.out.println("Update result: " + result);
        return result;
    }

    @Transactional
    public boolean updateAdminAccountRole(Integer IDAdminRole, Integer IDAdmin) throws SQLException {
        // * update role of current user
        return updateAdminAccountDao.updateAdminAccountRole(IDAdminRole, IDAdmin);
    }

    @Transactional
    public boolean updateAdminEducation(AdminEducation adminEducation, Integer IDAdmin) throws SQLException {
        // * update education of current user
        return updateAdminAccountDao.updateAdminEducation(adminEducation, IDAdmin);
    }

    @Transactional
    public boolean updateAdminAddress(AdminAddress adminAddress, Integer IDAdmin) throws SQLException {
        // * update address of current user
        return updateAdminAccountDao.updateAdminAddress(adminAddress, IDAdmin);
    }

    @Transactional
    public boolean updateAdminSkill(AdminSkill adminSkill, Integer IDAdmin) throws SQLException {
        // * update skill of current user
        return updateAdminAccountDao.updateAdminSkill(adminSkill, IDAdmin);
    }

    @Transactional
    public void processAdminChangeSet(JsonNode changes, Admin admin) throws SQLException {
        // Extract data from changes
        Integer adminId = admin.getId();

        System.out.println("Kiểm tra nickname: " + admin.getNickname());

        // Update user in the database
        updateAdmin(admin);

        // Handle related data like education, address, and skills
        JsonNode educations = changes.get("educations");
        JsonNode addresses = changes.get("addresses");
        JsonNode skills = changes.get("skills");

        for (JsonNode edu : educations) {
            JsonNode idNode = edu.get("id");
            JsonNode valueNode = edu.get("value");

            System.out.println("Dcm đây là giá trị của  education: " + valueNode);

            // Kiểm tra nếu valueNode hợp lệ (ít nhất có 1 giá trị)
            if (valueNode == null || valueNode.size() < 1) continue;

            String school = formatTextUTF_8.decodeValue(valueNode.get(0).asText());

            // Kiểm tra nếu idNode là null hoặc missing node (thực hiện INSERT)
            if (idNode == null || idNode.isNull()) {

                System.out.println("Đã vào null");

                // * CASE: INSERT NEW EDUCATION
                AdminEducation newEducation = new AdminEducation();
                newEducation.setSchool(school);

                System.out.println("school để insert  : " + newEducation.getSchool());
                updateAdminAccountDao.insertAdminEducation(newEducation, adminId);
            } else {
                // * CASE: POSSIBLE UPDATE
                int educationId = idNode.asInt();
                AdminEducation existingEducation = updateAdminAccountDao.findAdminEducationById(adminId, educationId);

                boolean changed = !Helper.safeEquals(existingEducation.getSchool(), school);

                if (changed) {
                    existingEducation.setSchool(school);
                    updateAdminAccountDao.updateAdminEducation(existingEducation, adminId); // UPDATE EXISTING EDUCATION
                } else {
                    continue;
                }
            }
        }




        for (JsonNode addrNode : addresses) {
            // Read id and value
            JsonNode idNode = addrNode.get("id");
            JsonNode valueNode = addrNode.get("value");

            System.out.println("Dcm đây là giá trị của  address: " + valueNode);

            if (valueNode == null || valueNode.size() < 3) continue; // Ensure structure

            String street = formatTextUTF_8.decodeValue(valueNode.get(0).asText());
            String city = formatTextUTF_8.decodeValue(valueNode.get(1).asText());
            String province = formatTextUTF_8.decodeValue(valueNode.get(2).asText());

            if (idNode == null || idNode.isNull()) {

                System.out.println(idNode + "Neu id null");

                // * CASE: INSERT NEW ADDRESS
                AdminAddress newAddress = new AdminAddress();
                newAddress.setStreetName(street);
                newAddress.setCity(city);
                newAddress.setProvince(province);
                updateAdminAccountDao.insertAdminAddress(newAddress, adminId); // Insert path

                System.out.println("Chèn dữ liệu oke rồi đấy");
            } else {
                // * CASE: POSSIBLE UPDATE
                int addressId = idNode.asInt();
                AdminAddress existing = updateAdminAccountDao.findAdminAddressById(adminId, addressId);

                boolean changed = !Helper.safeEquals(existing.getStreetName(), street) ||
                        !Helper.safeEquals(existing.getCity(), city) ||
                        !Helper.safeEquals(existing.getProvince(), province);

                if (changed) {
                    existing.setStreetName(street);
                    existing.setCity(city);
                    existing.setProvince(province);
                    updateAdminAccountDao.updateAdminAddress(existing, adminId); // Update path
                } else {
                    // Skip update
                    continue;
                }
            }
        }

        for (JsonNode skill : skills) {
            JsonNode idNode = skill.get("id");
            JsonNode valueNode = skill.get("value");

            System.out.println("Dcm đây là giá trị của  address: " + valueNode);


            // Kiểm tra valueNode có null hoặc không có ít nhất 1 giá trị hợp lệ
            if (valueNode == null || valueNode.size() < 1) continue;

            String skillName = formatTextUTF_8.decodeValue(valueNode.get(0).asText());

            // Kiểm tra idNode có null hoặc missing (insert case)
            if (idNode == null || idNode.isNull()) {
                // CASE: INSERT NEW SKILL
                AdminSkill newSkill = new AdminSkill();
                newSkill.setDescription(skillName);
                updateAdminAccountDao.insertAdminSkill(newSkill, adminId);
            } else {
                // CASE: POSSIBLE UPDATE
                int skillId = idNode.asInt();
                AdminSkill existingSkill = updateAdminAccountDao.findAdminSkillById(adminId, skillId);

                boolean changed = !Helper.safeEquals(existingSkill.getDescription(), skillName);

                if (changed) {
                    existingSkill.setDescription(skillName);
                    updateAdminAccountDao.updateAdminSkill(existingSkill, adminId); // Update existing skill
                } else {
                    continue;
                }
            }
        }


        // Optionally, handle the user role update as well
        updateUserAccountRole(admin.getAdminAccount().getRole().getId(), adminId);
    }

    @Transactional
    public boolean toggleAdminAccountStatus(int userId) throws SQLException {
        return updateAdminAccountDao.toggleAdminAccountStatus(userId);
    }

}
