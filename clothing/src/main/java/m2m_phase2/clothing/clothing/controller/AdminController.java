package m2m_phase2.clothing.clothing.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import m2m_phase2.clothing.clothing.entity.Account;
import m2m_phase2.clothing.clothing.entity.Userinfo;
import m2m_phase2.clothing.clothing.service.impl.AccountServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.ProductServiceImpl;
import m2m_phase2.clothing.clothing.service.impl.UserServiceImpl;
import m2m_phase2.clothing.clothing.utils.PasswordEncoderUtil;

@Controller
public class AdminController {
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	@Autowired
	private HttpSession session;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private ProductServiceImpl productServiceImpl;

	@GetMapping("/admin/login")
	public String adminLogin(Model model) {

		Account account = new Account();
		model.addAttribute("accadmin", account);
		model.addAttribute("authorities", false);

		return "Front_End/pages/sign-in-admin";
	}

	@PostMapping("/admin/loginPost")
	public String adminLoginPost(@ModelAttribute("accadmin") Account account, Model model, HttpServletRequest request) {

		Account accLogin = accountServiceImpl.findByemail(account.getEmail());

		String email = (String) accLogin.getEmail();
		String pass = (String) accLogin.getHashedPassword();
//        System.out.println(accLogin.isAdmin());

		if ((email.equalsIgnoreCase(accLogin.getEmail()))
				&& (PasswordEncoderUtil.verifyPassword(account.getHashedPassword(), pass) == true)) {
			if (accLogin.isAdmin() == true) {
				session.setAttribute("admin", accLogin);
				Map<Account, Userinfo> map = userService.getAll();
				model.addAttribute("listUser", map);				
				return "Front_End/pages/User(Management)";
			} else {
				model.addAttribute("authorities", true);
			}
		}
		return "Front_End/pages/sign-in-admin";
	}

	@GetMapping("/admin/user-management")
	public String toUserManagement(Model model) {

		if (session.getAttribute("admin") == null)
			return "Front_End/pages/sign-in";
		Map<Account, Userinfo> map = userService.getAll();
		Set<Account> set = map.keySet();
		
		model.addAttribute("listAccount", set);

		return "Front_End/pages/User(Management)";
	}
}