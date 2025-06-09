package com.manager.doc.service.admin.account;

import com.manager.doc.dao.user.account.delete.DeleteUserAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminDeleteUserAccountService {

    @Autowired
    private DeleteUserAccountDao deleteUserAccountDao;

    @Transactional
    public void deleteAdminAccount(Integer idAdmin) {
        deleteUserAccountDao.deleteAdmin(idAdmin);
    }

    @Transactional
    public void deleteUserAccount(Integer idUser) {
        deleteUserAccountDao.deleteUser(idUser);
    }

}
