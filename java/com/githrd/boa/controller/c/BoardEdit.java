package com.githrd.boa.controller.c;
/**
 *	컬렉션 수정 뷰를 보여줄 컨트롤러 입니다.
 *	@author 최이지
 * 	@since 2022.05.28
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.28	-	담담자 : 최이지
 * 									클래스 제작
 */
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.c.*;
public class BoardEdit implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/board/boardEdit";
		BoardDao bDao = new BoardDao();
		
		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/member/login.boa";
		}
		
		// 파라미터 꺼내고, 세션검사
		String scno = req.getParameter("cno");
		String sbno = req.getParameter("bno");
		int bno = 0;
		if(scno == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/board/boardList?cno=" + scno;
		}else if(sbno == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/collection/collecList.boa" + scno;
		}else {
			bno = Integer.parseInt(sbno);
		}
		
		// db 작업
		BoardVO bvo = bDao.bnoInfo(bno);
		HashMap<Integer, String> glist = bDao.genrList();
		HashMap<Integer, String> clist = bDao.getColls(sid);
		
		// 데이터 입력
		req.setAttribute("PINFO", bvo);
		req.setAttribute("FLIST", bvo.getList());
		req.setAttribute("GLIST", glist);
		req.setAttribute("CLIST", clist);
		
		return view;
	}
}
