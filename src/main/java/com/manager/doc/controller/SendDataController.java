package com.manager.doc.controller;

import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.sex.FetchSex;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class SendDataController {

    @Autowired
    private FetchSex fetchSex;

    @Autowired
    private AdminInformationService adminInfService;

    @RequestMapping("/Test")
    public String SendData(Model model) throws JSQLParserException {

//        Map<Integer, String> sexData = fetchSex.choiceSex();
//        Map<Integer, String> rolesUser = adminInfService.fetchUserRole();
//
//        model.addAttribute("SexData", sexData);
//        model.addAttribute("rolesUser", rolesUser);

        return "TestSendData";
    }

}
