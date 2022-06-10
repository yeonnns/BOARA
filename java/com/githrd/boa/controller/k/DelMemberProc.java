package com.githrd.boa.controller.k;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.MemberDao;
/**
 * 아이디로 회원 탈퇴 처리 해주는 클래스
 * @author	김소연
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.26	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class DelMemberProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/boara/main.boa";
		String sid = (String) req.getSession().getAttribute("SID");
		System.out.println(sid);
		if(sid == null) {
			// 로그인 안된 경우이므로 로그인 페이지로 보낸다
			view = "/boara/member/login.boa";
			return view;
		}
		MemberDao mDao = new MemberDao();
		int cnt = mDao.delMember(sid);
		System.out.println(cnt);
		if(cnt !=1 ) {
			// 실패
			view = "/boara/member/delMember.boa";
		} else {
			req.getSession().removeAttribute("SID");
		}
		return view;
	}
	
	

}
