package com.githrd.boa.dao.k;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.githrd.boa.db.BoaDBCP;
import com.githrd.boa.sql.k.MemberSQL;
import com.githrd.boa.vo.FileVO;
import com.githrd.boa.vo.k.MemberVO;

/**
 * 이 클래스는 회원가입,로그인,수정 탈퇴 관련 
 * 		데이터베이스 작업을 전담해서 처리하는 클래스
 * @author	김소연
 * @since	2022.05.24
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.24	-	클래스제작
 * 									담당자 ] 김소연
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
	// 로그인 처리
	public int getLogin(String id, String pw) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_LOGIN_CNT);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// ID 중복 확인
	public int getIdCount(String id) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ID_CNT);
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
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	// MAIL 중복 확인
	public int getMailCount(String mail) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MAIL_CNT);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, mail);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	// TEL 중복 확인
	public int getTelCount(String tel) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_TEL_CNT);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, tel);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("cnt");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// ID로 회원 정보 조회
	public MemberVO getIdInfo(String id) {
		MemberVO mVO = new MemberVO();
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		mVO.setList(list);
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_MEMBER_INFO);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.isFirst()) {
					mVO.setMno(rs.getInt("mno"));
					mVO.setName(rs.getString("name"));
					mVO.setId(rs.getString("id"));
					mVO.setPw(rs.getString("pw"));
					mVO.setMail(rs.getString("mail"));
					mVO.setTel(rs.getString("tel"));
				}
				FileVO fVO = new FileVO();
				fVO.setFno(rs.getInt("ino"));
				fVO.setSavename(rs.getString("savename"));
				
				list.add(fVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return mVO;
	}

	
	// 이름, 전화번호로 ID 찾기
	public String getSearchId(String name, String tel) {
		String id = "";
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_ID_SEARCH);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			rs = pstmt.executeQuery();
			rs.next();
			id = rs.getString("id");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return id;
	}
	// 아이디 메일로 PW 찾기
	public String getSerachPw(String id, String mail) {
		String pw = "";
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.SEL_PW_SEARCH);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, id);
			pstmt.setString(2, mail);
			rs = pstmt.executeQuery();
			rs.next();
			pw = rs.getString("pw");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		return pw;
	}
	
	
	
	
	

	// 회원 정보입력 데이터베이스 작업 전담 처리함수
	public int addMemInfo(MemberVO mVO) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.ADD_MEMBER);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, mVO.getName());
			pstmt.setString(2, mVO.getId());
			pstmt.setString(3, mVO.getPw());
			pstmt.setString(4, mVO.getMail());
			pstmt.setString(5, mVO.getTel());
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 단일 파일 정보 데이터베이스 입력 전담 처리함수
	public int addImgInfo(FileVO fVO) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.INSERT_FILEINFO);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, fVO.getId());
			pstmt.setString(2, fVO.getOriname());
			pstmt.setString(3, fVO.getSavename());
			pstmt.setLong(4, fVO.getLen());
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}

	// 회원 정보 수정
	public int editInfo(String sid, String psql) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.EDIT_MEMBER);
		sql = sql.replace("@@@", psql);
		pstmt  = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, sid);
		
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		return cnt;
	}
	
	// 사진 삭제하기 ('Y'->'N')
	public int editImg(int fno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.DEL_IMG);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, fno);
			
			cnt = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	//회원 탈퇴 처리 
	public int delMember(String sid) {
		int cnt = 0;
		con = db.getCon();
		String sql = mSQL.getSQL(mSQL.DEL_MEMBER);
		pstmt = db.getPstmt(con, sql);
		try {
			pstmt.setString(1, sid);
			
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
