package com.githrd.boa.controller.k;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.*;

/**
 * 로그인 폼 보여주는 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class LoginForm implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/login";
		if(req.getSession().getAttribute("SID") != null) {
			req.setAttribute("isRedirect", true);
			view = "/boara/main.boa";
		}
		return view;
	}

}
