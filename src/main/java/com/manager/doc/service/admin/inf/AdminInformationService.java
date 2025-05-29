package com.manager.doc.service.admin.inf;

import com.manager.doc.dao.admin.information.fetch.full.FetchFullAdminDao;
import com.manager.doc.dao.admin.information.fetch.roles.AdminRoleDao;
import com.manager.doc.dao.department.DepartmentDao;
import com.manager.doc.dao.user.information.fetch.full.FetchFullUserDao;
import com.manager.doc.model.admin.*;
import com.manager.doc.model.department.Department;
import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.user.*;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class AdminInformationService {

    @Autowired
    @Qualifier("fetchFullUserDaoImpl")
    private FetchFullUserDao fetchFullUserDao;

    @Autowired
    @Qualifier("fetchFullAdminDaoImpl")
    private FetchFullAdminDao fetchFullAdminDao;

    @Autowired
    @Qualifier("departmentDaoImpl")
    private DepartmentDao fetchDepartmentDao;

    @Autowired
    @Qualifier("adminRoleDaoImpl")
    private AdminRoleDao adminRoleDao;


//    --------------------------------------FOR ADMIN MANAGE USER ACCOUNT-----------------------------------------------------
    protected UserAccount getUserAccountByID(Integer IDUser) {

        return fetchFullUserDao.getUserAccountByID(IDUser);
    } // * private inf

    private List<User> multipleUserInformation() {
        List<User> users = new ArrayList<>(fetchFullUserDao.getUserInformation()); // * to get data from list users

        for (User user: users) {
            List<UserEducation> educationList = new ArrayList<>(fetchFullUserDao.getUserEducations(user.getId()));
            List<UserAddress> addressList = new ArrayList<>(fetchFullUserDao.getUserAddresses(user.getId()));
            List<UserSkill> skillList = new ArrayList<>(fetchFullUserDao.getUserSkills(user.getId()));

            for (UserEducation userEducation: educationList) { // * each user to be add education

                user.getEducations().add(userEducation);
            }

            for (UserAddress userAddress: addressList) { // * each user to be add address

                user.getAddresses().add(userAddress);
            }

            for (UserSkill userSkill: skillList) { // * each user to be add address

                user.getSkills().add(userSkill);
            }

        }

        return users;
    }

    public List<User> getMultipleInfUser() {

        return multipleUserInformation();
    } // * multiple inf

    public List<User> getAllUserInformation() { // * method get all data private and multiple inf user

        UserAccount newUserAccount;
        List<User> newUsers = new ArrayList<>();

        for (User user: multipleUserInformation()) {
            newUserAccount = getUserAccountByID(user.getId());

            user.getUserAccount().setId(newUserAccount.getId());
            user.getUserAccount().setUsername(newUserAccount.getUsername());
//            user.getUserAccount().setPassword(newUserAccount.getPassword());
            user.getUserAccount().setEnable(newUserAccount.getEnable());
            user.getUserAccount().setRole(newUserAccount.getRole());

            newUsers.add(user);
        }

        return newUsers;
    }

    public Set<Department> getAllDepartments() {

        return fetchDepartmentDao.getFullDepartment();
    }

    public Set<DepartmentWork> getAllDepartmentWork() {

        return fetchDepartmentDao.getFullDepartmentWork();
    }


//    --------------------------------------FOR ADMIN MANAGE ADMIN ACCOUNT-----------------------------------------------------

    public Map<Integer, String> fetchAdminRole() throws JSQLParserException { // * Fetch role user by id and value

        return adminRoleDao.fetchAdminRole();
    }

    protected AdminAccount getAdminAccountByID(Integer IDAdmin) {

        return fetchFullAdminDao.getAdminAccountByID(IDAdmin);
    } // * private admin inf

    private List<Admin> multipleAdminInformation() {
        List<Admin> admins = new ArrayList<>(fetchFullAdminDao.getAdminInformation()); // * to get data from list users

        for (Admin admin: admins) {
            List<AdminEducation> educationList = new ArrayList<>(fetchFullAdminDao.getAdminEducations(admin.getId()));
            List<AdminAddress> addressList = new ArrayList<>(fetchFullAdminDao.getAdminAddresses(admin.getId()));
            List<AdminSkill> skillList = new ArrayList<>(fetchFullAdminDao.getAdminSkills(admin.getId()));

            for (AdminEducation adminEducation: educationList) { // * each user to be add education

                admin.getEducations().add(adminEducation);
            }

            for (AdminAddress adminAddress: addressList) { // * each user to be add address

                admin.getAddresses().add(adminAddress);
            }

            for (AdminSkill adminSkill: skillList) { // * each user to be add address

                admin.getSkills().add(adminSkill);
            }

        }

        return admins;
    }

    public List<Admin> getMultipleInfAdmin() {

        return multipleAdminInformation();
    } // * multiple inf

    public List<Admin> getAllAdminInformation() { // * method get all data private and multiple inf user

        AdminAccount newAdminAccount;
        List<Admin> newAdmins = new ArrayList<>();

        for (Admin admin : multipleAdminInformation()) {
            newAdminAccount = getAdminAccountByID(admin.getId());

            admin.getAdminAccount().setId(newAdminAccount.getId());
            admin.getAdminAccount().setUsername(newAdminAccount.getUsername());
//            user.getUserAccount().setPassword(newUserAccount.getPassword());
            admin.getAdminAccount().setEnable(newAdminAccount.getEnable());
            admin.getAdminAccount().setRole(newAdminAccount.getRole());

            newAdmins.add(admin);
        }

        return newAdmins;
    }



}
