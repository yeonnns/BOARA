$(document).ready(function(){
	// 로그인 페이지 이동
	$('#lbtn').click(function(){
		var sid = $('#id').val();
		var spw = $('#pw').val();
		if(!sid){
			$('#id').focus();
			return;
		}
		if(!spw){
			$('#pw').focus();
			return;
		}
		
		$('#frm').attr('action', '/boara/member/loginProc.boa');
		$('#frm').submit();
	});
	

	// 홈버튼 클릭이벤트
	$('#hbtn').click(function(){
		$(location).attr('href', '/boara/main.boa');
	});
	
	// join 페이지
	$('#join').click(function(){
		$(location).attr('href','/boara/member/join.boa')	
	});
	
	
	// id/pw 찾기
	$('#check').click(function(){
		$(location).attr('href','/boara/member/idpwSearch.boa')	
	});
	
	// id 찾기 요청 처리
	$('#idck').click(function(){
		var sname = $('#name').val();
		var stel = $('#tel').val();
		if(!sname){
			$('#name').focus();
			return;
		}
		if(!stel){
			$('#tel').focus();
			return;
		}
		
		
		/*
		$('#frm').attr('action', '/boara/member/idSearchProc.boa');
		$('#frm').submit();
		*/
		
		$.ajax({
			url: '/boara/member/idSearchProc.boa',
			type: 'POST',
			dataType: 'json',
			data: {
				name: sname,
				tel: stel
			},
			success: function(data){
				if(data.result == 'OK'){
					var id = data.id;
					$('#serid').html(id);
					$('#srchIdResult').css('display', 'block');
				}
			},
			error: function(){
				alert('##### 통신 에러 #####');
			}
		});
	});
	
	// pw 찾기 요청 처리
	$('#pwck').click(function(){
		var sid = $('#id').val();
		var smail = $('#mail').val();
		if(!sid){
			$('#id').focus();
			return;
		}
		if(!smail){
			$('#mail').focus();
			return;
		}
		
		$.ajax({
			url: '/boara/member/pwSearchProc.boa',
			type: 'POST',
			dataType: 'json',
			data: {
				id: sid,
				mail: smail
			},
			success: function(data){
				if(data.result == 'OK'){
					var pw = data.pw;
					$('#serpw').html(pw);
					$('#srchPwResult').css('display', 'block');
				}
			},
			error: function(){
				alert('##### 통신 에러 #####');
			}
		});
	});
});
		