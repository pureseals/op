<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Member m = (Member)request.getAttribute("Member");

	Member ms = (Member)session.getAttribute("memberLoggedIn");

%>    

<%@ include file="/WEB-INF/views/common/header.jsp" %>
   <% if(m!=null){ %>
    <%= ms.getUserId() %>
    <%= ms.getPassword() %>
    <%= ms.getAddress() %>
    <%= ms.getHobby() %>
    <%= ms.getUserName() %>
    <%= ms.getGender() %>
    <%= ms.getAddress() %>
    <%= ms.toString() %>
    
<%} %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
