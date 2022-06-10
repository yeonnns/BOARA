package com.githrd.boa.sql.c;
/**
 * 	게시글 관련 질의명령 작성 페이지입니다.
 * 	@author 최이지
 * 	@since 2022.05.26
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.26	-	클래스 제작
 * 									담당자 : 최이지
 * 
 * 				2022.05.27	-	상수, sql문 추가(SEL_COLLS, ADD_NEWP_THUMB, ADD_NEWP, SEL_COLL_INFO)
 * 								isshow = 'Y' 혹은 isshow != 'N' 조건 추가
 * 								상수, sql문 삭제(SEL_COLL_NAME)
 * 									담당자 : 최이지
 * 
 * 				2022.05.28	-	상수, sql문 추가(SEL_COLL_FILES, SEL_BRD_FILES, SEL_BRD_DETAIL,
 * 								EDIT_BRD, Y_OLD_THUMB, C_NEW_THUMB, C_SEL_THUMB)
 * 
 * 								상수, sql문 수정(SEL_COLL_POSTS)
 * 									담당자 : 최이지
 */
public class BoardSQL {
	public final int SEL_BRD_INFO	=	1001;
	public final int SEL_COLL_POSTS	=	1002;
	public final int SEL_CNT_CNO	=	1003;
	public final int SEL_COLL_INFO	=	1004;
	public final int SEL_COLLS		=	1005;
	public final int SEL_MNO		=	1006;
	public final int SEL_COLL_FILES	=	1007;
	public final int SEL_BRD_FILES	=	1008;
	public final int SEL_BRD_DETAIL	=	1009;
	
	public final int DEL_BRD		=	3001;
	public final int BRD_CLICKED	=	3002;
	public final int ADD_NEWP_THUMB	=	3003;
	public final int ADD_NEWP		=	3004;
	public final int EDIT_BRD		=	3005;
	public final int Y_OLD_THUMB	=	3006;
	public final int C_NEW_THUMB	=	3007;
	public final int C_SEL_THUMB	=	3008;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_BRD_INFO:
			buff.append("SELECT ");
			buff.append("    bno, title, body, wdate, price, cno, cname, NVL(b.genre, 'empty') genre, clicks, b.isshow, id ");
			buff.append("FROM ");
			buff.append("    board b, member m, collection c ");
			buff.append("WHERE ");
			buff.append("    b.isshow != 'N' ");
			buff.append("    AND m.isshow = 'Y' ");
			buff.append("    AND c.isshow = 'Y' ");
			buff.append("    AND m.mno = c.mno ");
			buff.append("    AND cno = b.collection ");
			buff.append("    AND bno = ? ");
			break;
			
		case SEL_COLL_POSTS:
			buff.append("SELECT ");
			buff.append("    rno, bno, title, body, isshow, genre, price ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rno, bno, title, body, isshow, genre, price ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    bno, title, body, b.isshow isshow, NVL(b.genre, 'empty') genre, price ");
			buff.append("                FROM ");
			buff.append("                    board b, collection c, member m ");
			buff.append("                WHERE ");
			buff.append("                    b.collection = cno ");
			buff.append("                    AND c.mno = m.mno ");
			buff.append("                    AND cno = ? ");
			buff.append("                    AND b.isshow != 'N' ");
			buff.append("                ORDER BY ");
			buff.append("                    isshow, bno DESC ");
			buff.append("            ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");
			break;
			
		case SEL_CNT_CNO:
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("    board b, collection c, member m ");
			buff.append("WHERE ");
			buff.append("    b.collection = cno ");
			buff.append("    AND c.mno = m.mno ");
			buff.append("    AND cno = ? ");
			buff.append("    AND b.isshow != 'N' ");
			break;
			
		case SEL_COLL_INFO:
			buff.append("SELECT ");
			buff.append("    cname, NVL(descr, '컬렉션 설명이 없습니다.') descr, NVL(genre, 'empty') genre, ");
			buff.append("    id ");
			buff.append("FROM ");
			buff.append("    collection c, member m ");
			buff.append("WHERE ");
			buff.append("    cno = ? ");
			buff.append("    AND c.isshow = 'Y' ");
			buff.append("    AND m.isshow = 'Y' ");
			buff.append("    AND m.mno = c.mno ");
			break;
			
		case SEL_COLLS:
			buff.append("SELECT ");
			buff.append("    cno, cname ");
			buff.append("FROM ");
			buff.append("    collection c, member m ");
			buff.append("WHERE ");
			buff.append("    m.mno = c.mno ");
			buff.append("    AND m.isshow = 'Y' ");
			buff.append("    AND id = ? ");
			break;
			
		case SEL_MNO:
			buff.append("SELECT ");
			buff.append("    mno ");
			buff.append("FROM ");
			buff.append("    member ");
			buff.append("WHERE ");
			buff.append("    id = ? ");
			buff.append("    AND isshow = 'Y' ");
			break;
			
		case SEL_COLL_FILES:
			buff.append("SELECT ");
			buff.append("    ino, b.bno, upname, savename, imgsize, idate ");
			buff.append("FROM ");
			buff.append("    board b LEFT OUTER JOIN imgfile i ON b.bno = i.bno ");
			buff.append("WHERE ");
			buff.append("    b.isshow != 'N' ");
			buff.append("    AND i.isshow = 'C' ");
			buff.append("    AND whereis = 'P' ");
			buff.append("    AND collection = ? ");
			break;
			
		case SEL_BRD_FILES:
			buff.append("SELECT ");
			buff.append("    ino, upname, savename, imgsize, idate ");
			buff.append("FROM ");
			buff.append("    imgfile i, board b ");
			buff.append("WHERE ");
			buff.append("    i.bno = b.bno ");
			buff.append("    AND i.isshow != 'N' ");
			buff.append("    AND whereis = 'P' ");
			buff.append("    AND b.isshow != 'N' ");
			buff.append("    AND b.bno = ? ");
			break;
			
		case SEL_BRD_DETAIL:
			buff.append("SELECT ");
			buff.append("    title, isshow, collection, price, NVL(genre, 'empty') genre, body ");
			buff.append("FROM ");
			buff.append("    board ");
			buff.append("WHERE ");
			buff.append("    bno = ? ");
			buff.append("    AND isshow != 'N' ");
			break;
// ----------------------------------------------------------------------------------------
		case DEL_BRD:
			buff.append("UPDATE ");
			buff.append("    board ");
			buff.append("SET ");
			buff.append("    isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("    bno = ? ");
			break;
			
		case BRD_CLICKED:
			buff.append("UPDATE ");
			buff.append("    board ");
			buff.append("SET ");
			buff.append("    clicks = clicks + 1 ");
			buff.append("WHERE ");
			buff.append("    bno = ? ");
			break;
			
		case ADD_NEWP_THUMB:
			buff.append("INSERT INTO ");
			buff.append("    imgfile(ino, mno, bno, upname, savename, imgsize, isshow, whereis) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(ino)+1, 100001) FROM imgfile), ");
			buff.append("    ? , ");
			buff.append("    (SELECT NVL(MAX(bno)+1, 100001) FROM board), ");
			buff.append("    ?, ?, ?, 'C', 'P' ");
			buff.append(") ");
			break;
			
		case ADD_NEWP:
			buff.append("INSERT INTO ");
			buff.append("    board(bno, title, body, price, collection, genre, isshow) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(bno)+1, 100001) FROM board), ");
			buff.append("    ?, ?, ?, ?, ?, ? ");
			buff.append(") ");
			break;
			
		case EDIT_BRD:
			buff.append("UPDATE ");
			buff.append("    board ");
			buff.append("SET ");
			buff.append("    ### ");
			buff.append("WHERE ");
			buff.append("    isshow != 'N' ");
			buff.append("    AND bno = ? ");
			break;
			
		case Y_OLD_THUMB:
			buff.append("UPDATE ");
			buff.append("    imgfile ");
			buff.append("SET ");
			buff.append("    isshow = 'Y' ");
			buff.append("WHERE ");
			buff.append("    whereis = 'P' ");
			buff.append("    AND isshow = 'C' ");
			buff.append("    AND bno = ( ");
			buff.append("        SELECT ");
			buff.append("            bno ");
			buff.append("        FROM ");
			buff.append("            board ");
			buff.append("        WHERE ");
			buff.append("            bno = ? ");
			buff.append("            AND isshow != 'N' ");
			buff.append("    ) ");
			break;
			
		case C_NEW_THUMB:
			buff.append("INSERT INTO ");
			buff.append("    imgfile(ino, mno, bno, upname, savename, imgsize, isshow, whereis) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(ino)+1, 100001) FROM imgfile), ");
			buff.append("    ? , ? , ");
			buff.append("    ? , ? , ? , 'C' , 'P' ");
			buff.append(") ");
			break;
			
		case C_SEL_THUMB:
			buff.append("UPDATE ");
			buff.append("    imgfile ");
			buff.append("SET ");
			buff.append("    isshow = 'C' ");
			buff.append("WHERE ");
			buff.append("    ino = ? ");
			break;
		}
		
		return buff.toString();
	}
}
