package com.githrd.boa.controller;
/**
 * 	boara 프로젝트에서 사용할 MVC Model2 방식 controller 들의 인터페이스 입니다.
 * 	해당 인터페이스를 구현하여 controller들을 작성해주세요.
 * 
 * 	@author 최이지
 * 	@since 2022.05.23
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.23	-	담담자 : 최이지
 * 									클래스 제작
 */
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
public interface BoaInter {
	String exec(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException;
}
