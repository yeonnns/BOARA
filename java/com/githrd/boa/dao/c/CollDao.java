package com.githrd.boa.dao.c;
/**
 * 	컬렉션 관련 db 작업을하는 Dao 클래스입니다.
 * 	@author 최이지
 * 	@since 2022.05.25
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.25	-	클래스 제작
 * 									담당자 : 최이지
 * 
 * 				2022.05.26	-	함수 추가(delColl)
 * 									담당자	: 최이지
 * 
 * 				2022.05.27	-	함수 추가(addFile, addColl, addCollThumb , getMno)
 * 									담당자 : 최이지
 * 
 * 				2022.05.28	-	함수 추가(getFiles, cnoInfo, getCollFiles, 
 * 								editColl, oldToY, newToC, selToC)
 * 
 * 								함수 수정(getAllColl, getCollForId)
 * 									담당자 : 최이지
 */
import java.util.*;
import java.sql.*;
import com.githrd.boa.db.*;
import com.githrd.boa.sql.c.*;
import com.githrd.boa.vo.*;
import com.githrd.boa.vo.c.*;
import com.githrd.boa.util.*;
public class CollDao {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	BoaDBCP db;
	CollSQL cSQL;
	
	public CollDao() {
		db = new BoaDBCP();
		cSQL = new CollSQL();
	}

	// 장르 리스트
	public HashMap<Integer, String> genrList(){
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		
		con = db.getCon();
		stmt = db.getStmt(con);
		String sql = cSQL.getSQL(cSQL.SEL_GENRE);
		
		try {
			rs = stmt.executeQuery(sql);
			
			// 맵에 입력
			while(rs.next()) {
				int gno = rs.getInt("gno");
				String gname = rs.getString("gname");
				
				map.put(gno, gname);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		
		return map;
	}

	// 모든 컬렉션 파일 뽑아오기
	public HashMap<Integer, FileVO> getFiles(){
		HashMap<Integer, FileVO> map = new HashMap<Integer, FileVO>();
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_FILES);
		stmt = db.getStmt(con);
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				FileVO fvo = new FileVO();
				
				fvo.setDir("/resources/upload/");
				fvo.setFno(rs.getInt("ino"));
				fvo.setRno(rs.getInt("cno"));
				fvo.setOriname(rs.getString("upname"));
				fvo.setSavename(rs.getString("savename"));
				fvo.setLen(rs.getLong("imgsize"));
				fvo.setWdate(rs.getDate("idate"));
				fvo.setWtime(rs.getTime("idate"));
				fvo.setSdate();
				
				map.put(rs.getInt("cno"), fvo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return map;
	}
	
	// 컬렉션 이미지 히스토리 뽑아오기
	public ArrayList<FileVO> getCollFiles(int cno){
		ArrayList<FileVO> flist = new ArrayList<FileVO>();
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_COLL_FILES);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FileVO fvo = new FileVO();
				
				fvo.setDir("/resources/upload/");
				fvo.setFno(rs.getInt("ino"));
				fvo.setRno(cno);
				fvo.setOriname(rs.getString("upname"));
				fvo.setSavename(rs.getString("savename"));
				fvo.setLen(rs.getLong("imgsize"));
				fvo.setWdate(rs.getDate("idate"));
				fvo.setWtime(rs.getTime("idate"));
				fvo.setSdate();
				
				flist.add(fvo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return flist;
	}
	
	// 모든 컬렉션 뽑아오기
	public ArrayList<CollVO> getAllColl(PageUtil page){
		ArrayList<CollVO> list = new ArrayList<CollVO>();
		HashMap<Integer, String> gmap = genrList();
		HashMap<Integer, FileVO> fmap = getFiles();
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_ALL_COLL);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, page.getStartCont());
			pstmt.setInt(2, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CollVO cvo = new CollVO();
				
				int cno = rs.getInt("cno");
				cvo.setCrno(rs.getInt("rno"));
				cvo.setCno(cno);
				cvo.setCname(rs.getString("cname"));
				cvo.setMno(rs.getInt("mno"));
				cvo.setId(rs.getString("id"));
				
				// nullable : descr
				String desc = rs.getString("descr");
				if((desc != null) && (desc.length() > 20)) {
					desc = desc.substring(0, 20) + "...";
				}
				cvo.setDescr(desc);
				
				// nullable : genre
				String genre = rs.getString("genre");
				ArrayList<String> genr = new ArrayList<String>();
				if(!genre.equals("empty")) {
					String[] gens = genre.split("/");
					
					// 장르번호로 장르 찾기
					for(String g: gens) {
						int gno = Integer.parseInt(g);
						genr.add(gmap.get(gno));
					}
				}
				cvo.setGenre(genr);
				
				// 파일
				if(fmap.containsKey(cno)) {
					FileVO fvo = fmap.get(cno);
					ArrayList<FileVO> flist = new ArrayList<FileVO>();
					flist.add(fvo);
					cvo.setList(flist);
				}
				
				list.add(cvo);
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
	
	// id로 컬렉션 뽑아오기
	public ArrayList<CollVO> getCollForId(String id, PageUtil page){
		ArrayList<CollVO> list = new ArrayList<CollVO>();
		HashMap<Integer, String> gmap = genrList();
		HashMap<Integer, FileVO> fmap = getFiles();
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_COLL_ID);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, id);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CollVO cvo = new CollVO();
				
				int cno = rs.getInt("cno");
				cvo.setCrno(rs.getInt("rno"));
				cvo.setCno(cno);
				cvo.setCname(rs.getString("cname"));
				cvo.setMno(rs.getInt("mno"));
				cvo.setId(rs.getString("id"));
				
				// nullable : descr
				String desc = rs.getString("descr");
				if((desc != null) && (desc.length() > 20)) {
					desc = desc.substring(0, 20) + "...";
				}
				cvo.setDescr(desc);
				
				// nullable : genre
				String genre = rs.getString("genre");
				ArrayList<String> genr = new ArrayList<String>();
				if(!genre.equals("empty")) {
					String[] gens = genre.split("/");
					
					// 장르번호로 장르 찾기
					for(String g: gens) {
						int gno = Integer.parseInt(g);
						genr.add(gmap.get(gno));
					}
				}
				cvo.setGenre(genr);
				
				// 파일
				if(fmap.containsKey(cno)) {
					FileVO fvo = fmap.get(cno);
					ArrayList<FileVO> flist = new ArrayList<FileVO>();
					flist.add(fvo);
					cvo.setList(flist);
				}
				
				list.add(cvo);
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

	// id로 컬렉션 몇개 있는지
	public int countCno(String id) {
		int cnt = 0;
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_CNO_CNT);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, id);
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
	
	// 모든 컬렉션 수 가져오기
	public int countAll() {
		int cnt = 0;
		
		con = db.getCon();
		stmt = db.getStmt(con);
		String sql = cSQL.getSQL(cSQL.SEL_ALL_CNT);
		
		try {
			rs = stmt.executeQuery(sql);
			rs.next();
			
			cnt = rs.getInt("cnt");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(stmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 컬렉션 삭제
	public int delColl(int cno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.DEL_COLL);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 파일 업로드
	public int addFile(FileVO fvo, int mno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.ADD_NEWCOLL_THUMB);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			pstmt.setString(2, fvo.getOriname());
			pstmt.setString(3, fvo.getSavename());
			pstmt.setLong(4, fvo.getLen());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 컬렉션 생성
	public int addColl(CollVO cvo) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.ADD_NEWCOLL);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, cvo.getCname());
			pstmt.setString(2, cvo.getDescr());
			pstmt.setInt(3, cvo.getMno());
			pstmt.setString(4, cvo.getSgenre());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 썸네일과 함께 컬렉션 생성
	public int addCollThumb(CollVO cvo) {
		int cnt = 0;
		
		// 컬렉션 등록
		cnt = addColl(cvo);
		if(cnt != 1) {
			return cnt;
		}
		
		// 파일 등록
		ArrayList<FileVO> list = cvo.getList();
		for(FileVO f : list) {
			cnt += addFile(f, cvo.getMno());
		}
		
		return cnt;
	}
	
	// id -> mno
	public int getMno(String id) {
		int mno = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_MNO);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			
			mno = rs.getInt("mno");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return mno;
	}
	
	// cno -> 정보 찾기
	public CollVO cnoInfo(int cno) {
		HashMap<Integer, String> gmap = genrList();
		ArrayList<FileVO> flist = getCollFiles(cno);
		CollVO cvo = new CollVO();
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.SEL_COLL_CNO);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			cvo.setCno(cno);
			cvo.setCname(rs.getString("cname"));
			cvo.setDescr(rs.getString("descr"));
			cvo.setId(rs.getString("id"));
			
			// 장르
			String genre = rs.getString("genre");
			cvo.setSgenre(genre);
			ArrayList<String> genr = new ArrayList<String>();
			if(!genre.equals("empty")) {
				String[] gens = genre.split("/");
				
				// 장르번호로 장르 찾기
				for(String g: gens) {
					int gno = Integer.parseInt(g);
					genr.add(gmap.get(gno));
				}
			}
			cvo.setGenre(genr);
			
			// 파일
			cvo.setList(flist);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return cvo;
	}
	
	// 컬렉션 정보 변경
	public int editColl(int cno, String psql) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.EDIT_COLL);
		sql = sql.replace("###", psql);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 새 썸네일 등록시 isshow 값 변경
	public int oldToY(int cno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.Y_OLD_THUMB);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 새 썸네일 등록시 isshow = 'C' 로 등록
	public int newToC(int mno, ArrayList<FileVO> flist, int cno) {
		int cnt = 0;
			
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.C_NEW_THUMB);
		pstmt = db.getPstmt(con, sql);
			
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, cno);
			pstmt.setString(3, flist.get(0).getOriname());
			pstmt.setString(4, flist.get(0).getSavename());
			pstmt.setLong(5, flist.get(0).getLen());
			
			cnt = pstmt.executeUpdate();
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}		
		
		return cnt;
	}

	// 썸네일 선택시 isshow = 'C'로 변경
	public int selToC(int ino) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = cSQL.getSQL(cSQL.C_SEL_THUMB);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, ino);
			
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