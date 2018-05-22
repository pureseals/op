package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


/**
 * Servlet implementation class BoardWriteEnd
 */
@WebServlet("/board/boardWriteEnd")
public class BoardWriteEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteEnd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.파일업로드 로직
		//1.a multipart/form-data 여부 검사
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판 작성오류[form:enctype]");
			request.setAttribute("loc","/board/boardList");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return;
		}
		//1.b saveDirectory
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		System.out.println("saveDirectory="+saveDirectory);
		
		//1.c maxPostSize
		int maxPostSize = 1024 * 1024 * 10;
		
		//1.d MultipartRequest객체생성 ==> 파일 rename정책 커스터마이징
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", new MyFileRenamePolicy());
		
		//2. 파라미터 가져오기
		
		String boardTitle = multiReq.getParameter("title");
		String boardWriter = multiReq.getParameter("writer");
		String boardContent = multiReq.getParameter("content");
		
		
		String renamedFileName = multiReq.getFilesystemName("up_file"); //실제시스템에 저장된 파일명
		String originalFileName = multiReq.getOriginalFileName("up_file"); //사용자가 업로드한 파일명
		
		Board b = new Board(boardTitle, boardWriter, boardContent, originalFileName, renamedFileName);
		
		
		//3. 비지니스로직 처리
		int result = new BoardService().insertBoard(b);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardList";
		
		//4. view단 처리위임
		if(result>0) {
			msg = "게시글 등록 성공!";
			
		}else {
			msg = "게시글 등록 실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
