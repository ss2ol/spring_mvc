<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<!-- css링크 설정 -->
<link rel="stylesheet" href="./css/style.css" />


</head>
<body>
	<ul>
		<li><a href="hello" class="menu">처음 만들어보는 요청</a>
		<li><a href="/detail/1229" class="menu">상세보기</a>
		<li><a href="/parameter" class="menu">파라미터 입력</a>
		<!--  -->
		<li><a href="/forwarding" class="menu">forwarding - 데이터 전달</a><br />
		<li><a href="/redirect" class="menu">redirect - 데이터 전달 </a><br />
		<!--  -->
		<li><a href="fileview" class="menu">파일 목록 보기</a>
		<li><a href="excel.xls" class="menu">엑셀 다운로드</a>
		<li><a href="excleread" class="menu">엑셀 읽기</a>
		<li><a href="item.pdf" class="menu">PDF 출력</a>
		<li><a href="item.json" class="menu">JSON 출력</a>
		<li><a href="item.csv" class="menu">텍스트 출력</a>
		<li><a href="itemrest.json" class="menu">RESTController-JSON 출력</a>
		<!--  -->
		<li><a href="#" class="menu" id="ajax">ajax 요청(JSON)</a>
		<li><a href="#" class="menu" id="ajaxxml">ajax 요청(XML)</a>
		<!--  -->
		<li><a href="exception" class="menu" >예외 발생</a>
		<!--  -->
		<li><a href="message" class="menu" >스프링 메세지 출력</a>
		<!--  -->
		<li><a href="fileupload" class="menu" >파일 업로드</a>
		
		<fieldset>
		<legend>회원관리</legend>
		<li><a href="/user/join" class="menu" >회원가입</a>
		</fieldset>
		
		
		
		
	</ul>
	<div id="disp"></div>

	<div align="center" class="bady">
		<h2>상품 목록</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="80">상품아이디</th>
				<th align="center" width="320">상품이름</th>
				<th align="center" width="100">상품가격</th>
			</tr>
			<!-- list애 데이터가 없는 경우 -->
			<c:if test="${fn:length(list) ==0 }">
				<tr>
					<td colspan="3">데이터가 없습니다.</td>
				</tr>
			</c:if>

			<!-- list애 데이터가 있는 경우 -->
			<c:if test="${fn:length(list) !=0 }">
				<c:forEach var="item" items="${list}">
					<tr class="recode">
						<td align="center">${item.itemid}</td>
						<td align="left">&nbsp; <!-- 파라미터를 이용해서 데이터를 전달하는 방식 --> <!--
<a href="detail.html?itemid=${item.itemid}">${item.itemname}</a></td>
  --> <!-- URL를 이용해서 데이터를 전달하는 방식 --> <a
							href="/detail.html/${item.itemid}">${item.itemname}</a></td>


						<td align="right">${item.price}원&nbsp;</td>
					</tr>

				</c:forEach>
			</c:if>


		</table>

	</div>

</body>

<script>
	window.addEventListener("load", function() {
		//DOM 객체 찾아오기
		var ajax = document.getElementById("ajax");
		var disp = document.getElementById("disp");
		//alert(ajax);
		//alert(disp);

		//클릭 이벤트 처리
		ajax.addEventListener("click", function(e) {
			//ajax 요청 객체 생성
			var request = new XMLHttpRequest();
			//요청 생성
			request.open('get', '/itemrest.json')
			//요청 전송
			request.send('');
			//응답이 오면 수행 
			request.addEventListener('load', function(e) {
				//alert(request.responseText);

				//json 데이터 파싱
				var list = JSON.parse(request.responseText);
				var output = '';
				for (i in list) {
					//alert(list[i]);
					var item = list[i];

					output += "<h3>" + item.itemname + "</h3>";
					output += "<p>" + item.description + "</p>";

				}
				disp.innerHTML = output;

			});
		});

		//xml 요청
		var ajaxxml = document.getElementById("ajaxxml");
		ajaxxml.addEventListener('click', function(e) {
			//alert("xml 클릭");
			//ajax 요청 객체 생성
			var request = new XMLHttpRequest();
			//요청 생성
			request.open('get', '/item.xml')
			//요청 전송
			request.send('');
			//응답이 오면 수행 
			request.addEventListener('load', function(e) {
				var list = request.responseXML;
				//alert(list);

				var itemnames = list.getElementsByTagName("itemname");
				var descriptions = list.getElementsByTagName("description");

				var output = '';
				for (var i = 0; i < itemnames.length; i = i + 1) {
					var itemname = itemnames[i].childNodes[0].nodeValue;
					//alert(itemname);
					var description = descriptions[i].childNodes[0].nodeValue;

					output += '<h3>' + itemname + '</h3>';
					output += '<p>' + description + '</p>';

				}
				disp.innerHTML = output;

			});
		})
	});
</script>









</html>