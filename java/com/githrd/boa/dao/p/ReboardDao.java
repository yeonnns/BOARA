package com.githrd.boa.dao.p;
/**
 * 이 클래스는 댓글 게시판 관련 데이터베이스 작업을 전담해서 처리하는 클래스


 * @author	박소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 박소연
 *
 */

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.githrd.boa.db.BoaDBCP;
import com.githrd.boa.sql.p.ReboardSQL;
import com.githrd.boa.util.PageUtil;
import com.githrd.boa.vo.p.ReboardVO;

public class ReboardDao {
	
	private BoaDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private ReboardSQL rSQL;
	
	public ReboardDao() {
		db = new BoaDBCP();
		rSQL = new ReboardSQL();
	}
	
	
	//한 게시물에 달린 모든 댓글 리스트 조회 함수
	public ArrayList<ReboardVO> getList(PageUtil page, int bno) {
		ArrayList<ReboardVO> list = new ArrayList<ReboardVO>();
		
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_ALL_LIST);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			rs = pstmt.executeQuery();

			while(rs.next()) {
				ReboardVO rVO = new ReboardVO();
				rVO.setRowno(rs.getInt("rowno"));
				rVO.setRno(rs.getInt("rno"));
				rVO.setBno(rs.getInt("bno"));
				rVO.setUprno(rs.getInt("uprno"));
				rVO.setMno(rs.getInt("mno"));
				rVO.setId(rs.getString("id"));
				rVO.setBody(rs.getString("body"));
				rVO.setRdate(rs.getDate("rdate"));
				rVO.setRtime(rs.getTime("rdate"));
				rVO.setSdate();
				rVO.setSavename(rs.getString("savename"));
				rVO.setIsshow(rs.getString("isshow"));
				rVO.setStep(rs.getInt("step"));
				
				list.add(rVO);
				
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
	
	//총 댓글 수 조회 함수
	public int getTotalCount(int bno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = rSQL.getSQL(rSQL.SEL_TOTAL_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
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
	
	 //로그인한 회원 (작성자) 정보 조회 함수
	 public ReboardVO getWriterInfo(String id, int bno) { 
		 
		 ReboardVO rVO = new ReboardVO();
	 
		 con = db.getCon(); 
		 String sql = rSQL.getSQL(rSQL.SEL_WRITER_INFO); 
		 pstmt = db.getPstmt(con, sql);
		 try {
			 pstmt.setString(1, id);
			 pstmt.setInt(2, bno);
			 rs = pstmt.executeQuery();
			 rs.next();
			 
			 rVO.setMno(rs.getInt("mmno"));
			 rVO.setSavename(rs.getString("savename"));
			 rVO.setBno(rs.getInt("bbno"));
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 db.close(rs);
			 db.close(pstmt);
			 db.close(con);
		 }
		 return rVO;
	 }
	 
	 //댓글&대댓글 작성 데이터베이스 처리 함수
	 public int addReboard(ReboardVO rVO, int bno) { 
		 int cnt = 0;
		 con = db.getCon();
		 String sql = rSQL.getSQL(rSQL.INSERT_REBOARD);
		 pstmt = db.getPstmt(con, sql);
		 
		 try {
			 pstmt.setInt(1, bno);
			 //걍 댓글인 경우
			 if(rVO.getUprno() == 0) {
				 pstmt.setNull(2, java.sql.Types.NULL);
			 } else {
			 //대댓글인 경우
				 pstmt.setInt(2, rVO.getUprno());
			 }
			 pstmt.setString(3, rVO.getId());
			 pstmt.setInt(4, rVO.getMno());
			 pstmt.setString(5, rVO.getBody());
			 pstmt.setString(6, rVO.getIsshow());
			 cnt = pstmt.executeUpdate();
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 db.close(pstmt);
			 db.close(con);
		 }
		 return cnt; 
	 }

	 //대댓글 작성 시 원댓글 본문만 조회 함수
	 public ReboardVO getReboardInfo(int rno) {
		 ReboardVO rVO = new ReboardVO();
		 
		 con = db.getCon();
		 String sql = rSQL.getSQL(rSQL.SEL_REBOARD_INFO);
		 pstmt = db.getPstmt(con, sql);
		 
		 try {
				pstmt.setInt(1, rno);
				rs = pstmt.executeQuery();
				rs.next();
				rVO.setRno(rs.getInt("rrno"));
				rVO.setBno(rs.getInt("rbno"));
				rVO.setUprno(rs.getInt("uprno"));
				rVO.setMno(rs.getInt("rmno"));
				rVO.setId(rs.getString("rid"));
				rVO.setBody(rs.getString("body"));
				rVO.setRdate(rs.getDate("rdate"));
				rVO.setRtime(rs.getTime("rdate"));
				rVO.setSdate();
				rVO.setSavename(rs.getNString("fsavename"));
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				db.close(rs);
				db.close(pstmt);
				db.close(con);
			}		
				 
		 return rVO;
	 }
	 
	 //댓글 수정 시 데이터 조회하는 함수
	 public ReboardVO getEditData(int rno, String id) {
		 ReboardVO rVO = new ReboardVO();
		 
		 con = db.getCon();
		 String sql = rSQL.getSQL(rSQL.SEL_REBOARD_INFO);
		 pstmt = db.getPstmt(con, sql);
		 
		 try {
			 pstmt.setInt(1, rno);
			 pstmt.setString(2, id);
			 rs = pstmt.executeQuery();
			 rs.next();
			 rVO.setRno(rs.getInt("rrno"));
			 rVO.setBno(rs.getInt("rbno"));
			 rVO.setUprno(rs.getInt("uprno"));
			 rVO.setMno(rs.getInt("rmno"));
			 rVO.setBody(rs.getString("body"));
			 rVO.setRdate(rs.getDate("rdate"));
			 rVO.setRtime(rs.getTime("rdate"));
			 rVO.setSdate();
			 rVO.setSavename(rs.getString("fsavename"));
			 rVO.setIsshow(rs.getString("risshow"));
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 db.close(rs);
			db.close(pstmt);
			db.close(con);
		 }
		 return rVO;
	 }
	 
	 //댓글 수정 데이터베이스 작업 전담 함수
	 public int editReboard(int rno, String body, String isshow) {
	 	int cnt = 0;
	 	
	 	con = db.getCon();
	 	String sql = rSQL.getSQL(rSQL.UPDATE_REBOARD);
	 	pstmt = db.getPstmt(con, sql);
	 	
	 	try {
	 		pstmt.setString(1, body);
	 		pstmt.setString(2, isshow);
	 		pstmt.setInt(3, rno);
	 		cnt = pstmt.executeUpdate();
	 	} catch(Exception e) {
	 		e.printStackTrace();
	 	} finally {
	 		db.close(pstmt);
 			db.close(con);
	 	}
	 	return cnt;
	 }
	 
	 //댓글 삭제 데이터베이스 작업 함수
	 public int delReboard(int rno) {
		 int cnt = 0;
		 
		 con = db.getCon();
		 String sql = rSQL.getSQL(rSQL.DEL_REBOARD);
		 pstmt = db.getPstmt(con, sql);
		 
		 try {
			 pstmt.setInt(1, rno);
			 cnt = pstmt.executeUpdate();
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 db.close(pstmt);
			 db.close(con);
		 }
		 return cnt;
	 }
	 
	 //댓글 & 대댓글 작성 시 포인트 적립 처리하는 함수
	 public int addPoint(int mno, int gnp, int dcode) {
		 int cnt = 0;
		 
		 con = db.getCon();
		 String sql = rSQL.getSQL(rSQL.INSERT_POINT);
		 pstmt = db.getPstmt(con, sql);
		 
		 try {
			 pstmt.setInt(1, mno);
			 pstmt.setInt(2, gnp);
			 pstmt.setInt(3, dcode);
			 pstmt.setInt(4, mno);
			 pstmt.setInt(5, gnp);
			 cnt = pstmt.executeUpdate();
		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 db.close(pstmt);
			 db.close(con);
		 }
		 return cnt;
	 }
}
