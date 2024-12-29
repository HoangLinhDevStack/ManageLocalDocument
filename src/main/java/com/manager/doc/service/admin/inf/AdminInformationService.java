package com.manager.doc.service.admin.inf;

import com.manager.doc.dao.user.information.fetch.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminInformationService {
    @Autowired
    @Qualifier("userRoleDaoImpl")
    private UserRoleDao userRoleDao;

    public Map<Integer, String> fetchAdminRole() {

        return userRoleDao.fetchAdminRole();
    }

}
