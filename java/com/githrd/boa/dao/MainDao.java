package com.githrd.boa.dao;
/**
 * 이 클래스는 메인 페이지 관련 데이터베이스 작업을 전담해서 처리하는 클래스

 * @author	박소연
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.26	-	클래스제작
 * 									담당자 ] 박소연
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.githrd.boa.db.BoaDBCP;
import com.githrd.boa.sql.MainSQL;
import com.githrd.boa.util.p.MainPageUtil;
import com.githrd.boa.vo.MainVO;

public class MainDao {
	private BoaDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private MainSQL mSQL;
	
	public MainDao() {
		db = new BoaDBCP();
		mSQL = new MainSQL();
	}
	
	//모든 컬렉션 조회 함수
	public ArrayList<MainVO> getCollList(MainPageUtil page) {
		ArrayList<MainVO> list = new ArrayList<MainVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ALL_COLLECTION);
		pstmt = db.getPstmt(con, sql);
		
		try {
			
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MainVO mVO = new MainVO();
				mVO.setRowno(rs.getInt("rowno"));
				mVO.setCno(rs.getInt("cno"));
				
				String cname = rs.getString("cname");
				if(cname.length() > 8) {
					cname = cname.substring(0, 7) + "..";
				}
				mVO.setCname(cname);
				
				String savename = rs.getString("savename");
				if (savename == null) {
					savename = "noimage.jpg";
				}
				mVO.setSavename(savename);
				list.add(mVO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return list;
	}
	
	//총 컬렉션 갯수 조회 함수
	public int getTotalCoun() {
		int cnt = 0;
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_TOTAL_CNT);
		stmt = db.getStmt(con);
		
		try {
			rs = stmt.executeQuery(sql);
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
