<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   String userId = (String)request.getAttribute("userId");
   boolean isUsable = (boolean)request.getAttribute("isUsable");
   
   %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 중복검사</title>
<script src="<%=request.getContextPath() %>\js\jquery-3.3.1.js"></script>
<script>
function fn_checkIdDuplicate(){
	var userId = $("#userId").val().trim();
	if(userId.length<4){
		alert("아이디는 4글자 이상 가능합니다.");
		return false;
	}
	$("#userId").val(userId);
	return true;
}

function setUserId(userId){
	console.log(userId);
	var frm = opener.document.memberEnrollFrm;
	//opener는 팝업창을연 부모창을 가리킴.
	//memberEnroll.jsp

	frm.userId.value = userId;
	frm.idValid.value = 1;
	frm.pass_Word.focus();
	self.close();
}
</script>
<style>

	div#checkid-container{text-align:center; padding-top:50px;}
	span#duplicated{color:red; font-weight:bold;}

</style>
</head>
<body>
	<div id="checkid-container">
	
		<% if(isUsable==true){ %>
		[<span><%=userId %></span>]는 사용가능합니다
		<br /><br />
		<button onclick="setUserId('<%=userId%>');">닫기</button>
		<% }else{ %>
		
			[<span id="duplicated"><%=userId %></span>]는 이미 사용중입니다.
			<br /><br />
			<form action="<%=request.getContextPath()%>/member/checkIdDuplicate">
				<input type="text" name="userId" id="userId"/> &nbsp;
				<input type="submit" value="중복검사" onclick="return fn_checkIdDuplicate();"/>
			</form>
		
		<% } %>
	
	</div>
	
	
	

</body>
</html>