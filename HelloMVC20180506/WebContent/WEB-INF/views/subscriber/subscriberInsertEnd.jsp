<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Subscriber s = (Subscriber)request.getAttribute("sub");

%>    

<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%= s.getsId() %>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
