package com.githrd.boa.controller.p;
/**
 * 이 클래스는 한 게시물에 달린 댓글 리스트를 보여주는 클래스


 * @author	박소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 박소연
 *
 */
import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.p.ReboardDao;
import com.githrd.boa.util.PageUtil;
import com.githrd.boa.vo.p.ReboardVO;

public class ReboardList implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/reboardList";
		int nowPage = 1;
		int bno = 0;
		
		//파라미터 받고
		String spage = req.getParameter("nowPage");
		String sbno = req.getParameter("bno");
		
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		
		if(sbno != null) {			
			bno = Integer.parseInt(sbno);
		}
		
		//총 댓글 수
		ReboardDao rDao = new ReboardDao();
		int total = rDao.getTotalCount(bno);
		
		//페이지
		PageUtil page = new PageUtil(nowPage, total);
		
		
		ArrayList<ReboardVO> list = rDao.getList(page, bno);
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		req.setAttribute("BNO", bno);
		
		return view;
	}

}
