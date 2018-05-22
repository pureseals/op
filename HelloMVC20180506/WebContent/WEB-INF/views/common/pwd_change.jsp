<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
	String loc = request.getContextPath() + (String)request.getAttribute("loc");
%>


<script>

	alert("<%=msg%>");
	if("<%=msg %>" == "패스워드 변경 성공"){
		self.close();
	}
	
	location.href="<%=loc%>";

</script>