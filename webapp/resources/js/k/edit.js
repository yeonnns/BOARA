$(document).ready(function(){
	// reset 버튼 실행
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	
	// main 처리페이지 이동
	$('#hbtn').click(function(){
		$(location).attr('href', '/boara/main.boa');
	});
	
	// 탈퇴 처리페이지 이동
	$('#dbtn').click(function(){
		$(location).attr('href','/boara/member/delMember.boa');
	});
	
	// 비밀번호 일치 처리
	$('#repw').keyup(function(){
		var spw = $('#pw').val();
		var repw = $(this).val();
		if(spw != repw){
			$('#repwmsg').html('비밀번호가 일치하지 않습니다.');
			$('#repwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-red');
			$('#repwmsg').css('display', 'block');
		} else {
			$('#repwmsg').html('비밀번호가 일치합니다.');
			$('#repwmsg').removeClass('w3-text-green w3-text-red').addClass('w3-text-green');
			$('#repwmsg').css('display', 'block');
			$('#repw').parent().parent().stop().slideDown(300).stop().slideUp(300);
			$('#pw').css('background-color', 'lightgray').prop('readonly', true);
		}
	});
	// 수정 버튼 실행
	$('#ebtn').click(function(){
		// 받은 데이터 쪽
		var tmail = $('#tmail').val();
		var ttel = $('#ttel').val();

		
		
		// 입력한 데이터 꺼내기
		var pw = $('#pw').val();
		var mail = $('#mail').val();
		var tel = $('#tel').val();
		var fno = $('#file').val();
		
		if(!pw){
			$('#pw').prop('disabled', true);
		}
		if(tmail == mail){
			$('#mail').prop('disabled', true);
		}
		if(ttel == tel){
			$('#tel').prop('disabled', true);
		}
		if(!fno){
			$('#fno').prop('disabled', true);
		}
		if(!pw && (tmail == mail) && (ttel == tel) && !fno){
			// 수정을 한개도 하지 않는 경우..
			alert('수정된 개인정보가 없습니다.');
			return;
		}
		
		// 보낼 주소 설정하고
		$('#frm').attr('action', '/boara/member/editInfoProc.boa');
		$('#frm').submit();
	});
		
	
	// 프로필 이미지 삭제하기 
	$('.dbtn').click(function(){
		var sno = $(this).attr('id').substring(1);
		$('#tfno').val(sno);
		if(confirm('프로필 사진을 삭제하시겠습니까?')){
			$('#frm').attr('action','/boara/member/editImgProc.boa')
			$('#frm').submit();
		}

	});	
	
	// 사진 올릴시 보여주기
	$('.upfile').change(function(e){
		var path = URL.createObjectURL(e.target.files[0]);
		$('#img1').attr('src', path);	
		
	});
		
		
});
	
	
