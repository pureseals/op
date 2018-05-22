package com.kh.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/boardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		
	String type=null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		this.type = request.getParameter("type");
			
			if("list".equals(type)) {
				showList(request);
			}
			
			
		
		//1. 페이지 파라미터 변수 설정
		int bPage;
		
		try {
			bPage = Integer.parseInt(request.getParameter("bPage"));
		}catch(NumberFormatException e){
			bPage = 1; 
		}
		//2. 로직(데이터 설정 및 페이지 설정)
		//2.1 페이지당 SHOW COUNT 
		int numPerPage = 5;
		//2.2 전체 게시글수 소팅 BOARD_DATE
		int boardTotalCount = new BoardService().showBoardCount();
		System.out.println("----------서블릿"+ boardTotalCount);
		//2.3 전체 페이지수 Math.ceil((double)((전체 게시글수 / 페이지당 보여질 글수))
		int totalPage = (int)Math.ceil((double)boardTotalCount/numPerPage);
		System.out.println("---------------------------------------totalpage"+totalPage);
		
		
		
		//2.4 페이징된 게시글 가져오기 bPage : 1, 2, 3... , numPerPage : 5
		List<Board> boardList = new BoardService().showBoardListPart(bPage, numPerPage);
		
		
		
		Map<Integer, Integer> boardCommentCount = new HashMap<>();
		
		BoardService boardService = new BoardService();
		
		for(Board b : boardList) {
			boardCommentCount.put(b.getBoard_no(), boardService.getBordCommentCounte(b.getBoard_no()));
		}
		
		
		 for (Integer mapkey : boardCommentCount.keySet()){
		        System.out.println("key:"+mapkey+",value:"+boardCommentCount.get(mapkey));
		 }


		
		//2.5 페이징바 만들기
		String pageBar = "<ul>";
		int pageBarSize = numPerPage;
		//root
		String root = request.getContextPath();
		
		int pageNo = (bPage-1)/pageBarSize * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		//이전
		if(pageNo != 1) {
			pageBar +="<li><a href=" + root +"/board/boardList?bPage="+(pageNo-1)+">이전</a></li>";
		}
		
		//페이지 넘버링
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == bPage) {
				pageBar += "<li class='now'>" + pageNo + "</li>";
			}else {
				pageBar +="<li><a href="+root +"/board/boardList?bPage="+pageNo+">" + pageNo + "</a></li>"; 
			}
			pageNo++;
		}
		
		
		if(pageNo <= totalPage) {
			pageBar +="<li><a href=" + root +"/board/boardList?bPage="+ pageNo +">다음</a></li>";
		}
		
		pageBar += "</ul>";
		
		HttpSession session = request.getSession();
		
		System.out.println("세션은 ++++++++++++++++++++++++++++++" + session.getAttribute("memberLoggedIn"));
		System.out.println("비페이지 ++++++++++++++++++++++++++++++" + bPage);
		System.out.println("페이지바 ++++++++++++++++++++++++++++++" + pageBar);
		System.out.println("보카 카운트++++++++++++++++++++++++++++++" + boardCommentCount);
		System.out.println("");
		
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("bPage", bPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("bcc", boardCommentCount);
		
		request.getRequestDispatcher("/WEB-INF/views/board/boardList.jsp").forward(request, response);
	}

	private void showList(HttpServletRequest request) {
		System.out.println("testtesteststestest 여기는 리스트 페이지");
		System.out.println(request.getParameter("t"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
