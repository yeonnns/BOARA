package com.githrd.boa.util;

/**
 * 	파일 업로드에 필요한 기능을 처리,
 * 	업로드할 파일의 정보를 만들어 주는 기능의 클래스
 * 
 * 	@author 최이지
 * 	@since 2022.05.23
 * 	@version v.1.0
 * 	
 *			작업 이력
 *				2022.05.23	-	담당자 : 최이지
 *									클래스 제작
 *				2022.05.26 - 	담당자 : 김소연
 *									getter setter추가
 */
import java.io.*;
import java.io.File;
import java.util.*;
import javax.servlet.http.*;
import com.githrd.boa.vo.*;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;
public class FileUtil {
	// 파일이 몇개 업로드될 지 모르니
	private ArrayList<FileVO> list;
	private HttpServletRequest req;
	private MultipartRequest multi;
	private String dir, path, bPath;
	
	public FileUtil() {}
	public FileUtil(HttpServletRequest req, String dir) {
		this.req = req;
		this.dir = dir;
		setMulti();
		setList();
	}
	
	// MultipartRequest 세팅 함수
	public void setMulti() {
		path = this.getClass().getResource("/").getPath();	// 시스템 상 전체 경로 따오기
		
		// WEB-INF 이전 주소만 따오기
		path = path.substring(0, path.lastIndexOf("/WEB-INF")) + dir;
// 확인	System.out.println("path : " + path);
		
		try {
			/*
			~ MultipartRequest 생성자 매개변수~
			
			HttpServlet Request req
			저장경로
			업로드 가능 크기
			파일이름 인코딩 방식
			FileRenamePolicy
			 */
			multi = new MultipartRequest(req, path, 1024*1024*10, "UTF-8", new FileRenamePolicy() {
				@Override
				public File rename(File file) {
					// 업로드하는 파일이 겹칠경우 호출되는 함수
					// 우리는 파일이름_숫자.확장자 로 만들겠다.
					
					// 파일 이름 추출, 초기화
					String filename = file.getName();
					String newname = filename;
					
					// 확장자명 분리
					String name = filename.substring(0, filename.lastIndexOf("."));
					String ext = filename.substring(filename.lastIndexOf("."));
					
					// 경로, 파일 이름 집어넣어 객체 생성
					int count = 0;
					File f = new File(path, newname);
					
					// 새 이름의 파일이 있는지 검사
					// 존재시 겹치지 않을 때 까지 count 증가시킨 이름으로 파일 재생성
					while(f.exists()) {
						++count;
						newname = name + "_" + count + ext;
						
						f = new File(path, newname);
					}
					
					return f;
				}
			});
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 파일 정보 리스트 세팅 함수
	public void setList() {
		list = new ArrayList<FileVO>();
		
		// 전송된 파일 데이터의 키값만 뽑기
		Enumeration en = multi.getFileNames();
		while(en.hasMoreElements()) {
			String key = (String) en.nextElement();
			
			// 원래 이름, 저장이름 가져오기
			String oriname = multi.getOriginalFileName(key);	// 원래 이름
			
			// null 이면 저장하지 않고 다음 파일로 넘어감.
			if(oriname == null) {
				continue;
			}
			String savename = multi.getFilesystemName(key);	// 업로드된 저장 이름
			
			// 파일 크기 꺼내오기
			File file = multi.getFile(key);
			long len = file.length();
			
			// VO 만들어, 데이터 입력
			FileVO fVO = new FileVO();
			fVO.setOriname(oriname);
			fVO.setSavename(savename);
			fVO.setDir(dir);
			fVO.setLen(len);
			
			// 리스트 추가, 백업도 동시에 해주기
			list.add(fVO);
			saveBackup(file);
		}
	}
	
	// 업로드파일 -> 작업폴더 백업 함수
	public void saveBackup(File file) {
		bPath = this.getClass().getResource("/").getPath();
		bPath = bPath.substring(0, bPath.indexOf("/source")) // jsp 폴더 까지 올라가야함
				+ "/git/boara/boara/src/main/webapp/resources/upload";
		
		File devFile = new File(bPath, file.getName());
		FileInputStream fin = null;
		PrintStream ps = null;
		
		// 읽어서, 백업
		try {
			// 파일 읽어오기
			byte[] buff = new byte[10240];
			fin = new FileInputStream(file);
			ps = new PrintStream(devFile);
			
			// 파일이 몇개인지 모르니
			while(true) {
				// 데이터 넣어주기
				int len = fin.read(buff);
				if (len == -1) break;	// 읽은거 없는 경우
				
				ps.write(buff, 0, len);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<FileVO> getList() {
		return list;
	}
	public void setList(ArrayList<FileVO> list) {
		this.list = list;
	}
	public HttpServletRequest getReq() {
		return req;
	}
	public void setReq(HttpServletRequest req) {
		this.req = req;
	}
	public MultipartRequest getMulti() {
		return multi;
	}
	public void setMulti(MultipartRequest multi) {
		this.multi = multi;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getbPath() {
		return bPath;
	}
	public void setbPath(String bPath) {
		this.bPath = bPath;
	}
	
}