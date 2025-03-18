package com.manager.doc.dao.user.information.fetch.full;

import com.manager.doc.enumeration.user.RolesUser;
import com.manager.doc.model.user.*;

import java.util.List;

public interface FetchFullUserDao {

    List<User> getUserInformation();
    List<UserAddress> getUserAddresses(Integer IDUser);
    List<UserSkill> getUserSkills(Integer IDUser);
    List<UserEducation> getUserEducations(Integer IDUser);
    UserAccount getUserAccountByID(Integer IDUser);

}
