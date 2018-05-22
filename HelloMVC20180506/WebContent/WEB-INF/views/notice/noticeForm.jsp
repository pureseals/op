<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.notice.model.vo.*"%>
    <%
   		Notice notice = (Notice)request.getAttribute("notice"); 
    
    %>
    <%@ include file="/WEB-INF/views/common/header.jsp" %>
	<style>
	#tbl-notice_write{width:100%; border-collapse: collapse;}
	#tbl-notice_write td, #tbl-notice_write th{border:1px solid #bbb;}
	#tbl-notice_write td{text-align:left; padding:10px;} 
	
	
	#tbl-notice_write th{padding:10px;  }
	.submit_notice{padding:20px; font-size:54px;   background:#333; border:none; color:white;}
	
	</style>
	
	<script>
		function validate(){

			var content = $("[name=content]").val();
			
			if(content.trim().length == 0){
				alert("내용을 입력하세요.");
				return false; 
			}
			
			return true;
		}
	
	</script>
	<h2>
		공지사항 작성
	</h2>
		<form action="<%= request.getContextPath() %>/notice/noticeFormEnd" method="post" enctype="multipart/form-data">
	    <table id="tbl-notice_write">
	    	<tr>
	    		<th>제목</th>
	    		<td><input type="text" name="title" required/></td>
	    	</tr>
	    	<tr>
	    		<th>작성자</th>
	    		<td><input type="text" name="writer" value="<%=memberLoggedIn.getUserId() %>" /></td>
	    	</tr>
	    	<tr>
	    		<th>첨부파일</th>
	    		<td><input type="file" name="up_file"/></td>
	    	</tr>
	    	<tr>
	    		<th>내용</th>
	    		<td><textarea name="content" cols="50" rows="5"></textarea></td>
	    	</tr>
	  		<tr>
	  			<th colspan="2"><input type="submit" value="등록"  class="submit_notice" onclick="return validate()"/></th>
	  		</tr>
	    </table>
	    </form>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
