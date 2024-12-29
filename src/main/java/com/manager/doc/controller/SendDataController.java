package com.manager.doc.controller;

import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.sex.FetchSex;
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
    public String SendData(Model model) {

        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> rolesAdmin = adminInfService.fetchAdminRole();

        model.addAttribute("SexData", sexData);
        model.addAttribute("rolesAdmin", rolesAdmin);

        return "TestSendData";
    }

}
