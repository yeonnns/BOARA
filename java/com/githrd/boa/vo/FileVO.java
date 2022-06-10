package com.githrd.boa.vo;
/**
 * 	File 업로드/다운로드 시 사용할 vo 클래스입니다.
 * 
 *	@author 최이지
 * 	@since 2022.05.23
 * 	@version v.1.0
 * 	
 *			작업 이력
 *				2022.05.23	-	담당자 : 최이지
 *									클래스 제작
 *				2022.05.26 -    담당자 : 김소연
 *									id 추가
 *			
 */
import java.text.*;
import java.sql.*;
public class FileVO {
	private int fno, mno, rno, cnt;
	private long len;
	private String id, oriname, savename, dir, sdate, stime;
	private Date wdate;
	private Time wtime;
	
	// Getter Setter
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy년 MM월 dd일");
		sdate = form.format(wdate);
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public void setStime() {
		SimpleDateFormat form = new SimpleDateFormat("HH24:mm:ss");
		stime = form.format(wtime);
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
		setSdate();
	}
	public Time getWtime() {
		return wtime;
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
		setStime();
	}
}