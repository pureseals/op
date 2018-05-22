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
	
	
	
	span#fname{
		position:absolute;
		left:88px;
		top:42px;
		width:285px;
		background:#f5f5f5;
	
	}
	
	
	
	
	</style>
	
	<script>
	$(function(){
		$("[name=up_file]").change(function(){
			//$(this).val()은 선택한 파일명임.
			
			if($(this).val()==""){
				$("#fname").show();
			}else{
				$("#fname").hide();
				
				
			}
		})
		
		
	})
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
		공지사항 수정
	</h2>
		<form action="<%= request.getContextPath() %>/notice/noticeUpdateEnd" method="post" enctype="multipart/form-data">
	    <table id="tbl-notice_write">
	    	<tr>
	    		<th>글번호</th>
	    		<td> <input type="text" name="no" value="<%= notice.getNoticeNo() %>" readonly required/> </td>
	    	</tr>
	    
	    	<tr>
	    		<th>제목</th>
	    		<td><input type="text" name="title" value="<%= notice.getNoticeTitle() %>" required/></td>
	    	</tr>
	    	<tr>
	    		<th>작성자</th>
	    		<td><input type="text" name="writer" value="<%= notice.getNoticeWriter() %>" /></td>
	    	</tr>
	    	<tr>
	    		<th>첨부파일</th>
	    		<td style="position:relative">
	    		<% if(notice.getFilePath() != null){ %>
					<!-- 파일 태그의 value 속성은 임의로 변경할 수 없음 -->
	    			<input type="file" name="up_file" />
					<span id="fname"><%=notice.getFilePath() %></span>
					<!-- 파일변경대비 기존파일이름 필드 -->
					<input type="hidden" name="old_file" value= "<%= notice.getFilePath() %>" />
	    		<%} else { %>
	    			<input type="file" name="up_file" />
	    		<%} %>
	    	</tr>
	    	<tr>
	    		<th>첨부파일</th>
	    		<td><%= notice.getFilePath()!=null ? "<a href='javascript:fn_fileDownload("+notice.getFilePath()+")' ><img src='"+request.getContextPath()+"/upload/notice/"+notice.getFilePath()+"' /></a>" : "" %></td>
	    	</tr>
	    	<tr>
	    		<th>내용</th>
	    		<td><textarea name="content" cols="50" rows="5"> <%= notice.getNoticeContent() %></textarea></td>
	    	</tr>
	  		<tr>
	  			<th colspan="2"><input type="submit" value="수정"  class="submit_notice" onclick="return validate()"/></th>
	  		</tr>
	    </table>
	    </form>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
