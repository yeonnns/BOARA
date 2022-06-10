package com.githrd.boa.controller.p;
/**
 * 이 클래스는 대댓글 작성 데이터베이스 작업을 전담해서 처리하는 클래스


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

/**
 * @author aknod
 *
 */
public class ReboardCommentProc implements BoaInter {

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
		
		//파라미터 꺼낸다
		String sno = req.getParameter("mno");
		int mno = Integer.parseInt(sno);
		String suprno = req.getParameter("uprno");
		String body = req.getParameter("body");
		String spo = req.getParameter("spo");
		int bno = 0;
		String sbno = req.getParameter("bno");
		
		if(sbno != null) {
			bno = Integer.parseInt(sbno);
		}
		
		ReboardVO rVO = new ReboardVO();
		
		//스포 댓글 아닌 경우
		if(spo == null) {
			spo = "Y";
		}
		//스포일러 댓글인 경우
		rVO.setIsshow(spo);	
		
		rVO.setId(sid);
		rVO.setMno(mno);
		rVO.setBody(body);
		rVO.setUprno(Integer.parseInt(suprno));
		
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.addReboard(rVO, bno);
		
		if(cnt == 1) {
			//대댓글 등록 성공
			
			//대댓글 1개 작성 시 3포인트 적립
			int pnt = rDao.addPoint(mno, 3, 103);
			
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/boara/reboard/reboardList.boa");
			view = "/reboard/redirect";
		} else {
			//대댓글 등록 실패
			req.setAttribute("isRedirect", false);
			req.setAttribute("VIEW", "/boara/reboard/reboardComment.boa");
			view = "/reboard/redirect";
		}
		
		return view;
	}

}
