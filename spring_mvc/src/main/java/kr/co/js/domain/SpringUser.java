package kr.co.js.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class SpringUser {
	private String email;
	private String pw;
	private String nickname;
	private String image;
	private Date regdate;
	private Date logindate;
	private int emailauth;
	
	
	
	
	
}




