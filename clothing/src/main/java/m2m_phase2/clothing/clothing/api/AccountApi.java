package m2m_phase2.clothing.clothing.api;

import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.constant.AccountEnum;
import m2m_phase2.clothing.clothing.data.dto.AccountDto;
import m2m_phase2.clothing.clothing.data.entity.Account;
import m2m_phase2.clothing.clothing.service.AccountService;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class AccountApi {

    @Autowired
    AccountServiceImpl accountImpl;

    @Autowired
    AccountService account;

    @GetMapping("/getAccountByUsernameAndEmail")
    public Account findByUsernameAndEmail(@RequestParam("username") String username, @RequestParam("email") String email) {
        return accountImpl.findByUsernameAndEmail(username, email);
    }

    @PostMapping("/api-admin/postCreateAccount")
    public ResponseEntity<?> doPostCreateAccount(@RequestBody AccountDto accountDto) {
        String insertStatus;
        try {
            insertStatus = accountImpl.createAccount(accountDto);
        } catch (SQLException | NullPointerException e) {
            insertStatus = AccountEnum.error.getValue();
        }
        return ResponseEntity.ok(insertStatus);
    }
    @GetMapping("/api-public/user/checkLoginStatus")
    @ResponseBody
    public boolean isLoggedIn(HttpSession session) {
        // Lấy thông tin người dùng từ session
        Object loggedInUser = session.getAttribute("loggedInUser");

        // Kiểm tra nếu thông tin người dùng không null và là một chuỗi không rỗng
        return loggedInUser instanceof String && !((String) loggedInUser).isEmpty();
    }
//    @GetMapping("/getUserByUsernameAndEmail")
//    public ResponseEntity<?> doGetUserByUsernameAndEmail(UserDto userDto) {
//        UserM user;
//        try {
//            user = account.getUserByUsernameAndEmail(userDto);
//        } catch (SQLException e) {
//            System.out.println("Call API Failed: /api-public/users/getUserByUsernameAndEmail");
//            throw new RuntimeException(e);
//        }
//        return ResponseEntity.ok(user);
//    }

}
