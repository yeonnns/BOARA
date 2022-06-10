package com.githrd.boa.sql.c;
/**
 * 	컬렉션 관련 질의명령 작성 페이지입니다.
 * 	@author 최이지
 * 	@since 2022.05.25
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.25	-	클래스 제작
 * 									담당자 : 최이지
 * 
 * 				2022.05.27	-	상수, sql문 추가(ADD_FILE, SEL_MNO)
 * 									담당자 : 최이지
 * 
 * 				2022.05.28	-	상수, sql문 추가(SEL_FILES, SEL_COLL_CNO, SEL_COLL_FILES,
 * 								EDIT_COLL, Y_OLD_THUMB, C_NEW_THUMB, C_SEL_THUMB)
 * 									담당자 : 최이지
 */
public class CollSQL{
	public final int SEL_GENRE			=	1001;
	public final int SEL_ALL_COLL		=	1002;
	public final int SEL_COLL_ID		=	1003;
	public final int SEL_CNO_CNT		=	1004;
	public final int SEL_ALL_CNT		=	1005;
	public final int SEL_MNO			=	1006;
	public final int SEL_FILES			=	1007;
	public final int SEL_COLL_CNO		=	1008;
	public final int SEL_COLL_FILES		=	1009;
	
	public final int DEL_COLL			=	3001;
	public final int ADD_NEWCOLL_THUMB	=	3002;
	public final int ADD_NEWCOLL		=	3003;
	public final int EDIT_COLL			=	3004;
	public final int Y_OLD_THUMB		=	3005;
	public final int C_NEW_THUMB		=	3006;
	public final int C_SEL_THUMB		=	3007;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_GENRE:
			buff.append("SELECT ");
			buff.append("	gno, gname ");
			buff.append("FROM ");
			buff.append("	genre ");
			break;
			
		case SEL_ALL_COLL:
			buff.append("SELECT ");
			buff.append("    rno, cno, cname, descr, mno, id, genre ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rno, cno, cname, descr, mno, id, genre ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    cno, cname, descr, c.mno mno, id, NVL(genre, 'empty') genre ");
			buff.append("                FROM ");
			buff.append("                    collection c, member m ");
			buff.append("                WHERE ");
			buff.append("                    c.isshow = 'Y' ");
			buff.append("                    AND m.isshow = 'Y' ");
			buff.append("                    AND c.mno = m.mno ");
			buff.append("                ORDER BY ");
			buff.append("                    cno DESC ");
			buff.append("            ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");
			break;
			
		case SEL_COLL_ID:
			buff.append("SELECT ");
			buff.append("    rno, cno, cname, descr, mno, id, genre ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rno, cno, cname, descr, mno, id, genre ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    cno, cname, descr, c.mno mno, id, NVL(genre, 'empty') genre ");
			buff.append("                FROM ");
			buff.append("                    collection c, member m ");
			buff.append("                WHERE ");
			buff.append("                    c.isshow = 'Y' ");
			buff.append("                    AND m.isshow = 'Y' ");
			buff.append("                    AND c.mno = m.mno ");
			buff.append("                    AND id = ? ");
			buff.append("                ORDER BY ");
			buff.append("                    cno DESC ");
			buff.append("            ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rno BETWEEN ? AND ? ");
			break;
			
		case SEL_CNO_CNT:
			buff.append("SELECT ");
			buff.append("    MAX(rownum) cnt ");
			buff.append("FROM ");
			buff.append("    collection c, member m ");
			buff.append("WHERE ");
			buff.append("    c.mno = m.mno ");
			buff.append("    AND id = ? ");
			break;
			
		case SEL_ALL_CNT:
			buff.append("SELECT ");
			buff.append("    COUNT(cno) cnt ");
			buff.append("FROM ");
			buff.append("    collection ");
			buff.append("WHERE ");
			buff.append("    isshow = 'Y' ");
			break;
			
		case SEL_MNO:
			buff.append("SELECT ");
			buff.append("    mno ");
			buff.append("FROM ");
			buff.append("    member ");
			buff.append("WHERE ");
			buff.append("    id = ? ");
			break;
			
		case SEL_FILES:
			buff.append("SELECT ");
			buff.append("    ino, cno, upname, savename, imgsize, idate ");
			buff.append("FROM ");
			buff.append("    collection c, imgfile i ");
			buff.append("WHERE ");
			buff.append("    c.isshow = 'Y' ");
			buff.append("    AND i.isshow = 'C' ");
			buff.append("    AND whereis = 'C' ");
			buff.append("    AND cno = bno ");
			break;
			
		case SEL_COLL_CNO:
			buff.append("SELECT ");
		    buff.append("cno, cname, NVL(descr, '컬렉션 설명 없음') descr, NVL(genre, 'empty') genre, id ");
		    buff.append("FROM ");
			buff.append("    collection c, member m ");
			buff.append("WHERE ");
			buff.append("    c.isshow = 'Y' ");
			buff.append("    AND m.isshow = 'Y' ");
			buff.append("    AND cno = ? ");
			buff.append("    AND m.mno = c.mno ");
			break;
			
		case SEL_COLL_FILES:
			buff.append("SELECT ");
			buff.append("    ino, upname, savename, imgsize, idate ");
			buff.append("FROM ");
			buff.append("    imgfile i, collection c ");
			buff.append("WHERE ");
			buff.append("    bno = cno ");
			buff.append("    AND i.isshow != 'N' ");
			buff.append("    AND whereis = 'C' ");
			buff.append("    AND c.isshow = 'Y' ");
			buff.append("    AND cno = ? ");
			break;
// ----------------------------------------------------------------------------------------
		case DEL_COLL:
			buff.append("UPDATE ");
			buff.append("    collection ");
			buff.append("SET ");
			buff.append("    isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("    cno = ? ");
			break;
			
		case ADD_NEWCOLL_THUMB:
			buff.append("INSERT INTO ");
			buff.append("    imgfile(ino, mno, bno, upname, savename, imgsize, isshow, whereis) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(ino)+1, 100001) FROM imgfile), ");
			buff.append("    ? , ");
			buff.append("    (SELECT NVL(MAX(cno)+1, 100001) FROM collection), ");
			buff.append("    ?, ?, ?, 'C', 'C' ");
			buff.append(") ");
			break;
			
		case ADD_NEWCOLL:
			buff.append("INSERT INTO ");
			buff.append("    collection(cno, cname, descr, mno, genre) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(cno)+1, 100001) FROM collection), ");
			buff.append("    ? , ? , ? , ? ");
			buff.append(") ");
			break;
			
		case EDIT_COLL:
			buff.append("UPDATE ");
			buff.append("    collection ");
			buff.append("SET ");
			buff.append("    ### ");
			buff.append("WHERE ");
			buff.append("    isshow = 'Y' ");
			buff.append("    AND cno = ? ");
			break;
			
		case Y_OLD_THUMB:
			buff.append("UPDATE ");
			buff.append("    imgfile ");
			buff.append("SET ");
			buff.append("    isshow = 'Y' ");
			buff.append("WHERE ");
			buff.append("    whereis = 'C' ");
			buff.append("    AND isshow = 'C' ");
			buff.append("    AND bno = ( ");
			buff.append("        SELECT ");
			buff.append("            cno ");
			buff.append("        FROM ");
			buff.append("            collection ");
			buff.append("        WHERE ");
			buff.append("            cno = ? ");
			buff.append("			 AND isshow = 'Y' ");
			buff.append("    ) ");
			break;
			
		case C_NEW_THUMB:
			buff.append("INSERT INTO ");
			buff.append("    imgfile(ino, mno, bno, upname, savename, imgsize, isshow, whereis) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(ino)+1, 100001) FROM imgfile), ");
			buff.append("    ? , ? , ");
			buff.append("    ?, ?, ?, 'C', 'C' ");
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