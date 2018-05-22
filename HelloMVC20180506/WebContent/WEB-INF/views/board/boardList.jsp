<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.board.model.vo.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% 
	List<Board> boardList = (List<Board>)request.getAttribute("boardList");
	Map<Integer, Integer> bcc = (Map<Integer, Integer>)request.getAttribute("bcc");
%>
<style>
*{box-sizing:border-box;}
a{text-decoration: none; color:#333;}
ul, li{padding:none; list-style: none; }

#boardList-container{}
#boardList-container #tbl-board	{border-collapse:collapse; width:100%;}
#boardList-container #tbl-board th, #boardList-container #tbl-board td{border:1px solid #bbb; padding:10px; }	

#pageBar{width:100%;  text-align:center; }
#pageBar ul{overflow:hidden;}
#pageBar li{margin-right:5px; display:inline-block; border:1px solid #bbb; cursor:pointer;}
#pageBar li a{padding:10px 15px; display:inline-block; width:100%; height:100%;}
#pageBar li:hover{background:#bbb;}
#pageBar .now{padding:10px 15px; background:#333; color:#fff;}
#btn-add{border:1px solid #ccc; background:#333; padding:50px; font-size:55px; width:100%; color:#fff;}
</style>

<section id="boardList-container">
	<div id="board-container">
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>내용</th>
				<!-- <th>첨부파일</th> -->
				<th>첨부파일</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%
			if(boardList==null || boardList.isEmpty()){
		%>
		
		<tr>
			<td colspan="9" align="center">데이터가 존재하지 않습니다.</td>
		</tr>
				
		<% }else{		
			for(Board b : boardList){
		%>	
			<tr>
				<td><%=b.getBoard_no() %></td>
				<td><a href="<%= request.getContextPath()%>/board/boardView?no=<%=b.getBoard_no() %>"><%=b.getBoard_title()%></a>  [<%= bcc.get(b.getBoard_no()) %>]</td>
				<td><%=b.getBoard_writer()%></td>
				<td><%=b.getBoard_content()%></td>
				
				
				<%-- <td><%=b.getBoard_renamed_filename()%></td> --%>
				<td>
				<% if(b.getBoard_original_filename() != null){ %>
				<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
				<% }%>
				</td>
				
				<td><%=b.getBoard_date()%></td>
				<td><%=b.getBoard_readcount()%></td>
			</tr>	 
			
			<%}
		}%>
			
			<% if(memberLoggedIn != null){ %>
				<input type="button" value="글쓰기" id="btn-add" onclick="fn_goBoardForm();" />
			<%} %>
		</table>
	</div>

<div id="pageBar"><%=request.getAttribute("pageBar") %></div>
</section>
<script>
<% if(memberLoggedIn != null){ %>
function fn_goBoardForm(){
	location.href="<%= request.getContextPath()%>/board/boardWrite?type=write";
}
<%}%>

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>		
		
		
	