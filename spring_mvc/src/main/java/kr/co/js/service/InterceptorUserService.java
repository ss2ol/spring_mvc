package kr.co.js.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.js.domain.SpringUser;

public interface InterceptorUserService {
	//로그인 처리 메서드
	public SpringUser login(HttpServletRequest request, HttpServletResponse response); 

}
