package com.manager.doc.service.admin.account;

import com.manager.doc.dao.user.account.create.CreateUserAccountDao;
import com.manager.doc.dao.user.information.create.CreateUserDao;
import com.manager.doc.dto.user.CreateUserAccountDTO;
import com.manager.doc.model.user.*;
import com.manager.doc.service.format.FormatTextUTF_8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminCreateUserAccountService {

    @Autowired
    @Qualifier("format_to_UTF_8")
    private FormatTextUTF_8 formatConfig;

    @Autowired
    @Qualifier("createUserAccountDaoImpl")
    private CreateUserAccountDao createUserAccountDao;

    @Autowired
    @Qualifier("createPartUserDaoImpl")
    private CreateUserDao createPartUserDao;

    @Autowired
    @Qualifier("getPasswordEncoder")
    private PasswordEncoder encoder;

    @Transactional
    public void createUserAccount(CreateUserAccountDTO createUserAccountDTO) {

        System.out.println("Service layer: " + createUserAccountDTO.getUser().getName());

        User user = new User(); // * Create user information
        user.setName(formatConfig.decodeValue(createUserAccountDTO.getUser().getName())); // * decoding text to criteria form UTF-8
        user.setSex(createUserAccountDTO.getSex());

        // * set value for user account
        user.getUserAccount().setUsername(createUserAccountDTO.getUsername());
        user.getUserAccount().setPassword(encoder.encode(createUserAccountDTO.getPassword()));
        user.getUserAccount().setRole(createUserAccountDTO.getRole());

        // * get id user from db
        Integer userId = createPartUserDao.save(user);
        user.setId(userId); // * set for object

        createUserAccountDao.save(user);
    }
}
