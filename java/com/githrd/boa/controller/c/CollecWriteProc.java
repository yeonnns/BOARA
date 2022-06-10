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
import com.githrd.boa.util.*;
import com.githrd.boa.dao.c.*;
import com.githrd.boa.vo.*;
import com.githrd.boa.vo.c.*;
import com.oreilly.servlet.*;
public class CollecWriteProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 본인 아이디 받고, 반환값 초기화
		req.setAttribute("isRedirect", true);
		String sid = (String)req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/boara/member/login.boa";
		}
		String view = "/boara/collection/collecList.boa?cid=" + sid;
		
		// 파일 업로드 경로 설정
		FileUtil futil = new FileUtil(req, "/resources/upload/");
		MultipartRequest multi = futil.getMulti();
		
		// 파라미터 꺼내기
		String cname = multi.getParameter("cname");
		String descr = multi.getParameter("descr");
		String genre = multi.getParameter("genre");
		ArrayList<FileVO> list = futil.getList();
		
		// 데이터 세팅
		CollVO cvo = new CollVO();
		CollDao cDao = new CollDao();
		cvo.setCname(cname);
		cvo.setDescr(descr);
		cvo.setMno(cDao.getMno(sid));
		cvo.setSgenre(genre);
		cvo.setList(list);
		
		// db 작업
		int cnt = cDao.addCollThumb(cvo);
		
		// 결과에 따른 처리
		if(cnt != 1) {// 실패시 : 다시 작성폼으로
			view = "/boara/collection/collecWrite.boa";
		}
		
		return view;
	}

}
