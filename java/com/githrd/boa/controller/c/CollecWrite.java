package com.githrd.boa.controller.c;
/**
 *	컬렉션 작성 뷰를 보여줄 컨트롤러 입니다.
 *	@author 최이지
 * 	@since 2022.05.27
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.27	-	클래스 제작
 * 									담당자 : 최이지
 */
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.c.*;
public class CollecWrite implements BoaInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/collection/collecWrite";
		CollDao cDao = new CollDao();
		
		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/member/login.boa";
		}
		
		// 장르 불러오기
		HashMap<Integer, String> glist = cDao.genrList();
		
		// 데이터 입력
		req.setAttribute("GLIST", glist);
		
		return view;
	}
}
