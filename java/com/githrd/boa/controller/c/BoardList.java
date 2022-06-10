package com.githrd.boa.controller.c;
/**
 *	컬렉션 리스트 (회원 페이지 메인) 뷰를 보여줄 컨트롤러 입니다.
 *	@author 최이지
 * 	@since 2022.05.24
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.24	-	담담자 : 최이지
 * 									클래스 제작
 * 		
 * 				2022.05.26	-	담당자 : 최이지
 * 									db 작업
 */
import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.vo.c.*;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.util.*;
public class BoardList implements BoaInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 변수 초기화
		int cno = 0;
		String view = "/board/boardList";
		BoardDao bDao = new BoardDao();
		
		// 파라미터 얻기
		String scno = req.getParameter("cno");
		if (scno != null) cno = Integer.parseInt(scno);
		
		String msg = req.getParameter("msg");
		
		// nowPage 처리
		int nowPage = 1;
		String spage = req.getParameter("nowPage");
		if(spage != null) nowPage = Integer.parseInt(spage);

		// 페이지 처리
		int total = bDao.countCollPosts(cno);
		PageUtil page = new PageUtil(nowPage, total);
		
		// db 작업
		ArrayList<BoardVO> list = bDao.getCollPosts(cno, page);
		CollVO cvo = bDao.getCollInfo(cno);
		
		// 데이터 세팅
		if(cno != 0) req.setAttribute("CNO", cno);
		if(msg != null) req.setAttribute("MSG", msg);
		req.setAttribute("PAGE", page);
		req.setAttribute("PLIST", list);
		req.setAttribute("CINFO", cvo);
		
		return view;
	}
}
