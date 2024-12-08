package com.manager.doc.controller.admincontroller.sup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/super")
public class AdminSuperController {

    @GetMapping // space working admin page
    public String adminRedirect() {

        return "admin/homepage";
    }
}
