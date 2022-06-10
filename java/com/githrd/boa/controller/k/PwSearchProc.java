package com.githrd.boa.controller.k;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.*;

/**
 *  비밀번호 찾기  요청 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class PwSearchProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		StringBuffer buff = new StringBuffer();
		String id = req.getParameter("id");
		String mail = req.getParameter("mail");

		
		MemberDao mDao = new MemberDao();
		String serpw = mDao.getSerachPw(id, mail);
		System.out.println(serpw);
		
		buff.append("{");
		buff.append("\"result\": \"");
		if(serpw != null) {
			buff.append("OK");
			buff.append("\",");
			buff.append("\"pw\": \"");
			buff.append(serpw);
			buff.append("\"");
			// ==> { "result": "OK", "pw": "12345"}
		} else {
			buff.append("NO");
			buff.append("\"");
		}
		
		buff.append("}");
		return buff.toString();
	}

}

