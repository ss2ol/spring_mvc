package kr.co.js.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.js.dao.SpringUserMapper;
import kr.co.js.domain.SpringUser;

@Service
public class SpringUserServiceImpl implements SpringUserService {


	@Autowired
	private SpringUserMapper springUserDao;

	@Override
	public Map<String, Object> join(MultipartHttpServletRequest requset, HttpServletResponse response) {
		//리턴할 결과를 저장한 인스턴스
		Map<String, Object> map = new HashMap<>();
		//결과 초기외
		//회원가입 성공 여부 
		map.put("result", false);
		//이메일 중복검사 결과
		map.put("emailcheck", false);
		//닉네임 중복검사 결과
		map.put("nickname", false);


		//파라미터 읽기=controller에서 읽으면 안해도 됨
		String email = requset.getParameter("email");
		String pw = requset.getParameter("pw");
		String nickname = requset.getParameter("nickname");
		//파일의 경우
		MultipartFile image = requset.getFile("image");
		//파일이 여러개 라면 getFiles로 호출하고 List로 받아야함
		//List<MultipartFile> images = requset.getFiles("image");

		//작업을 수행해야 하면 작업을 수행

		//email중복체크 수행
		String emailresult = springUserDao.emailCheck(email);
		if(emailresult == null) {
			map.put("emailcheck", true);
		}

		//nickname중복체크 수행
		String nicknameresult = springUserDao.nicknameCheck(nickname);
		if(nicknameresult == null) {
			map.put("nicknamecheck", true);
		}

		//파일 이름을 생성 - 파일 이름이 중복되지 않도록 하기 위해서 
		String originalFileName = image.getOriginalFilename();
		//변경된 이름
		String changeFileName = UUID.randomUUID().toString() + originalFileName;

		//이메일과 닉네임 중복검사르 통과한 경우 회원가입
		if(emailresult == null && nicknameresult == null) {
			//repository의 메서드를 호출해야하면 필요한 파라미터 생성
			SpringUser springUser = new SpringUser();
			springUser.setEmail(email);
			springUser.setPw(pw);
			springUser.setNickname(nickname);
			springUser.setImage(changeFileName);

			//repository 메서드 호출  - 회원가입
			int result = springUserDao.join(springUser);

			//성공하면 
			if(result >0) {
				String uploadPath = requset.getServletContext().getRealPath("/profile");

				String filePath = uploadPath + "/" + changeFileName;

				File f = new File(filePath);
				try {
					image.transferTo(f);
					map.put("result",true);
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			}


		}


		//메서드 호출 결과를 가지고 리턴할 데이터 작성 

		return map;
	}


}
