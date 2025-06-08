package com.manager.doc.controller.admincontroller.sup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.doc.model.admin.Admin;
import com.manager.doc.model.admin.AdminAccount;
import com.manager.doc.model.admin.AdminRoles;
import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.*;
import com.manager.doc.service.admin.account.AdminReadUserAccountService;
import com.manager.doc.service.admin.account.AdminUpdateUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.department.FetchDepartment;
import com.manager.doc.service.document.DocumentService;
import com.manager.doc.service.helper.Helper;
import com.manager.doc.service.office.FetchOffice;
import com.manager.doc.service.sex.FetchSex;
import com.manager.doc.service.user.inf.UserInformationService;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    @Autowired
    private DocumentService documentService;


//   * ----------------- update account user -------------------

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
                                     @RequestParam("changeSetJson") String changeSetJson,
                                     Model model,
                                     RedirectAttributes redirectAttributes) throws JSQLParserException, SQLException, JsonProcessingException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode changes = mapper.readTree(changeSetJson);
            System.out.println(changes.toString());

            UserRoles userRoles = new UserRoles();
            userRoles.setId(roleId);
            Sex sex = new Sex();
            sex.setId(genderId);

            user.getUserAccount().setRole(userRoles);
            user.setSex(sex);

            System.out.println("Cập nhập Thoong tin sinh nhật: " + user.getDateOfBirth());

            adminUpdateUserAccountService.processUserChangeSet(changes, user);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin tài khoản người dùng thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/ManagerBook/admin/super/list-account";
    }


    @PostMapping("/update-password-user")
    public String adminUpdateUserPasswordAccount(@RequestParam("IDUser") Integer IDUser,
                                                 @RequestParam("password") String password,
                                                 Model model,
                                                 RedirectAttributes redirectAttributes) throws JSQLParserException, SQLException {

        boolean result = adminUpdateUserAccountService.updateUserPassword(IDUser, password);

        if (!result) {
            redirectAttributes.addFlashAttribute("uploadError", "Cập nhật mật khẩu thất bại!");
        } else {
            redirectAttributes.addFlashAttribute("uploadSuccess", "Cập nhật mật khẩu thành công!");
        }

        List<UserAccount> accounts = adminReadUserAccountService.getUsersAccount();
        model.addAttribute("roleUser", userInformationService.fetchUserRole());
        model.addAttribute("DepartmentKeyAndValue", fetchDepartment.choiceDepartment());
        model.addAttribute("OfficeKeyAndValue", fetchOffice.choiceOffices());
        model.addAttribute("userAccount", accounts);
        model.addAttribute("allUserInformation", adminInformationService.getAllUserInformation());
        model.addAttribute("departmentWork", fetchDepartment.fetchFullDepartmentWork());

        return "redirect:/ManagerBook/admin/super/list-account";
    }

    @RequestMapping(value = "/toggle-account-status/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String toggleUserAccountStatus(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            boolean result = adminUpdateUserAccountService.toggleAccountStatus(id);
            if (result) {
                redirectAttributes.addFlashAttribute("successMessage", "Cập nhật trạng thái tài khoản thành công");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật trạng thái tài khoản thất bại");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/ManagerBook/admin/super/list-account";
    }


//  *  ------------------ update account admin -------------------

    @GetMapping("/admin/{id}")
    public String adminUpdateAdminAccountForm(@PathVariable int id, Model model) throws JSQLParserException {

        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> roleAdmins = adminInformationService.fetchAdminRole();

        model.addAttribute("sexData", sexData);
        model.addAttribute("roleAdmin", roleAdmins);
        model.addAttribute("admin", adminUpdateUserAccountService.findAdminAccountFullInformation(id));

        return "admin/build_account/update_admin_account";
    }

    @GetMapping("/admin-yourself")
    public String adminUpdateYourSelfAccountForm(Model model) throws JSQLParserException {

        // Lấy thông tin người dùng từ Spring Security
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        // Kiểm tra kiểu dữ liệu của principal
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            // Đây là đối tượng User của Spring Security
            username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
        } else {
            // Nếu không phải User, có thể là String hoặc kiểu khác
            username = principal.toString();
        }

        // Lấy IDAdmin từ username
        Integer adminId = documentService.getAdminIdByUsername(username);

        Map<Integer, String> sexData = fetchSex.choiceSex();
        Map<Integer, String> roleAdmins = adminInformationService.fetchAdminRole();

        model.addAttribute("sexData", sexData);
//        model.addAttribute("roleAdmin", roleAdmins);
        model.addAttribute("admin", adminUpdateUserAccountService.findAdminAccountFullInformation(adminId));

        return "admin/build_information/update-yourself";
    }

    @PostMapping("/update-admin-yourself")
    public String adminUpdateYourSelfAccount(@ModelAttribute("admin") Admin admin,
                                     @RequestParam(value = "roleId", required = false) Integer roleId,
                                     @RequestParam(value = "genderId", required = false) Integer genderId,
                                     @RequestParam("changeSetJson") String changeSetJson,
                                     Model model,
                                     RedirectAttributes redirectAttributes) throws JSQLParserException, SQLException, JsonProcessingException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode changes = mapper.readTree(changeSetJson);
            System.out.println(changes.toString());

            AdminRoles adminRoles = new AdminRoles();
            adminRoles.setId(roleId);
            Sex sex = new Sex();
            sex.setId(genderId);

            admin.getAdminAccount().setRole(adminRoles);
            admin.setSex(sex);

            adminUpdateUserAccountService.processAdminChangeSet(changes, admin);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin tài khoản thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }



        return "redirect:/ManagerBook/admin/super/update-account/admin-yourself";
    }

    @GetMapping("/admin-account-password/{id}")
    public String adminUpdateAdminPasswordAccountForm(@PathVariable int id, Model model) {

        model.addAttribute("admin", adminUpdateUserAccountService.findAdminAccountFullInformation(id));
        return "admin/build_account/update_admin_account_password";
    }


    @PostMapping("/update-admin")
    public String adminUpdateAccount(@ModelAttribute("admin") Admin admin,
                                     @RequestParam(value = "roleId", required = false) Integer roleId,
                                     @RequestParam(value = "genderId", required = false) Integer genderId,
                                     @RequestParam("changeSetJson") String changeSetJson,
                                     Model model,
                                     RedirectAttributes redirectAttributes) throws JSQLParserException, SQLException, JsonProcessingException {

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode changes = mapper.readTree(changeSetJson);
            System.out.println(changes.toString());

            AdminRoles adminRoles = new AdminRoles();
            adminRoles.setId(roleId);
            Sex sex = new Sex();
            sex.setId(genderId);

            admin.getAdminAccount().setRole(adminRoles);
            admin.setSex(sex);

            System.out.println("debug nick name admin: " + admin.getNickname());

            adminUpdateUserAccountService.processAdminChangeSet(changes, admin);
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thông tin tài khoản admin thành công!");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/ManagerBook/admin/super/list-account-admin";
    }


    @PostMapping("/update-password-admin")
    public String adminUpdateAdminPasswordAccount(@RequestParam("IDAdmin") Integer IDAdmin,
                                                 @RequestParam("password") String password,
                                                 Model model,
                                                 RedirectAttributes redirectAttributes) throws JSQLParserException, SQLException {

        boolean result = adminUpdateUserAccountService.updateAdminPassword(IDAdmin, password);

        if (!result) {
            redirectAttributes.addFlashAttribute("uploadError", "Cập nhật mật khẩu thất bại!");
        } else {
            redirectAttributes.addFlashAttribute("uploadSuccess", "Cập nhật mật khẩu thành công!");
        }

        List<AdminAccount> accounts = adminReadUserAccountService.getAdminsAccount();
        model.addAttribute("roleAdmin", adminInformationService.fetchAdminRole());
        model.addAttribute("DepartmentKeyAndValue", fetchDepartment.choiceDepartment());
        model.addAttribute("OfficeKeyAndValue", fetchOffice.choiceOffices());
        model.addAttribute("adminAccount", accounts);
        model.addAttribute("allAdminInformation", adminInformationService.getAllAdminInformation());
        model.addAttribute("departmentWork", fetchDepartment.fetchFullDepartmentWork());

        return "redirect:/ManagerBook/admin/super/list-account-admin";
    }

    @RequestMapping(value = "/toggle-admin-account-status/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String toggleAdminAccountStatus(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            boolean result = adminUpdateUserAccountService.toggleAdminAccountStatus(id);
            if (result) {
                redirectAttributes.addFlashAttribute("successMessage", "Cập nhật trạng thái tài khoản thành công");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Cập nhật trạng thái tài khoản thất bại");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
        }
        return "redirect:/ManagerBook/admin/super/list-account-admin";
    }


}
