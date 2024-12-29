package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.dao.user.information.fetch.UserRoleDao;
import com.manager.doc.dto.user.account.UserAccountDTO;
import com.manager.doc.service.admin.account.AdminCreateUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.sex.FetchSex;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("admin/super/create-account")
public class AdminSuperCreateAccountController {

    @Autowired
    private AdminCreateUserAccountService adminCreateUserAccountService;

    @Autowired
    private FetchSex fetchSex;


    @GetMapping
    public String adminChoiceFormAccount() {
        return "admin/build_account/choice_form";
    }

    @GetMapping("/user")
    public String adminCreateUserAccountForm(@ModelAttribute("userAccountDTO") UserAccountDTO userAccountDTO, Model model) throws JSQLParserException {

        Map<Integer, String> sexData = fetchSex.choiceSex();
//        Map<Integer, String> roleUsers = userRoleDao.fetchUserRole();
        model.addAttribute("sexData", sexData);
        System.out.println(fetchSex.choiceSex());

        return "admin/build_account/create_user_account";
    }

    @GetMapping("/admin")
    public String adminCreateAdminAccountForm() {

        return "admin/build_account/";
    }

    @PostMapping("/user")
    public String adminCreateUserAccount() {
        return "admin/homepage";
    }

    @PostMapping("/admin")
    public String adminCreateAdminAccount() {
        return "admin/homepage";
    }


}
