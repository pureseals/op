package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = 0;
		try {
		 boardNo = Integer.parseInt(request.getParameter("no"));
		}catch(NumberFormatException e) {
			request.setAttribute("msg",  "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		//2 비지니스 로직
		BoardService boardService = new BoardService();
		
		
		
		//쿠키검사
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";
		boolean hasRead = false;
		
		if(cookies!= null) {
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				
				if("boardCookie".equals(name)) {
					boardCookieVal = value;
					if(boardCookieVal.contains("|"+boardNo+"|")){
						hasRead = true;
						break;
					}
				}
			}
		}
		
		//게시글 읽음여부
		if(!hasRead) {
			boardService.increaseReadCount(boardNo);
			
			//쿠키생성
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal+"|"+boardNo+"|");
			//boardCookie.setPath("/mvc/board");// 작성안하면, 자동으로 현재경로로 셋팅됨.
			//boardCookie.setMaxAge(60*60*24);//작성안하면, 브라우져에서 영구저장.
			
			System.out.println("boardCookie생성 : " + boardCookie.getValue());
			response.addCookie(boardCookie);
		}
		
		Board b = new BoardService().selectOne(boardNo);
		//댓글 가져오기
		
		List<BoardComment> commentList = boardService.selectCommentList(boardNo);
		

		
		
		
		
		//3 view 단 처리 위임
		String view = "";
		if(b != null) {
			request.setAttribute("board", b);
			request.setAttribute("commentList", commentList);
			view = "/WEB-INF/views/board/boardView.jsp";
		}else {
			request.setAttribute("msg",  "조회한 게시글이 존재하지 않습니다.");
			request.setAttribute("loc", "/board/boardList");
			view = "/WEB-INF/views/common/msg.jsp";
		}
		
		
		HttpSession session = request.getSession();
		
		System.out.println("세션은 ++++++++++++++++++++++++++++++" + session.getAttribute("memberLoggedIn"));
		Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
		System.out.println("유저아이디 ++++++++++++++++++++++++++++++" + memberLoggedIn.getUserId());
		System.out.println("보드라이터 ++++++++++++++++++++++++++++++" + b.getBoard_writer());
	    
		System.out.println("보드넘버 ++++++++++++++++++++++++++++++" + b.getBoard_no());
		System.out.println("커맨트 리스트는 ++++++++++++++++++++++++++++++" + commentList);
		
		
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
