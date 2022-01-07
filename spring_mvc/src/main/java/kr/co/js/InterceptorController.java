package kr.co.js;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.js.domain.SpringUser;
import kr.co.js.service.InterceptorUserService;

@Controller
public class InterceptorController {
	@GetMapping("/interceptor/login")
	public String login() {
		return "interceptor/login";
	}
	
	@GetMapping("/interceptor/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/board/boardlist")
	public String boardlist() {
		return "board/boardlist";
	}
	
	@GetMapping("/board/boardwrite")
	public String boardwrite() {
		return "board/boardwrite";
	}
	
	
	@Autowired
	private InterceptorUserService interceptorUserService;
	
	
	@PostMapping("/interceptor/login")
	public void login(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		SpringUser springUser= interceptorUserService.login(request, response);
		session.setAttribute("LOGIN", springUser);
		
		
	}

}
