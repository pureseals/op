package com.kh.member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet(name="MemberLoginServlet", urlPatterns="/member/MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		resPro(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		resPro(request, response);
	}
	
	public void resPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.한글 인코딩처리
		request.setCharacterEncoding("utf-8");
		//2.파라미터값 변수에 기록하기
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		//쿠키관련
		
		String saveId = request.getParameter("saveId");
			
		System.out.println("saveId=" + saveId);
		//on 또는 null
		
		
		System.out.println("userId="+userId);
		System.out.println("password="+password);
		//response.getWriter().append("Served at : " ).append(request.getContextPath());
		
		//3. 비지니스 로직
		int result = new MemberService().loginCheck(userId, password);
		
		//쿠키
		if(saveId != null) {
			Cookie c = new Cookie("saveId", userId);
			c.setMaxAge(60*60*24*7); //유효기간을 초단위로 설정
			c.setPath("/"); //해당 도메인 전역에서 이 쿠키를 사용함.
			response.addCookie(c);
		}else{
			Cookie c = new Cookie("saveId", userId);
			c.setMaxAge(0);
			c.setPath("/");
			response.addCookie(c);
		}
		
		
		
		
		//4. 받은결과에 따라서 view단 분기
		
		String view= "";
		String msg = "";
		String loc = "/";
		

		String Referer = request.getHeader("Referer");
		String Origin = request.getHeader("Origin");
		String url = request.getRequestURL().toString();
		String uri = request.getRequestURI();
		//크롬외 브라우져 용
		if(Origin ==null) {
			Origin = url.replace(uri, "");
		}
		
		loc = Referer.replace(Origin + request.getContextPath(), "");
		
		
		
		
		
		if(result == MemberService.LOGIN_OK) {
			view ="/";
			//회원정보 가져오기
			Member m = new MemberService().selectOne(userId);
			System.out.println("member@MemberLoginServlet="+m);
			
			//request.setAttribute("memberLoggedIn", m);
			
			
			
			
			//세션생성
			//세션이 존재하면, 해당세션을 리턴, 없으면 새로 생성해서 리턴.
			HttpSession session = request.getSession();
			
//			HttpSession session = request.getSession(true);
//			//세션이 존재하면, 해당세션을 리턴, 없으면 null리턴
//			HttpSession session = request.getSession(false);
			System.out.println("발급된 세션아이디 : " + session.getId());
			session.setAttribute("memberLoggedIn", m);
			//세션타임아웃설정
			session.setMaxInactiveInterval(5*60);
			//리다이렉트
			//더이상 request를 이용할 필요 없음.
			
			response.sendRedirect(request.getContextPath()+loc);
			
			//로그인이 실패한경우
		}else{
			view = "/WEB-INF/views/common/msg.jsp";
			
			if(result == MemberService.WRONG_PASSWORD) {
				msg = "패스워드를 잘못 입력하셨습니다.";
			}else if(result == MemberService.ID_NOT_EXIST){
				msg = "존재하지 않는 아이디 입니다.";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			
			RequestDispatcher reqDispatcher = request.getRequestDispatcher(view);
			reqDispatcher.forward(request, response);
			
		}
		
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}


	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

}












