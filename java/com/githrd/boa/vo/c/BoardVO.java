package com.githrd.boa.vo.c;

/**
 * 	게시글 관련 변수들을 다루는 VO 클래스입니다.
 * 	@author 최이지
 * 	@since 2022.05.25
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.25	-	클래스 제작
 * 									담당자 : 최이지
 * 
 * 				2022.05.27	-	변수 추가 (list, sgenre, mno)
 * 								sdate 포맷형식 수정
 * 									담당자 : 최이지
 */
import java.util.ArrayList;
import java.sql.*;
import java.text.*;

import com.githrd.boa.vo.*;
public class BoardVO {
	private int bno, price, cno, clicks, mno;
	private String title, body, sdate, isshow, id, cname, sgenre;
	private Date wdate;
	private Time wtime;
	ArrayList<String> genre;
	ArrayList<FileVO> list;
	
	// Getter Setter
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
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
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat dform = new SimpleDateFormat("[yyyy.MM.dd ");
		SimpleDateFormat tform = new SimpleDateFormat("HH:mm:ss 작성]");
		sdate = dform.format(wdate) + tform.format(wtime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
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
	}
	public Time getWtime() {
		return wtime;
	}
	public void setWtime(Time wtime) {
		this.wtime = wtime;
	}
	public ArrayList<String> getGenre() {
		return genre;
	}
	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSgenre() {
		return sgenre;
	}
	public void setSgenre(String sgenre) {
		this.sgenre = sgenre;
	}
	public ArrayList<FileVO> getList() {
		return list;
	}
	public void setList(ArrayList<FileVO> list) {
		this.list = list;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
}
