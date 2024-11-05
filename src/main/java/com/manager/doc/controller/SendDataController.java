package com.manager.doc.controller;

import com.manager.doc.dao.admin.account.CreateUserAccountDao;
import com.manager.doc.dao.admin.account.CreateUserAccountDaoImpl;
import com.manager.doc.dao.admin.account.CreateUserAccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;

@Controller
public class SendDataController {

    @Autowired
    private CreateUserAccountDaoImpl accountDao;

    @RequestMapping("/Test")
    public String SendData(Model model) {


        return "TestSendData";
    }

}
