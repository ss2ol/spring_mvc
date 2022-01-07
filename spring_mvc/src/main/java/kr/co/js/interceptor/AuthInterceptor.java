package kr.co.js.interceptor;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.js.domain.SpringUser;


@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		SpringUser springUser = (SpringUser)request.getSession().getAttribute("LOGIN");

		if(springUser == null) {
			try {
				
				String uri = request.getRequestURI();
				
				String query = request.getQueryString();
				if(query == null || query.equals("null")) {
					query = "";
				}else {
					query = "?" + query;
				}
				
				if(request.getMethod().equals("GET")){
					request.getSession().setAttribute("dest",uri + query);
				}
				response.sendRedirect("/interceptor/login");

			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			return false;
		}
		return true;

	}

}

