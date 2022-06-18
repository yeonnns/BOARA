<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>댓글 목록 페이지</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/user.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/p/reboard.js"></script>
<script type="text/javascript" src="/boara/resources/js/main.js"></script>
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
<!-- #############################여기서부터 아래 표시한 곳까지 모든 페이지에서 동일. -->
<!-- <script type="text/javascript" src="/boara/resources/js/main.js"></script> 모든 페이지에 복붙해주세요. -->
   <!-- Navbar -->
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
   
   <!-- Header -->
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
<!-- #################여기까진 모든 페이지에서 동일. 복붙해주세요 -->  
   
   <!-- First Grid -->
   <!-- 페이지 본문 -->
   <div class="w3-row-padding w3-padding-64 w3-container" style="height: 700px; margin: 0;">
   		<button class="w3-center w3-indigo w3-hover-grey" id="wwwbtn" style="border: none; margin-left: 880px; margin-bottom: 20px;">새댓글작성</button>
   		<div style="width: 800px; margin: 0 auto;">
   		
<c:forEach var="data" items="${LIST}">
			<div class="w3-col w3-center" style="width: 700px; height: 160px; padding-left: ${data.step * 70}px;">
				<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding">
					<div class="w3-col box120 pdAll10 w3-border-right">
						<img src="/boara/resources/img/avatar/img_avatar4.png" class="inblock avtBox100 w3-border w3-border-grey">
					</div>
					<div class="w3-rest w3-padding">
						<div class="w3-col w3-border-bottom" id="${data.mno}">
							<span class="memberid" style="float: left;">${data.id}</span>
							<span style="float: right;">${data.sdate}</span>
						</div>
						<div class="w3-col w3-margin-top" style="height: 50px; border: 1px solid grey">
<c:if test="${data.isshow eq 'Y'}">
							<span class="w3-col w3-padding ft12" style="float: left; text-align: left;">${data.body}</span>
</c:if>
<c:if test="${data.isshow eq 'S'}">
							<span class="w3-col w3-padding ft12 spoiler" style="float: left; color: red;" id="${data.isshow}">스포일러 댓글입니다. 확인하고 싶으면 본문을 클릭해주세요.</span>
							<span class="w3-col w3-padding ft12 sbody" style="float: left; text-align: left; display:none;">${data.body}</span>
</c:if>						
						</div>
							<div class="w3-col w3-twothird w3-right" id="${data.rno}" style="height: 15px;">
								<div class="w3-col w3-button w3-small w70 w3-right listbutton">댓글</div>
			<c:if test="${SID eq data.id}">
								<div class="w3-col w3-button w3-small w70 w3-right listbutton" id="editbtn">수정</div>
								<div class="w3-col w3-button w3-small w70 w3-right listbutton" id="delbtn">삭제</div>
			</c:if>
							</div>
					</div>
				</div>
			</div>
</c:forEach>
			
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
   </div>
   
   	<!-- 댓글 삭제 메세지 출력 모달창 시작 -->
<c:if test="${not empty MSG}">
	<div id="modal" class="w3-modal" style="display:block;">
	    <div class="w3-modal-content mxw650 w3-animate-top w3-card-4">
	      <header class="w3-container w3-blue"> 
	        <span onclick="document.getElementById('modal').style.display='none'" 
	        class="w3-button w3-display-topright">&times;</span>
	        <h2>상상이 현실이 된다, 보아라!</h2>
	      </header>
	      <div class="w3-container w3-center">
	        <h4>${MSG}</h4>
	      </div>
	    </div>
 	</div>
</c:if>
	<!-- 댓글 삭제 메세치 출력 모달창 끝 -->
   
   <!-- 데이터 전송용 form 태그 -->
	<form method="POST" action="/boara/reboard/reboardList.boa" id="frm" name="frm">
		<input type="hidden" id="nowPage" name="nowPage" value="${PAGE.nowPage}">
		<input type="hidden" id="rno" name="rno">
		<input type="hidden" id="uprno" name="uprno">
		<input type="hidden" id="bno" name="bno" value="${BNO}">
		<input type="hidden" id="lid" name="lid">
	</form>
   
   <!-- Footer -->
   <div class="w3-row-padding w3-padding-64 w3-container" style="height: 200px; margin: 0;">
	   <footer class="w3-container w3-padding-64 w3-center w3-opacity">
	   		<p>(주)보아라</p>
	   </footer>
   </div>
</body>
</html>