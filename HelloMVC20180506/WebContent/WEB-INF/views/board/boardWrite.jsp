<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>


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
padding:10px;

} 

.submit_btn{padding:30px; font-size:20px;  background:#333; color:#fff; border:none;}

</style>

<div id="board-container">
<h2>게시판 작성</h2>
<form action="<%= request.getContextPath() %>/board/boardWrite?type=end" method="post" enctype="multipart/form-data">

	<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" id="" required/></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>
				<input type="text" name="writer" value="<%= memberLoggedIn.getUserId() %>" readonly required />
			</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="up_file" id="" /></td>
		</tr>
		<tr>
			<th>내용</th>
			<td>
				<textarea name="content" id="" cols="50" rows="5"></textarea>
			</td>
		</tr>
		<tr>
			<th colspan=2>
				<input type="submit" value="등록하기" class="submit_btn" onclick="return fn_validate()" />
			</th>
		</tr>
	</table>		




</form>
</div>
<script>

	function fn_validate(){
		$title = $("input[name=title]").val();
		$content = $("textarea[name=content]").val();
		
		console.log($content);
		
		if($title.trim().length==0 ){
			alert("제목을 입력해주세요")
			return false;	
		}
		
		if($content.trim().length == 0 ){
			alert("내용을 입력해주세요")
			return false;	
		}
		return true;
		
	}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>		
		
		
	