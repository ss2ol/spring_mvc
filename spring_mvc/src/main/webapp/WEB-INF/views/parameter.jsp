<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>parameter 입력</title>
</head>
<body>
<form action ="/parameter" method ="post">
이름:<input type ="text" name="name"/><br/>
나이:<input type ="text" name="age"/><br/>
<fieldset>
<legend>성별</legend>
<input type="radio" name="gender" value="man"/>남자
<input type="radio" name="gender" value="woman" checked="checked"/>여자
</fieldset>

<fieldset>
<select name ="job">
<option value = "student">학생</option>
<option value = "IT">IT관련</option>
<option value = "publiccofficial">공무원</option>
<option value = "etc">기타</option>
</select>
</fieldset>

<input type="submit" value="전송"/>

</form>
</body>
</html>