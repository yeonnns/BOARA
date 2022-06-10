package com.githrd.boa.controller.j;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.*;
import com.githrd.boa.dao.j.*;
import com.githrd.boa.vo.j.*;

/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author	정준영
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 정준영 .
 *
 */

public class Myinfo implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sid = (String) req.getSession().getAttribute("SID");
		String view = "/member/myinfo";
		if(sid == null) {
			req.setAttribute("isRirect", true);
			return view = "/member/join.boa";
		}
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getMemberInfo(sid);
		int mno = mDao.getMno(sid);
		mVO.setPoint(mDao.getMyPoint(mno));
		int bcnt = mDao.getBoardCnt(mVO.getMno());
		int rcnt = mDao.getReplyCnt(mVO.getMno());
		mVO.setBcnt(bcnt);
		mVO.setRcnt(rcnt);
		
		
		req.setAttribute("DATA", mVO);
		return view; 
	}

}
