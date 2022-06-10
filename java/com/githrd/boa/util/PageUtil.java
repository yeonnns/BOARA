package com.githrd.boa.util;
/**
 * 	boara 컬렉션-게시물 리스트 내에서 페이지를 이동할 때
 * 	필요한 정보를 계산, 기억시키는 클래스입니다.
 * 	
 * @author 최이지
 *	@since 2022.05.23
 *	@version v.1.0
 *			
 *			작업이력
 *				2022.05.23	-	클래스 제작
 *									담당자 : 최이지
 */
public class PageUtil {
	private int nowPage = 1;	// 현재 페이지
	private int totalCount;		// 총 게시물 수
	
	private int pageRow = 3;	// 한 페이지에 보여줄 게시물 수
	private int pageGroup = 3;	// 한 화면 당 이동 가능한 페이지
	
	private int startPage;		// page bar에 노출시킬 최소 페이지 값
	private int endPage;		// page bar에 노출시킬 최대 페이지 값
	
	private int startCont;		// 현재 페이지에서 보여줄 시작 게시글의 rownum
	private int endCont;		// 현재 페이지에서 보여줄 마지막 게시글의 rownum
	
	private int totalPage = 1;	// 총 페이지 수

	public PageUtil() {}
	public PageUtil(int nowPage, int totalCount) {
		// pageutil의 생성자 함수 호출
		this(nowPage, totalCount, 3, 3);
	}
	public PageUtil(int nowPage, int totalCount, int pageRow, int pageGroup) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.pageRow = pageRow;
		this.pageGroup = pageGroup;
		
		setTotalPage();
		setStartPage();
		setEndPage();
		setCont();
	}
	
	// 총 페이지 수 게산 함수
	public void setTotalPage() {
//		총 페이지수 = 총 게시물 수 / 화면당 나오는 게시물 수
//		하지만 나누어 떨어지지 않으면 한 페이지 더 필요함.
		totalPage = (totalCount % pageRow == 0) ? (
				// 게시물 0일경우 한 페이지만 필요.
				(totalCount == 0) ? 1 : (totalCount / pageRow)) : (totalCount / pageRow + 1);
		
	}
	
	// page bar에 보여지는 최소 페이지 값 계산하는 함수
	public void setStartPage() {
		// 왜 이렇게 하는지는 솔직히 잘 모르겠음.. 그냥 -1 해주면 되는거 아닌지
		int tmp = (nowPage - 1) / pageGroup;
		startPage = tmp * pageGroup + 1;
	}
	
	// page bar에 보여지는 최대 페이지 값 계산하는 함수
	public void setEndPage() {
		int tmp = (nowPage - 1) / pageGroup;
		endPage = (tmp + 1) * pageGroup;
		
		if(endPage > totalPage) endPage = totalPage;
	}
	
	// 시작, 게시물 번호를 계산 함수
	public void setCont() {// 글번호가 아니라 rownum 이라 1000은 따로 안더해줘도 OK
		// 시작 게시물 번호는 페이지 - 1
		startCont = (nowPage - 1) * pageRow + 1;
		endCont = (nowPage * pageRow > totalCount) ? totalCount : (nowPage * pageRow);
	}
	
	// Getters Setters
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageRow() {
		return pageRow;
	}
	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}
	public int getPageGroup() {
		return pageGroup;
	}
	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getStartCont() {
		return startCont;
	}
	public void setStartCont(int startCont) {
		this.startCont = startCont;
	}
	public int getEndCont() {
		return endCont;
	}
	public void setEndCont(int endCont) {
		this.endCont = endCont;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}