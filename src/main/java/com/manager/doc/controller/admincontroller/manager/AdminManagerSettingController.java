package com.manager.doc.controller.admincontroller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/manager/setting")
public class AdminManagerSettingController {

    @GetMapping
    public String adminSetting() {
        return "admin/setting/admin_setting";
    }

}
