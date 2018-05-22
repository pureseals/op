package com.kh.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFileDownload
 */
@WebServlet("/board/boardFileDownload")
public class BoardFileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFileDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 실제파일 저장경로를 절대경로로 가져오기
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		String fileName= request.getParameter("fname");
		String sep = File.separator;
		String f = saveDirectory + sep + fileName;
		
		System.out.println("파일경로는= " + f);
		
		//2. 파일입출력스트림생성
		
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		
		//3. 브라우져에 따른 파일명처리 분기
		String resFileName = "";
		String header = request.getHeader("user-agent");
		boolean isMSIE = header.indexOf("MSIE") != -1 || header.indexOf("Trident") != -1;
		System.out.println("user-agent="+header);
		System.out.println("isMSIE="+isMSIE);
		
		if(isMSIE) {
			//ie는 명시적 이노딩처리함. 인코딩결과 공백은 +로 치환됨.
			// + =>인코딩문자인 %20(정규식)
			resFileName = URLEncoder.encode(fileName, "UTF-8");
			resFileName = resFileName.replaceAll("\\+", "%20");
			
		}else {
			resFileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
		}
		
		System.out.println("resFileName ="+resFileName);
		
		
		//4. response헤더 컨텐트 타입지정
		//application/octet-stream : 이진데이터(실행파일/다운로드파일)일 경우 사용.
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename="+resFileName);
		//5. response객체에 파일쓰기
		int read = -1; 
		while((read=bis.read()) != -1){
			bos.write(read);
		} 
		bos.close();
		bis.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
