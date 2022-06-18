<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LOGIN PAGE</title>
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/k/login.js"></script>
<style type="text/css">
	#msg {
		font-size: 20pt;
		font-weight: bold;
		color: indigo;
	}
	.main{
	max-width :900px;
	}	
</style>

</head>
<body>
	<div class="w3-content w3-center main">
		<h1 class="w3-text-indigo w3-padding w3-border-bottom"><b>Login</b></h1>
		<form method="POST" action="" class="w3-col w3-padding w3-border w3-margin-top" id="frm" name="frm">
			<div class="w3-col w3-margin-top">
				<label for="id" class="w3-col s2 w3-right-align w3-text-grey w3-large">I D : </label>
				<div class="w3-col m9 ">
					<input type="text" name="id" id="id" placeholder="아이디를 입력하세요"
							class="w3-col w3-input w3-border-bottom w3-margin-left">
				</div>
			</div>
			<div class="w3-col w3-margin-top w3-margin-bottom">
				<label for="pw" class="w3-col s2 w3-right-align w3-text-grey w3-large">P W : </label>
				<div class="w3-col m9">
					<input type="password" name="pw" id="pw" placeholder="비밀번호를 입력하세요"
							class="w3-col w3-input w3-border-bottom w3-margin-left">
				</div>
			</div>
		</form>
		<div class="w3-col w3-margin-top">
			<div class="w3-half w3-indigo w3-text-white w3-hover-grey w3-xlarge" id="hbtn">Main</div>
			<div class="w3-half w3-indigo w3-text-white w3-hover-grey w3-xlarge" id="lbtn">Login</div>
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-button w3-text-indigo w3-right w3-xlarge" id="check">ID / PW 확인</div>
			<div class="w3-button w3-text-indigo w3-right w3-xlarge" id="join">JOIN</div>
		</div>
		<div class="w3-col w3-padding w3-card-4 w3-hide"><span id="msg">${SID} 님은 이미 로그인했습니다.</span></div>
	</div>
</body>
</html>