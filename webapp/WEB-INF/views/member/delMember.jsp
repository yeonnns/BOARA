<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DELETE PAGE</title>
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/k/del.js"></script>
<style type="text/css">
	.main {
	max-width :900px;
	}
	#repwmsg, #delbox{
	display: none;
	}
</style>
</head>
<body>
	<div class="w3-content w3-center main">
		<h1 class="w3-text-indigo w3-padding w3-border-bottom "><b>${SID} 회원탈퇴</b></h1>
		<form method="POST" action="" name="frm" id="frm" class="w3-col w3-margin-top w3-border w3-margin-bottom w3-text-grey w3-padding">
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
		</form>
		<div class="w3-content w3-center main">
			<h4 class="w3-margin-bottom w3-center ">
			탈퇴 후에는 ${SID} 로 다시 가입할 수 없으며 아이디와 데이터는 복구 할 수 없습니다.
			<br>
			게시판형 서비스에 남아있는 게시글은 탈퇴 후 삭제 할 수 없습니다.
			</h4>
		</div>
		<div >
			<input type="radio" name="delete" id="delete" class="w3-radio" value="delete"> <label for="delete"> <b class="w3-large">안내 사항을 모두 확인하였으며, 이에 동의합니다.</b></label>
		</div>
		<div class="w3-col w3-margin-top" id="delbox">
			<div class="w3-button w3-indigo w3-large w3-text-white w3-hover-grey" id="dbtn">탈퇴하기</div>
		</div>
		
	</div>
</body>
</html>