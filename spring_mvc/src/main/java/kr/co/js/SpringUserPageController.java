package kr.co.js;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.js.service.SpringUserService;

@Controller
public class SpringUserPageController {
	@Autowired
	private SpringUserService springUserSerivce;
	
	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
	

}
