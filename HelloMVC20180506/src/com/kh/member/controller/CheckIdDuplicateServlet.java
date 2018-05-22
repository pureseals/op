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
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/member/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.전송값 한글대비 인코딩
		request.setCharacterEncoding("utf-8");
		//2. 전송값 꺼내서 변수에 담기
		String userId = request.getParameter("userId");
		//3. 비지니스로직처리
		
		Member m = new MemberService().selectOne(userId);
		boolean isUsable =  m==null ? true :false;
		System.out.println(userId+" : isUsable? " + isUsable);
		request.setAttribute("userId", userId);
		request.setAttribute("isUsable", isUsable);
		//4.view단 위임
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/checkIdDuplicate.jsp");
		reqDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
