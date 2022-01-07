package kr.co.js.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.adamsoft.util.CryptoUtil;
import kr.co.js.dao.SpringUserMapper;
import kr.co.js.domain.SpringUser;

@Service
public class InterceptorUserServiceImpl implements InterceptorUserService {

	@Autowired
	private SpringUserMapper springUserDao;

	@Override
	public SpringUser login(HttpServletRequest request, HttpServletResponse response) {
		SpringUser springUser = null;

		String email = request.getParameter("email");
		String pw = request.getParameter("userpw");
		System.out.println(email);
		System.out.println(pw);
		List<SpringUser> list = springUserDao.login();
		try {
			for(SpringUser user : list) {
				if(CryptoUtil.decryptAES256(user.getEmail(),"ssol").equals(email)&& BCrypt.checkpw(pw, user.getPw())) {
					springUser = new SpringUser();
					springUser.setEmail(email);
					springUser.setNickname(user.getNickname());
					springUser.setImage(user.getImage());
					break;
				}
			}

		}
		catch(Exception e){
			System.out.println(e.getLocalizedMessage());
		}
		return springUser;
	}


}
