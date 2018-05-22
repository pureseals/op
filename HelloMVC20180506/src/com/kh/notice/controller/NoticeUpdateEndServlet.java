package com.kh.notice.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class NoticeForm
 */
@WebServlet("/notice/noticeUpdateEnd")
public class NoticeUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/****************** 파일 업로드 로직 시작 **********************/
		
		//1. 업로드될 파일의 저장경로
		//servletContext객체 = > 절대경로
		String saveDirectory = getServletContext().getRealPath("/upload/notice");
		System.out.println("saveDirectory="+saveDirectory);
		
		
		//2.파일최대용량 : cos.jar 무료버진이 제공하는 파일 최대 크기는 10mb;
		int maxPostSize = 1024*1024*10;
		
		//3.MultipartRequest객체 생성
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", new DefaultFileRenamePolicy());
		
		
		/****************** 파일 업로드 로직 끝  **********************/
		
		int noticeNo = Integer.parseInt(multiReq.getParameter("no"));
		String title = multiReq.getParameter("title");
		String writer = multiReq.getParameter("writer");
		String content = multiReq.getParameter("content");
		String filePath = multiReq.getFilesystemName("up_file");
		//시스템에 중복된 이름이 있다면, 새로부여된 파일명을 리턴함.
		
		String old_file = multiReq.getParameter("old_file");
		
		
		System.out.println("filePath="+filePath);
		//실제 업로드된 파일 존재 여부
		
		File f = multiReq.getFile("up_file");
		//f의 null여부와 파일사이즈를 체크
		if(f!=null&& f.length() > 0) {
			//첨부한 파일이 있는 경우, 기존 파일은 삭제처리함.
			File delFile = new File(saveDirectory+"/"+old_file);
			boolean bool = delFile.delete();
			System.out.println( bool ? "기존파일삭제성공!" : "기존파일삭제실패");
		}else {
			//첨부한 파일이 없는 경우
			
		filePath = old_file;
		
		}
		
		
		Notice notice = new Notice();
		notice.setNoticeNo(noticeNo);
		notice.setNoticeTitle(title);
		notice.setNoticeWriter(writer);
		notice.setFilePath(filePath);
		notice.setNoticeContent(content);
		
		
		
		int result = new NoticeService().updateNotice(notice);
		
		if(result>0) {
			request.getRequestDispatcher("/notice/noticeList").forward(request, response);
			System.out.println("성공");
		}else {
			request.setAttribute("msg", "안되안되");
			request.setAttribute("loc", "/notice/noticeList");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
