package com.githrd.boa.controller.d;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.d.MailDao;
import com.githrd.boa.sql.d.*;

public class MailConfirm implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isredirect", true);
		String view="/member/joinProc.boa";
		
		String mailCode = req.getParameter("code");
		String mailId = req.getParameter("id");
		String dbcode, dbid="";

		MailSQL mSQL = new MailSQL();
		MailDao mDao = new MailDao();
		String idcode = mDao.getLastCert();
		dbid = idcode.substring(0, idcode.indexOf('&'));
		dbcode = idcode.substring(idcode.indexOf('&')+1);
		
		if(mailCode.equals(dbcode)&&mailId.equals(dbid)) {
			req.getSession().setAttribute("SID", dbid);
			int code = Integer.parseInt(dbcode);
			int cnt = mDao.certAfter(dbid, code);
			if(cnt==1) {
				req.setAttribute("isredirect", false);
				view = "/main";
			}
		}
		
		
		
		
		return view;
	}

}
