package com.githrd.boa.db;
/**
 * 	db 작업에 필요한 자원을 만드는 클래스
 * 
 * 	@author 최이지
 * 	@since 2022.05.23
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.23	-	담담자 : 최이지
 * 									클래스 제작
 */
import java.sql.*;

import javax.naming.*;
import javax.sql.*;
public class BoaDBCP {
	private DataSource ds;

	public BoaDBCP() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:comp/env");
			ds = (DataSource) envContext.lookup("jdbc/TestDB");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// Connection 반환 함수
	public Connection getCon() {
		// 반환값 변수 초기화
		Connection con = null;
		
		try {
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	// Statement 반환 함수
	public Statement getStmt(Connection con) {
		// 반환값 변수 초기화
		Statement stmt = null;
		
		try {
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return stmt;
	}
	
	// PreparedStatement 반환 함수
	public PreparedStatement getPstmt(Connection con, String sql) {
		// 반환값 변수 초기화
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return pstmt;
	}
	
	// 자원 반환 함수
	public void close(Object o) {
		try {
			if(o instanceof ResultSet) ((ResultSet)o).close();
			else if(o instanceof PreparedStatement) ((PreparedStatement)o).close();
			else if(o instanceof Statement) ((Statement)o).close();
			else if(o instanceof Connection) ((Connection)o).close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
