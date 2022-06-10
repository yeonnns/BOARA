package com.githrd.boa.controller.k;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
/**
 * logout 처리 요청 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class Logout implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/boara/main.boa";
		req.setAttribute("isRedirect", true);
		
		req.getSession().removeAttribute("SID");
		return view;
	}

}
