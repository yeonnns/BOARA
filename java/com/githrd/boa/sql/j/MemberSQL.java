package com.githrd.boa.sql.j;

/**
 * @author	정준영
 * @since	2022.05.25
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 정준영
 *
 */

public class MemberSQL {
	
	public final int SEL_MEMBER_MNO			= 1000;
	public final int SEL_MEMBER_INFO		= 1001;
	public final int SEL_BOARD_CNT			= 1002;
	public final int SEL_BOARD_LIST			= 1003;
	public final int SEL_REPLY_CNT			= 1004;
	public final int SEL_REPLY_LIST			= 1005;
	public final int SEL_POINTHISTORY		= 1006;
	public final int SEL_POINTHISTORY_CNT	= 1007;
	public final int SEL_MARK_WISH_CNT		= 1008;
	public final int SEL_MARK_WISH_LIST		= 1009;
	public final int SEL_MARK_LIKE_CNT		= 1010;
	public final int SEL_MARK_LIKE_LIST		= 1011;
	public final int SEL_MYPOINT			= 0001;
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
			case SEL_MYPOINT:
				buff.append("SELECT ");
				buff.append("    sumpoint ");
				buff.append("FROM ");
				buff.append("( ");
				buff.append("SELECT MAX(pno) mpno ");
				buff.append("FROM POINT ");
				buff.append("WHERE MNO = ? ");
				buff.append("), point ");
				buff.append("WHERE ");
				buff.append("    mpno = pno ");
				break;
			case SEL_MEMBER_INFO:
				buff.append("    SELECT ");
				buff.append("        id, MEMBER.mno mno, joindate, savename ");
				buff.append("    FROM ");
				buff.append("        MEMBER, IMGFILE ");
				buff.append("    WHERE ");
				buff.append("        id = ? and whereis = 'P' ");
				break;
			case SEL_BOARD_CNT:
				buff.append("SELECT count(*) cnt ");
				buff.append("FROM  ");
				buff.append("    (  SELECT ");
				buff.append("       	bno, title, wdate, clicks ");
				buff.append("    FROM ( ");
				buff.append("        SELECT  ");
				buff.append("             cno ");
				buff.append("        FROM ");
				buff.append("             collection ");
				buff.append("        WHERE   ");
				buff.append("             mno = ? ) c, board b ");
				buff.append("    WHERE ");
				buff.append("        c.cno = b.collection ) ");
				break;
			case SEL_REPLY_CNT:
				buff.append("SELECT ");
				buff.append("  	count(*) cnt ");
				buff.append("FROM ");
				buff.append("    (SELECT  ");
				buff.append("       body, rdate, isshow ");
				buff.append("    FROM ");
				buff.append("        (SELECT  ");
				buff.append("            body, rdate, isshow ");
				buff.append("        FROM ");
				buff.append("            REPLY ");
				buff.append("        WHERE ");
				buff.append("            mno = ? ) ) ");
				break;
			case SEL_REPLY_LIST:
				buff.append("SELECT ");
				buff.append("    rno, body, rdate, isshow ");
				buff.append("FROM ");
				buff.append("    (SELECT  ");
				buff.append("        ROWNUM rno, body, rdate, isshow ");
				buff.append("    FROM ");
				buff.append("        (SELECT  ");
				buff.append("            body, rdate, isshow ");
				buff.append("        FROM ");
				buff.append("            REPLY ");
				buff.append("        WHERE ");
				buff.append("            mno = ? ");
				buff.append("        order by rno)) ");
				buff.append("WHERE rno between ? and ? ");
				break;
			case SEL_BOARD_LIST:
				buff.append("SELECT rno, bno, title, wdate, clicks ");
				buff.append("FROM  ");
				buff.append("    (  SELECT ");
				buff.append("       rownum rno, bno, title, wdate, clicks ");
				buff.append("    FROM ( ");
				buff.append("        SELECT  ");
				buff.append("             cno ");
				buff.append("        FROM ");
				buff.append("             collection ");
				buff.append("        WHERE   ");
				buff.append("             mno = ? ) c, board b ");
				buff.append("    WHERE ");
				buff.append("        c.cno = b.collection ) ");
				buff.append("WHERE ");
				buff.append("    rno BETWEEN ? and ? ");
				break;
			case SEL_POINTHISTORY:
				buff.append("SELECT  rno, dcode, det, pdate, gnp ");
				buff.append("FROM    (SELECT ROWNUM rno, dcode, det, pdate, gnp ");
				buff.append("        FROM    (SELECT gnp, pdate, upcode, det, p.dcode ");
				buff.append("                FROM point p, detailcode d ");
				buff.append("                where mno = ? and p.dcode = d.dcode ");
				buff.append("                ORDER BY pno )) ");
				buff.append("where rno BETWEEN ? AND ? ");
				break;
			case SEL_POINTHISTORY_CNT:
				buff.append("SELECT ");
				buff.append("    count(*) cnt ");
				buff.append("FROM ");
				buff.append("    POINT ");
				buff.append("WHERE ");
				buff.append("    MNO = ? ");
				break;
			case SEL_MEMBER_MNO:
				buff.append("SELECT ");
				buff.append("    mno ");
				buff.append("FROM ");
				buff.append("    MEMBER ");
				buff.append("WHERE ");
				buff.append("    id = ? ");
				break;
			case SEL_MARK_WISH_CNT:
				buff.append("SELECT ");
				buff.append("    count(*) cnt ");
				buff.append("FROM ");
				buff.append("    (SELECT ");
				buff.append("       title, body, wdate, clicks ");
				buff.append("    FROM   ");
				buff.append("        (SELECT  ");
				buff.append("            bno jbno ");
				buff.append("        FROM ");
				buff.append("            mark ");
				buff.append("        WHERE ");
				buff.append("            mno = ? ");
				buff.append("            and isshow = 'J'), board ");
				buff.append("    WHERE  ");
				buff.append("        jbno = bno) ");
				break;
			case SEL_MARK_WISH_LIST:
				buff.append("SELECT ");
				buff.append("    rno, title, body, wdate, clicks ");
				buff.append("FROM ");
				buff.append("    (SELECT ");
				buff.append("       rownum rno, title, body, wdate, clicks ");
				buff.append("    FROM   ");
				buff.append("        (SELECT  ");
				buff.append("            bno jbno ");
				buff.append("        FROM ");
				buff.append("            mark ");
				buff.append("        WHERE ");
				buff.append("            mno = ? ");
				buff.append("            and isshow = 'J'), board ");
				buff.append("    WHERE  ");
				buff.append("        jbno = bno) ");
				buff.append("WHERE ");
				buff.append("    rno between ? and ? ");
				break;
			case SEL_MARK_LIKE_CNT:
				buff.append("SELECT ");
				buff.append("    count(*) cnt ");
				buff.append("FROM ");
				buff.append("    (SELECT ");
				buff.append("       title, body, wdate, clicks ");
				buff.append("    FROM   ");
				buff.append("        (SELECT  ");
				buff.append("            bno jbno ");
				buff.append("        FROM ");
				buff.append("            mark ");
				buff.append("        WHERE ");
				buff.append("            mno = ? ");
				buff.append("            and isshow = 'F'), board ");
				buff.append("    WHERE  ");
				buff.append("        jbno = bno) ");
				break;
			case SEL_MARK_LIKE_LIST:
				buff.append("SELECT ");
				buff.append("    rno, title, body, wdate, clicks ");
				buff.append("FROM ");
				buff.append("    (SELECT ");
				buff.append("       rownum rno, title, body, wdate, clicks ");
				buff.append("    FROM   ");
				buff.append("        (SELECT  ");
				buff.append("            bno jbno ");
				buff.append("        FROM ");
				buff.append("            mark ");
				buff.append("        WHERE ");
				buff.append("            mno = ? ");
				buff.append("            and isshow = 'F'), board ");
				buff.append("    WHERE  ");
				buff.append("        jbno = bno) ");
				buff.append("WHERE ");
				buff.append("    rno between ? and ? ");
				break;
		}
		return buff.toString();
	}
}
