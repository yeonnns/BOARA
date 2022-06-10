package com.githrd.boa.controller.k;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.k.MemberDao;
import com.githrd.boa.util.FileUtil;
import com.githrd.boa.vo.FileVO;
import com.oreilly.servlet.MultipartRequest;

public class EditInfoProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/boara/member/myinfo.boa";
		String sid = (String) req.getSession().getAttribute("SID");
		if(sid == null) {
			return "/boara/member/login.boa";
		}
		FileUtil fUtil = new FileUtil(req, "/resources/upload");
		MultipartRequest multi = fUtil.getMulti();
		
		String id = multi.getParameter("id");
		String pw = multi.getParameter("pw");
		String mail = multi.getParameter("mail");
		String tel = multi.getParameter("tel");
		
		FileVO fVO = fUtil.getList().get(0); 
		fVO.setId(id);
		
		StringBuffer buff = new StringBuffer(" ");
		if(pw != null) {
			buff.append(" , pw = '" + pw + "' ");
		}
		if(mail != null) {
			buff.append(" , mail = '" + mail + "' ");
		}
		if(tel != null) {
			buff.append(" , tel = '" + tel + "' ");
		}
		
		MemberDao mDao = new MemberDao();
		if(buff.toString().length() > 1 ) {
			String psql = buff.toString().substring(3);
			
			int cnt = mDao.editInfo(sid, psql);
			
			if(cnt != 1 )   {
				view = "/boara/member/editInfo.boa";
			}
		}
		
		mDao.addImgInfo(fVO);
		
		return view;

	}
}
