<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% String alerts=null; %>
    <% String userId = (String)request.getAttribute("userId"); %>
    <%
		if(request.getAttribute("alert") != null){
			alerts = (String)request.getAttribute("alert");
		}    
    
    
    %>
    
<!DOCTYPE html>
<html>
<head>
<script src="<%=request.getContextPath() %>\js\jquery-3.3.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
if("<%= alerts %>" != "null"){
	alert("<%= alerts %>");
}
function fn_validation(){
	$rpwd = $("#rpassword").val();
	$pwd = $("#password").val();	
	$pwdc = $("#passwordChk").val();
	
	if($rpwd ==""){
		alert("기존 패스워드를 입력해주세요");
		return false; 
	}
	if($pwd ==""){
		alert("패스워드를 입력해주세요");
		return false; 
	}
	if($pwdc ==""){
		alert("확인패스워드를 입력해주세요");
		return false; 
	}
	
	if($pwd =! $pwdc){
		alert("패스워드와 패스워드 확인이 불일치 합니다.");
		return false; 
	}
	return true;
}
function fn_cancel(){
self.close();	
}

</script>
<title>비밀번호 변경</title>
<style>
	input{ padding:10px; }
</style>
</head>

<body>
<form action="<%= request.getContextPath() %>/member/updatePasswordEnd" onsubmit="return fn_validation()" method="post">
	
	<table>
		<tr>
			<td>기존비밀번호</td>
			<td><input type="password" name="rpassword" id="rpassword" /></td>
		</tr>
	
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="password" id="password" /></td>
		</tr>
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="password" name="passwordChk" id="passwordChk" /></td>
		</tr>
		<tr>
				<td>
				</td>
				<td style="text-align: left;">
					<input type="submit" value="전송" style="background: #333; color: #fff; border: 1px solid #ececec;" />
					<input type="button" value="취소" style="background:#ececec; color: #333; border: 1px solid #ececec;" onclick="fn_cancel();"/>
				</td>
				
			</tr>
	
	
	</table>
	
	
	<input type="hidden" name ="userId" id="userId" value="<%= userId %>"/>
	 
	

</form>
</body>
</html>