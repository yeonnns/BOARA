$(document).ready(function(){
	
// 리스트 페이지 기능 -----------------------------------------------------------------

	// 컬렉션 클릭시
	$('.cbox').click(function(){
		var cno = $(this).attr('id');
		$('#cno').val(cno);
		
		$(location).attr('href', '/boara/board/boardList.boa?cno=' + cno);
	});
	
// 컬렉션 수정/삭제 -------------------------------------------------------------------
	
	// 컬렉션 삭제버튼 클릭시
	$('.dbtn').click(function(){
		// 컬렉션 번호 읽어오기
		var scno = $(this).parent().attr('id');
		
		// 폼태그에 파라미터 세팅
		$('#cno').val(scno);
		
		// 전송
		$('#frm').attr('action', '/boara/collection/collecDel.boa');
		$('#frm').submit();
	});
	
	// 컬렉션 수정버튼 클릭시
	$('.ebtn').click(function(){
		// 컬렉션 번호 읽어오기
		var scno = $(this).parent().attr('id');
		
		// 폼태그에 파라미터 세팅
		$('#cno').val(scno);
		
		// 전송
		$('#frm').attr('action', '/boara/collection/collecEdit.boa');
		$('#frm').submit();
	});

// 페이지 처리 ------------------------------------------------------------------------

	$('.cpbtn').click(function(){
		// 페이지 데이터 읽어오기
		var page = $(this).attr('id');
		
		// 특정 회원의 컬렉션을 보고있을 경우
		var addr = $(location).attr('href');
		var pat = /cid/g;
		if(pat.test(addr)){
			var cid = addr.substring(addr.search(pat));
			$('#frm').attr('action', '/boara/collection/collecList.boa?' + cid);
		}
		
		$('#nowPage').val(page);
		$('#frm').submit();
	});

// 컬렉션 작성 페이지 ---------------------------------------------------------------------

	// 파일 프리뷰
	$('#thumb').change(function(e){
		var sfile = $(this).val();
		var path;
		if(sfile){
			path = URL.createObjectURL(e.target.files[0]);
		}
		$('#preview').attr('src', path);
	});
	
	$('#wpbtn').click(function(){
		// 유효성 검사 : 값 가져오기
		var cname = $('#cname').val();
		var descr = $('#descr').val();

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
		
		// 일반 텍스트
		if(!cname){
			alert('컬렉션 이름은 필수입니다.');
			$('#cname').focus();
			return;
		}else if(cname.length > 30){
			alert('컬렉션 이름은 최대 30글자입니다.');
			$('#cname').focus();
			return;
		}
		
		if(descr.legnth > 30){
			alert('컬렉션 설명은 최대 30글자입니다.');
			$('#descr').focus();
			return;
		}else if(descr.length == 0){// 입력값 없으면 name 지워주기
			$('#descr').prop('disabled', true);
		}
		
		// 체크박스 : 장르
		if(genre == ""){
			$('input:checkbox[id="genr"]').prop('disabled', true);
			$('#genre').prop('disabled', true);
		}else{
			$('#genre').val(genre);
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
		var tcname = $('#tcname').val();
		var tdescr = $('#tdescr').val();
		var tgenre = $('#tgenre').val();
		 
		// 변경 된 값 찾아오기
		var cname = $('#cname').val().trim();
		var descr = $('#descr').val().trim();
		
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
		if(cname == tcname){
			$('#cname').prop('disabled', true);
		}
		if(descr == tdescr){
			$('#descr').prop('disabled', true);
		}
		if(genre == tgenre){
			$('#descr').prop('disabled', true);
		}
		
		$('#frm').submit();
	});
});