package kr.co.js.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import kr.co.js.domain.SpringUser;

@Repository
public interface SpringUserMapper {
	//이메일 중복 확인을 위한 SQL
	//이메일은 암호화를 했으므로 데이터 베이스에서 직접 비교 불가능해서 이메일을 리턴
	@Select("select email from SpringUser ")
	public List <String> emailCheck ();
	
    //닉네임 중복처리를 위한 SQL 
	@Select("select nickname from SpringUser where nickname = #{nickname}")
	public String nicknameCheck (String nickname);

	//회원가입을 위한 SQL
	@Insert("insert into SpringUser(email, pw, nickname, image) values(#{email}, #{pw}, #{nickname}, #{image})")    
	public int join(SpringUser springUser);
	
	//로그인 처리ㅇ를 위한 메서드 
	@Select("select email, pw, nickname, image from SpringUser ")
	public List <SpringUser> login ();
	
}
