package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.service.SubscriberService;
import com.kh.member.model.vo.Member;
import com.kh.member.model.vo.Subscriber;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/subscriber/subscriberInsertEnd")
public class SubscriberInsertEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String sNumber = request.getParameter("phone_number");
		String sBirth = request.getParameter("birth");
		String sName = request.getParameter("sName");
		String sId = request.getParameter("sId");
		String sPassword = request.getParameter("password_chk");
		String sEmail = request.getParameter("email");
		

		int result =0;
		
		Subscriber s = new Subscriber();
 
		s.setsId(sId);
		s.setsBirth(sBirth);
		s.setsNumber(sNumber);
		s.setsName(sName);
		s.setsPassword(sPassword);
		s.setsEmail(sEmail);
		System.out.println(s);
		result = new SubscriberService().insertSubscriber(s);
		
		
		if(result > 0 ) {
			request.setAttribute("Subscriber", s);
			System.out.println("성공"+s);
			
		}else {
			request.setAttribute("msg", "회원가입에 실패하였습니다.");
			System.out.println("실패");
		}
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/member/memberEnrollEnd.jsp");
		reqDispatcher.forward(request, response);
		
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
