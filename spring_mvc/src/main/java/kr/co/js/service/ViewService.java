package kr.co.js.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewService {
	//파일 목록을 가져오는 서비스 메서드
	public List<String> fileview(HttpServletRequest request,HttpServletResponse response  );

	//엑셀 파일 내용을 읽어서 리턴하는 메서드
	public List<Map<String, Object>> excelread(HttpServletRequest request,HttpServletResponse response  );

}
