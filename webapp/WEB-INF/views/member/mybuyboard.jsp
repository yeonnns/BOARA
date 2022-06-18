<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>구매 게시글 리스트</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/user.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/j/member.js"></script>
<script type="text/javascript" src="/boara/resources/js/main.js"></script>
<style>
</style>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		//게시물 하나 클릭 시 해당 게시물 상세 페이지로 이동
		$('.brdList').click(function(){
			var sbno = $(this).attr('id');
			alert(sbno);
			window.top.location.href = '/boara/board/boardDetail.boa?bno=' + sbno;
			//$(top.document).attr('href',  '/boara/board/boardDetail.boa?bno=' + sbno)
		});
	});
</script>
<body class="w3-ligth-grey">
	<!-- 데이터 전송용 폼태그 -->
	<form method="POST" action="/boara/board/boardDetail.boa" id="frm" name="frm">
		<input type="hidden" name="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" name="bno" id="bno">
	</form>
	
	<div class="w3-content mxw700">
		<!-- 페이지 헤더 -->
		<header class="w3-col w3-card-4 mgb20">
			<h1 class="w3-indigo w3-center w3-padding w3-card-4 mg0">나의 구매글 리스트</h1>
		</header>
		
		<div class="w3-col w3-white w3-card-4 w3-round-large pd15">
			<div class="w3-col w3-light-grey w3-center w3-border">
				<div class="w3-col m3">
					<div class="w3-col m5 w3-border-right">글번호</div>
					<div class="w3-col m7 w3-border-right">작성자</div>
				</div>
				<div class="w3-col m4 w3-border-right">글제목</div>
				<div class="w3-col m3 w3-border-right">작성일</div>
				<div class="w3-col m1 w3-border-right">조회수</div>
			</div>
			
<c:forEach var="data" items="${LIST}">
			<div class="w3-col w3-white w3-hover-blue w3-center w3-border-bottom w3-border-left w3-border-right brdList" id="${data.bno}">
				<div class="w3-col m3">
					<div class="w3-col m5 w3-border-right">${data.bno}</div>
					<div class="w3-col m7 w3-border-right">${data.id }</div>
				</div>
				<div class="w3-col m4 w3-border-right">${data.title}</div>
				<div class="w3-col m3 w3-border-right">${data.sdate}</div>
				<div class="w3-col m1 w3-border-right"></div>
			</div>
</c:forEach>

		</div>
		
		<!-- 페이지 처리 시작 -->
		<div class="w3-center">
			<div class="w3-bar w3-border w3-round-medium w3-card w3-margin-top w3-margin-bottom">
	<c:if test="${PAGE.startPage eq 1}">
				<div class="w3-bar-item w3-light-grey">&laquo;</div>
	</c:if>
	<c:if test="${PAGE.startPage ne 1}">
				<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${PAGE.startPage - 1}">&laquo;</div>
	</c:if>
	<c:forEach var="page" begin="${PAGE.startPage}" end="${PAGE.endPage}">
			<c:if test="${page eq PAGE.nowPage}">
				<div class="w3-bar-item w3-orange">${page}</div>
			</c:if>
			<c:if test="${page ne PAGE.nowPage}">
				<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${page}">${page}</div>
			</c:if>
	</c:forEach>
			<c:if test="${PAGE.endPage eq PAGE.totalPage}">
				<div class="w3-bar-item w3-light-grey">&raquo;</div>
			</c:if>
			<c:if test="${PAGE.endPage ne PAGE.totalPage}">
				<div class="w3-bar-item w3-button w3-hover-blue pbtn" id="${PAGE.endPage + 1}">&raquo;</div>
			</c:if>
			</div>
		</div>
		<!-- 페이지 처리 태그 끝 -->
		
	</div>
</body>
</html>