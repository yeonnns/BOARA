package com.githrd.boa.controller.p;
/**
 * 이 클래스는 댓글 수정 데이터베이스 작업을 전담해서 처리하는 클래스


 * @author	박소연
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 박소연
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.p.ReboardDao;

public class ReboardEditProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/reboard/redirect";
		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		
		//로그인 안 된 경우
		if(sid == null) {
			return "/boara/member/login.boa";
		}
		
		//파라미터 꺼내고
		String spage = req.getParameter("nowPage");
		String srno = req.getParameter("rno");
		int rno = Integer.parseInt(srno);
		String body = req.getParameter("body");
		String spo = req.getParameter("spo");
		String sbno = req.getParameter("bno");
		int bno = 0;
		
		if(sbno != null) {
			bno = Integer.parseInt(sbno);
		}
		
		if(spo == null) {
			spo = "Y";
		}
		
		
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.editReboard(rno, body, spo);
				
		if(cnt == 0) {
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/boara/reboard/reboardEdit.boa");
		} else {
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/boara/reboard/reboardList.boa");
		}
				
		return view;
	}

}
