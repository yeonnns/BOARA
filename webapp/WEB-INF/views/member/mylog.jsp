<!-- 정준영 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>메인페이지</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/w3.css">
<link rel="stylesheet" type="text/css" href="/boara/resources/css/user.css">
<script type="text/javascript" src="/boara/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/boara/resources/js/j/member.js"></script>
<script type="text/javascript" src="/boara/resources/js/main.js"></script>
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
dt {
margin: 5px;
font-size : 20pt;
}
dd:hover{
	color:#000!important;background-color:#ccc!important;
	cursor: pointer;
}
dd {
margin: 5px;
font-size : 15pt;
font-weight: lighter ;
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
       <a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="mwrite">글 작성</a>
       <a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" id="mcoll">새 컬렉션 작성</a>
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
<c:if test="${not empty SID}">   
   <!-- mypage -->
	   	<div class="w3-container w3-padding w3-margin w3-border-indigo w3-card-4" style="padding:128px 16px">
   				<h1>${SID}님 마이페이지 입니다.</h1>
   		</div>
   	<div class="w3-container w3-padding w3-margin w3-border-indigo w3-card-4" style="padding:128px 16px">
			<div class="w3-col w3-display-container w3-border w3-margin" style="width: 250px; height: 250px;">
				<img src="/boara/resources/img/${DATA.avt}" class="avtimg250 w3-display-middle">
			</div>
			<div class="w3-rest w3-text-grey">
				<div class="w3-col "> 
				<h4><span class="w3-col m2">닉네임 : </span><span class="w3-rest" id="id">${DATA.id}</span></h4>
				</div>
				<div class="w3-col  "> 
				<h4><span class="w3-col m2">가입일 :</span><span class="w3-rest">${DATA.sdate}</span></h4>
				</div>
				<div class="w3-col "> 
				<h4><span class="w3-col m2">작성 </span><span class="w3-col m3">게시글 <a>${DATA.bcnt}</a></span><span class="w3-col m3" id="id">댓글 <a>${DATA.rcnt}</a></span>
					</h4>
				</div>
				<div class="w3-col"> 
				<h4><span class=	"w3-col m2">포인트 : </span><span class="w3-col m3 w3-margin-right" style="text-align:right;" id="id">${DATA.point}</span></h4><div class="w3-rest w3-button w3-indigo w3-hover-blue">충전</div>
				</div>
			</div>  
		</div> 
   
   
   <!-- 사이드 바 -->
   
   <div class="w3-container w3-padding w3-margin w3-border-indigo w3-card-4" style="padding:128px 16px">
	   <nav class="navBox w3-left navBox">
   			<div class="navBox-div w3-border">
   				<dl>
					<dt>회원관련</dt>
						 <dd>회원정보 수정</dd>
						 <dd>회원 탈퇴</dd>
						 <dd>로그</dd>
						 <dd>구매글 리스트</dd>
					<dt>포인트</dt>
						<dd>포인트 충전</dd>
						<dd>포인트 이용내역</dd>
					<dt>이벤트</dt>
						<dd>출석 이벤트</dd>
				</dl>
   			</div>
  	 </nav>
 <!-- 본문 내용 -->
  	 <!-- 보여질 기능들 -->
		<div class="w3-rest w3-text-grey w3-margin-left">
			<div class="w3-content mxw650 w3-margin-top">
			<div class="w3-button w3-hover-blue w3-indigo btnbox" id="board">게시글</div>
			<div class="w3-button w3-hover-blue w3-indigo btnbox" id="reply">댓글</div>
			<div class="w3-button w3-hover-blue w3-indigo btnbox" id="like">좋아요</div>
			<div class="w3-button w3-hover-blue w3-indigo btnbox" id="wish">찜</div>
<!-- 글목록 리스트 -->
<form method="POST" action="/board/boardList.boa" >
<c:if test="${empty DIVISION or DIVISION eq 'board'}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">번호</div>
			<div class="w3-col m6 w3-center">제목</div>
			<div class="w3-col m3 w3-right">등록일</div>
			<div class="w3-col m1 w3-right">조회수</div>
		</div>
		
<c:forEach var="data" items="${LIST}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">${data.rno}</div>
			<div class="w3-col m6 w3-center">${data.title}</div>
			<div class="w3-col m3 w3-right">${data.sdate}</div>
			<div class="w3-col m1 w3-right">${data.clicks}</div>
		</div>
</c:forEach>
</c:if>
<!-- 댓글 리스트 -->
<c:if test="${DIVISION eq 'reply'}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">번호</div>
			<div class="w3-col m6 w3-center">댓글내용</div>
			<div class="w3-col m3 w3-right">등록일</div>
			<div class="w3-col m2 w3-right">삭제여부</div>
		</div>
<c:forEach var="data" items="${LIST}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">${data.rno}</div>
			<div class="w3-col m6 w3-center">${data.body}</div>
			<div class="w3-col m3 w3-right">${data.sdate}</div>
		<c:if test="${data.isshow eq 'Y'}">
			<div class="w3-col m2 w3-right">등록</div>
		</c:if>
		<c:if test="${data.isshow eq 'N'}">
			<div class="w3-col m2 w3-right">삭제</div>
		</c:if>
		</div>
</c:forEach>
</c:if>
<!-- 찜목록 리스트 -->
<c:if test="${DIVISION eq 'wish'}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">번호</div>
			<div class="w3-col m6 w3-center">제목</div>
			<div class="w3-col m3 w3-right">등록일</div>
			<div class="w3-col m1 w3-right">조회수</div>
		</div>
<c:forEach var="data" items="${LIST}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">${data.rno}</div>
			<div class="w3-col m6 w3-center">${data.title}</div>
			<div class="w3-col m3 w3-right">${data.sdate}</div>
			<div class="w3-col m1 w3-right">${data.clicks}</div>
		</div>
</c:forEach>
</c:if>
<!-- 좋아요목록 리스트 -->
<c:if test="${DIVISION eq 'like'}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">번호</div>
			<div class="w3-col m6 w3-center">제목</div>
			<div class="w3-col m3 w3-right">등록일</div>
			<div class="w3-col m1 w3-right">조회수</div>
		</div>
<c:forEach var="data" items="${LIST}">
		<div class="w3-col w3-round-large w3-card-4 w3-margin-bottom w3-margin-top w3-padding w3-center">
			<div class="w3-col m1 w3-left">${data.bcnt}</div>
			<div class="w3-col m6 w3-center">${data.title}</div>
			<div class="w3-col m3 w3-right">${data.sdate}</div>
			<div class="w3-col m1 w3-right">${data.clicks}</div>
		</div>
</c:forEach>
</c:if>
</form>


		<!-- 페이지 처리 시작 -->
		<div class="w3-center">
			<div class="w3-bar w3-border w3-margin-top w3-margin-bottom">
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
   </div>
   
   
   
   
   
   <footer class="w3-container w3-margin-top w3-padding-64 w3-center w3-opacity w3-border-top">
		<p>(주)보아라</p>
	</footer>
  <!-- 데이터 전송용 form 태그 -->
	<form method="POST" action="/boara/member/mylog.boa" id="frm" name="frm">
		<input type="hidden" id="nowPage" name="nowPage" value="${PAGE.nowPage}">
	</form> 
	<form method="POST" action="/boara/member/mylog.boa" id="frm1" name="frm1">
		<input type="hidden" id="division" name="division" value="">
	</form> 
   
</c:if>
<script>
// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
  var x = document.getElementById("navDemo");
  if (x.className.indexOf("w3-show") == -1) {
    x.className += " w3-show";
  } else { 
    x.className = x.className.replace(" w3-show", "");
  }
}
</script>


</body>
</html>