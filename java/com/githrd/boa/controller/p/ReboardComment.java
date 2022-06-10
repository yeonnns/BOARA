package com.githrd.boa.controller.p;
/**
 * 이 클래스는 대댓글 작성 페이지 보여주는 클래스


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

public class ReboardComment implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardComment";

		String sid = (String) req.getSession().getAttribute("SID");
		//로그인 안함
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/member/login.boa";
		}
		int bno = 0;
		String sbno = req.getParameter("bno");
		
		if(sbno != null) {
			bno = Integer.parseInt(sbno);
		}
		
		//로그인 함
		ReboardDao rDao = new ReboardDao();
		ReboardVO rVO = rDao.getWriterInfo(sid, bno);
		
		req.setAttribute("DATA", rVO);
		
		return view;
	}

}
