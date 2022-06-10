package com.githrd.boa.dao.c;
/**
 * 	게시글 관련 db 작업을하는 Dao 클래스입니다.
 * 	@author 최이지
 * 	@since 2022.05.25
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.25	-	클래스 제작
 * 									담당자 : 최이지
 * 
 * 				2022.05.27	-	함수 추가(getColls, addFile, addPost, addPostThumb, getMno, getCollInfo)
 * 									담당자 : 최이지
 * 
 * 				2022.05.28	-	함수 추가(getBrdFiles, bnoInfo, editBrd)
 * 									담당자 : 최이지
 */
import java.util.*;
import java.sql.*;
import com.githrd.boa.db.*;
import com.githrd.boa.sql.c.*;
import com.githrd.boa.vo.FileVO;
import com.githrd.boa.vo.c.*;
import com.githrd.boa.util.*;
public class BoardDao {
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	BoaDBCP db;
	BoardSQL bSQL;
	CollSQL cSQL;
	
	public BoardDao() {
		db = new BoaDBCP();
		bSQL = new BoardSQL();
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
	
	// 게시글 이미지 히스토리 뽑아오기
	public ArrayList<FileVO> getBrdFiles(int bno){
		ArrayList<FileVO> flist = new ArrayList<FileVO>();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_BRD_FILES);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FileVO fvo = new FileVO();
				
				fvo.setDir("/resources/upload/");
				fvo.setFno(rs.getInt("ino"));
				fvo.setRno(bno);
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
	
	// 게시글 정보 불러오기
	public BoardVO getInfo(int bno){
		BoardVO bvo = new BoardVO();
		HashMap<Integer, String> gmap = genrList();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_BRD_INFO);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			rs.next();
			
			bvo.setCno(rs.getInt("cno"));
			bvo.setBno(rs.getInt("bno"));
			bvo.setTitle(rs.getString("title").replaceAll("\\r\\n", "<br>"));
			bvo.setBody(rs.getString("body").replaceAll("\\r\\n", "<br>"));
			bvo.setWdate(rs.getDate("wdate"));
			bvo.setWtime(rs.getTime("wdate"));
			bvo.setSdate();
			bvo.setPrice(rs.getInt("price"));
			bvo.setCname(rs.getString("cname"));
			bvo.setClicks(rs.getInt("clicks"));
			bvo.setIsshow(rs.getString("isshow"));
			bvo.setId(rs.getString("id"));
			
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
			bvo.setGenre(genr);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return bvo;
	}
	
	// 컬렉션 소속 파일 불러오기
	public HashMap<Integer, FileVO> getFiles(int cno){
		HashMap<Integer, FileVO> fmap = new HashMap<Integer, FileVO>();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_COLL_FILES);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				FileVO fvo = new FileVO();
				
				fvo.setDir("/resources/upload/");
				fvo.setFno(rs.getInt("ino"));
				fvo.setRno(rs.getInt("bno"));
				fvo.setOriname(rs.getString("upname"));
				fvo.setSavename(rs.getString("savename"));
				fvo.setLen(rs.getLong("imgsize"));
				fvo.setWdate(rs.getDate("idate"));
				fvo.setWtime(rs.getTime("idate"));
				fvo.setSdate();
				
				fmap.put(rs.getInt("bno"), fvo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return fmap;
	}

	// 컬렉션 소속 게시글 불러오기
	public ArrayList<BoardVO> getCollPosts(int cno, PageUtil page){
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		HashMap<Integer, String> gmap = genrList();
		HashMap<Integer, FileVO> fmap = getFiles(cno);
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_COLL_POSTS);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			pstmt.setInt(2, page.getStartCont());
			pstmt.setInt(3, page.getEndCont());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				
				int bno = rs.getInt("bno");
				bvo.setBno(bno);
				bvo.setTitle(rs.getString("title"));
				bvo.setIsshow(rs.getString("isshow"));
				bvo.setPrice(rs.getInt("price"));
				
				// 미리보기 처리 : body
				String body = rs.getString("body").replace("\\r\\n", " ");
				body = body.replace("<br>", " ");
				if(body.length() > 20) {
					body = body.substring(0, 20) + "...";
				}
				bvo.setBody(body);
				
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
				bvo.setGenre(genr);
				
				// 파일
				if(fmap.containsKey(bno)) {
					FileVO fvo = fmap.get(bno);
					ArrayList<FileVO> flist = new ArrayList<FileVO>();
					flist.add(fvo);
					bvo.setList(flist);
				}
				
				list.add(bvo);
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

	// 컬렉션 소속 게시글 개수 세기
	public int countCollPosts(int cno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_CNT_CNO);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
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

	// 게시글 삭제
	public int delPost(int bno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.DEL_BRD);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}

	// 조회수 올려주기
	public int viewUp(int bno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.BRD_CLICKED);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// id 소속 컬렉션 불러오기
	public HashMap<Integer, String> getColls(String id){
		HashMap<Integer, String> clist = new HashMap<Integer, String>();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_COLLS);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cno = rs.getInt("cno");
				String cname = rs.getString("cname");
				clist.put(cno, cname);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return clist;
	}

	// 신규포스트 썸네일 등록
	public int addFile(FileVO fvo, int mno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.ADD_NEWP_THUMB);
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
	
	// 포스팅 처리
	public int addPost(BoardVO bvo) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.ADD_NEWP);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setString(1, bvo.getTitle());
			pstmt.setString(2, bvo.getBody());
			pstmt.setInt(3, bvo.getPrice());
			pstmt.setInt(4, bvo.getCno());
			pstmt.setString(5, bvo.getSgenre());
			pstmt.setString(6, bvo.getIsshow());
			
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	
		// id -> mno
		public int getMno(String id) {
			int mno = 0;
			
			con = db.getCon();
			String sql = bSQL.getSQL(bSQL.SEL_MNO);
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

	// 썸네일과 함께 포스팅 처리
	public int addPostThumb(BoardVO bvo) {
		int cnt = 0;
		
		// 게시글 등록
		cnt = addPost(bvo);
		if(cnt != 1) {
			return cnt;
		}
		
		// 파일 등록
		ArrayList<FileVO> list = bvo.getList();
		for(FileVO f : list) {
			cnt += addFile(f, bvo.getMno());
		}
		
		return cnt;
	}

	// 컬렉션 이름, 설명, 장르 가져오기
	public CollVO getCollInfo(int cno) {
		HashMap<Integer, String> gmap = genrList();
		CollVO cvo = new CollVO();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_COLL_INFO);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, cno);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			cvo.setCname(rs.getString("cname"));
			cvo.setDescr(rs.getString("descr"));
			cvo.setId(rs.getString("id"));
			
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
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return cvo;
	}
	
	// bno -> 정보찾기
	public BoardVO bnoInfo(int bno) {
		HashMap<Integer, String> gmap = genrList();
		ArrayList<FileVO> flist = getBrdFiles(bno);
		BoardVO bvo = new BoardVO();
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.SEL_BRD_DETAIL);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			bvo.setBno(bno);
			bvo.setTitle(rs.getString("title"));
			bvo.setIsshow(rs.getString("isshow"));
			bvo.setCno(rs.getInt("collection"));
			bvo.setPrice(rs.getInt("price"));
			bvo.setBody(rs.getString("body"));	//textarea에 넣어주니까 replace 처리X
			
			// 장르
			String genre = rs.getString("genre");
			bvo.setSgenre(genre);
			ArrayList<String> genr = new ArrayList<String>();
			if(!genre.equals("empty")) {
				String[] gens = genre.split("/");
				
				// 장르번호로 장르 찾기
				for(String g: gens) {
					int gno = Integer.parseInt(g);
					genr.add(gmap.get(gno));
				}
			}
			bvo.setGenre(genr);
			
			// 파일
			bvo.setList(flist);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(rs);
			db.close(pstmt);
			db.close(con);
		}
		
		return bvo;
	}
	
	// 게시글 정보 변경
	public int editBrd(int bno, String psql) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.EDIT_BRD);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(2, bno);
			
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
	public int oldToY(int bno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.Y_OLD_THUMB);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, bno);
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.close(pstmt);
			db.close(con);
		}
		
		return cnt;
	}
	
	// 새 썸네일 등록시 isshow = 'C'로 등록
	public int newToC(int mno, ArrayList<FileVO> flist, int bno) {
		int cnt = 0;
		
		con = db.getCon();
		String sql = bSQL.getSQL(bSQL.C_NEW_THUMB);
		pstmt = db.getPstmt(con, sql);
		
		try {
			pstmt.setInt(1, mno);
			pstmt.setInt(2, bno);
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
		String sql = bSQL.getSQL(bSQL.C_SEL_THUMB);
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