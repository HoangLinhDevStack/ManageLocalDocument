package com.manager.doc.dao.admin.information.fetch.full;

import com.manager.doc.model.admin.*;
import com.manager.doc.model.user.*;

import java.util.List;

public interface FetchFullAdminDao {


    List<Admin> getAdminInformation();
    List<AdminAddress> getAdminAddresses(Integer IDAdmin);
    List<AdminSkill> getAdminSkills(Integer IDAdmin);
    List<AdminEducation> getAdminEducations(Integer IDAdmin);
    AdminAccount getAdminAccountByID(Integer IDAdmin);
    AdminAccount getAdminAccountByUsername(String username);
    Admin getAdminInformationById(Integer id);

}
