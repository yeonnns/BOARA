package com.githrd.boa.controller.k;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.MemberDao;
import com.githrd.boa.vo.k.MemberVO;
/**
 * 회원정보 수정 폼 보여주는 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class EditInfo implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/member/editInfo";
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			view = "/boara/member/login.boa";
			req.setAttribute("isRedirect", true);
			return view;
		}
	
		MemberDao mDao = new MemberDao();
		MemberVO mVO = mDao.getIdInfo(sid);

		
		// 데이터 심고
		req.setAttribute("DATA", mVO);
		req.setAttribute("LIST", mVO.getList());
		
		return view;
	
	}

}
