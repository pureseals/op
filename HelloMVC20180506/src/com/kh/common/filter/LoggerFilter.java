package com.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LoggerFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		   
		      HttpServletRequest httpReq=(HttpServletRequest)request;
		      String uri = httpReq.getRequestURI(); // /mvc/member/memberView 이것을 돌려줌,
		      //사용자의 요청이 http://localhost:9090/mvc/member/memberView?userId=abcde
		      String url = httpReq.getRequestURL().toString();
		      String queryString = httpReq.getQueryString();
		      
		      System.out.println("url="+url);
		      System.out.println("queryString="+queryString);
		      
		      //로깅 정보 출력 
		      System.out.println("===============================================");
		      System.out.println(uri);
		      System.out.println("-----------------------------------------------");
		      
		      //필터체인의 다음필터체인호출      
		      chain.doFilter(request, response);
			
			
			
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	

}
