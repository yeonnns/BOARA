package com.githrd.boa.controller.c;
/**
 * 	컬렉션 작성 요청 컨트롤러입니다.
 * 	@author 최이지
 * 	@since 2022.05.27
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.27	-	클래스 제작
 * 									담당자 : 최이지
 */
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.*;
import com.githrd.boa.vo.c.*;
import com.githrd.boa.util.FileUtil;
import com.oreilly.servlet.MultipartRequest;

public class BoardWriteProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 본인 아이디 받고, 반환값 초기화
		req.setAttribute("isRedirect", true);
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/boara/member/login.boa";
		}
		
		// 파일 업로드 경로 설정
		FileUtil futil = new FileUtil(req, "/resources/upload/");
		MultipartRequest multi = futil.getMulti();
		
		// 파라미터 꺼내기
		String title = multi.getParameter("title");
		int cno = Integer.parseInt(multi.getParameter("cno"));
		String body = multi.getParameter("body");
		String genre = multi.getParameter("genre");
		ArrayList<FileVO> list = futil.getList();
		int price = Integer.parseInt(multi.getParameter("price"));
		String show = multi.getParameter("noti");
		
		// 데이터 세팅
		BoardDao bDao = new BoardDao();
		BoardVO bvo = new BoardVO();
		bvo.setTitle(title);
		bvo.setCno(cno);
		bvo.setBody(body);
		bvo.setSgenre(genre);
		bvo.setList(list);
		bvo.setPrice(price);
		bvo.setIsshow(show);
		bvo.setMno(bDao.getMno(sid));
		
		// db 작업
		int cnt = bDao.addPostThumb(bvo);
		String view = "/boara/board/boardList.boa?cno=" + cno;
		
		if(cnt != 1) {// 실패시 : 다시 작성폼으로
			view = "/boara/board/boardWrite.boa";
		}
		
		return view;
	}
}
