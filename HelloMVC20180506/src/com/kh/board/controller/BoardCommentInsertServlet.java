package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터값 핸들링
		String boardCommentWriter = request.getParameter("boardCommentWriter");
		String boardCommentContent = request.getParameter("boardCommentContent");
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment bc = new BoardComment(0, boardCommentLevel, boardCommentWriter, boardCommentContent, boardRef, boardCommentRef, null);
		System.out.println("boardComment="+bc);
		//2. 업무 로직
		int result = new BoardService().insertBoardComment(bc);
		System.out.println("result@BCIsertServlet="+result);
		
		//3. view단 처리위임
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardView?no="+boardRef;
		
		System.out.println("---------------loc값은 -----------------"+loc);
		
		if(result>0)
			msg = "댓글 등록 성공!";
		else
			msg = "댓글 등록 실패!";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher(view).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
