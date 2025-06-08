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
import com.manager.doc.service.format.SafeDecoder;
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
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;
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

        // Get existing data
        List<UserEducation> existingEducations = updateUserAccountDao.getAllUserEducationById(userId);
        List<UserAddress> existingAddresses = updateUserAccountDao.getAllUserAddressById(userId);
        List<UserSkill> existingSkills = updateUserAccountDao.getAllUserSkillById(userId);

        // Process educations
        Set<Integer> existingEducationIds = existingEducations.stream()
                .map(UserEducation::getId)
                .collect(Collectors.toSet());
        Set<Integer> newEducationIds = new HashSet<>();
        
        for (JsonNode edu : educations) {
            JsonNode idNode = edu.get("id");
            JsonNode valueNode = edu.get("value");

            if (valueNode == null || valueNode.size() < 1) continue;

            String school = valueNode.get(0).asText();

            if (idNode == null || idNode.isNull()) {
                UserEducation newEducation = new UserEducation();
                newEducation.setSchool(SafeDecoder.decodeIfEncoded(school, formatTextUTF_8::decodeValue));
                updateUserAccountDao.insertUserEducation(newEducation, userId);
            } else {
                int educationId = idNode.asInt();
                newEducationIds.add(educationId);
                UserEducation existingEducation = updateUserAccountDao.findUserEducationById(userId, educationId);

                boolean changed = !Helper.safeEquals(existingEducation.getSchool(), school);

                if (changed) {
                    existingEducation.setSchool(SafeDecoder.decodeIfEncoded(school, formatTextUTF_8::decodeValue));
                    updateUserAccountDao.updateUserEducation(existingEducation, userId);
                }
            }
        }

        // Delete removed educations
        existingEducationIds.removeAll(newEducationIds);
        for (Integer educationId : existingEducationIds) {
            updateUserAccountDao.deleteUserEducation(userId, educationId);
        }

        // Process addresses
        Set<Integer> existingAddressIds = existingAddresses.stream()
                .map(UserAddress::getId)
                .collect(Collectors.toSet());
        Set<Integer> newAddressIds = new HashSet<>();

        for (JsonNode addrNode : addresses) {
            JsonNode idNode = addrNode.get("id");
            JsonNode valueNode = addrNode.get("value");

            if (valueNode == null || valueNode.size() < 3) continue;

            String street = valueNode.get(0).asText();
            String city = valueNode.get(1).asText();
            String province = valueNode.get(2).asText();

            if (idNode == null || idNode.isNull()) {
                UserAddress newAddress = new UserAddress();
                newAddress.setStreetName(SafeDecoder.decodeIfEncoded(street, formatTextUTF_8::decodeValue));
                newAddress.setCity(SafeDecoder.decodeIfEncoded(city, formatTextUTF_8::decodeValue));
                newAddress.setProvince(SafeDecoder.decodeIfEncoded(province, formatTextUTF_8::decodeValue));
                updateUserAccountDao.insertUserAddress(newAddress, userId);
            } else {
                int addressId = idNode.asInt();
                newAddressIds.add(addressId);
                UserAddress existing = updateUserAccountDao.findUserAddressById(userId, addressId);

                boolean changed = !Helper.safeEquals(existing.getStreetName(), street) ||
                        !Helper.safeEquals(existing.getCity(), city) ||
                        !Helper.safeEquals(existing.getProvince(), province);

                if (changed) {
                    existing.setStreetName(SafeDecoder.decodeIfEncoded(street, formatTextUTF_8::decodeValue));
                    existing.setCity(SafeDecoder.decodeIfEncoded(city, formatTextUTF_8::decodeValue));
                    existing.setProvince(SafeDecoder.decodeIfEncoded(province, formatTextUTF_8::decodeValue));
                    updateUserAccountDao.updateUserAddress(existing, userId);
                }
            }
        }

        // Delete removed addresses
        existingAddressIds.removeAll(newAddressIds);
        for (Integer addressId : existingAddressIds) {
            updateUserAccountDao.deleteUserAddress(userId, addressId);
        }

        // Process skills
        Set<Integer> existingSkillIds = existingSkills.stream()
                .map(UserSkill::getId)
                .collect(Collectors.toSet());
        Set<Integer> newSkillIds = new HashSet<>();

        for (JsonNode skill : skills) {
            JsonNode idNode = skill.get("id");
            JsonNode valueNode = skill.get("value");

            if (valueNode == null || valueNode.size() < 1) continue;

            String skillName = valueNode.get(0).asText();

            if (idNode == null || idNode.isNull()) {
                UserSkill newSkill = new UserSkill();
                newSkill.setDescriptions(SafeDecoder.decodeIfEncoded(skillName, formatTextUTF_8::decodeValue));
                updateUserAccountDao.insertUserSkill(newSkill, userId);
            } else {
                int skillId = idNode.asInt();
                newSkillIds.add(skillId);
                UserSkill existingSkill = updateUserAccountDao.findUserSkillById(userId, skillId);

                boolean changed = !Helper.safeEquals(existingSkill.getDescriptions(), skillName);

                if (changed) {
                    existingSkill.setDescriptions(SafeDecoder.decodeIfEncoded(skillName, formatTextUTF_8::decodeValue));
                    updateUserAccountDao.updateUserSkill(existingSkill, userId);
                }
            }
        }

        // Delete removed skills
        existingSkillIds.removeAll(newSkillIds);
        for (Integer skillId : existingSkillIds) {
            updateUserAccountDao.deleteUserSkill(userId, skillId);
        }

        // Update user role
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

        // Get existing data
        List<AdminEducation> existingEducations = updateAdminAccountDao.getAllAdminEducationById(adminId);
        List<AdminAddress> existingAddresses = updateAdminAccountDao.getAllAdminAddressById(adminId);
        List<AdminSkill> existingSkills = updateAdminAccountDao.getAllAdminSkillById(adminId);

        // Process educations
        Set<Integer> existingEducationIds = existingEducations.stream()
                .map(AdminEducation::getId)
                .collect(Collectors.toSet());
        Set<Integer> newEducationIds = new HashSet<>();

        for (JsonNode edu : educations) {
            JsonNode idNode = edu.get("id");
            JsonNode valueNode = edu.get("value");

            System.out.println("Dcm đây là giá trị của  education: " + valueNode);

            // Kiểm tra nếu valueNode hợp lệ (ít nhất có 1 giá trị)
            if (valueNode == null || valueNode.size() < 1) continue;

            String school = valueNode.get(0).asText();

            // Kiểm tra nếu idNode là null hoặc missing node (thực hiện INSERT)
            if (idNode == null || idNode.isNull()) {

                System.out.println("Đã vào null");

                // * CASE: INSERT NEW EDUCATION
                AdminEducation newEducation = new AdminEducation();
                newEducation.setSchool(SafeDecoder.decodeIfEncoded(school, formatTextUTF_8::decodeValue));

                System.out.println("school để insert  : " + newEducation.getSchool());
                updateAdminAccountDao.insertAdminEducation(newEducation, adminId);
            } else {
                // * CASE: POSSIBLE UPDATE
                int educationId = idNode.asInt();
                newEducationIds.add(educationId);
                AdminEducation existingEducation = updateAdminAccountDao.findAdminEducationById(adminId, educationId);

                boolean changed = !Helper.safeEquals(existingEducation.getSchool(), school);

                if (changed) {
                    existingEducation.setSchool(SafeDecoder.decodeIfEncoded(school, formatTextUTF_8::decodeValue));
                    updateAdminAccountDao.updateAdminEducation(existingEducation, adminId); // UPDATE EXISTING EDUCATION
                } else {
                    continue;
                }
            }
        }

        // Delete removed educations
        existingEducationIds.removeAll(newEducationIds);
        for (Integer educationId : existingEducationIds) {
            updateAdminAccountDao.deleteAdminEducation(adminId, educationId);
        }

        // Process addresses
        Set<Integer> existingAddressIds = existingAddresses.stream()
                .map(AdminAddress::getId)
                .collect(Collectors.toSet());
        Set<Integer> newAddressIds = new HashSet<>();

        for (JsonNode addrNode : addresses) {
            // Read id and value
            JsonNode idNode = addrNode.get("id");
            JsonNode valueNode = addrNode.get("value");

            System.out.println("Dcm đây là giá trị của  address: " + valueNode);

            if (valueNode == null || valueNode.size() < 3) continue; // Ensure structure

            String street = valueNode.get(0).asText();
            String city = valueNode.get(1).asText();
            String province = valueNode.get(2).asText();

            if (idNode == null || idNode.isNull()) {

                System.out.println(idNode + "Neu id null");

                // * CASE: INSERT NEW ADDRESS
                AdminAddress newAddress = new AdminAddress();
                newAddress.setStreetName(SafeDecoder.decodeIfEncoded(street, formatTextUTF_8::decodeValue));
                newAddress.setCity(SafeDecoder.decodeIfEncoded(city, formatTextUTF_8::decodeValue));
                newAddress.setProvince(SafeDecoder.decodeIfEncoded(province, formatTextUTF_8::decodeValue));
                updateAdminAccountDao.insertAdminAddress(newAddress, adminId); // Insert path

                System.out.println("Chèn dữ liệu oke rồi đấy");
            } else {
                // * CASE: POSSIBLE UPDATE
                int addressId = idNode.asInt();
                newAddressIds.add(addressId);
                AdminAddress existing = updateAdminAccountDao.findAdminAddressById(adminId, addressId);

                boolean changed = !Helper.safeEquals(existing.getStreetName(), street) ||
                        !Helper.safeEquals(existing.getCity(), city) ||
                        !Helper.safeEquals(existing.getProvince(), province);

                if (changed) {
                    existing.setStreetName(SafeDecoder.decodeIfEncoded(street, formatTextUTF_8::decodeValue));
                    existing.setCity(SafeDecoder.decodeIfEncoded(city, formatTextUTF_8::decodeValue));
                    existing.setProvince(SafeDecoder.decodeIfEncoded(province, formatTextUTF_8::decodeValue));
                    updateAdminAccountDao.updateAdminAddress(existing, adminId); // Update path
                } else {
                    // Skip update
                    continue;
                }
            }
        }

        // Delete removed addresses
        existingAddressIds.removeAll(newAddressIds);
        for (Integer addressId : existingAddressIds) {
            updateAdminAccountDao.deleteAdminAddress(adminId, addressId);
        }

        // Process skills
        Set<Integer> existingSkillIds = existingSkills.stream()
                .map(AdminSkill::getId)
                .collect(Collectors.toSet());
        Set<Integer> newSkillIds = new HashSet<>();

        for (JsonNode skill : skills) {
            JsonNode idNode = skill.get("id");
            JsonNode valueNode = skill.get("value");

            System.out.println("Dcm đây là giá trị của  address: " + valueNode);

            // Kiểm tra valueNode có null hoặc không có ít nhất 1 giá trị hợp lệ
            if (valueNode == null || valueNode.size() < 1) continue;

            String skillName = valueNode.get(0).asText();

            // Kiểm tra idNode có null hoặc missing (insert case)
            if (idNode == null || idNode.isNull()) {
                // CASE: INSERT NEW SKILL
                AdminSkill newSkill = new AdminSkill();
                newSkill.setDescription(SafeDecoder.decodeIfEncoded(skillName, formatTextUTF_8::decodeValue));
                updateAdminAccountDao.insertAdminSkill(newSkill, adminId);
            } else {
                // CASE: POSSIBLE UPDATE
                int skillId = idNode.asInt();
                newSkillIds.add(skillId);
                AdminSkill existingSkill = updateAdminAccountDao.findAdminSkillById(adminId, skillId);

                boolean changed = !Helper.safeEquals(existingSkill.getDescription(), skillName);

                if (changed) {
                    existingSkill.setDescription(SafeDecoder.decodeIfEncoded(skillName, formatTextUTF_8::decodeValue));
                    updateAdminAccountDao.updateAdminSkill(existingSkill, adminId); // Update existing skill
                } else {
                    continue;
                }
            }
        }

        // Delete removed skills
        existingSkillIds.removeAll(newSkillIds);
        for (Integer skillId : existingSkillIds) {
            updateAdminAccountDao.deleteAdminSkill(adminId, skillId);
        }

        // Optionally, handle the user role update as well
        updateAdminAccountRole(admin.getAdminAccount().getRole().getId(), adminId);
    }

    @Transactional
    public boolean toggleAdminAccountStatus(int userId) throws SQLException {
        return updateAdminAccountDao.toggleAdminAccountStatus(userId);
    }

    @Transactional
    public boolean deleteUserEducation(Integer userId, Integer educationId) throws SQLException {
        // First check if the education exists for this user
        UserEducation existingEducation = updateUserAccountDao.findUserEducationById(userId, educationId);

        if (existingEducation == null) {
            throw new IllegalStateException("Education not found for user " + userId + " with education ID " + educationId);
        }

        // If education exists, proceed with deletion
        return updateUserAccountDao.deleteUserEducation(userId, existingEducation.getId());
    }

}

