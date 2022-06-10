package com.githrd.boa.sql.p;
/*
 * @author	박소연
 * @since	2022/05/23
 * @version	v.1.0
 * 		
 * 			작업 이력
 * 				2022/05/23	담당자 : 박소연
 * 							클래스제작
 * 				2022/05/24	담당자 : 박소연
 * 							클래스 수정
 * 				2022/05/25	담당자 : 박소연
 * 							클래스 수정
 * 				2022/05/26	담당자 : 박소연
 * 							클래스 수정
 * 				2022/05/27	담당자 : 박소연
 * 							클래스 수정
 * 				
 */
public class ReboardSQL {

	public final int SEL_ALL_LIST = 1001;	//한 게시물에 달린 전체 댓글 목록 조회
	public final int SEL_TOTAL_CNT = 1002;	//한 게시물에 달린 전체 댓글 갯수 조회
	public final int SEL_WRITER_INFO = 1003;	//댓글 작성자 정보 조회
	public final int SEL_REBOARD_INFO = 1004;	//댓글 내용 조회 (댓글 수정)
	public final int SEL_REBOARD_BODY = 1005; //대댓글 작성 시 원댓글 본문만 조회
	
	public final int DEL_REBOARD = 2001;	//댓글 삭제
	public final int UPDATE_REBOARD = 2002;	//댓글 수정

	public final int INSERT_REBOARD = 3001;	//댓글 작성
	public final int INSERT_COMMENT = 3002;	//대댓글 작성
	public final int INSERT_POINT = 3003;	//댓글&대댓글 작성 시 포인트 적립
	//public final int INSERT_RRPOINT = 3004;	//대댓글 작성 시 포인트 적립


	public String getSQL(int code) {
		
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
		case SEL_ALL_LIST://한 게시물에 달린 모든 댓글 리스트 조회
			buff.append("SELECT ");
			buff.append("    rowno, rno, bno, uprno, mno, id, body, rdate, savename, isshow, step ");
			buff.append("FROM ");
			buff.append("    ( ");
			buff.append("        SELECT ");
			buff.append("            ROWNUM rowno, rno, bno, uprno, mno, id, body, rdate, savename, isshow, step ");
			buff.append("        FROM ");
			buff.append("            ( ");
			buff.append("                SELECT ");
			buff.append("                    r.rno , r.bno , uprno, r.mno, r.id, body, rdate, savename, r.isshow, (level - 1) step ");
			buff.append("                FROM ");
			buff.append("                    reply r, member m, imgfile f ");
			buff.append("                WHERE ");
			buff.append("                    r.isshow IN('Y', 'S') ");
			buff.append("                    AND r.mno = m.mno ");
			buff.append("                    AND m.mno = f.mno(+) ");
			buff.append("                    AND r.bno = ? ");
			buff.append("                    AND f.isshow(+) = 'C' ");
			buff.append("                    AND f.whereis(+) = 'M' ");
			buff.append("                START WITH ");
			buff.append("                    uprno IS NULL ");
			buff.append("                CONNECT BY ");
			buff.append("                    PRIOR r.rno = uprno ");
			buff.append("                ORDER SIBLINGS BY ");
			buff.append("                    rdate DESC ");
			buff.append("           ) ");
			buff.append("    ) ");
			buff.append("WHERE ");
			buff.append("    rowno BETWEEN ? AND ? ");
			break;
		case SEL_TOTAL_CNT://한 게시물에 달린 모든 댓글 갯수 조회
			buff.append("SELECT ");
			buff.append("    COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("    reply ");
			buff.append("WHERE ");
			buff.append("    isshow IN('Y', 'S') ");
			buff.append("    AND bno = ? ");
			break;
		case SEL_WRITER_INFO://댓글 작성하는 회원 정보 조회 + bno 파라미터 받아서 bno 넘기기
			buff.append("SELECT ");
			buff.append("    m.mno mmno, savename, b.bno bbno ");
			buff.append("FROM ");
			buff.append("    member m, imgfile f, board b ");
			buff.append("WHERE ");
			buff.append("	m.isshow IN('Y', 'A') ");
			buff.append("    AND m.mno = f.mno(+) ");
			buff.append("    AND f.isshow(+) = 'C' ");
			buff.append("    AND f.whereis(+) = 'M' ");
			buff.append("    AND m.id = ? ");
			buff.append("    AND b.bno = ? ");
			break;
		case INSERT_REBOARD://댓글&대댓글 작성
			buff.append("INSERT INTO ");
			buff.append("    reply(rno, bno, uprno, id, mno, body, isshow) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(rno) + 1, 100001) FROM reply), ");
			buff.append("    ?, ?, ?, ?, ?, ? ");
			buff.append(") ");
			break;
		case SEL_REBOARD_INFO://댓글 수정 시 수정할 댓글 정보 조회
			buff.append("SELECT ");
			buff.append("    r.rno rrno, r.bno rbno, uprno, r.mno rmno, body, rdate, savename, r.isshow risshow, f.savename fsavename ");
			buff.append("FROM ");
			buff.append("    reply r, member m, imgfile f ");
			buff.append("WHERE ");
			buff.append("    r.isshow IN('Y', 'S') ");
			buff.append("    AND r.mno = m.mno ");
			buff.append("    AND m.mno = f.mno(+) ");
			buff.append("    AND r.rno = ? ");
			buff.append("    AND m.id = ? ");
			buff.append("    AND f.isshow(+) = 'C' ");
			buff.append("    AND f.whereis(+) = 'M' ");
			break;
		case DEL_REBOARD://댓글 삭제
			buff.append("UPDATE ");
			buff.append("    reply ");
			buff.append("SET ");
			buff.append("    isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("    rno = ? ");
			break;
		case UPDATE_REBOARD://댓글 수정
			buff.append("UPDATE ");
			buff.append("    reply ");
			buff.append("SET ");
			buff.append("    body = ?, ");
			buff.append("    isshow = ? ");
			buff.append("WHERE ");
			buff.append("    rno = ? ");
			break;
		case INSERT_POINT://댓글 & 대댓글
			buff.append("INSERT INTO ");
			buff.append("    point(pno, mno, gnp, dcode, sumpoint) ");
			buff.append("VALUES( ");
			buff.append("    (SELECT NVL(MAX(pno) + 1, 1000000001) FROM point), ");
			buff.append("    ?, ?, ?, ");
			buff.append("    (SELECT ");
			buff.append("        sumpoint ");
			buff.append("    FROM ");
			buff.append("        point ");
			buff.append("    WHERE ");
			buff.append("         (SELECT ");
			buff.append("                MAX(pno) ");
			buff.append("            FROM ");
			buff.append("                point ");
			buff.append("            WHERE ");
			buff.append("                mno = ?) = pno) + ? ");
			buff.append("    ) ");
			break;
		}
		
		return buff.toString();
	}

}
