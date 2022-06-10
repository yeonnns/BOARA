package com.githrd.boa.controller.p;
/**
 * 이 클래스는 새 댓글 작성 페이지 보여주는 클래스

 * @author	박소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
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

public class ReboardWrite implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardWrite";
		int bno = 0;
		
		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		
		//로그인 안 함
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			view = "/boara/member/login.blp";
			return view;
		}
		
		//파라미터 뽑고
		String sbno = req.getParameter("bno");
		if(sbno != null) {
			bno = Integer.parseInt(sbno);
		}
		
		//로그인 함
		ReboardDao rDao = new ReboardDao();
		ReboardVO rVO = rDao.getWriterInfo(sid, bno);
		
		//작성자 정보를 심는다
		req.setAttribute("DATA", rVO);
		
		return view;
	}

}
