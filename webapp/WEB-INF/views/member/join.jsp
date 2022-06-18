<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JOIN PAGE</title>
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/k/join.js"></script>
<style type="text/css">
	.main {
	max-width :900px;
	}
	#idmsg, #telmsg, #repwmsg, #mailmsg {
	display: none;
	}
	
	.picbox {
	width: 200px;
	height: 200px;
	margin-left: 10px;
	overflow: hidden;
	display: inline-block;
	margin-top : 10px;
}
</style>


</head>
<body>
	<div class="w3-content w3-center main">
		<h1 class="w3-text-indigo w3-padding w3-border-bottom "><b>BOARA 회원가입</b></h1>
		<form method="POST" action="" name="frm" id="frm" encType="multipart/form-data"
									class="w3-col w3-margin-top w3-border w3-margin-bottom w3-text-grey w3-padding">
			<div class="w3-margin-top  w3-content">
				<label for="name" class="w3-col s3 w3-right-align w3-large">회원이름 : </label>
				<input type="text" name="name" id="name" class="w3-margin-bottom w3-margin-left m8 w3-col w3-input w3-border w3-round-medium"
								placeholder="이름을 입력하세요">
			</div>
			<div class="w3-margin-top w3-content">
				<label for="id" class="w3-col s3 w3-right-align w3-large">아 이 디 : </label>
				<div class="w3-col m8">
					<input type="text" name="id" id="id" class="w3-margin-bottom w3-margin-left w3-col m9 w3-input w3-border w3-round-medium"
								placeholder="아이디를 입력하세요">
					<div class="w3-margin-left w3-col m2 w3-right w3-button w3-indigo w3-text-white w3-round-medium w3-large" id="idck">check</div>
					<span class="w3-col mgb10 w3-center w3-text-green" id="idmsg">아이디 체크 처리 메세지</span>
				</div>
			</div>
			<div class="w3-margin-top w3-content">
				<label for="pw" class="w3-col s3 w3-right-align w3-large">비밀번호 : </label>
				<div class="w3-col m8">
					<input type="password" name="pw" id="pw" class="w3-margin-bottom w3-margin-left w3-col w3-input w3-border w3-round-medium"
							placeholder="비밀번호를 입력하세요">
				</div>
			</div>
			<div class="w3-margin-top w3-content">
				<label for="repw" class="w3-col s3 w3-right-align w3-large">비밀번호 확인 : </label>
				<div class="w3-col m8">
					<input type="password" id="repw" class="w3-margin-bottom w3-margin-left w3-col w3-input w3-border w3-round-medium">
					<span class="w3-col w3-text-red w3-center" id="repwmsg">비밀번호 체크 처리 메세지</span>
				</div>
			</div>
			<div class="w3-margin-top  w3-content">
				<label for="tel" class="w3-col s3 w3-right-align w3-large">전화번호 : </label>
				<div class="w3-col m8">
					<input type="text" name="tel" id="tel" class="w3-margin-bottom w3-margin-left w3-col m9 w3-input w3-border w3-round-medium"
								placeholder="전화번호를 입력하세요 (-제외)">
					<div class="w3-margin-left w3-col m2 w3-right w3-button w3-indigo w3-text-white w3-round-medium w3-large " id="telck">check</div>
					<span class="w3-col mgb10 w3-center w3-text-green w3-margin-bottom" id="telmsg">전화번호 체크 처리 메세지</span>
				</div>
			</div>		
			<div class="w3-margin-top w3-content">
				<label for="mail" class="w3-col s3 w3-right-align w3-large">회원메일 : </label>
				<div class="w3-col m8">
					<input type="text" name="mail" id="mail" class="w3-margin-bottom w3-margin-left w3-col m9 w3-input w3-border w3-round-medium"
							placeholder="메일을 입력하세요">
					<div class="w3-margin-left w3-col m2 w3-right w3-button w3-indigo w3-text-white w3-round-medium w3-large " id="mailck">check</div>
					<span class="w3-col mgb10 w3-center w3-text-green w3-margin-bottom" id="mailmsg">메일 체크 처리 메세지</span>
				</div>
			</div>
			<div class="w3-margin-top w3-content">
				<label for="file" class="w3-col s3 w3-right-align w3-large">프로필 사진 : </label>
				<div class="w3-col m8" id="filebox">
					<input type="file" id="file" name="file" class="upfile w3-input w3-border w3-col w3-margin-left w3-round-medium w3-margin-bottom">
				</div>
			</div>
			<div class="w3-margin-top w3-content">
				<label class="w3-col s3 w3-right-align w3-large">preview : </label>
				<div class="w3-col m8" >
					<div class="w3-display-container picbox " >
						<img id="img1" class="w3-display-middle" style="width: 100%; height: auto;" src="">
					</div>
				</div>
			</div>
		</form>
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-text-white w3-indigo w3-padding w3-large w3-hover-grey" id="rbtn">Reset</div> 
			<div class="w3-third w3-text-white w3-indigo w3-padding w3-large w3-hover-grey" id="hbtn">Main</div> 
			<div class="w3-third w3-text-white w3-indigo w3-padding w3-large w3-hover-grey" id="jbtn">Join</div> 
		</div>
	</div>
</body>
</html>