package com.manager.doc.service.admin.inf;

import com.manager.doc.dao.department.DepartmentDao;
import com.manager.doc.dao.user.information.fetch.full.FetchFullUserDao;
import com.manager.doc.model.department.Department;
import com.manager.doc.model.department.DepartmentWork;
import com.manager.doc.model.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminInformationService {

    @Autowired
    @Qualifier("fetchFullUserDaoImpl")
    private FetchFullUserDao fetchFullUserDao;

    @Autowired
    @Qualifier("departmentDaoImpl")
    private DepartmentDao fetchDepartmentDao;

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


}
