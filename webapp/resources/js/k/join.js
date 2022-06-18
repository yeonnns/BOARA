$(document).ready(function(){
	
	
	// 아이디 체크 버튼
	$('#idck').click(function(){
		var sid = $('#id').val();
		if(!sid){
			$('#id').focus();
			alert("아이디를 입력하세요!")
			return;
		}
		
		$.ajax({
			url: '/boara/member/idCheck.boa',
			type: 'post',
			dataType: 'json',
			data: {
				id: sid
			},
			success: function(data){
				var result = data.result;
				$('#idmsg').removeClass('w3-text-green w3-text-red');
					
				if(result == 'OK'){
					// 입력한 아이디가 사용가능한 경우
					$('#idmsg').html('사용 가능한 아이디 입니다.');
					$('#idmsg').addClass('w3-text-green');
				} else {
					// 입력한 아이디가 사용불가능한 경우
					$('#idmsg').html('사용 불가능한 아이디 입니다.');
					$('#idmsg').addClass('w3-text-red');
				}
					$('#idmsg').css('display', 'block');
				},
			error: function(){
				alert('### 통신 에러 ###');
			}
		});
			
	});
		
	// 메일 체크 버튼
	$('#mailck').click(function(){
		var smail = $('#mail').val();
		if(!smail){
			$('#mail').focus();
			alert("메일주소를 입력하세요!")
			return;
		}
		$.ajax({
			url: '/boara/member/mailCheck.boa',
			type: 'post',
			dataType: 'json',
			data: {
				mail: smail
			},
			success: function(data){
				var result = data.result;
				$('#mailmsg').removeClass('w3-text-green w3-text-red');
					
					if(result == 'OK'){
						$('#mailmsg').html('사용 가능한 메일 입니다.');
						$('#mailmsg').addClass('w3-text-green');
					} else {
						$('#mailmsg').html('사용 불가능한 메일 입니다.');
						$('#mailmsg').addClass('w3-text-red');
					}
						$('#mailmsg').css('display', 'block');
					},
					error: function(){
						alert('### 통신 에러 ###');
					}
				});
				
			});
			
	// 전화번호 체크 버튼
	$('#telck').click(function(){
	var stel = $('#tel').val();
	
		if(!stel){
			$('#tel').focus();
			alert("전화번호를 입력하세요!")
			return;
		}
		$.ajax({
			url: '/boara/member/telCheck.boa',
			type: 'post',
			dataType: 'json',
			data: {
				tel: stel
			},
			success: function(data){
				var result = data.result;
				$('#telmsg').removeClass('w3-text-green w3-text-red');
					
					if(result == 'OK'){
						$('#telmsg').html('사용 가능한 전화번호 입니다.');
						$('#telmsg').addClass('w3-text-green');
					} else {
						$('#telmsg').html('사용 불가능한 전화번호 입니다.');
						$('#telmsg').addClass('w3-text-red');
					}
						$('#telmsg').css('display', 'block');
					},
					error: function(){
						alert('### 통신 에러 ###');
					}
				});
				
			});
		
	
	
	// 비밀번호 일치 처리
	$('#repw').keyup(function(){
		// 할일
		// 입력된 데이터 읽어온다.
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
	
		
	// reset
	$('#rbtn').click(function(){
		document.frm.reset();
	});
	// home 
	$('#hbtn').click(function(){
		$(location).attr('href', '/boara/main.boa');
	});	
	// join
	$('#jbtn').click(function(){
		// 데이터 유효성 검사

	
		
		var el = $('#name, #id, #pw, #tel, #mail, #file'); 
		
		for(var i = 0 ; i < el.length ; i++ ){
			var txt = $(el).eq(i).val();
			if(!txt){
				alert('필수 입력사항을 확인하세요.');
				$(el).eq(i).focus();
				return;
			}
		}
		$('#frm').attr('action', '/boara/member/joinProc.boa').submit();
		
	});	
	
	
	// 사진 올릴시 보여주기
	$('.upfile').change(function(e){
		var path = URL.createObjectURL(e.target.files[0]);
		$('#img1').attr('src', path);	
		
	});
	
	});
	