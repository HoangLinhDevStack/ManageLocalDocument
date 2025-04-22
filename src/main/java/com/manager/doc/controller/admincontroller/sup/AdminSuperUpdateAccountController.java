package com.manager.doc.controller.admincontroller.sup;

import com.manager.doc.model.sex.Sex;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAccount;
import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.model.user.UserSkill;
import com.manager.doc.service.admin.account.AdminReadUserAccountService;
import com.manager.doc.service.admin.account.AdminUpdateUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.department.FetchDepartment;
import com.manager.doc.service.helper.HelperUpdateUser;
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

//    @PostMapping("/update-user")
//    public String adminUpdateAccount(@ModelAttribute("user") User user,
//                                    @RequestParam(value = "roleId", required = false) Integer roleId,
//                                    @RequestParam(value = "genderId", required = false) Integer genderId,
//                                    @RequestParam(value = "education[0]", required = false) String school,
//                                    @RequestParam(value = "address[0]", required = false) String streetAddress,
//                                    @RequestParam(value = "skills[0]", required = false) String skillDescription,
//                                    Model model,
//                                    RedirectAttributes redirectAttributes) throws JSQLParserException {
//        try {
//
//            Integer userId = user.getId();
//            if (userId == null) {
//                throw new IllegalArgumentException("User ID cannot be null");
//            }
//
//            // Set the Sex object if genderId is provided
//            if (genderId != null) {
//                com.manager.doc.model.sex.Sex sex = new com.manager.doc.model.sex.Sex();
//                sex.setId(genderId);
//                user.setSex(sex);
//            }
//
//            // Get the existing user to preserve the picture and other data
//            User existingUser = adminUpdateUserAccountService.findUserAccountFullInformation(userId);
//            if (existingUser != null) {
//                // Preserve picture if not provided in the form
//                if (user.getPicture() == null) {
//                    user.setPicture(existingUser.getPicture());
//                }
//
//                // Preserve sex if not provided in the form
//                if (user.getSex() == null) {
//                    user.setSex(existingUser.getSex());
//                }
//            }
//
//            // Update the user basic information
//            boolean result = adminUpdateUserAccountService.updateUser(user);
//            boolean allSuccess = result;
//
//            if (!result) {
//                System.out.println("Error updating user basic information");
//                throw new SQLException("Failed to update user basic information");
//            }
//
//            // Update user role if provided
//            if (roleId != null) {
//                boolean roleResult = adminUpdateUserAccountService.updateUserAccountRole(roleId, userId);
//                allSuccess = allSuccess && roleResult;
//                if (!roleResult) {
//                    System.out.println("Error updating user role");
//                }
//            }
//
//            // Update user education if provided
//            if (school != null && !school.trim().isEmpty()) {
//                UserEducation education = new UserEducation();
//                education.setSchool(school);
//                boolean educationResult = adminUpdateUserAccountService.updateUserEducation(education, userId);
//                allSuccess = allSuccess && educationResult;
//                if (!educationResult) {
//                    System.out.println("Error updating user education");
//                }
//            }
//
//            // Update user address if provided
//            if (streetAddress != null && !streetAddress.trim().isEmpty()) {
//                // Parse the address - assuming format like "Street Name, City, Province"
//                String[] addressParts = streetAddress.split(",");
//
//                UserAddress address = new UserAddress();
//                address.setStreetName(addressParts[0].trim());
//
//                if (addressParts.length > 1) {
//                    address.setCity(addressParts[1].trim());
//                }
//
//                if (addressParts.length > 2) {
//                    address.setProvince(addressParts[2].trim());
//                }
//
//                boolean addressResult = adminUpdateUserAccountService.updateUserAddress(address, userId);
//                allSuccess = allSuccess && addressResult;
//                if (!addressResult) {
//                    System.out.println("Error updating user address");
//                }
//            }
//
//            // Update user skills if provided
//            if (skillDescription != null && !skillDescription.trim().isEmpty()) {
//                UserSkill skill = new UserSkill();
//                skill.setDescriptions(skillDescription);
//                boolean skillResult = adminUpdateUserAccountService.updateUserSkill(skill, userId);
//                allSuccess = allSuccess && skillResult;
//                if (!skillResult) {
//                    System.out.println("Error updating user skills");
//                }
//            }
//
//            if (!allSuccess) {
//                System.out.println("Error updating some user information");
//                redirectAttributes.addFlashAttribute("warningMessage", "User basic information updated, but some additional information could not be updated.");
//            } else {
//                System.out.println("Success updating user account and all related information");
//                redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
//            }
//        } catch (SQLException e) {
//            System.err.println("Database error occurred: " + e.getMessage());
//            e.printStackTrace();
//
//            // Add error message to the model
//            model.addAttribute("errorMessage", "An error occurred while updating the user information: " + e.getMessage());
//            model.addAttribute("sexData", fetchSex.choiceSex());
//            model.addAttribute("roleUser", userInformationService.fetchUserRole());
//            model.addAttribute("user", user);
//
//            // Return to the form with the error message
//            return "admin/build_account/update_user_account";
//        } catch (Exception e) {
//            System.err.println("Unexpected error occurred: " + e.getMessage());
//            e.printStackTrace();
//
//            // Add error message to the model
//            model.addAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
//            model.addAttribute("sexData", fetchSex.choiceSex());
//            model.addAttribute("roleUser", userInformationService.fetchUserRole());
//            model.addAttribute("user", user);
//
//            // Return to the form with the error message
//            return "admin/build_account/update_user_account";
//        }
//
//        // Redirect to the list account page
//        return "redirect:/ManagerBook/admin/super/list-account";
//    }







    @PostMapping("/update-user")
    public String adminUpdateAccount(@ModelAttribute("user") User user,
                                     @RequestParam(value = "roleId", required = false) Integer roleId,
                                     @RequestParam(value = "genderId", required = false) Integer genderId,

//                                     * education pass values by array
                                     @RequestParam(value = "educations", required = false) String[] educations,

//                                     * address pass values by array
                                     @RequestParam(value = "streets", required = false) String[] streets,
                                     @RequestParam(value = "cities", required = false) String[] cities,
                                     @RequestParam(value = "provinces", required = false) String[] provinces,

//                                     * skills pass values by array
                                     @RequestParam(value = "skills", required = false) String[] skills,

                                     Model model,
                                     RedirectAttributes redirectAttributes) throws JSQLParserException, SQLException {
        try {
            Integer userId = user.getId();
            System.out.println("Received user ID: " + userId);
            if (userId == null) throw new IllegalArgumentException("User ID cannot be null");

            // Set gender if provided
            if (genderId != null) {
                Sex sex = new Sex();
                sex.setId(genderId);
                user.setSex(sex);
            }

            // Get existing user to preserve picture and sex
            User existingUser = adminUpdateUserAccountService.findUserAccountFullInformation(userId);
            if (existingUser != null) {
                if (user.getPicture() == null) user.setPicture(existingUser.getPicture());
                if (user.getSex() == null) user.setSex(existingUser.getSex());
            }

            // Update basic user information
            boolean allSuccess = adminUpdateUserAccountService.updateUser(user);

            // Update additional info (role, education, address, skills)
            if (roleId != null) allSuccess &= adminUpdateUserAccountService.updateUserAccountRole(roleId, userId);

//            for (UserEducation edu : user.getEducations()) {
//                if (user.getEducations() != null) allSuccess &= adminUpdateUserAccountService.updateUserEducation(edu, userId);
//            }
//
//            for (UserAddress addr : user.getAddresses()) {
//                if (user.getAddresses() != null) allSuccess &= adminUpdateUserAccountService.updateUserAddress(addr, userId);
//            }
//
//            for (UserSkill skill : user.getSkills()) {
//                if (user.getSkills() != null) allSuccess &= adminUpdateUserAccountService.updateUserSkill(skill, userId);
//            }


            UserEducation userEducation = new UserEducation();
            UserAddress UserAddress = new UserAddress();
            UserSkill userSkill = new UserSkill();

            for (int i = 0; i < educations.length; i++) {
                System.out.println("Education " + i + ": " + educations[i]);
                if(educations[i] != null && allSuccess) {
                    userEducation.setSchool(educations[i]);
                    adminUpdateUserAccountService.updateUserEducation(userEducation, userId);
                }
            }


            for (int i = 0; i < streets.length; i++) {
                System.out.println("Street Address " + i + ": " + streets[i]);
            }
            for (int i = 0; i < cities.length; i++) {
                System.out.println("City " + i + ": " + cities[i]);
            }
            for (int i = 0; i < provinces.length; i++) {
                System.out.println("Province " + i + ": " + provinces[i]);
            }

            for (int i = 0; i < skills.length; i++) {
                System.out.println("Skill Description " + i + ": " + skills[i]);
            }


            System.out.println("Address List: " + user.getAddresses());
            System.out.println("Skills List: " + user.getSkills());


//            if (education != null) allSuccess &= adminUpdateUserAccountService.updateUserEducation(education, userId);
//            if (address != null) allSuccess &= adminUpdateUserAccountService.updateUserAddress(address, userId);
//            if (userSkill != null) allSuccess &= adminUpdateUserAccountService.updateUserSkill(userSkill, userId);

            // Handle success or failure
            if (allSuccess) {
                redirectAttributes.addFlashAttribute("successMessage", "User updated successfully");
                return "redirect:/ManagerBook/admin/super/list-account";
            } else {
                redirectAttributes.addFlashAttribute("warningMessage", "Some user information could not be updated.");
                return "redirect:/ManagerBook/admin/super/list-account";
            }
        } catch (SQLException e) {
            return handleError(e, model, user);
        } catch (Exception e) {
            return handleError(e, model, user);
        }
    }

    private String handleError(Exception e, Model model, User user) throws SQLException, JSQLParserException {
        e.printStackTrace();
        model.addAttribute("errorMessage", "An error occurred: " + e.getMessage());
        model.addAttribute("sexData", fetchSex.choiceSex());
        model.addAttribute("roleUser", userInformationService.fetchUserRole());
        model.addAttribute("user", user);
        return "admin/build_account/update_user_account";
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
