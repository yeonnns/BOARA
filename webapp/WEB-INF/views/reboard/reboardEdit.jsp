<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>댓글 수정 페이지</title>
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
   <div class="w3-row-padding w3-padding-64 w3-container">
   		<div style="width: 800px; height: 200px; margin: 0 auto;">
			<div class="w3-col w3-center" style="width: 700px; height: 100%;">
				<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-padding" style="height: 200px;">
					<div class="w3-col box120 pdAll10 w3-border-right" style="height: 100%;">
						<div>댓글 수정</div>
						<img src="/boara/resources/img/avatar/img_avatar4.png" class="inblock avtBox100 w3-border w3-border-grey" style="margin-top: 20px;">
					</div>
					<div class="w3-rest w3-padding">
						<div class="w3-col w3-border-bottom">
							<span style="float: left;">${SID}</span>
							<span style="float: right;">${DATA.sdate}</span>
						</div>
						<input type="hidden" id="obody" value="${DATA.body}">
						<form method="POST" action="/boara/reboard/reboardEditProc.boa" 
											id="frm" name="frm" class="w3-col" style="margin-top:10px;">
							<input type="hidden" id="nowPage" name="nowPage" value="${param.nowPage}">
							<input type="hidden" id="bno" name="bno" value="${DATA.bno}">
							<input type="hidden" id="mno" name="mno" value="${DATA.mno}">
							<input type="hidden" id="rno" name="rno" value="${DATA.rno}">
							<textarea class="w3-col w3-padding ft12" id="body" name="body"
										style="resize: none; height:120px;">${DATA.body}</textarea>
						
						<!-- <div class="w3-col w3-margin-top" style="height: 50px;">
							<input class="w3-col w3-padding ft12"></input>
						</div> -->
						
							<div class="w3-col w3-right" id="${data.bno}" style="height: 15px;">
<c:if test="${DATA.isshow eq 'S'}">
								<label style="float:left;"><input type="checkbox" checked="checked" name="spo" id="spo" value="S"> 스포일러 포함 시 체크</label>
</c:if>
<c:if test="${DATA.isshow eq 'Y'}">
								<label style="float:left;"><input type="checkbox" name="spo" id="spo" value="S"> 스포일러 포함 시 체크</label>
</c:if>								
								<div class="w3-col w3-button w3-indigo w70 w3-right" style="padding-top:2px; width: 55px; height:20px; font-size:5pt;" id="editbtn">등록</div>
							</div>
						</form>	
					</div>
				</div>
			</div>
   		</div>
   </div>  	
   <!-- Footer -->
   <footer class="w3-container w3-padding-64 w3-center w3-opacity">
   		<p>(주)보아라</p>
   </footer>
</body>
</html>