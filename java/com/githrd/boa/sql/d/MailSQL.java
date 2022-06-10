package com.githrd.boa.sql.d;

/**
 * @author	양동수
 * @since	2022.05.27
 * @version	v.1.0
 * 
 * 			작업이력 ]
 * 				2022.05.25	-	클래스제작
 * 									담당자 ] 양동수
 *
 */

public class MailSQL {
	
	public final int INSERT_NEW_CERT			= 1001;
	public final int GET_LAST_CERT				= 1002;
	public final int UP_CERT_AFTER				= 1003;
	
	
	public String getSQL(int code) {
		StringBuffer buff = new StringBuffer();
		
		switch(code) {
			case INSERT_NEW_CERT:
				buff.append("INSERT INTO "); 
				buff.append("cert( ");
				buff.append("    cno, id, code, isverify ");
				buff.append(") ");
				buff.append("VALUES( ");
				buff.append("    (SELECT NVL(MAX(cno)+1, 100001) FROM cert), ");
				buff.append("    ?, ?, 'N' ");
				buff.append(") ");
				break;
			case GET_LAST_CERT:
				buff.append("SELECT ");
				buff.append("    id, code ");
				buff.append("FROM ");
				buff.append("    cert ");
				buff.append("WHERE ");
				buff.append("    cno =( ");
				buff.append("            SELECT ");
				buff.append("                MAX(cno) cno ");
				buff.append("            FROM ");
				buff.append("                cert ");
				buff.append("        ) ");
				break;
			case UP_CERT_AFTER:
				buff.append("UPDATE ");
				buff.append("    cert ");
				buff.append("SET ");
				buff.append("    isverify = 'Y' ");
				buff.append("WHERE ");
				buff.append("    id = ? ");
				buff.append("    AND ");
				buff.append("    code= ? ");
				break;
		}
		return buff.toString();
	}
}
