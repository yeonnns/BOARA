package com.githrd.boa.controller.k;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.controller.d.GenerateCertNumber;
import com.githrd.boa.controller.d.SendMail;
import com.githrd.boa.dao.d.MailDao;
import com.githrd.boa.dao.k.*;
import com.githrd.boa.util.FileUtil;
import com.githrd.boa.vo.FileVO;
import com.githrd.boa.vo.k.*;
import com.oreilly.servlet.MultipartRequest;
/**
 * 회원가입 처리 요청 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class JoinProc implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("isRedirect", true);
		String view = "/boara/main.boa";
		if(req.getSession().getAttribute("SID") != null) {	
			return view;
		}
		FileUtil futil = new FileUtil(req, "/resources/upload");
		MultipartRequest multi = futil.getMulti();
		String name = multi.getParameter("name");
		String id = multi.getParameter("id");
		String pw = multi.getParameter("pw");
		String tel = multi.getParameter("tel");
		String mail = multi.getParameter("mail");
		
		FileVO fVO = futil.getList().get(0); 
		fVO.setId(id);
		
		MemberVO mVO = new MemberVO();
		mVO.setName(name);
		mVO.setId(id);
		mVO.setPw(pw);
		mVO.setTel(tel);
		mVO.setMail(mail);
		
		GenerateCertNumber gc = new GenerateCertNumber();//랜덤숫자발생기객체생성
        String snum = gc.excuteGenerate();//랜덤숫자발생함수실행
        int inum = Integer.parseInt(snum);//형변환
        
        MailDao maDao = new MailDao();
        maDao.insertCert(id, inum);
        System.out.println(id+"@#"+inum);
        SendMail sm = new SendMail();
        sm.gmailSend(mail, inum, id);

		
		MemberDao mDao = new MemberDao();
		int cnt = mDao.addMemInfo(mVO);
		if(cnt != 1) {
			return "/boara/member/join.boa";
		} else {
			mDao.addImgInfo(fVO);
			
		}
		return view;
	}
	

}