package com.manager.doc.service.user.inf;

import com.manager.doc.dao.user.information.fetch.roles.UserRoleDao;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserInformationService {

    @Autowired
    @Qualifier("userRoleDaoImpl")
    private UserRoleDao userRoleDao;

    public Map<Integer, String> fetchUserRole() throws JSQLParserException { // * Fetch role user by id and value

        return userRoleDao.fetchUserRole();
    }

}
