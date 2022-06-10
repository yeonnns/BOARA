package com.githrd.boa.controller.c;
/**
 * 	컬렉션 수정 요청 컨트롤러입니다.
 * 	@author 최이지
 * 	@since 2022.05.28
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.28	-	클래스 제작
 * 									담당자 : 최이지
 */
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.util.*;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.*;
import com.oreilly.servlet.MultipartRequest;
public class CollecEditProc implements BoaInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/collection/redirect";
		
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
		int cno = Integer.parseInt(multi.getParameter("cno"));
		String cname = multi.getParameter("cname");
		String descr = multi.getParameter("descr");
		String genre = multi.getParameter("genre");
		System.out.println(genre);
		
		if(cname != null) {
			buff.append(" , cname = '" + cname +"' ");
		}
		if(!genre.equals("")) {
			buff.append(" , genre = '" + genre +"' ");
		}
		if(descr != null) {
			if(!descr.equals("컬렉션 설명 없음")) {
				buff.append(" , descr = '" + descr +"' ");
			}
		}
		
		// db 작업
		CollDao cDao = new CollDao();
		String psql = buff.toString();
		int cnt = 0;
		
		// 맨 앞 콤마 지워주기
		if(psql.length() > 1) {
			psql = psql.substring(3);
			
			// psql이 존재하는경우에만 실행
			// 컬렉션 관련 변경사항 질의명령
			cnt = cDao.editColl(cno, psql);
			if(cnt != 1) {
				req.setAttribute("isRedirect", true);
				return "/boara/collection/collecEdit.boa?cno=" + cno;
			}
		}
		
		
		
		// 새썸네일 있을경우, 설정해주기
		if(list.size() == 1) {
			// 있던 썸네일 isshow 값 변경
			cDao.oldToY(cno);
			
			// 새 썸네일 설정
			int mno = cDao.getMno(sid);
			cnt = cDao.newToC(mno, list, cno);
			
			if(cnt != 1) {
				req.setAttribute("isRedirect", true);
				return "/boara/collection/collecEdit.boa?cno=" + cno;
			}
		}
		
		// 새 썸네일 없는경우, 체크된 썸네일 있는지 검사
		String sino = multi.getParameter("sthumb");
		if(sino != null) {
			// 있던 썸네일 isshow 값 변경
			cDao.oldToY(cno);
			
			// 선택한 썸네일 설정
			int ino = Integer.parseInt(sino);
			cnt = cDao.selToC(ino);

			if(cnt != 1) {
				req.setAttribute("isRedirect", true);
				return "/boara/collection/collecEdit.boa?cno=" + cno;
			}
		}
		
		// nowPage 꺼내주기
		int nowPage = 1;
		String spage = req.getParameter("nowPage");
		if(spage != null) nowPage = Integer.parseInt(spage);
		
		// 데이터 심어주기
		req.setAttribute("MSG", cno + "번 컬렉션 설정 변경 성공");
		req.setAttribute("VIEW", "/boara/collection/collecList.boa?cid=" + sid);
		req.setAttribute("NOWPAGE", nowPage);
		
		return view;
	}
}
