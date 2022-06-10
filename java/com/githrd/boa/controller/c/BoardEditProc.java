package com.githrd.boa.controller.c;
/**
 * 	게시글 수정 요청 컨트롤러입니다.
 * 	@author 최이지
 * 	@since 2022.05.28
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.28	-	클래스 제작
 * 									담당자 : 최이지
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.util.FileUtil;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;

public class BoardEditProc implements BoaInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/board/redirect";
	
		// 세션 검사
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/boara/member/login.boa";
		}
		
		// 새 썸네일 있을 경우 : 파일 업로드 경로 설정
		FileUtil futil = new FileUtil(req, "/resources/upload/");
		MultipartRequest multi = futil.getMulti();
		ArrayList<FileVO> list = futil.getList();
		
		// 파라미터 가져오기, sql문 생성
		StringBuffer buff = new StringBuffer();
//		int oricno = Integer.parseInt(multi.getParameter("tcno"));
		int bno = Integer.parseInt(multi.getParameter("bno"));
		String scno = multi.getParameter("cno");
		int cno = 0;
		String title = multi.getParameter("title");
		String noti = multi.getParameter("noti");
		String sprice = multi.getParameter("price");
		int price = 0;
		String genre = multi.getParameter("genre");
		
		if(scno != null) {
			cno = Integer.parseInt(scno);
			buff.append("cno = " + cno + " ");
		}
		if(title != null) {
			buff.append(" , title = '" + title +"' ");
		}
		if(noti != null) {
			buff.append(" , isshow = '" + noti +"' ");
		}
		if(sprice != null) {
			price = Integer.parseInt(sprice);
			buff.append(" , price = " + price +" ");
		}
		if(!genre.equals("")) {
			buff.append(" , genre = '" + genre +"' ");
		}
		
		// db 작업
		BoardDao bDao = new BoardDao();
		String psql = buff.toString();
		int cnt = 0;
		
		// 맨 앞 콤마 지워주기
		if(psql.length() > 1) {
			psql = psql.substring(3);
			
			// psql이 존재하는경우에만 실행
			// 게시글 관련 변경사항 질의명령
			cnt = bDao.editBrd(bno, psql);
			if(cnt != 1) {
				req.setAttribute("isRedirect", true);
				return "/boara/board/boardEdit.boa?bno=" + bno;
			}
		}
		
		// 새 썸네일 있을 경우, 설정해주기
		if(list.size() == 1) {
			// 있던 썸네일 isshow 값 변경
			bDao.oldToY(bno);
			
			// 새 썸네일 설정
			int mno = bDao.getMno(sid);
			cnt = bDao.newToC(mno, list, bno);
			
			if(cnt != 1) {
				req.setAttribute("isRedirect", true);
				return "/boara/board/boardEdit.boa?bno=" + bno;
			}
		}
		
		// 새 썸네일 없는경우, 체크된 썸네일 있는지 검사
		String sino = multi.getParameter("sthumb");
		if(sino != null) {
			// 있던 썸네일 isshow 값 변경
			bDao.oldToY(cno);
			
			// 선택한 썸네일 설정
			int ino = Integer.parseInt(sino);
			cnt = bDao.selToC(ino);

			if(cnt != 1) {
				req.setAttribute("isRedirect", true);
				return "/boara/board/boardEdit.boa?bno=" + bno;
			}
		}
		
		// 데이터 심어주기
		req.setAttribute("VIEW", "/boara/board/boardDetail.boa?bno=" + bno);
		
		return view;
	}
}
