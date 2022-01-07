package kr.co.js;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.js.service.SpringUserService;


//HTML 출력을 위한 controller
@Controller
public class SpringUserPageController {
	@Autowired
	private SpringUserService springUserSerivce;
	
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	
	@GetMapping("/user/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	

}
