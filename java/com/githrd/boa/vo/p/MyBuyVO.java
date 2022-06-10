package com.githrd.boa.vo.p;

import java.util.*;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class MyBuyVO {
	private int bno, rowno, cnt;
	private String title, body, id, sdate, stime, isshow;
	private Date wdate;
	private Time wtime;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		sdate = form.format(wdate);
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public void setStime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		stime = form.format(wtime);
	}
	public int getRowno() {
		return rowno;
	}
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "MyBuyVO [bno=" + bno + ", rowno=" + rowno + ", cnt=" + cnt + ", title=" + title + ", body=" + body
				+ ", id=" + id + ", sdate=" + sdate + ", stime=" + stime + ", isshow=" + isshow + ", wdate=" + wdate
				+ ", wtime=" + wtime + "]";
	}
	
	
}
