package com.githrd.boa.controller;
/**
 * 	이 클래스는 boara의 메인페이지 뷰를 불러오는 클래스
 * 
 * 	@author 최이지
 * 	@since 2022.05.23
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.23	-	담당자 : 최이지
 * 									클래스 제작
 * 				2022.05.25  - 	담당자 : 박소연
 * 									클래스 수정 (로그인, 로그아웃, 내 정보 버튼)
 *    			2022.05.26  - 	담당자 : 박소연
 * 									클래스 수정 (오늘의 HOT 게시물 탭에 컬렉션 리스트 뜨게 함, 페이징 처리)
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.dao.MainDao;
import com.githrd.boa.util.PageUtil;
import com.githrd.boa.util.p.MainPageUtil;
import com.githrd.boa.vo.MainVO;

public class MainForm implements BoaInter {
	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/main";
		int nowPage = 1;
		
		String spage = req.getParameter("nowPage");
		
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		
		//총 컬렉션 수
		MainDao mDao = new MainDao();
		int total = mDao.getTotalCoun();
		
		MainPageUtil page = new MainPageUtil(nowPage, total);
		
		ArrayList<MainVO> list = mDao.getCollList(page);
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		return view;
	}
}
