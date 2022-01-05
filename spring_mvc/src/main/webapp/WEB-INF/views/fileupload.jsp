<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!--spring form tag 를 사용하기 위한 설정  -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>

	<form action="fileuploadrequest" method="post"
		enctype="multipart/form-data"> 
	이메일:<input type="text" name="email" /><br />
	파일:<input type="file" name="report" /><br />
	<input type="submit" value="전송" /> </form>

	<form action="fileuploadparam" method="post"
		enctype="multipart/form-data"> 
	이메일:<input type="text" name="email" /><br />
	파일:<input type="file" name="report" /><br />
	<input type="submit" value="전송" /> </form>

	<form action="fileuploadcommand" method="post"
		enctype="multipart/form-data"> 
	이메일:<input type="text" name="email" /><br />
	파일:<input type="file" name="report" /><br />
	<input type="submit" value="전송" /> 
	
	</form>
</body>
</html>