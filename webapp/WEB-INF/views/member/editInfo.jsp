<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>INFO EDIT</title>
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/k/edit.js"></script>
<style type="text/css">
	#repwmsg {
		display: none;
	}
	.picbox {
	width: 200px;
	height: 200px;
	overflow: hidden;
	display: inline-block;
	margin-top : 10px;
	
</style>
</head>
<body>
	<div class="w3-content w3-center main">
		<h1 class="w3-text-indigo w3-padding w3-border-bottom "><b>회원정보 수정</b></h1>
			<form method="POST" action="" name="frm" id="frm" encType="multipart/form-data"
					class="w3-col w3-margin-top  w3-margin-bottom w3-text-grey w3-padding">
				
				<input type="hidden" id="id" name="id" value="${SID}">
				<input type="hidden" id="tmail" value="${DATA.mail}">
				<input type="hidden" id="ttel" value="${DATA.tel}">
				<input type="hidden" id="tfno" name="tfno" > 
		<!-- 
		-->
			<div class="w3-margin-top  w3-content">>
				<label for="name" class="w3-col s3 w3-right-align w3-large">회원이름 : </label>
				<h4 class="w3-col s8 w3-center w3-margin-top w3-magrin-left w3-margin-bottom">${DATA.name}</h4>
			</div>
			<div class="w3-margin-top  w3-content">
				<label class="w3-col s3 w3-right-align w3-large">아 이 디 : </label>
				<h4 class="w3-col s8 w3-center w3-magrin-left w3-magrin-left w3-margin-bottom">${DATA.id}</h4>
			</div>
			<div class="w3-margin-top w3-content">
				<label for="pw" class="w3-col s3 w3-right-align w3-large">비밀번호 : </label>
				<input type="password" name="pw" id="pw" class="w3-margin-bottom w3-margin-left m8 w3-col w3-input w3-border w3-round-medium">
			</div>
			<div class="w3-margin-top  w3-content">
				<label for="repw" class="w3-col s3 w3-right-align w3-large">비밀번호 확인 : </label>
				<div>
					<input type="password" id="repw" class="w3-margin-bottom w3-margin-left m8 w3-col w3-input w3-border w3-round-medium">
					<span class="w3-col w3-text-red w3-center w3-margin-bottom" id="repwmsg"># 비밀번호가 일치하지 않습니다.</span>
				</div>
			</div>
			<div class="w3-margin-top  w3-content">
				<label for="mail" class="w3-col s3 w3-right-align w3-large">회원메일 : </label>
				<input type="text" name="mail" id="mail" class="w3-margin-bottom w3-margin-left m8 w3-col w3-input w3-border w3-round-medium" value="${DATA.mail}">
			</div>
			<div class="w3-margin-top  w3-content">
				<label for="tel" class="w3-col s3 w3-right-align w3-large">전화번호 : </label>
				<input type="text" name="tel" id="tel" class="w3-margin-bottom w3-margin-left m8 w3-col w3-input w3-border w3-round-medium" value="${DATA.tel}">
			</div>
				
	<c:forEach var="data" items="${LIST}">
	<!-- 사진 삭제 기능 -->
		<c:if test="${not empty data.savename}">
			<div class="w3-margin-top  w3-content">
				<label for="file" class="w3-col s3 w3-right-align w3-large">프로필 사진 : </label>
				<div class="w3-col m8" >
					<div class="w3-display-container picbox w3-center" >
						<img id="${data.fno}" class="w3-display-middle" style="width: 100%; height: auto;" src="/boara/resources/upload/${data.savename}">
					</div>
					<div class="w3-center w3-col w3-content">
						<div class="w3-button w3-indigo w3-center w3-show-inline-block w3-text-white w3-hover-grey w3-xlarge dbtn" style="width: 200px;" id="${data.fno}">삭제</div>
					</div>
				</div>
			</div> 
		</c:if>
		<!-- 사진 삭제 했을 시 추가 기능 -->
		<c:if test="${empty data.savename}">
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

		</c:if>
	</c:forEach>
		
		</form>	
		<div class="w3-col w3-margin-top w3-card-4">
			<div class="w3-third w3-text-white w3-indigo w3-padding w3-large w3-hover-grey w3-inline-block" id="hbtn">Main</div> 
			<div class="w3-third w3-text-white w3-indigo w3-padding w3-large w3-hover-grey" id="rbtn">Reset</div> 
			<div class="w3-third w3-text-white w3-indigo w3-padding w3-large w3-hover-grey" id="ebtn">Edit</div> 
		</div>
		<div class="w3-col w3-margin-top">
			<div class="w3-button w3-text-indigo w3-right w3-xlarge" id="dbtn">탈퇴하기</div>
		</div>
	
	</div>
</body>
</html>