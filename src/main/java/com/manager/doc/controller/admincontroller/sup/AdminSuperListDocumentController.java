package com.manager.doc.controller.admincontroller.sup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/super/list-document")
public class AdminSuperListDocumentController {

    @GetMapping
    public String adminListDocument() {

        return "admin/document/list_document";
    }

}
