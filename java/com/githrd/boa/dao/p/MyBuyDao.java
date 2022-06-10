package com.githrd.boa.dao.p;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.githrd.boa.db.BoaDBCP;
import com.githrd.boa.sql.p.MyBuySQL;
import com.githrd.boa.util.p.MyBuyPageUtil;
import com.githrd.boa.vo.p.MyBuyVO;

/**
 * 이 클래스는 마이페이지-구매글 리스트 보여주기 처리하는 클래스


 * @author	박소연
 * @since	2022.05.27
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.27	-	클래스제작
 * 									담당자 ] 박소연
 *
 */
public class MyBuyDao {

	private BoaDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MyBuySQL mSQL;
	
	public MyBuyDao() {
		db = new BoaDBCP();
		mSQL = new MyBuySQL();
	}
	
	//내가 구매한 구매글 리스트 조회 함수
	public ArrayList<MyBuyVO> getList(MyBuyPageUtil page, String id) {
		 ArrayList<MyBuyVO> list = new  ArrayList<MyBuyVO>();
		 
		 con = db.getCon();
		 String sql = mSQL.getSQL(mSQL.SEL_MYBUY_LIST);
		 pstmt = db.getPstmt(con, sql);
		 
		 try {
			 pstmt.setString(1, id);
			 pstmt.setInt(2, page.getStartCont());
			 pstmt.setInt(3, page.getEndCont());
			 rs = pstmt.executeQuery();
			 
			 while(rs.next()) {
				 MyBuyVO mVO = new MyBuyVO();
				 mVO.setRowno(rs.getInt("rowno"));
				 mVO.setBno(rs.getInt("bno"));
				 mVO.setTitle(rs.getString("title"));
				 mVO.setBody(rs.getString("body"));
				 mVO.setWdate(rs.getDate("wdate"));
				 mVO.setWtime(rs.getTime("wdate"));
				 mVO.setSdate();
				 mVO.setId(rs.getString("id"));
				 
				 list.add(mVO);
			 }
			 
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 db.close(rs);
			 db.close(pstmt);
			 db.close(con);
		 }
		 return list;
	}
	
	//내가 구매한 게시글 갯수 조회 함수
	public int getTotalCount(String id) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MYBUY_COUNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return cnt;
	}

}
