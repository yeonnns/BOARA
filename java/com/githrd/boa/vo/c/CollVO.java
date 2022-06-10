package com.githrd.boa.vo.c;
/**
 * 	컬렉션 관련 변수들을 다루는 VO 클래스입니다.
 * 	@author 최이지
 * 	@since 2022.05.25
 * 	@version v.1.0
 * 			작업이력
 * 				2022.05.25	-	클래스 제작
 * 									담당자 : 최이지
 * 
 * 				2022.05.27	-	변수 추가 (list, sgenre)
 * 									담당자 : 최이지
 */
import java.util.*;
import com.githrd.boa.vo.*;
public class CollVO {
	private int cno, mno, crno;
	private String descr, cname, id, sgenre;
	ArrayList<String> genre;
	ArrayList<FileVO> list;
	
	public String getSgenre() {
		return sgenre;
	}
	public void setSgenre(String sgenre) {
		this.sgenre = sgenre;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public ArrayList<String> getGenre() {
		return genre;
	}
	public void setGenre(ArrayList<String> genre) {
		this.genre = genre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCrno() {
		return crno;
	}
	public void setCrno(int crno) {
		this.crno = crno;
	}
	public ArrayList<FileVO> getList() {
		return list;
	}
	public void setList(ArrayList<FileVO> list) {
		this.list = list;
	}
	
}
