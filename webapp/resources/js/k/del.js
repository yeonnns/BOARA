$(document).ready(function(){
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
	
	// 체크시 탈퇴 버튼 노출
	$('#delete').click(function(){
		$('#delbox').css('display','block')
	});


	// 탈퇴처리
	$('#dbtn').click(function(){
		var spw = $('#pw').val();	
		if(!spw){
			$('#pw').focus();
			return;
		}
	
		alert('탈퇴 처리 되었습니다.')
		$('#frm').attr('action', '/boara/member/delMemberProc.boa');
		$('#frm').submit();
	
	});
	
		
});