<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="com.kh.board.model.vo.*, java.util.*" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


<%
	Board b = (Board)request.getAttribute("board");
	List<BoardComment> bcList = (List<BoardComment>)request.getAttribute("commentList");

	System.out.println("board@boardView.jsp="+b);
	
	String memId = null;
	if(memberLoggedIn !=null){
		memId = memberLoggedIn.getUserId();
	}
	System.out.println("테스트종료");
	System.out.println(memId);
%>


<style>

*{
box-sizing:border-box;
}
#board-container{
width:100%;
}

#board-container table{
	border-collapse:collapse;
	width:100%;
	
}

#board-container table input{padding:10px; }


#board-container table th, #board-container table td{
border:1px solid #bbb;
padding:5px 10px 10px 10px;

} 

.submit_btn{padding:30px; font-size:20px;  background:#333; color:#fff; border:none;}
.up_file_img {width:100%;}

/* 댓글관련 */

div#comment-container button#btn-insert{
	width:100px;
	height:100px;
	color:white;
	background:#333;
	position:relative;
	top:-40px;
	border:none;
}


.level1{
}
#board-container table .level2 td:first-of-type{
	padding-left:100px

}
#comment-container button.btn-reply{display:none; padding:12px; border:none; background:white; color:#333;}
#comment-container tr:hover {background:#333; color:#fff;}
#comment-container tr:hover button.btn-reply{display:inline;}
#board-container table th, #board-container table td{border:none;}
.btn_box{text-align:right;}
#board-container table th, #board-container table td{border-top:1px solid #bbb; border-bottom:1px solid #bbb;}
.btn-delete{background:red; color:white; display:none;}
#comment-container tr:hover button.btn-delete{display:inline;}
</style>

<div id="board-container">
<h2>게시글</h2>

	<table>
		<tr>
			<th>글번호</th>
			<td><%=b.getBoard_no() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=b.getBoard_title() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<%=b.getBoard_writer() %>
			</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>
				<%=b.getBoard_readcount() %>
			</td>
		</tr>		
		<tr>
		
		
			<th>첨부파일</th>
			<td>
				<% if(b.getBoard_original_filename() != null){ %>	
				<a href="<%=request.getContextPath() %>/board/boardFileDownload?fname=<%= b.getBoard_renamed_filename()%>">		
					<img src="<%=request.getContextPath()%>/images/file.png" alt="첨부파일" width="16px"/>
				</a>
					<%= b.getBoard_renamed_filename()!=null ? "<a href=javascript:fn_fileDownload('"+b.getBoard_renamed_filename()+"') ><img src='"+request.getContextPath()+"/upload/board/"+b.getBoard_renamed_filename()+"' class='up_file_img'/></a>" : "" %></td>
				<%} %>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<%=b.getBoard_content() %>
			</td>
		</tr>
		<tr>

		<%-- 관리자 또는 글 작성자인 경우 수정/삭제기능 제공 --%>
		<% if( memberLoggedIn != null && (memberLoggedIn.getUserId().equals("admin") || memberLoggedIn.getUserId().equals(b.getBoard_writer()))){ %> 
			<th colspan=2>
				<input type="submit" value="수정" class="submit_btn" onclick="fn_updateBoard()" />
				<input type="submit" value="삭제" class="submit_btn" onclick="fn_deleteBoard()" />
			</th>
		</tr>
		
		<%} %>
	</table>	
	
<hr style ="margin:30px 0;"/>

<div id="comment-container">
	<div class="comment-editor">
		<form action="<%=request.getContextPath() %>/board/boardCommentInsert" method="post">
			<textarea name="boardCommentContent" id="" cols="100" rows="6"></textarea>
			<button id="btn-insert" type="submit">등록</button>
			<input type="hidden" name="boardCommentWriter" value='<%= memberLoggedIn != null ? memberLoggedIn.getUserId() : "" %>' />
			<input type="hidden" name="boardRef" value="<%= b.getBoard_no() %>" />
			<input type="hidden" name="boardCommentRef" value="0" />
			<input type="hidden" name="boardCommentLevel" value="1" />
		</form>	
	</div>
	<% if(bcList!=null){ %>
	<table id="tbl-comment">
		<% for(BoardComment bc : bcList){ %>
			
			<% if(memberLoggedIn != null) {%>
				<%if(memberLoggedIn != null &&
		("admin".equals(memberLoggedIn.getUserId())
		|| bc.getBoardCommentWriter().equals(memberLoggedIn.getUserId()))
			){ %>
						
			<form action="<%=request.getContextPath() %>/board/boardCommentDeleteServlet" name="boardCommentDel" method="post">
				<input type="hidden" name="commetDel" value="" />
				<input type="hidden" name="loc" value="<%=b.getBoard_no() %>" />
			</form>
			<%} %>
		<%} %>
			<% if(bc.getBoardCommentLevel() == 1){
		%>
			<tr class="level1">
				<td>
					<sub class="comment-writer"><%= bc.getBoardCommentWriter() %></sub>
					<sub class="comment-writer"><%= bc.getBoardCommentDate() %></sub>
					<br />
					<%=bc.getBoardCommentContent() %>
				</td>
				<td class="btn_box">
					<button class="btn-reply" value ="<%= bc.getBoardCommentNo()%>">답글</button>
					<!-- 삭제버튼추가 -->
					<%if(memberLoggedIn != null &&
						("admin".equals(memberLoggedIn.getUserId())
						|| bc.getBoardCommentWriter().equals(memberLoggedIn.getUserId()))
							){ %>
						<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
					<%} %>
				</td>
			</tr>			
		<%}else{ %>
			<tr class="level2">
				<td>
					<sub class="comment-writer"><%= bc.getBoardCommentWriter() %></sub>
					<sub class="comment-writer"><%= bc.getBoardCommentDate() %></sub>
					<br />
					<%=bc.getBoardCommentContent() %>
				</td>
				<td class="btn_box">
					<!-- 삭제버튼추가 -->
					<%if(memberLoggedIn != null &&
						("admin".equals(memberLoggedIn.getUserId())
						|| bc.getBoardCommentWriter().equals(memberLoggedIn.getUserId()))
							){ %>
						<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>

					<%} %>
				</td>
			</tr>			
			
		<%} %>
		<%} %>
	</table>
	<%} %>
</div>

		
</div>

<script>
fn_loginAlert = function(){
	<% if( memberLoggedIn == null ){ %> 
		alert("로그인후 이용하실 수 있습니다.");
		$("#userId").focus();
	<% }else{ %>
	<% } %>
}


function fn_checkLogin(){
	<% if( memberLoggedIn == null ){ %> 
	alert("로그인후 이용하실 수 있습니다.");
	$("#userId").focus();
	<% } %>
}



//댓글삭제버튼 클릭시
$(".btn-delete").click(function(e){
	console.log($(this).parent().parent());
	console.log($(this).val());
	fn_checkLogin();
	
	$("[name=commetDel]").val($(this).val());
	
	
	$("[name=boardCommentDel]").submit();

});

$("textarea[name=boardCommentContent]").click(fn_loginAlert);
$("[name=boardCommentFrm]").submit(function(e){
	
	if(<%= memberLoggedIn == null  %>){ 
		alert("로그인후 이용하실 수 있습니다.");
		$("#userId").focus();
		fn_loginAlert;
		e.preventDefault();
		return;
	}
});



console.log("test");
function fn_fileDownload(filePath){
	console.log(filePath);
	//ie는 자동으로 인코딩 처리 하지 않음.
	filePath = encodeURIComponent(filePath);
	location.href= "<%=request.getContextPath() %>/board/boardFileDownload?fname="+filePath;
}


// 답글버튼 클릭시
$(".btn-reply").on("click", function(){
	<% if(memberLoggedIn != null){ %>
		var tr = $("<tr></tr>");
		var html = '<td style="display:none; text-align:left;" colsapn="">';
		
		html += '<form action="<%=request.getContextPath() %>/board/boardCommentInsert" method="post">';
		html += '<textarea name="boardCommentContent" id="" cols="100" rows="6"></textarea>';
		html += '<button id="btn-insert" type="submit">등록</button>';
		html += '<input type="hidden" name="boardCommentWriter" value="<%= memberLoggedIn != null ? memberLoggedIn.getUserId() : "" %>" />';
		html += '<input type="hidden" name="boardRef" value="<%= b.getBoard_no() %>" />';
		html += '<input type="hidden" name="boardCommentRef" value="'+$(this).val()+'" />';
		html += '<input type="hidden" name="boardCommentLevel" value="2" />';
		html += '</form>';	
		html += '</td>';

		
		//생성된 노드를 페이지에 추가
		tr.html(html);
		
		console.log(tr);
		
		tr.insertAfter($(this).parent().parent()).children("td").slideDown(300);
		$(this).off("click");
		
		tr.find('form').submit(function(e){
			if(<%= memberLoggedIn == null  %>){ 
				alert("로그인후 이용하실 수 있습니다.");
				$("#userId").focus();
				fn_loginAlert;
				e.preventDefault();
				return;
			}
			
			var len = $(this).children("textarea").val().trim().length;
			if(len==0) e.preventDefault();
			
		});

		tr.find("textarea").focus();
		

		
	<%} else {%>
	//로그인하지 않은 경우
 
	
	
	<%} %>
});





</script>




<!-- end of div#board-container -->









<%@ include file="/WEB-INF/views/common/footer.jsp" %>		
		
		
	