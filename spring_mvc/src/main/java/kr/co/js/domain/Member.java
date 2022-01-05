package kr.co.js.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Member {
	//이메일 형식인지 검사
	//@Email
	//정규식 이용
	@Pattern(regexp="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(?:[a-zA-Z]{2,6})$", message="이메일 형식과 맞지 않음")
	//필수
	@NotNull
	private String email;
	@NotNull
	//최소사이즈 확인
	@Size(min=5)
	private String pw;
	private String loginType;

}
