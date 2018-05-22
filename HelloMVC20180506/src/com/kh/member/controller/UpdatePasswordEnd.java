package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class UpdatePasswordEnd
 */
@WebServlet(name="updatePasswordEnd", urlPatterns="/member/updatePasswordEnd")
public class UpdatePasswordEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String rpassword = request.getParameter("rpassword");
		String password = request.getParameter("password");
		
		
		Member m = new MemberService().selectOne(userId);
		
		System.out.println("패스워드 M" + m.getPassword());
		System.out.println("패스워드 리콰이어드" + rpassword);
		
		String view= "";
		String msg = "";
		String loc = "/";
		
		if(m.getPassword().equals(rpassword)) {
			int result = 0;
			System.out.println("패스워드 확인");
			result = new MemberService().updateMemberPassword(userId, password);
			if(result > 0 ) {
				view = "/WEB-INF/views/common/msg.jsp";
				msg = "패스워드 변경 성공";
				System.out.println("패스워드 변경 성공");
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				
				RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
				reqDispatcher.forward(request, response);
				
			}else {
				System.out.println("패스워드 변경 실패");
			}
		}else {
			request.setAttribute("userId", userId);
			request.setAttribute("alert", "패스워드 입력값 오류!");
			System.out.println("패스워드 입력값 오류");
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberUpdatePassword.jsp");
			reqDispatcher.forward(request, response);
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
