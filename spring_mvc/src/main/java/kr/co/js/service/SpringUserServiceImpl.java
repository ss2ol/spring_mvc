package kr.co.js.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import kr.co.adamsoft.util.CryptoUtil;
import kr.co.js.dao.SpringUserMapper;
import kr.co.js.domain.SpringUser;

@Service
public class SpringUserServiceImpl implements SpringUserService {


	@Autowired
	private SpringUserMapper springUserDao;

	@Override
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		//리턴할 결과를 저장한 인스턴스
		Map<String, Object> map = new HashMap<>();
		//결과 초기화
		//회원 가입 성공 여부
		map.put("result", false);
		//이메일 중복 검사 결과
		map.put("emailcheck", false);
		//닉네임 중복 검사 결과
		map.put("nicknamecheck", false);

		//파라미터를 읽기 - Controller에서 읽으면 하지 않아도 됨
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");

		//복호화가 가능한 암호화를 위해서 key를 새엇ㅇ
		String key = "ssol";

		//파일의 경우
		MultipartFile image = request.getFile("image");
		//파일이 여러 개 라면 getFiles를 호출하고 List로 받아야 합니다.
		//List<MultipartFile> images = request.getFiles("image");

		//작업을 수행해야 하면 작업을 수행

		//email 중복체크를 수행
		List<String> emailresult = springUserDao.emailCheck();
		boolean flag = false;
		for(String e : emailresult) {
			try {
				if(email.equals(CryptoUtil.decryptAES256(e, "ssol"))) {
					flag = true;
					break;
				}
			}catch(Exception ex) {
				System.out.println(ex.getLocalizedMessage());
			}
		}
		
		
		if(flag == false) {
			map.put("emailcheck", true);
		}

		//nickname 중복 검사 수행
		String nicknameresult = springUserDao.nicknameCheck(nickname);
		if(nicknameresult == null) {
			map.put("nicknamecheck", true);
		}

		//파일 이름을 생성 - 파일 이름이 중복되지 않도록 하기 위해서
		String changeFileName = "default.jpg";
		if(image.isEmpty() == false) {
			String originalFileName = image.getOriginalFilename();
			//변경된 이름
			changeFileName = 
					UUID.randomUUID().toString() + originalFileName;
		}

		//이메일 과 닉네임 중복검사를 통과한 경우 회원가입
		if(flag == false && nicknameresult == null) {
			//Repository 의 메서드를 호출해야 하면 필요한 파라미터를 생성
			SpringUser springUser = new SpringUser();
			try {
				//이메일을 복호화가 가능한 암호화를 이용해서 저장
				springUser.setEmail(
						CryptoUtil.encryptAES256(email, key));
				//복호화는 불가능하고 비교만 가능한 방법으로 암호화를 해서 저장
				springUser.setPw(BCrypt.hashpw(
						pw, BCrypt.gensalt()));
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			springUser.setNickname(nickname);
			springUser.setImage(changeFileName);
			//Repository 메서드를 호출 - 회원 가입
			int result = springUserDao.join(springUser);

			//성공하면 
			if(result > 0) {
				//파일 업로드
				String uploadPath = 
						request.getServletContext().getRealPath(
								"/profile");
				String filePath = uploadPath + "/" + changeFileName;
				File f = new File(filePath);
				try {
					//이미지가 있는 경우만 업로드 
					if(image.isEmpty() == false) {
						image.transferTo(f);
					}
					map.put("result", true);
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}

			}
		}

		return map;
	}

		@Override
		public Map<String, Object> emailCheck(HttpServletRequest request, HttpServletResponse response) {
			Map<String, Object> map = new HashMap<>();
			//파라미터 읽기
			String email = request.getParameter("email");

			//이메일을 전부 가져와서
			List <String> result = springUserDao.emailCheck();
			//가져온 이메일 순회
			boolean flag = false;
			for(String e : result) {
				try {
					//복호화를 해서 비교
					if(email.equals(CryptoUtil.decryptAES256(e, "ssol"))) {
						flag = true;
						break;
					}
				}catch(Exception ex) {
					System.out.println(ex.getLocalizedMessage());
				}
				
			}
			//이메일이 없는 경우는 result 에 true
			//이메일이 존재하는 경우는 result에 false
			if(flag == false) {
				map.put("result", true);
			}else {
				map.put("result", false);
			}
			return map;

		}

			@Override
			public Map<String, Object> nicknameCheck(HttpServletRequest requset, HttpServletResponse response) {
				Map<String, Object> map = new HashMap<>();
				//파라미터 읽기
				String nickname = requset.getParameter("nickname");


				//닉네임 을 전부 가져와서
				String result = springUserDao.nicknameCheck(nickname);
				
				//닉네임이 없는 경우 result 에 true
				//닉네임이 존재하는 경우 false
				if(result == null) {
					map.put("result", true);
				}else {
					map.put("result", false);
				}
				return map;

			}
			
			@Override
			public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) {
				Map<String, Object> map = new HashMap<>();
				//로그인 성공 여부를 먼저 저장
				map.put("result", false);
				
				//파라미터 읽어오기
				String email = request.getParameter("email");
				String pw = request.getParameter("pw");
				
				//로그인 하기 위한 정보를 가져오기
				List<SpringUser> list = springUserDao.login();
				
				//암호화할 때 사용했던 키
				String key = "itggangpae";
				
				try {
					for(SpringUser user : list) {
						if(email.equals(CryptoUtil.decryptAES256(
								user.getEmail(), key)) && 
								BCrypt.checkpw(pw, user.getPw())) {
							//로그인 성공
							map.put("result", true);
							//필요한 정보 저장
							map.put("email", email);
							map.put("nickname", user.getNickname());
							map.put("image", user.getImage());
							break;
						}
					}
					
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				//세션에 저장
				request.getSession().setAttribute("userinfo", map);
				
				return map;
			}

		}
