<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.notice.model.vo.*"%>
    <%
    	List<Notice> list = (List<Notice>)request.getAttribute("list");
    %>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <style>
    
    #notice-container{}
    #notice-container{}
    #notice-container table{border-collapse:collapse; width:100%; clear:both;}
    #notice-container table th, #notice-container table td {padding:10px; border:1px solid #bbb}
    #notice-container table th{}
    #notice-container table td{}
    .notice_no{text-align:center;}
    .file_path{text-align:center;}
    .notice_statuse{text-align:center;}
    #btn-add{margin-bottom:30px; float:right;}
    
    </style>
    <div id="notice-container">
    	<h2>공지사항</h2>
    	
    	<% if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getUserId())){ %>
    		<input type="button" value="글쓰기" id="btn-add" onclick="fn_insertNotice();" />
			<script>
				function fn_insertNotice(){
					location.href="<%=request.getContextPath()%>/notice/noticeForm";
				}
			</script>
    		
    		
    		
		<% } %>    	
    	
    	<table id="tbl-notice">
    		<tr>
    			<th>번호</th>
    			<th>제목</th>
    			<th>내용</th>
    			<th>작성자</th>
    			<th>작성일</th>
    			<th>첨부주소</th>
    			<th>등록상태</th>
    		</tr>
	<%
			if(list==null || list.isEmpty() ){
		%>
		
		<tr>
			<td colspan="9" align="center">데이터가 존재하지 않습니다.</td>
		</tr>
				
		<% }else{		
			for(Notice n : list){
		%>
		<% if(n.getStatus().equals("Y")){ %>	
			<tr>
				<td class="notice_no"><a href="<%= request.getContextPath()%>/member/memberView?userId=<%=n.getNoticeNo() %>"><%=n.getNoticeNo() %></a></td>
				<td><a href="<%= request.getContextPath()%>/notice/noticeView?no=<%=n.getNoticeNo() %>"><%=n.getNoticeTitle()  %></a></td>
				<td><%=n.getNoticeContent()  %></td>
				<td><%=n.getNoticeWriter()  %></td>
				<td><%=n.getNoticeDate()  %></td>
				<td class="file_path"><% if(n.getFilePath() != null){ %> <a href="<%=request.getContextPath()%>" ><img src="<%=request.getContextPath()%>/images/file.png" /></a> <% }else{ %> 없네 <% }%></td>
				<td class="notice_statuse"><%=n.getStatus()  %></td>
			</tr>	 
		<%} %>
			<%}
		}%>
		
    		
    		
    	</table>
    
    </div>
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
