<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${POST.title}</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/user.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/c/ez.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/main.js"></script>
<script type="text/javascript" src="/boara/resources/js/c/board.js"></script>
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
<c:if test="${not empty POST.cname}">
		<h1 class="w3-margin w3-jumbo">${POST.cname}</h1>
</c:if>
<c:if test="${empty POST.cname}">
		<h1 class="w3-margin w3-jumbo">Boara</h1>
</c:if>
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
		<div class="w3-row-padding w3-padding-64 w3-container w700 inline">
<c:if test="${not empty POST}">
			<div class="w3-border-bottom">
				<div>
					<h6 style="color:gray; text-align:left;">[${POST.bno}]</h6>
				</div>
				<div class="w3-col">
					<div class="w3-col m1">
	<c:if test="${POST.isshow eq 'A'}">
						<div class="noti w3-round w3-left" style="margin-top:20px; margin-left: 10px;">공지</div>
	</c:if>
					</div>
					<div class="w3-col m10 w3-margin-left">
						<h1 class="w3-left" style="text-align:left; display:inline-block; marign-top:30px; word-wrap:break-word;">${POST.title}</h1>
					</div>
				</div>
	<c:if test="${not empty POST.genre}">
				<div class="left">
		<c:forEach var="genr" items="${POST.genre}">
					<div class="genre w3-round">${genr}</div>
		</c:forEach>
				</div>
	</c:if>
	<c:if test="${POST.price ne 0}">
				<h4 style="color:gray; padding-left:10px; text-align:left;">${POST.id} ${POST.price}P</h4>
	</c:if>
	<c:if test="${POST.price eq 0}">
				<h4 style="color:gray; padding-left:10px; text-align:left;">${POST.id}</h4>
	</c:if>
				<h6 style="color:gray; text-align:right;">${POST.sdate} 조회수 : ${POST.clicks}</h6>
			</div>
			<p style="text-align:left; word-wrap:break-word; padding-bottom:5px;" class="w3-border-bottom">${POST.body}</p>
</c:if>

			<div class="left">
				<div class="inline">
				<%-- 좋아요 기능 구현해야됨!!!! --%>
					<!-- <img src="/boara/resources/img/c/unlike.jpg" class="like">
					<img src="/boara/resources/img/c/liked.jpg" class="like"> -->
					<div class="genre w3-round" id="reshow">댓글 보기</div>
					<div class="genre w3-round" id="upcoll">컬렉션으로</div>
				</div>
<c:if test="${POST.id eq SID}">
				<div style="text-align:right">
					<div class="genre w3-round" id="ebtn">수정</div>
					<div class="genre w3-round" id="dbtn">삭제</div>				
				</div>
</c:if>
			</div>
		</div>
	</div>
	
	<%-- Footer --%>
	<footer class="w3-container w3-padding-64 w3-center w3-opacity w3-border-top">
		<p>(주)보아라</p>
	</footer>
	
	<%-- 데이터 전송용 폼 --%>
	<form method="POST" name="frm" id="frm" action="/boara/collection/collecList.boa">
		<input type="hidden" name="bno" id="bno" value="${POST.bno}">
		<input type="hidden" name="cno" id="cno" value="${POST.cno}">
	</form>

</body>
</html>