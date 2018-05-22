package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet(name="MemberUpdate", urlPatterns="/member/memberUpdate")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		//String password = request.getParameter("password");
		String userName = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String[] hobbys = request.getParameterValues("hobby");
		String hobby = String.join(",", hobbys);
		int result =0;
		
		Member m = new Member();
		
		m.setUserId(userId);
		//m.setPassword(password);
		m.setUserName(userName);
		m.setAge(age);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setGender(gender);
		m.setHobby(hobby);
		
//		
		result = new MemberService().updateMember(m);
		
		
		if(result> 0) {
			HttpSession session = request.getSession();
			Member ms = new MemberService().selectOne(((Member)session.getAttribute("memberLoggedIn")).getUserId());
			session.setAttribute("memberLoggedIn", ms);
		}
		request.setAttribute("Member", m);
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberEnrollEnd.jsp");
		reqDispatcher.forward(request, response);
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
