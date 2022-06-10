package com.githrd.boa.controller.k;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.MemberDao;
/**
 * 입력받은 메일 중복확인 처리 클래스
 * @author	김소연
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class MailCheck implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		StringBuffer buff = new StringBuffer();
		String mail = req.getParameter("mail");
		MemberDao mDao = new MemberDao();
		int cnt = mDao.getMailCount(mail);
		
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