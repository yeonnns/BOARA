package com.githrd.boa.controller.k;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.MemberDao;
/**
 * 입력받은 아이디 중복확인 처리 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class IdCheck implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		StringBuffer buff = new StringBuffer();
		String id = req.getParameter("id");
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getIdCount(id);
		
		buff.append("{");
		buff.append("\"result\" : \"");
		if(cnt == 0) {
			buff.append("OK");
		} else {
			buff.append("NO");
		}
		buff.append("\"");
		buff.append("}");

		return buff.toString();
	}

}