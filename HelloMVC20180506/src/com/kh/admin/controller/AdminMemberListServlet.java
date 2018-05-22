package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.admin.model.service.AdminService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/memberList")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//관리자가 아닌경우 
		Member memberLoggedIn = (Member)request.getSession(true).getAttribute("memberLoggedIn");
		
		if(memberLoggedIn == null || !"admin".equals(memberLoggedIn.getUserId())) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return; 
		}
		
		
		
		//1. 파라미터 변수에 담기
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage =1;
		}
		System.out.println("cPage="+cPage);
		
		//2.비지니스 로직
		
			//페이지당 멤버
		int numPerPage = 5;
			//전체게시물
		int totalMember = new AdminService().selectMemberCount();
			//(공식1) totalPage
			//100건이면, totalPage = 20, totalPage=21;
		//int totalPage = (Integer)(totalMember / numPerPage) + (totalMember % numPerPage);
		int totalPage = ((int)Math.ceil((double)(totalMember / numPerPage)));
				
		//2.2 페이징된 회원리스트 가져오기
		List<Member> list = new AdminService().selectMemberList(cPage, numPerPage);
		System.out.println("RM :::::: list@AdminMemberListServlet="+list);
		
		//2.3 페이징바 만들기
		String pageBar = "";
		int pageBarSize = 5; //페이지바 페이지갯수
		//(공식3) 시작페이지 구하기
		//cPage= 5, pageBarSize= 5 --> 1
		
		
		//cPage= 6, pageBarSize= 5 --> 1
		
		
		//cPage= 10, pageBarSize= 5 --> 6
		
		//cPage= 14, pageBarSize= 5 --> 11
		
		
		//       1            2           3                 4
		// 1 2 3 4 5 | 6 7 8 9 10 | 11 12 13 14 15 |  16 17 18 19 20  
		//
		
		//int pageNo = (int)(Math.ceil(cPage/pageBarSize) * pageBarSize) +1;
		int pageNo  =  (cPage-1)/pageBarSize * pageBarSize+1;
		int pageEnd =  pageNo + pageBarSize - 1; 
		
		//[이전]
		if(pageNo == 1) {
			
		}else {
			pageBar +="<a href=" +request.getContextPath()+"/admin/memberList?cPage="+(pageNo-1)+"><span>[이전]</span></a>";
			
		}
		
		//[pageNo]
		while(pageNo <= pageEnd && pageNo <= totalPage) {
			if(pageNo == cPage) {
				pageBar += "<span>" + pageNo + "</span>";
				
			}else {
				pageBar += "<a href="+request.getContextPath()+"/admin/memberList?cPage="+pageNo+"><span>" + pageNo + "</span></a>";
			}
			pageNo++;
		}
		
		
		//[다음]
		
		if(pageNo > totalPage) {
			
		}else{
			pageBar +="<a href=" +request.getContextPath()+"/admin/memberList?cPage="+(pageNo)+"><span>[다음]</span></a>";
		}
		
		
		
		
		String url = "/WEB-INF/views/admin/memberList.jsp";
		//2. view단 처리위임
		RequestDispatcher reqDispatcher = request.getRequestDispatcher(url);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		
		
		
		
		
		
		reqDispatcher.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
