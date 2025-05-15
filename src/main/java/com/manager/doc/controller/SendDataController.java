package com.manager.doc.controller;

import com.manager.doc.dao.user.account.update.UpdateUserAccountDao;
import com.manager.doc.model.user.User;
import com.manager.doc.model.user.UserAccount;
import com.manager.doc.model.user.UserAddress;
import com.manager.doc.model.user.UserEducation;
import com.manager.doc.service.admin.account.AdminReadUserAccountService;
import com.manager.doc.service.admin.account.AdminUpdateUserAccountService;
import com.manager.doc.service.admin.inf.AdminInformationService;
import com.manager.doc.service.department.FetchDepartment;
import com.manager.doc.service.office.FetchOffice;
import com.manager.doc.service.sex.FetchSex;
import com.manager.doc.service.format.FormatTextUTF_8;
import com.manager.doc.service.user.inf.UserInformationService;
import net.sf.jsqlparser.JSQLParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Controller
public class SendDataController {

    @Autowired
    @Qualifier("format_to_UTF_8")
    private FormatTextUTF_8 formatConfig;

    @Autowired
    private FetchSex fetchSex;

    @Autowired
    private AdminInformationService adminInfService;

    @Autowired
    private UserInformationService userInformationService;

    @Autowired
    private FetchDepartment fetchDepartment;

    @Autowired
    private FetchOffice fetchOffice;

    @Autowired
    private AdminReadUserAccountService adminReadUserAccountService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AdminInformationService adminInformationService;

    @Autowired
    private AdminUpdateUserAccountService adminUpdateUserAccountService;

    @Autowired
    private UpdateUserAccountDao updateUserAccountDao;

    @GetMapping("/Test")
    public String SendData(Model model) throws JSQLParserException {
        

        model.addAttribute("user", new User());
//        Map<Integer, String> sexData = fetchSex.choiceSex();
//        Map<Integer, String> rolesUser = adminInfService.fetchUserRole();
//
//        model.addAttribute("SexData", sexData);
//        model.addAttribute("rolesUser", rolesUser);

//        Normalizer2 normalizer = Normalizer2.getNFKCInstance();
//        String name = normalizer.normalize("BÃ1i Tuáo¥n ThÃ nh");

//        String corruptedText = "BÃ¹i Tuáº¥n ThÃ nh";
//
//        // Fix corrupted text: Convert from ISO-8859-1 to UTF-8
//        String fixedText = new String(corruptedText.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
//
//        String[] list = new String[]{
//                "Nguyá»n HoÃ ng Linh",
//                "Nguyá»n Thá» Nhung",
//                "Nguyá»n Thá» Há»ng Háº¡nh",
//                "Mai Tuáº¥n Äáº¡i Há»ng",
//                "Nguyá»n VÄn DÅ©ng",
//                "Nguyá»n Há»ng DÅ©ng",
//                "Liá»u Nhá»¯ Hiá»n",
//                "Liá»u ThÃ£ Táº§n",
//                "HoÃ ng Nháº­t HÅ©",
//                "Há»ng HoÃ ng VÆ°á»£ng",
//                "Há»ng ThÃ nh VÆ°á»£ng",
//                "VÆ°á»£ng HoÃ ng Linh",
//                "VÆ°á»£ng Nháº­t Äá»©c",
//                "VÆ°á»£ng Nháº­t TÃ¢n",
//                "VÆ°á»£ng Viáº¿t Tuáº¥n",
//                "VÆ°á»£ng HÆ°á»ng Ban",
//                "Nháº¥t ChÃ­ ThÃ nh",
//                "HoÃ ng Nháº­t Hoa",
//                "Mai HÆ°á»ng Äáº¡i Thá»§y",
//                "HoÃ ng VÄn Tháº£o",
//                "CÃ´ng Thanh Mai",
//                "Cao BÃ¡ QuÃ¡t",
//                "BÃ¡o Biá»n ThÃ nh",
//                "Thiá»n ChÃ­ áº¨n",
//                "Há»ng HÃ i Nhá»",
//                "Há»ng VÆ°á»£ng Nháº¥t",
//                "VÆ°Æ¡ng Tuáº¥n Kiá»t",
//                "BÃ¹i Tuáº¥n TÃº",
//                "BÃ¹i Tuáº¥n TÃ¹ng",
//                "BÃ¹i Tuáº¥n ThÃ nh"};
//
//        for(int i = 0; i < list.length; i++) {
//
//            String f = fixTextDynamically(list[i]);
//            System.out.println(f);
//        }
//
//        // Print the fixed text
//        System.out.println("Fixed Text: " + fixedText);
        System.out.println("Default Charset: " + java.nio.charset.Charset.defaultCharset());

        return "TestSendData";
    }

    @PostMapping(value = "/Test", produces = "application/x-www-form-urlencoded;charset=UTF-8")
    public String postTestData(@ModelAttribute("user") User user)  {

        // Print the byte array of the input name in UTF-8 encoding
        System.out.println(Arrays.toString(user.getName().getBytes(StandardCharsets.UTF_8)));

        // Convert the byte array back to the string
//        String decodedText = new String(user.getName().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String text = user.getName();
//        formatConfig.format_ISO_8859_1_to_UTF_8().setValue(text);
        String decodeText = formatConfig.decodeValue(text);


        System.out.println("Mã hóa: " + decodeText);  // Should print the original value of user.getName()
        System.out.println("Default Charset: " + java.nio.charset.Charset.defaultCharset());

        return "TestSendData";
    }

    @GetMapping("/read")
    public String getRead(Model model) throws JSQLParserException {


//        System.out.println(adminReadUserAccountService.getUsersAccount());
//
//        for (UserAccount userAccounts : adminReadUserAccountService.getUsersAccount()) {
//            System.out.println(userAccounts.getUsername());
//            System.out.println(userAccounts.getEnable());
//            System.out.println(userAccounts.getPassword());
//        }

        List<UserAccount> accounts = adminReadUserAccountService.getUsersAccount();
        System.out.println("Fetched Users: " + accounts);


        model.addAttribute("roleUser", userInformationService.fetchUserRole());
        model.addAttribute("DepartmentKeyAndValue", fetchDepartment.choiceDepartment());
        model.addAttribute("OfficeKeyAndValue", fetchOffice.choiceOffices());
        model.addAttribute("userAccount", accounts);
        model.addAttribute("allUserInformation", adminInformationService.getAllUserInformation());
        model.addAttribute("departmentWork", fetchDepartment.fetchFullDepartmentWork());


        return "admin/build_account/read_user_account";

//        return "TestSendData";
    }

    @GetMapping(value ="/inf", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserEducation getFullInfUser() throws SQLException {
//        return adminInformationService.getFullUserAndAccount();

//        return ResponseEntity.ok(adminInformationService.getFullUserAndAccount()).getBody();
//        return ResponseEntity.ok(adminInformationService.getAllDepartmentWork()).getBody();
//        return ResponseEntity.ok(adminInformationService.getFullPrivateInformationUser()).getBody();
//        return ResponseEntity.ok(adminInformationService.getMultipleInfUser()).getBody();
//        return ResponseEntity.ok(adminInformationService.getUserEducationById(1)).getBody();
//        return ResponseEntity.ok(fetchDepartment.getDepartmentByID(1)).getBody();
//        return ResponseEntity.ok(fetchDepartment.departmentSet()).getBody();
//        return ResponseEntity.ok(fetchDepartment.fetchFullDepartmentWork()).getBody();
//        return ResponseEntity.ok(adminUpdateUserAccountService.findUserAccountFullInformation(1)).getBody();
        return ResponseEntity.ok(updateUserAccountDao.findUserEducationById(1,4)).getBody();
//        return ResponseEntity.ok(adminInformationService.getAllUserInformation()).getBody();

    }


}
