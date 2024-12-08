package com.manager.doc.controller;

import com.manager.doc.dao.admin.account.create.CreateUserAccountDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendDataController {

    @Autowired
    private CreateUserAccountDaoImpl accountDao;

    @RequestMapping("/Test")
    public String SendData(Model model) {


        return "TestSendData";
    }

}
