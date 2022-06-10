package com.githrd.boa.controller.p;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.p.MyBuyDao;
import com.githrd.boa.util.p.MyBuyPageUtil;
import com.githrd.boa.vo.p.MyBuyVO;

public class MyBuyBoard implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/mybuyboard";
		
		String sid = (String) req.getSession().getAttribute("SID");
		
		int nowPage = 1;
		
		//로그인 체크 
		if(sid == null) {
			req.setAttribute("isRedirect", true);
			return "/boara/member/login.boa"; 
		}
		
		String spage = req.getParameter("nowPage");
		
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		
		//구매한 게시글 수
		MyBuyDao mDao = new MyBuyDao();
		int total = mDao.getTotalCount(sid);
		
		//페이지
		MyBuyPageUtil page = new MyBuyPageUtil(nowPage, total);
		
		ArrayList<MyBuyVO> list = mDao.getList(page, sid);
		
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		
		return view;
	}

}
