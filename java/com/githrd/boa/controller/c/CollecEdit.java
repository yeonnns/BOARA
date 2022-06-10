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
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.c.*;
public class CollecEdit implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/collection/collecEdit";
		CollDao cDao = new CollDao();
		
		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/member/login.boa";
		}
		
		// 파라미터 꺼내고, 세션 검사
		String scno = req.getParameter("cno");
		int cno = 0;
		if(scno == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/collection/collecList.boa";
		}else {
			cno = Integer.parseInt(scno);
		}
		
		int nowPage = 1;
		String spage = req.getParameter("nowPage");
		if(spage != null) nowPage = Integer.parseInt(spage);
		
		// db 작업
		CollVO cvo = cDao.cnoInfo(cno);
		HashMap<Integer, String> glist = cDao.genrList();

		// 데이터 입력
		req.setAttribute("CINFO", cvo);
		req.setAttribute("FLIST", cvo.getList());
		req.setAttribute("GLIST", glist);
		req.setAttribute("NOWPAGE", nowPage);
				
		return view;
	}
}
