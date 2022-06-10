package com.githrd.boa.controller.c;
/**
 * 	컬렉션 삭제요청 컨트롤러입니다.
 * 	@author 최이지
 * 	@since 2022.05.26
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.26	-	담당자 : 최이지
 * 									클래스 제작
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.c.*;

public class CollecDel implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/collection/redirect";
		
		// 파라미터 가져오기
		int page = Integer.parseInt(req.getParameter("nowPage"));
		int cno = Integer.parseInt(req.getParameter("cno"));
		
		// db 작업
		CollDao cDao = new CollDao();
		int cnt = cDao.delColl(cno);
		
		// 결과에 따른 처리
		if(cnt == 0) {
			req.setAttribute("MSG", cno + "번 컬렉션 삭제 실패");
		}else {
			req.setAttribute("MSG", cno + "번 컬렉션 삭제 성공");			
		}
		
		// 데이터 입력
		req.setAttribute("NOWPAGE", page);
		req.setAttribute("VIEW", "/boara/collection/collecList.boa");
		
		return view;
	}
}
