package kr.co.js.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import kr.co.js.domain.SpringUser;

@Repository
public interface SpringUserMapper {
	@Select("select email from SpringUser where email = #{email}")
	public String emailCheck (String email);

	@Select("select nickname from SpringUser where nickname = #{nickname}")
	public String nicknameCheck (String nickname);

	@Insert("insert into SpringUser(email, pw, nickname, image) values(#{email}, #{pw}, #{nickname}, #{image})")    
	public int join(SpringUser springUser);
}
