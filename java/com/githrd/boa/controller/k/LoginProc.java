package com.githrd.boa.controller.k;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.MemberDao;
/**
 * 로그인 처리요청 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class LoginProc implements BoaInter {
	
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/boara/main.boa";
		if(req.getSession().getAttribute("SID") != null) {
			// 로그인 한 상태
			return view;
		}
		// 로그인 안된 상태
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getLogin(id, pw);
		
		if(cnt == 1) {
			// 로그인 아이디 세션에 기록
			req.getSession().setAttribute("SID", id);
		} else {	
			view = "/boara/member/login.boa";
		}
		return view;
	}
}
	

