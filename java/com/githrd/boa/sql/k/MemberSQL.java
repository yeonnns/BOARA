package com.githrd.boa.sql.k;

/**
 * 이 클래스는 회원관련 질의명령을 기억하고 
 * 필요한 순간에 반환해주는 클래스
 *
 * @author	김소연
 * @since	2022.05.26
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.26	-	클래스제작
 * 									담당자 ] 김소연
 *
 */
public class MemberSQL {
	public final int SEL_LOGIN_CNT		= 1001;
	public final int SEL_ID_CNT			= 1002;
	public final int SEL_TEL_CNT		= 1003;
	public final int SEL_MAIL_CNT		= 1004;
	public final int SEL_MEMBER_INFO	= 1005;
	public final int SEL_ID_SEARCH		= 1006;
	public final int SEL_PW_SEARCH		= 1007;

	public final int EDIT_MEMBER		= 2001;	
	public final int DEL_MEMBER			= 2002;
	public final int DEL_IMG			= 2003;
	
	public final int ADD_MEMBER			= 3001;
	public final int INSERT_FILEINFO 	= 3002;
	
	
	
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		switch(code) {
		case SEL_LOGIN_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND id = ? ");
			buff.append("	AND pw = ? ");
			break;
		case SEL_ID_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			break;
		case SEL_MAIL_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	mail = ? ");
			break;
		case SEL_TEL_CNT:
			buff.append("SELECT ");
			buff.append("	COUNT(*) cnt ");
			buff.append("FROM ");
			buff.append("	member ");
			buff.append("WHERE ");
			buff.append("	tel = ? ");
			break;
		case SEL_MEMBER_INFO:
			buff.append("SELECT ");
			buff.append("	m.mno, name, id, pw, mail, tel, ino, savename ");
			buff.append("FROM ");
			buff.append(" 	member m, imgfile f ");
			buff.append("WHERE ");
			buff.append("	m.isshow = 'Y' ");
			buff.append("	AND f.isshow(+) = 'C' ");
			buff.append("	AND f.mno(+) = m.mno ");
			buff.append("	AND id = ? ");
			break;
		case SEL_ID_SEARCH:
			buff.append("SELECT ");
			buff.append("	id ");
			buff.append("FROM ");
			buff.append(" 	member ");
			buff.append("WHERE ");
			buff.append("	name = ? ");
			buff.append("	AND tel = ? ");
			break;
		case SEL_PW_SEARCH:
			buff.append("SELECT ");
			buff.append("	pw ");
			buff.append("FROM ");
			buff.append(" 	member ");
			buff.append("WHERE ");
			buff.append("	id = ? ");
			buff.append("	AND mail = ? ");
			break;
		case EDIT_MEMBER:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	@@@ ");
			buff.append("WHERE ");
			buff.append("	isshow = 'Y' ");
			buff.append("	AND id = ? ");
			break;
		
		case ADD_MEMBER:
			buff.append("INSERT INTO ");
			buff.append("	member(mno, name, id, pw, mail, tel, isshow) ");
			buff.append("VALUES( ");
			buff.append("		(SELECT NVL(MAX(mno) + 1, 1001) FROM member), ");
			buff.append("		?, ?, ?, ?, ?, 'R' ");
			buff.append(")");
			break;
			
		case INSERT_FILEINFO:
			buff.append("INSERT INTO ");
			buff.append("	imgfile(ino, mno, upname, savename, imgsize, isshow, whereis) ");
			buff.append("VALUES( ");
			buff.append("	(SELECT NVL(MAX(ino) + 1, 100001) FROM imgfile), ");
			buff.append("	(SELECT mno FROM member WHERE id = ? ), ");
			buff.append("	?, ?, ?, 'C', 'M' ");
			buff.append(") ");
			break;
			
		case DEL_MEMBER:
			buff.append("UPDATE ");
			buff.append("	member ");
			buff.append("SET ");
			buff.append("	isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("	isshow IN('Y', 'A') ");
			buff.append("	AND id = ? ");
			break;
			
		case DEL_IMG:
			buff.append("UPDATE ");
			buff.append("	imgfile ");
			buff.append("SET ");
			buff.append("	isshow = 'N' ");
			buff.append("WHERE ");
			buff.append("	isshow IN('Y', 'C') ");
			buff.append("	AND ino = ? ");
			break;
		
		}
		return buff.toString();
	}
}
