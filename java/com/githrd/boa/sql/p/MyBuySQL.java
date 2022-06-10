package com.githrd.boa.sql.p;
/*
 * @author	박소연
 * @since	2022/05/27
 * @version	v.1.0
 * 		
 * 			작업 이력
 * 				2022/05/27	담당자 : 박소연
 * 							클래스제작
 * 				
 */
public class MyBuySQL {

	public final int SEL_MYBUY_LIST = 1001; // 내가 구매한 게시글 리스트 조회
	public final int SEL_MYBUY_COUNT = 1002;
	
	public String getSQL(int code) {
		
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_MYBUY_LIST:
			buff.append("SELECT ");
			buff.append("    rowno, bno, title, body, wdate, id ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("    SELECT ");
			buff.append("        ROWNUM rowno, bno, title, body, wdate, id ");
			buff.append("    FROM ");
			buff.append("        ( ");
			buff.append("        SELECT ");
			buff.append("            bno, title, b.body, wdate, id, pdate ");
			buff.append("        FROM ");
			buff.append("            point p, board b, collection c, member m ");
			buff.append("        WHERE ");
			buff.append("            SUBSTR(pno, 5, 6) = bno ");
			buff.append("            AND b.collection = cno ");
			buff.append("            AND p.mno = ( ");
			buff.append("                            SELECT ");
			buff.append("                                mno ");
			buff.append("                            FROM ");
			buff.append("                                member ");
			buff.append("                            WHERE ");
			buff.append("                                id = ? ");
			buff.append("                        ) ");
			buff.append("            AND m.mno = c.mno ");
			buff.append("            AND b.isshow = 'Y' ");
			buff.append("        ORDER BY ");
			buff.append("            pdate DESC ");
			buff.append("        ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rowno BETWEEN ? AND ? ");
			break;
		case SEL_MYBUY_COUNT:
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("    SELECT ");
			buff.append("        bno, title, b.body, wdate, id, pdate ");
			buff.append("    FROM ");
			buff.append("        point p, board b, collection c, member m ");
			buff.append("    WHERE ");
			buff.append("        SUBSTR(pno, 5, 6) = bno ");
			buff.append("        AND b.collection = cno ");
			buff.append("        AND p.mno = ( ");
			buff.append("                        SELECT ");
			buff.append("                            mno ");
			buff.append("                        FROM ");
			buff.append("                            member ");
			buff.append("                        WHERE ");
			buff.append("                            id = ? ");
			buff.append("                    ) ");
			buff.append("        AND m.mno = c.mno ");
			buff.append("        AND b.isshow = 'Y' ");
			buff.append("    ) ");
			break;
		}
		return buff.toString();
	}
	
}
