package com.githrd.boa.controller.p;
/**
 * 이 클래스는 댓글 삭제 데이터베이스 작업을 전담해서 처리하는 클래스


 * @author	박소연
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 박소연
 *
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.githrd.boa.controller.BoaInter;
import com.githrd.boa.dao.p.ReboardDao;

public class ReboardDel implements BoaInter {

	@Override
	public String exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String view = "/reboard/redirect";
		
		String srno = req.getParameter("rno");
		int rno = Integer.parseInt(srno);
		
		//데이터베이스 작업
		ReboardDao rDao = new ReboardDao();
		int cnt = rDao.delReboard(rno);
		
		req.setAttribute("VIEW", "/boara/reboard/reboardList.boa");
		
		if(cnt == 0) {
			//실패
			req.setAttribute("MSG", rno + " 번 글, 삭제 작업에 실패했습니다.");			
		} else {
			req.setAttribute("MSG", rno + " 번 글, 삭제 작업에 성공했습니다.");
		}
		return view;
	}

}
