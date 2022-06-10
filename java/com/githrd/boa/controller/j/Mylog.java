package com.githrd.boa.controller.j;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.*;
import com.githrd.boa.dao.j.*;
import com.githrd.boa.vo.j.*;
import com.githrd.boa.util.*;
/**
 * @author	정준영
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 정준영
 *
 */

public class Mylog implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sid = (String) req.getSession().getAttribute("SID");
		String division = req.getParameter("division");
		String view = "/member/mylog";
		if(sid == null) {
			req.setAttribute("isRirect", true);
			return view = "/member/join.boa";
		}
		
		int nowPage = 1;
		String spage = req.getParameter("nowPage");
		if(spage != null) {
			nowPage = Integer.parseInt(spage);
		}
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getMemberInfo(sid);
		int mno = mDao.getMno(sid);
		mVO.setPoint(mDao.getMyPoint(mno));
		int bcnt = mDao.getBoardCnt(mno);
		int rcnt = mDao.getReplyCnt(mno);
		int cnt = 0;
		PageUtil page = null;
		ArrayList<MemberVO> list = null;
		if(division == null || division.equals("board") ) {	
			page = new PageUtil(nowPage, bcnt);
			list = mDao.getBoardList(page, mno);
		} else if(division.equals("reply")) {
			page = new PageUtil(nowPage, rcnt);
			list = mDao.getReplyList(page, mno);
		} else if(division.equals("wish")) {
			cnt = mDao.getMarkWishCnt(mno);
			page = new PageUtil(nowPage, cnt);
			list = mDao.getMarkWishList(page, mno);
		} else if(division.equals("like")) {
			cnt = mDao.getMarkLikeCnt(mno);
			page = new PageUtil(nowPage, cnt);
			list = mDao.getMarkLikeList(page, mno);
		}
		mVO.setBcnt(bcnt);
		mVO.setRcnt(rcnt);
		
		
		req.setAttribute("DATA", mVO);
		req.setAttribute("LIST", list);
		req.setAttribute("PAGE", page);
		req.setAttribute("DIVISION", division);
		return view; 
	}

}
