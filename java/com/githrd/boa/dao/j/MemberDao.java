package com.githrd.boa.dao.j;

import java.sql.*;
import java.util.*;

import com.githrd.boa.db.*;
import com.githrd.boa.sql.j.*;
import com.githrd.boa.util.PageUtil;
import com.githrd.boa.vo.j.*;



/**
 * 이 클래스는 회원 관련 데이터베이스 작업을 전담해서 처리하는 클래스
 * @author	정준영
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 정준영
 *
 */


public class MemberDao {
	private BoaDBCP db;
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberSQL mSQL;
	
	
	public MemberDao() {
		db = new BoaDBCP();
		mSQL = new MemberSQL();
	}
	
	public MemberVO getMemberInfo(String id) {
		MemberVO mVO = new MemberVO();
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_INFO);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			mVO.setId(rs.getString("id"));
			mVO.setMno(rs.getInt("mno"));
			mVO.setAvt(rs.getString("savename"));
			mVO.setJdate(rs.getDate("joindate"));
			mVO.setSdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return mVO;
	}
	public int getMyPoint(int mno) {
		int point = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MYPOINT);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			point = rs.getInt("sumpoint");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return point;
	}
	public int getMno(String id) {
		int smno = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_MNO);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			smno = rs.getInt("mno");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return smno;
	}
	
	public int getBoardCnt(int mno) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_BOARD_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	public int getReplyCnt(int mno) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_REPLY_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	public int getPointHistoryCnt(int mno) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_POINTHISTORY_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	public int getMarkWishCnt(int mno) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MARK_WISH_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	public int getMarkLikeCnt(int mno) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MARK_WISH_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	public ArrayList<MemberVO> getBoardList(PageUtil page, int mno){
		System.out.println("mno : " + mno);
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_BOARD_LIST);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setRno(rs.getInt("rno"));
				mVO.setBno(rs.getInt("bno"));
				mVO.setTitle(rs.getString("title"));
				mVO.setJdate(rs.getDate("wdate"));
				mVO.setClicks(rs.getInt("clicks"));
				mVO.setSdate();
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	public ArrayList<MemberVO> getReplyList(PageUtil page, int mno){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_REPLY_LIST);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setRno(rs.getInt("rno"));
				mVO.setBody(rs.getString("body"));
				mVO.setJdate(rs.getDate("rdate"));
				mVO.setIsshow(rs.getString("isshow"));
				mVO.setSdate();
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	public ArrayList<MemberVO> getMarkWishList(PageUtil page, int mno){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MARK_WISH_LIST);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setRno(rs.getInt("rno"));
				mVO.setTitle(rs.getString("title"));
				mVO.setJdate(rs.getDate("wdate"));
				mVO.setClicks(rs.getInt("clicks"));
				mVO.setSdate();
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	public ArrayList<MemberVO> getMarkLikeList(PageUtil page, int mno){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MARK_LIKE_LIST);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setRno(rs.getInt("rno"));
				mVO.setTitle(rs.getString("title"));
				mVO.setJdate(rs.getDate("wdate"));
				mVO.setClicks(rs.getInt("clicks"));
				mVO.setSdate();
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
	
	public ArrayList<MemberVO> getPointHistory(PageUtil page, int mno){
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_POINTHISTORY);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mVO = new MemberVO();
				mVO.setDet(rs.getString("det"));
				mVO.setGnp(rs.getInt("gnp"));
				mVO.setJdate(rs.getDate("pdate"));
				mVO.setDcode(rs.getInt("dcode"));
				mVO.setSdate();
				list.add(mVO);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return list;
	}
}
