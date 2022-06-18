<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 컬렉션</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/user.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/c/ez.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/main.js"></script>
<script type="text/javascript" src="/boara/resources/js/c/collec.js"></script>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
img {
   width: 150px;
   height: 200px;
}
</style>
</head>
<body>
	<%-- Navbar --%>
	<div class="w3-top">
		<div class="w3-bar w3-indigo w3-card w3-left-align w3-large">
			<a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="main">메인</a>
			<a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="collection">컬렉션</a>
<c:if test="${not empty SID}">
			<a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="mwrite">글작성</a>
			<a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="mcoll">새 컬렉션</a>
</c:if>        
			<h4 style="float: right; padding-right:40px;">Boara</h4>
		</div>
	</div>
   
   <%-- Header --%>
	<header class="w3-container w3-indigo w3-center" style="padding:128px 16px">
		<h1 class="w3-margin w3-jumbo">Boara</h1>
<c:if test="${empty SID}">
		<button class="w3-button w3-black w3-padding-large w3-large w3-margin-top" id="mlogin">Login</button>
</c:if>
<c:if test="${not empty SID}">
		<button class="w3-button w3-indigo w3-padding-large w3-large w3-margin-top" id="msid">${SID}</button>     
		<button class="w3-button w3-indigo w3-padding-large w3-large w3-margin-top" id="mlogout">Logout</button>     
</c:if>
	</header>
   
   <%-- First Grid --%>
	<div class="w3-center">
		<div class="w3-row-padding w3-padding-64 w3-container w900 inline w3-margin-top">
			<div class="w3-col w3-padding w3-pale-blue">
				<form method="POST" name="frm" id="frm" class="w3-center" encType="multipart/form-data"
					action="/boara/collection/collecWriteProc.boa">
					<div class="w3-col w3-margin-bottom w3-margin-top">
						<label for="cname" class="w3-col m3 w3-padding" style="text-align:right">컬렉션 이름 : </label>
						<input type="text" class="w3-input w3-col m7" id="cname" name="cname">
					</div>
					<div class="w3-col w3-margin-bottom">
						<label for="genr" class="w3-col m3 w3-padding" style="text-align:right; height: 67.5px;">장르(선택, 최대 5개) : </label>
<c:forEach var="genr" items="${GLIST}">
						<label class="w3-col m3 left mgt10"><input type="checkbox" id="genr" name="genr" value="${genr.key}"> ${genr.value}</label>
</c:forEach>
						<input type="hidden" name="genre" id="genre">
					</div>
					<div class="w3-col w3-margin-bottom">
						<label for="descr" class="w3-col m3 w3-padding" style="text-align:right">컬렉션 설명(선택) : </label>
						<input type="text" class="w3-input w3-col m7" id="descr" name="descr">
					</div>
					<div class="w3-col w3-margin-bottom">
						<label for="thumb" class="w3-col m3 w3-padding" style="text-align:right">썸네일(선택) : </label>
						<input type="file" class="w3-input w3-col m7" id="thumb" name="thumb">
					</div>
					<div class="w3-col w3-margin-bottom">
						<label class="w3-col m3 w3-padding" style="text-align:right">썸네일 미리보기 : </label>
						<div class="w3-col m7 w3-center">
							<div class="thbox">
								<img class="thumb" id="preview" src="/boara/resources/img/noimage.jpg">
							</div>
						</div>
					</div>
					
				</form>
			</div>
			
			<%-- 생성 버튼 --%>
			<div style="text-align:right">
				<div class="genre w3-round w3-margin-top" id="wpbtn">컬렉션 생성</div>
			</div>
		</div>
	</div>
   
</body>
</html>