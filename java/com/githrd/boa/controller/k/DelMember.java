package com.githrd.boa.controller.k;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
/**
 * 회원 탈퇴 폼 보여주는 클래스
 * @author	김소연
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class DelMember implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/delMember";
		if(req.getSession().getAttribute("SID") == null) {
			req.setAttribute("isRedirect", true);
			view = "/boara/member/login.boa";
		}
		
		return view;
	}

}