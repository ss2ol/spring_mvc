package kr.co.js.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.js.domain.SpringUser;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		if (request.getMethod().equals("POST")) {
			request.getSession().removeAttribute("LOGIN");
		}
		return true; 
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) 
	throws Exception{
		if (request.getMethod().equals("POST")) {
			SpringUser springUser = (SpringUser)request.getSession().getAttribute("LOGIN");
			if(springUser != null) {
				Object dest = request.getSession().getAttribute("dest");
			
					response.sendRedirect(dest != null ? (String)dest : "/");
				
			}else {
				response.sendRedirect("/interceptor/login");
			}
		}
	}

}