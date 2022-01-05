package kr.co.js.service;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface SpringUserService {
	//회원가입을 위한 서비스
	//결과로 회원가입 성공 여부, 이메일 중복체크 결과 여부, 닉네임 중복 체크결과여부
	public Map<String,Object> join(MultipartHttpServletRequest requset, HttpServletResponse response);


}
