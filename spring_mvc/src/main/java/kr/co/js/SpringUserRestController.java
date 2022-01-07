package kr.co.js;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.js.service.SpringUserService;

//데이터 제공을 위한 controller
@RestController
public class SpringUserRestController {
	@Autowired
	private SpringUserService springUserService;
	
	//회원가입 요청을 처리하는 메서드
	@PostMapping("/user/join")
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response){
		return springUserService.join(request, response);
	}
	
	//이메일 중복 검사 요청 처리 메서드
	@GetMapping("/user/emailcheck")
	public Map<String, Object> emailcheck(HttpServletRequest request, HttpServletResponse response){
		return springUserService.emailCheck(request, response);
	}
	

	//닉네임 중복 검사 요청 처리 메서드 
	@GetMapping("/user/nicknamecheck")
	public Map<String, Object> nicknamecheck(HttpServletRequest request, HttpServletResponse response){
		return springUserService.nicknameCheck(request, response);
	}
	
	//login 요청이 post 방식으로 왔을 때 처리하는 메서드를 생성
	@PostMapping("/user/login")
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response){
		return springUserService.login(request, response);
	}

	
	

}
