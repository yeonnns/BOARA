package com.githrd.boa.dao.d;

import java.sql.*;
import java.util.*;

import com.githrd.boa.db.*;
import com.githrd.boa.sql.d.*;
import com.githrd.boa.vo.d.*;

/**
 * 이 클래스는   
 * 	
 * @author	양동수
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.27	-	클래스제작
 * 									담당자 ] 양동수
 */
public class MailDao {
	
	private BoaDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MailSQL mSQL;
	
	public MailDao() {
		db = new BoaDBCP();
		mSQL = new MailSQL();
	}

	//cert테이블 입력
	public int insertCert(String id, int code) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.INSERT_NEW_CERT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, id);
			pstmt.setInt(2, code);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	//DB에서 가장 마지막에 cert신청한 유져의 id가져오기 전담처리함수
	public String getLastCert() {
		String id, idcode = "";
		int code;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.GET_LAST_CERT);
		stmt = db.getStmt(con);
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			id = rs.getString("id");
			code = rs.getInt("code");
			idcode = id+'&'+code;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		return idcode;
	}
	
	//이메일링크를 클릭하여 인증된 id의 cert isverify 변경 전담 처리함수
	public int certAfter(String id, int code) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.UP_CERT_AFTER);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			pstmt.setInt(2, code);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
}