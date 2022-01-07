window.addEventListener("load", function(event){
	//DOM 객체 찾아오기
	var joinform = document.getElementById("joinform");
	
	var email = document.getElementById("email");
	var pw = document.getElementById("pw");
	var pw1 = document.getElementById("pw1");
	var nickname = document.getElementById("nickname");
	var image = document.getElementById("image");
	
	var msg = document.getElementById("msg");
	var emailmsg = document.getElementById("emailmsg");
	var pwmsg = document.getElementById("pwmsg");
	var nicknamemsg = document.getElementById("nicknamemsg");
	
	var joinbtn = document.getElementById("joinbtn");
	var mainbtn = document.getElementById("mainbtn");
	var loginbtn = document.getElementById("loginbtn");
	
 	mainbtn.addEventListener("click", function(event){
 		location.href="../";
	});
	
	loginbtn.addEventListener("click", function(event){
 		location.href="login";
	});
	
	image.addEventListener("change", function(event) {
		if (this.files && this.files[0]) {
			var filename = this.files[0].name;
			var reader = new FileReader();
			reader.addEventListener("load", function(e) {
				img.src = e.target.result;
			});
			reader.readAsDataURL(this.files[0]);
		}
	})
	
	//email 란에서 포커스가 떠나면 중복검사를 수행
	email.addEventListener('focusout', function(e){
	//ajax 요청할때 URL 과 객체 생성 
	var url = "emailcheck?email=" + email.value;
	var request = new XMLHttpRequest();
	
	//요청 생성
	request.open("get",url, true);
	
	//요청전송
	request.send('');
	
	//ajax 요청 응답이 오면 
	request.addEventListener('load',function show(e){
	var data = JSON.parse(request.responseText);
	if(data.result == true){
	emailmsg.innerHTML = '사용 가능한 이메일';
	emailmsg.style.color = 'Blue';
	}else{
	emailmsg.innerHTML = '사용 불가능한 이메일';
	emailmsg.style.color = 'Red';
	}
	})
	});
	
		//nickname 란에서 포커스가 떠나면 중복검사를 수행
	nickname.addEventListener('focusout', function(e){
	//ajax 요청할때 URL 과 객체 생성 
	var url = "nicknamecheck?email=" + nickname.value;
	var request = new XMLHttpRequest();
	
	//요청 생성
	request.open("get",url, true);
	
	//요청전송
	request.send('');
	
	//ajax 요청 응답이 오면 
	request.addEventListener('load',function show(e){
	var data = JSON.parse(request.responseText);
	if(data.result == true){
	nicknamemsg.innerHTML = '사용 가능한 닉네임';
	nicknamemsg.style.color = 'Blue';
	}else{
    nicknamemsg.innerHTML = '사용 불가능한 닉네임';
	nicknamemsg.style.color = 'Red';
	}
	})
	});
		
		

     // 회원가입 누르면 
	joinbtn.addEventListener("click", function(event){
	//회원가입 여부를 저장하기 위한 변수 
		var flag = false;
		emailmsg.innerHTML = ""
		pwmsg.innerHTML = ""
		nicknamemsg.innerHTML = ""
		
		if (email.value.trim().length < 1) {
			emailmsg.innerHTML = '이메일은 필수 입력입니다.<br/>';
			flag = true;
		} else {
			var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			alert(emailRegExp.test(email.value.trim()));
			if (!emailRegExp.test(email.value.trim())) {
					emailmsg.innerHTML = '잘못된 이메일 형식입니다.<br/>';
					flag=true;
			}
		}

		if (pw.value.trim().length < 1) {
			pwmsg.innerHTML += '비밀번호는 필수 입력입니다.<br/>';
			flag = true;
		} else {
			var pwRegExp = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$/;
			if (!pwRegExp.test(pw.value.trim())) {
					pwmsg.innerHTML = '비밀번호는 숫자와 영문 대소문자 그리고 특수문자가 포함되어야 합니다.<br/>';
					flag = true;
			} else {
				if (pw.value.trim() !== pw1.value.trim()) {
					pwmsg.innerHTML += '2개의 비밀번호는 같아야 합니다.<br/>';
					flag = true;
				}
			}
		}

		if (nickname.value.trim().length < 1) {
			nicknamemsg.innerHTML += '별명은 필수 입력입니다.<br/>';
			flag = true;
		} else {
			var nicknameRegExp = /^[a-zA-z가-힣0-9]{2,5}$/;
			if (!nicknameRegExp.test(nickname.value.trim())) {
				nicknamemsg.innerHTML = '닉네임은 영문 한글 숫자로 2자 이상 5자 이하이어야 합니다.<br/>';
				flag = true;
			}
		}
		
		
		if (flag == true) {
			return;
			event.preventDefault();
		}
		
		var url="join";
	  	var request=new XMLHttpRequest();
		  	
		request.open("post", url, true);
		var formdata = new FormData(joinform);

		request.send(formdata);
		request.addEventListener('load', function show(e){
			 var map = JSON.parse(e.target.responseText);
			 alert(map.result);
			 if(map.result == true){
			 	location.href = "../";
			 }else{
			 	if(map.emailcheck == false){
			 		emailmsg.innerHTML = "사용 불 가능한 이메일입니다.";
			 		emailmsg.style.color = "Red";
			 	}else{
			 		emailmsg.innerHTML = "사용 가능한 이메일입니다.";
			 		emailmsg.style.color = "Blue";
			 	}
			 	if(map.nicknamecheck == false){
			 		nicknamemsg.innerHTML = "사용 불 가능한 닉네임입니다.";
			 		nicknamemsg.style.color = "Red";
			 	}else{
			 		nicknamemsg.innerHTML = "사용 가능한 닉네임입니다.";
			 		nicknamemsg.style.color = "Blue";
			 	}
			 }
		  })
	});
});
	