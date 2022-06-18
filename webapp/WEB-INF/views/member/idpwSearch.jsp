<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID/PW SEARCH</title>
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/k/login.js"></script>
</head>
<style type="text/css">
	.main {
	max-width :900px;
	}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('input[type="radio"]').click(function(){
	    var ckck = $(this).val();
	    if(ckck == 'idsear'){
	    	$('#ids').css('display', 'block');
	    	$('#pws').css('display', 'none');
	    } else if(ckck == 'pwsear'){
	    	$('#ids').css('display', 'none');
   		 	$('#pws').css('display', 'block');
	    }
	});
	$('#idexit').click(function(){
		$('#srchIdResult').css('display', 'none');
	});
	$('#pwexit').click(function(){
		$('#srchPwResult').css('display', 'none');
	});
	
});
</script>
<body>
	<div class="w3-center w3-content w3-margin-top">
		<div class="s8" id="select">
			<div>
				<input type="radio" name="select" id="idsel" class="w3-radio" value="idsear"> <label for="idsel"> ID 찾기</label>
				<input type="radio" name="select" id="pwsel" class="w3-radio" value="pwsear"> <label for="pwsel"> PW 찾기</label>
			</div>
		</div>	
		<!-- 아이디 찾기 -->
		<div id="ids" style="display: none;">
			<div class="w3-show-inline-block w3-margin-top w3-margin-bottom main" >
				<h1 class="w3-text-indigo w3-padding w3-border-bottom"><b>ID 찾기</b></h1>
				<form method="POST" action="" class="w3-col w3-padding w3-margin-top w3-border" id="frm" name="frm">
					<div class="w3-col w3-margin-top ">
						<label for="name" class="w3-col s2 w3-right-align w3-text-grey w3-large">이    름 : </label>
						<div class="w3-col m9 pdl10" >
							<input type="text" name="name" id="name" placeholder="이름을 입력하세요" class="w3-margin-left w3-col w3-input w3-border-bottom">			
						</div>
					</div>
					<div class="w3-col w3-margin-top w3-margin-bottom">
						<label for="tel" class="w3-col m2 w3-right-align w3-text-grey w3-large">전화번호 : </label>
						<div class="w3-col m9 pdl10" >
								<input type="text" name="tel" id="tel" placeholder="전화번호를 입력하세요" class="w3-margin-left w3-col w3-input w3-border-bottom">			
						</div>
					</div>
				</form>
				<div class="w3-col w3-margin-top">
					<div class="w3-button w3-indigo w3-large w3-text-white w3-hover-grey" id="idck">Search</div>
				</div>
			</div>
		</div>
	<!-- id 찾았을때 보이는 모달창 -->
	<div class="w3-modal" id="srchIdResult">
		<div class="w3-modal-content w3-animate-top w3-card-4">
		 	<header class="w3-text-white w3-indigo w3-padding w3-border-bottom w3-large">아이디 찾기
		 		 <span id="idexit" class="w3-button w3-display-topright">&times;</span>
		 	</header>
		 	<div class="w3-container">
				<p class="w3-center">회원가입시 사용한 아이디는 </p>
				<b><p class="w3-cell" id ="serid" ></p></b>
				<p class="w3-center"> 입니다.</p>
			</div>
		</div>
	</div>
		<!-- 비번 찾기 -->
		<div id="pws" style="display: none;">
			<div class="w3-show-inline-block w3-margin-top w3-margin-bottom main " >
				<h1 class="w3-text-indigo w3-padding w3-border-bottom"><b>PW 찾기</b></h1>
				<form method="POST" action="" class="w3-col w3-padding w3-margin-top w3-margin-bottom w3-border " id="frm" name="frm">
					<div class="w3-col w3-margin-top">
						<label for="id" class="w3-col s2 w3-right-align w3-text-grey w3-large">I D : </label>
						<div class="w3-col m9 pdl10" >
							<input type="text" name="id" id="id" placeholder="아이디를 입력하세요" class="w3-margin-left w3-col w3-input w3-border-bottom">			
						</div>
					</div>
					<div class="w3-col w3-margin-top w3-margin-bottom">
						<label for="mail" class="w3-col m2 w3-right-align w3-text-grey w3-large ">이 메 일 : </label>
						<div class="w3-col m9 pdl10" >
								<input type="text" name="mail" id="mail" placeholder="이메일을 입력하세요" class="w3-margin-left w3-col w3-input w3-border-bottom">			
						</div>
					</div>
				</form>
				<div class="w3-col w3-margin-top">
					<div class="w3-button w3-indigo w3-text-white w3-large w3-hover-grey" id="pwck">Search</div>
				</div>
			</div>
		</div>

	<!-- 비밀번호 찾았을때 보이는 모달창 -->
	<div class="w3-modal" id="srchPwResult">
		<div class="w3-modal-content w3-animate-top w3-card-4">
		 	<header class="w3-text-white w3-indigo w3-padding w3-border-bottom w3-large">비밀번호 찾기
		 	 	<span id="pwexit" class="w3-button w3-display-topright">&times;</span>
		 	</header>
		 	<div id="modal" class="w3-container">
				<p class="w3-center">회원가입시 사용한 비밀번호는 </p>
				<b><p class="w3-cell" id ="serpw" ></p></b>
				<p class="w3-center"> 입니다.</p>
			</div>
		</div>
	</div>
	</div>
</body>
</html>