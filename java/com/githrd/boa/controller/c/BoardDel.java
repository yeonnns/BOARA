package com.githrd.boa.controller.c;
/**
 * 	게시글 삭제요청 컨트롤러입니다.
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
public class BoardDel implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/board/redirect";
		
		// 파라미터 가져오기
		int page = 1;
		String spage= req.getParameter("nowPage");
		if(spage != null) page = Integer.parseInt(spage);
		int bno = Integer.parseInt(req.getParameter("bno"));
		int cno = Integer.parseInt(req.getParameter("cno"));
		
		// db 작업
		BoardDao bDao = new BoardDao();
		int cnt = bDao.delPost(bno);
		
		// 결과에 따른 처리
		if(cnt == 0) {
			req.setAttribute("MSG", bno + "번 게시글 삭제 실패");
		}else {
			req.setAttribute("MSG", bno + "번 게시글 삭제 성공");			
		}
		
		// 데이터 입력
		req.setAttribute("NOWPAGE", page);
		req.setAttribute("VIEW", "/boara/board/boardList.boa?cno=" + cno);
		
		return view;
	}

}
