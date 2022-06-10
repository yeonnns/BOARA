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
 * 				2022.05.25	-	담당자 : 최이지
 * 									페이징 처리
 * 		
 * 				2022.05.26	-	담당자 : 최이지
 * 									메세지 팝업 데이터 추가
 */
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.c.*;
import com.githrd.boa.util.*;
public class CollecList implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// 변수 초기화
		// 반환값 변수
		String view = "/collection/collecList";
		
		// db 관련 변수
		ArrayList<CollVO> list = new ArrayList<CollVO>();
		CollDao cDao = new CollDao();
		
		// 페이지 처리 변수
		PageUtil page;
		int nowPage = 1;
		int total;

		// 페이지값이 있을경우, 셋팅
		String spage = req.getParameter("nowPage");
		if(spage != null) nowPage = Integer.parseInt(spage);
		
		// 파라미터 꺼내오기
		String cid = req.getParameter("cid");
		String msg = req.getParameter("msg");

		// db 작업, 페이지 처리
		if(cid == null) {
			total = cDao.countAll();
			page = new PageUtil(nowPage, total);
			list = cDao.getAllColl(page);
		}else {
			total = cDao.countCno(cid);
			page = new PageUtil(nowPage, total);
			list = cDao.getCollForId(cid, page);
		}
		
		req.setAttribute("LIST", list);
		req.setAttribute("CID", cid);
		req.setAttribute("PAGE", page);
		if(msg != null) {
			req.setAttribute("MSG", msg);
		}
		
		return view;
	}
}
