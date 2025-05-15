package com.manager.doc.controller.admincontroller.sup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/super/up-document")
public class AdminSuperUpDocumentController {

    @GetMapping
    public String adminUpDocument() {
        return "admin/document/up_document";
    }

    @PostMapping
    public String adminListDocument() {
        return "admin/document/list_document";
    }

}
