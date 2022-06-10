package com.githrd.boa.controller.k;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.*;

/**
 * 아이디 찾기 처리 요청 클래스
 * @author	김소연
 * @since	2022.05.27
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.27	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class IdSearchProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", null);
		StringBuffer view = new StringBuffer();
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		
		MemberDao mDao = new MemberDao();
		String serid = mDao.getSearchId(name, tel);
		
		view.append("{");
		view.append("\"result\": \"");
		if(serid != null) {
			view.append("OK");
			view.append("\",");
			view.append("\"id\": \"");
			view.append(serid);
			view.append("\"");
			// ==> { "result": "OK", "id": "jennie"}
		} else {
			view.append("NO");
			view.append("\"");
		}
		
		view.append("}");
		return view.toString();
	}

}
