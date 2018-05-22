package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/member/memberDelete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
	
		
		
		
		HttpSession session = request.getSession();
		int result = 0;
		//System.out.println("지우는 아이디 " + session.getAttribute("memberLoggedIn") );
		String userId = ((Member)session.getAttribute("memberLoggedIn")).getUserId();
		
		result = new MemberService().MemberDelete(userId);
		
		if(result > 0) {
			request.setAttribute("complete", "succed");
			session.invalidate();
		}
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/");
		reqDispatcher.forward(request, response);
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
