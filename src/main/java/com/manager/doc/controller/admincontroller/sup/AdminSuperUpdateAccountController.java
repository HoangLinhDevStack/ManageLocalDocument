package com.manager.doc.controller.admincontroller.sup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.*;
import com.manager.doc.service.admin.account.AdminReadUserAccountService;
import com.manager.doc.service.admin.account.AdminUpdateUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.department.FetchDepartment;
import com.manager.doc.service.helper.Helper;
import com.manager.doc.service.office.FetchOffice;
import com.manager.doc.service.sex.FetchSex;
import com.manager.doc.service.user.inf.UserInformationService;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/super/update-account")
public class AdminSuperUpdateAccountController {

    @Autowired
    private FetchSex fetchSex;

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private AdminUpdateUserAccountService adminUpdateUserAccountService;

    @Autowired
    private AdminInformationService adminInformationService;

    @Autowired
    private FetchDepartment fetchDepartment;

    @Autowired
    private FetchOffice fetchOffice;

    @Autowired
    private AdminReadUserAccountService adminReadUserAccountService;

    @Autowired
    private PasswordEncoder encoder;


    @GetMapping("/user/{id}")
    public String adminUpdateAccountForm(@PathVariable int id, Model model) throws JSQLParserException {

        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> roleUsers = userInformationService.fetchUserRole();

        model.addAttribute("sexData", sexData);
        model.addAttribute("roleUser", roleUsers);
        model.addAttribute("user", adminUpdateUserAccountService.findUserAccountFullInformation(id));

        return "admin/build_account/update_user_account";
    }

    @GetMapping("/user-account-password/{id}")
    public String adminUpdateUserPasswordAccountForm(@PathVariable int id, Model model) {

        model.addAttribute("user", adminUpdateUserAccountService.findUserAccountFullInformation(id));
        return "admin/build_account/update_user_account_password";
    }


    @PostMapping("/update-user")
    public String adminUpdateAccount(@ModelAttribute("user") User user,
                                     @RequestParam(value = "roleId", required = false) Integer roleId,
                                     @RequestParam(value = "genderId", required = false) Integer genderId,

                                     @RequestParam("changeSetJson") String changeSetJson, // * value of json string contain key and values of user field

                                     Model model) throws JSQLParserException, SQLException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode changes = mapper.readTree(changeSetJson);
        System.out.println(changes.toString());

        System.out.println("DCM đây là quốc gia" + user.getNation());

        UserRoles userRoles = new UserRoles();
        userRoles.setId(roleId);
        Sex sex = new Sex();
        sex.setId(genderId);

//
        user.getUserAccount().setRole(userRoles);
//        int idRole = user.getUserAccount().getRole().getId();
//        System.out.println("Id User roles" + idRole);
        user.setSex(sex);
//
        adminUpdateUserAccountService.processChangeSet(changes, user);

        return "redirect:/ManagerBook/admin/super/list-account";
    }


    @PostMapping("/update-password-user")
    public String adminUpdateUserPasswordAccount(@RequestParam("IDUser") Integer IDUser,
                                                 @RequestParam("password") String password,
                                                 Model model) throws JSQLParserException, SQLException {

        boolean result = adminUpdateUserAccountService.updatePassword(IDUser, password);

        if (!result) {
            System.out.println("Error updated password");
        } else {
            System.out.println("Success updated password");
        }


        List<UserAccount> accounts = adminReadUserAccountService.getUsersAccount();
        System.out.println("Fetched Users: " + accounts);


        model.addAttribute("roleUser", userInformationService.fetchUserRole());
        model.addAttribute("DepartmentKeyAndValue", fetchDepartment.choiceDepartment());
        model.addAttribute("OfficeKeyAndValue", fetchOffice.choiceOffices());
        model.addAttribute("userAccount", accounts);
        model.addAttribute("allUserInformation", adminInformationService.getAllUserInformation());
        model.addAttribute("departmentWork", fetchDepartment.fetchFullDepartmentWork());

        return "admin/build_account/read_user_account";
    }
}
