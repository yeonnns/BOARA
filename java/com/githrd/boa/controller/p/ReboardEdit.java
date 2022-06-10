package com.githrd.boa.controller.p;
/**
 * 이 클래스는 댓글 수정 페이지 보여주는 클래스


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
import com.githrd.boa.vo.p.ReboardVO;

public class ReboardEdit implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardEdit";
		
		String sid = (String) req.getSession().getAttribute("SID");
		
		//로그인 안 됨
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/member/login.boa";
		}
		
		//파라미터 꺼내기
		int page = 1;
		int rno = 0;
		int bno = 0;
		
		String spage = req.getParameter("nowPage");
		String srno = req.getParameter("rno");
		
		if(srno != null) {
			rno = Integer.parseInt(srno);	
		}

		String sbno = req.getParameter("bno");
		
		if(sbno != null) {
			bno = Integer.parseInt(sbno);
		}
				
		ReboardDao rDao = new ReboardDao();
		ReboardVO rVO = rDao.getEditData(rno, sid);
				
		req.setAttribute("DATA", rVO);
		
		return view;
	}

}
