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
 * Servlet implementation class AdminMemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class AdminMemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member memberLoggedIn = (Member)request.getSession(true).getAttribute("memberLoggedIn");
		if(memberLoggedIn == null || !"admin".equals(memberLoggedIn.getUserId())) {
			request.setAttribute("msg", "잘못된 경로로 접근하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			return; 
		}
		
		//1. 파라미터 변수처리
		
		String searchType = request.getParameter("searchType");
		String searchKeyword= request.getParameter("searchKeyword");
		
		System.out.printf("searchType=%s, searchKeyword=%s\n", searchType, searchKeyword);
		//2. 업무로직
		List<Member> list = null;
		int cPage;
		//2.1 검색된 총 회원수 조회
		int totalMember = 0;
		
		switch(searchType) {
		case "userId" : totalMember = new AdminService().selectMemberCountByUserId(searchKeyword); break;
		case "userName" : totalMember = new AdminService().selectMemberCountByUserName(searchKeyword);break;
		case "gender" :totalMember = new AdminService().selectMemberCountByGender(searchKeyword); break;
		}
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage =1;
		}

		
	int numPerPage = 5;
	int totalPage = ((int)Math.ceil((double)(totalMember / numPerPage))+1);
			
	//2.2 페이징된 회원리스트 가져오기
	

	
	switch(searchType) {
		case "userId" : list = new AdminService().selectMemberCountListByUserId(searchKeyword, cPage, numPerPage); break;
		case "userName" : list = new AdminService().selectMemberCountListByUserName(searchKeyword, cPage, numPerPage);break;
		case "gender" :list = new AdminService().selectMemberCountListByGender(searchKeyword, cPage, numPerPage); break;
	}
	
	
	
	
	
	//2.3 페이징바 만들기
	String pageBar = "";
	int pageBarSize = 5; //페이지바 페이지갯수

	int pageNo  =  (cPage-1)/pageBarSize * pageBarSize+1;
	int pageEnd =  pageNo + pageBarSize - 1; 
	
	System.out.println("페이지 넘 " + pageNo);
	System.out.println("페이지 엔드 " + pageEnd);
	System.out.println("토탈 " + totalPage);
	//[이전]
	if(pageNo == 1) {
		
	}else {
		pageBar +="<a href=" +request.getContextPath()+"/admin/memberFinder?searchKeyword="+searchKeyword+"&searchType="+searchType+"&cPage="+(pageNo-1)+"><span>[이전]</span></a>";
	}
	//[pageNo]
	while(pageNo <= pageEnd && pageNo <= totalPage) {
		if(pageNo == cPage) {
			pageBar += "<span>" + pageNo + "</span>";
			
		}else {

			pageBar +="<a href=" +request.getContextPath()+"/admin/memberFinder?searchKeyword="+searchKeyword+"&searchType="+searchType+"&cPage="+pageNo+"><span>"+pageNo+"</span></a>";
		}
		pageNo++;
	}
	//[다음]
	if(pageNo > totalPage) {
	}else{
		pageBar +="<a href=" +request.getContextPath()+"/admin/memberFinder?searchKeyword="+searchKeyword+"&searchType="+searchType+"&cPage="+pageNo+"><span>[다음]</span></a>";
	}
		

	
	
		//2. 업무로직

		

		//3. 처리결과 view단 위임
		request.setAttribute("list", list);
		
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/memberFinder.jsp");
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
