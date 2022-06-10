package com.githrd.boa.controller.c;
/**
 *	특정 게시글의 뷰를 보여줄 컨트롤러 입니다.
 *	@author 최이지
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
import com.githrd.boa.vo.c.*;
public class BoardDetail implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/board/boardDetail";
		
		// 파라미터 꺼내기
		String sbno = req.getParameter("bno");
		int bno = 0;
		if(sbno != null) bno = Integer.parseInt(sbno);
		
		// 선택된 게시글이 없다면
		if(bno == 0) {
			req.setAttribute("isRedirect", true);
			return "/boara/main.boa";
		}
		
		// db 작업
		BoardDao bDao = new BoardDao();
		BoardVO bVO = bDao.getInfo(bno);
		
		// 조회수 올리기
		bDao.viewUp(bno);
		
		// 데이터 세팅
		req.setAttribute("BNO", bno);
		req.setAttribute("POST", bVO);
		
		return view;
	}
}
