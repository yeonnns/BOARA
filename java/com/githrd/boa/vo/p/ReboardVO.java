package com.githrd.boa.vo.p;

import java.sql.Time;

import java.text.SimpleDateFormat;
import java.util.*;

public class ReboardVO {

	private int rno, bno, uprno, mno, cnt, ano, clicks, rowno, step,//reply 테이블 컬럼
				pno, gnp, dcode, sumpoint;//point 테이블 컬럼
	private String body, isshow, sdate, stime, savename, id;
	private Date rdate;
	private Time rtime;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getUprno() {
		return uprno;
	}
	public void setUprno(int uprno) {
		this.uprno = uprno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy/MM/dd");
		sdate = form.format(rdate);
	}
	public String getStime() {
		return stime;
	}
	public void setStime() {
		SimpleDateFormat form = new SimpleDateFormat("HH:mm:ss");
		stime = form.format(rtime);
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
		setSdate();
	}
	public Time getRtime() {
		return rtime;
	}
	public void setRtime(Time rtime) {
		this.rtime = rtime;
		setStime();
	}
	public int getRowno() {
		return rowno;
	}
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getGnp() {
		return gnp;
	}
	public void setGnp(int gnp) {
		this.gnp = gnp;
	}
	public int getDcode() {
		return dcode;
	}
	public void setDcode(int dcode) {
		this.dcode = dcode;
	}
	public int getSumpoint() {
		return sumpoint;
	}
	public void setSumpoint(int sumpoint) {
		this.sumpoint = sumpoint;
	}
	@Override
	public String toString() {
		return "ReboardVO [rno=" + rno + ", bno=" + bno + ", uprno=" + uprno + ", mno=" + mno + ", cnt=" + cnt
				+ ", ano=" + ano + ", clicks=" + clicks + ", rowno=" + rowno + ", step=" + step + ", pno=" + pno
				+ ", gnp=" + gnp + ", dcode=" + dcode + ", sumpoint=" + sumpoint + ", body=" + body + ", isshow="
				+ isshow + ", sdate=" + sdate + ", stime=" + stime + ", savename=" + savename + ", id=" + id
				+ ", rdate=" + rdate + ", rtime=" + rtime + "]";
	}
	
	
}
