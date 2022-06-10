package com.githrd.boa.vo;

public class MainVO {
	private String cname, savename;
	private int cno, rowno;
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getRowno() {
		return rowno;
	}
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	@Override
	public String toString() {
		return "MainVO [cname=" + cname + ", savename=" + savename + ", cno=" + cno + ", rowno=" + rowno + "]";
	}
}
