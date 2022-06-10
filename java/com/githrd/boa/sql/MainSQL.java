package com.githrd.boa.sql;
/*
 * @author	박소연
 * @since	2022/05/23
 * @version	v.1.0
 * 		
 * 			작업 이력
 * 				2022/05/23	담당자 : 박소연
 * 							클래스제작
 */
public class MainSQL {
	
	public final int SEL_ALL_COLLECTION = 1001; //전체 컬렉션 조회
	public final int SEL_TOTAL_CNT= 1002; //전체 컬렉션 갯수
	
	public String getSQL(int code) {
		
		StringBuffer buff = new StringBuffer();

		switch(code) {
		case SEL_ALL_COLLECTION:
			buff.append("SELECT ");
			buff.append("    rowno, cno, cname, savename ");
			buff.append("FROM ");  
			buff.append("    ( ");
			buff.append("    SELECT ");
			buff.append("        ROWNUM rowno, c.cno, c.cname, f.savename ");
			buff.append("    FROM ");
			buff.append("        collection c, imgfile f ");
			buff.append("    WHERE ");
			buff.append("        c.isshow = 'Y' ");
			buff.append("        AND c.mno = f.mno(+) ");
			buff.append("        AND f.isshow(+) = 'C' ");
			buff.append("        AND f.whereis(+) = 'C' ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rowno BETWEEN ? AND ? ");
			break;
		case SEL_TOTAL_CNT:
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("    collection ");
			buff.append("WHERE ");
			buff.append("    isshow = 'Y' ");
		    break;
		}
		
		return buff.toString();
	}
}
