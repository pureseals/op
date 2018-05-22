<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.notice.model.vo.*"%>
    <%
   		Notice notice = (Notice)request.getAttribute("notice"); 
    
    %>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	<style>
	#tbl-notice_view{width:100%; border-collapse: collapse;}
	#tbl-notice_view td, #tbl-notice_view th{border:1px solid #bbb;}
	#tbl-notice_view td{text-align:left; padding:10px;} 
	
	
	#tbl-notice_view th{padding:10px;  }
	
	</style>
	<script>
		function fn_fileDownload(filePath){
			//ie는 자동으로 인코딩 처리 하지 않음.
			filePath = encodeURIComponent(filePath);
			location.href= "<%=request.getContextPath() %>/notice/noticeFileDownload?fname="+filePath;
		}
	
	
	</script>
	    <table id="tbl-notice_view">
	    	<tr>
	    		<th>제목</th>
	    		<td><%= notice.getNoticeTitle() %></td>
	    	</tr>
	    	<tr>
	    		<th>작성자</th>
	    		<td><%= notice.getNoticeWriter() %></td>
	    	</tr>
	    	<tr>
	    		<th>첨부파일</th>
	    		<td><%= notice.getFilePath()!=null ? "<a href='"+ request.getContextPath()+"/notice/noticeFileDownload?fname="+notice.getFilePath()+"' ><img src='"+request.getContextPath()+"/upload/notice/"+notice.getFilePath()+"' /></a>" : "" %></td>
	    		<td><%= notice.getFilePath()!=null ? "<a href='javascript:fn_fileDownload("+notice.getFilePath()+")' ><img src='"+request.getContextPath()+"/upload/notice/"+notice.getFilePath()+"' /></a>" : "" %></td>
	    		
	    	</tr>
	    	<tr>
	    		<th>내용</th>
	    		<td><%= notice.getNoticeContent() %></td>
	    	</tr>
	    	<%-- 관리자인 경우 공지사항 수정/삭제 가능 --%>
	    	<% if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getUserId())){ %>
	    	<tr>
	    		<td colspan="2"><input type="button" value="수정" onclick="fn_updateNotice();"/> 
	    		 <input type="button" value="삭제" onclick="fn_deleteNotice();"/></td>
	    	</tr>
	    	<%} %>
	    </table>
	    	<%-- 관리자인 경우 공지사항 수정/삭제 가능 --%>
   	    	<% if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getUserId())){ %>
   	    	<script>
   	    	function fn_updateNotice(){
   	    		location.href = "<%=request.getContextPath()%>/notice/noticeUpdate?no=<%= notice.getNoticeNo() %>"
   	    		
   	    	}
   	    	function fn_deleteNotice(){
   	    		var r = confirm("삭제 하시겠습니까?");
	   	    		if(r==true){
						console.log("test");
		   	    		location.href = "<%=request.getContextPath()%>/notice/noticeDeleteEnd?no=<%= notice.getNoticeNo() %>"
	   	    		}else{
	   	    		}
   	    	}
	    	</script>
	    	<%} %>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
