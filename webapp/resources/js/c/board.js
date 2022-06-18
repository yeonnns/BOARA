$(document).ready(function(){
	
// 리스트 페이지 기능 -----------------------------------------------------------------

	// 컬렉션 클릭시
	$('.pbox').click(function(){
		var bno = $(this).attr('id');
		$('#cno').val(bno);
		
		$(location).attr('href', '/boara/board/boardDetail.boa?bno=' + bno);
	});
	
// 게시글 상세보기 - 하단 버튼들 ------------------------------------------------------

	// 좋아요 기능
	$('.like').click(function(){
		
	});
	
	// 컬렉션으로
	$('#upcoll').click(function(){
		var cno = $('#cno').val();
		$(location).attr('href', '/boara/board/boardList.boa?cno=' + cno);
	});
	
	// 댓글 리스트
	$('#reshow').click(function(){
		var bno = $('#bno').val();
		$(location).attr('href', '/boara/reboard/reboardList.boa?bno=' + bno);
	});

// 게시글 수정/삭제 버튼들 ------------------------------------------------------------
	
	// 게시글 삭제
	$('.dbtn').click(function(){
		// 게시글 번호 읽어오기
		var sbno = $(this).parent().attr('id');
		
		// 폼태그에 파라미터 세팅
		$('#bno').val(sbno);
		
		// 전송
		$('#frm').attr('action', '/boara/board/boardDel.boa');
		$('#frm').submit();
	});
	
	// 게시글 상세보기 -> 게시글 삭제
	$('#dbtn').click(function(){
		$('#frm').attr('action', '/boara/board/boardDel.boa');
		$('#frm').submit();
	});
	
	// 컬렉션 수정
	$('.ebtn').click(function(){
		// 게시글 번호 읽어오기
		var sbno = $(this).parent().attr('id');
		
		// 폼태그에 파라미터 세팅
		$('#bno').val(sbno);
		
		// 전송
		$('#frm').attr('action', '/boara/board/boardEdit.boa');
		$('#frm').submit();
	});
	
	// 게시글 상세보기 -> 게시글 수정
	$('#ebtn').click(function(){
		$('#frm').attr('action', '/boara/board/boardEdit.boa');
		$('#frm').submit();
	});

// 게시글 작성 페이지 -----------------------------------------------------------------

	// 파일 프리뷰
	$('#thumb').change(function(e){
		var sfile = $(this).val();
		var path;
		if(sfile){
			path = URL.createObjectURL(e.target.files[0]);
		}
		$('#preview').attr('src', path);
	});
	
	// 작성버튼
	$('#wpbtn').click(function(){
		// 데이터 따오기
		var title = $('#title').val();
		var cno = $('#cno').val();
		var body = $('#body').val().trim();
		var price = $('#price').val();
		
		// 체크박스 : 장르
		var genre = "";
		var chkbox = $('input:checkbox[id="genr"]:checked');
		var ck_gnr = chkbox.length;
		for(i=0; i<ck_gnr; i++){
			if(i == (ck_gnr - 1)){
				genre += chkbox[i].value;
			}else{
				genre += chkbox[i].value + "/";
			}
		}
		
		// 유효성 검사 실시
		// 파일
		var el = $('#thumb');
		// 입력된 파일 없으면 name 값 제거
		if(!$(el).val()){
			el.prop('disabled', true);
		}
		
		// 체크박스 : 장르
		if(genre == ""){
			$('input:checkbox[id="genr"]').prop('disabled', true);
			$('#genre').prop('disabled', true);
		}else{
			$('#genre').val(genre);
		}
		
		// 일반 텍스트
		if(!title){
			alert('글 제목은 필수입니다.');
			$('#title').focus();
			return;
		}else if(title.length > 50){
			alert('글 제목은 최대 50글자입니다.');
			$('#title').focus();
			return;
		}
		
		if(!cno){
			alert('게시글이 들어갈 컬렉션을 선택하세요.');
			return;
		}
		
		if(!body){
			alert('본문을 입력하세요.');
			$('#body').focus();
			return;			
		}else if(body.length > 2000){
			alert('본문은 최대 2000자입니다.');
			$('#body').focus();
			return;			
		}
		
		if(price > 50000){
			alert('가격은 최대 50000P입니다.');
			$('#price').focus();
			return;
		}else if(!price){// 입력값 없으면 0으로 세팅
			$('#price').val('0');
		}
		
		$('#frm').submit();
	});

// 컬렉션 수정 페이지 ---------------------------------------------------------------------

	// 파일 프리뷰
	$('#newthumb').change(function(e){
		var sfile = $(this).val();
		var path;
		if(sfile){
			path = URL.createObjectURL(e.target.files[0]);
		}
		$('#preview').attr('src', path);
	});
	
	$('#epbtn').click(function(){
		// 유효성 검사 : 원래 값 꺼내기
		var ttitle = $('#ttitle').val();
		var tnoti = $('#tnoti').val();
		var tcno = $('#tcno').val();
		var tprice = $('#tprice').val();
		var tgenre = $('#tgenre').val();
		var tbody = $('#tbody').val();
		
		// 변경 된 값 찾아오기
		var title = $('#title').val().trim();
		var noti = $('[name="noti"]:checked').val();
		var cno = $('#cno').val().trim();
		var price = $('#price').val().trim();
		if(!price){// 입력값 없으면 0으로 세팅
			$('#price').val('0');
			price = 0;
		}
		var body = $('#body').val().trim();
		
		// 체크박스 : 장르
		var genre = "";
		var chkbox = $('input:checkbox[id="genr"]:checked');
		var ck_gnr = chkbox.length;
		for(i=0; i<ck_gnr; i++){
			if(i == (ck_gnr - 1)){
				genre += chkbox[i].value;
			}else{
				genre += chkbox[i].value + "/";
			}
		}
		
		// 새 파일 : 유효성 검사
		var el = $('#newthumb');
		// 입력된 파일 없으면 name 값 제거
		if(!$(el).val()){
			el.prop('disabled', true);
			
		}else{// 파일 두개 올라온경우
			if($('input:radio[id="sthumb"]:checked').val()){
				alert('새 파일을 올리거나, 아래 이미지 중 선택해 주세요.');
				return;
			}	
		}
		
		// 변경 안됐으면 전송 안되게 막기
		if(title == ttitle){
			$('#title').prop('disabled', true);
		}else if(title.length > 50){
			alert('글 제목은 최대 50글자입니다.');
			$('#title').focus();
			return;
		}
		if(tnoti == noti){
			$('#noti').prop('disabled', true);
		}
		if(cno == tcno){
			$('#cno').prop('disabled', true);
		}
		if(!cno){
			alert('게시글이 들어갈 컬렉션을 선택하세요.');
			return;
		}
		if(price == tprice){
			$('#price').prop('disabled', true);
		}else if(price > 50000){
			alert('가격은 최대 50000P입니다.');
			$('#price').focus();
			return;
		}
		if(genre == tgenre){
			$('#descr').prop('disabled', true);
		}
		if(!body){
			alert('본문을 입력하세요.');
			$('#body').focus();
			return;
		}else if(body.length > 2000){
			alert('본문은 최대 2000자입니다.');
			$('#body').focus();
			return;			
		}else if(body == tbody){
			$('#body').prop('disabled', true);
		}
		
		$('#frm').submit();
	});
	
// 페이지 이동관련 --------------------------------------------------------------------

	$('.cpbtn').click(function(){
		// 페이지 데이터 읽어오기
		var page = $(this).attr('id');
		
		// 컬렉션 처리
		var addr = $(location).attr('href');
		var pat = /cno/g;
		if(pat.test(addr)){
			var cno = addr.substring(addr.search(pat));
			$('#frm').attr('action', '/boara/board/boardList.boa?' + cno);
		}
		
		$('#nowPage').val(page);
		$('#frm').submit();
	});
});