package com.githrd.boa.controller.p;
/**
 * 이 클래스는 새 댓글 작성 데이터베이스 작업을 전담해서 처리하는 클래스


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

public class ReboardWriteProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/boara/reboard/reboardList.boa";
		
		//로그인 체크
		String sid = (String) req.getSession().getAttribute("SID");
		
		//로그인 안 된 경우 
		if(sid == null) {
			view = "/boara/member/login.boa";
			return view;
		}
		
		int bno = 0;
		
		//파라미터 꺼내고
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		String body = req.getParameter("body");
		String spo = req.getParameter("spo");
		String sbno = req.getParameter("bno");
		
		if(sbno != null) {
			bno = Integer.parseInt(sbno);
		}
		//새 댓글의 경우 언제나 상위댓글번호가 없다.
		int uprno = 0;
	
		ReboardVO rVO = new ReboardVO();
		
		//스포일러 댓글 아닌 경우
		if(spo == null) {
			spo = "Y";
		}
		
		//스포일러 댓글인 경우
		rVO.setIsshow(spo);	
		
		rVO.setId(sid);
		rVO.setBody(body);
		rVO.setUprno(uprno);
		rVO.setMno(mno);

		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.addReboard(rVO, bno);
		
		if(cnt == 1) {
			//댓글 등록 성공
			
			//댓글 1개 작성 시 5포인트 적립
			int pnt = rDao.addPoint(mno, 5, 102);
			
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/boara/reboard/reboardList.boa");
			view = "/reboard/redirect";
		} else {
			//댓글 등록 실패
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/boara/reboard/reboardWrite.boa");
			view = "/reboard/redirect";
		}
		
		return view;
	}

}
