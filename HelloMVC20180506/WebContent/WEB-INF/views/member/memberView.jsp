<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Arrays"%>
    

<%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%

//	Member m = (Member)session.getAttribute("memberLoggedIn");
    Member m = (Member)request.getAttribute("Member");
   	String[] hobbys = m.getHobby().split(",");
   	System.out.println(hobbys);
    %>
    <style>
 
    </style>
<script>
function fn_validation(){
	$pw = $("#pass_Word").val();
		
	if($pw == ""){
		alert("비번을 입력 하세요");
		return false; 
	}		
	
	$pwc = $("#pass_WordChk").val();
	
	if($pwc == ""){
		alert("비번 확인을 입력 하세요");
		return false;
	}
	
	if($pw != $pwc){
		
		alert("비밀번호가 맞지 않습니다.")
		return false; 		
	}
	return true; 
}
</script>
 <form action="<%= request.getContextPath() %>/member/memberUpdate" method="post" onsubmit="return fn_validation();">
<table>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="userId" id="user_Id" value="<%= m.getUserId() %>" readonly />  </td>
		
	</tr>
<!-- 	<tr>
		<th>패스워드</th>
		<td><input type="password" name="password" id="pass_Word" /></td>
	</tr>
	<tr>
		<th>패스워드확인</th>
		<td><input type="password" name="password_chk" id="pass_WordChk" /></td>
	</tr> -->
	<tr>
		<th>이름</th>
		<td><input type="text" name="userName" id="userName" value="<%= m.getUserName() %>" /></td>
	</tr>
	<tr>
		<th>나이</th>
		<td><input type="number" name="age" id="age" value="<%= m.getAge() %>"/></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="email" name="email" id="email" value="<%= m.getEmail() %>" /></td>
	</tr>
	<tr>
		<th>휴대폰</th>
		<td><input type="tel" id="phone" name="phone" value="<%= m.getPhone() %>" /></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="address" id="address"  value="<%= m.getAddress() %>"/></td>
	</tr>	
	<tr>
		<th>성별</th>
		<td>
			
			<input type="radio" name="gender" id="gender0" value="M" <% if(m.getGender().equals("M")){ %>checked <% } %> />
			<label for="gender0">남</label>
			<input type="radio" name="gender" id="gender1" value="F" <% if(m.getGender().equals("F")){ %>checked <% } %> />
			<label for="gender1">여</label>
		</td>
	</tr>
	<tr>
		<th>취미</th>
		<td><input type="checkbox" name="hobby" id="hobby1"  value ="운동" <% if(Arrays.asList(hobbys).contains("운동")){%> checked<%} %>/>
		<label for="hobby1">운동</label>
		<input type="checkbox" name="hobby" id="hobby2"  value ="등산" <% if(Arrays.asList(hobbys).contains("등산")){%> checked<%} %>/>
		<label for="hobby2">등산</label>
		<input type="checkbox" name="hobby" id="hobby3"  value ="독서" <% if(Arrays.asList(hobbys).contains("독서")){%> checked<%} %>/>
		<label for="hobby3">독서</label>
		<input type="checkbox" name="hobby" id="hobby4"  value ="게임"<% if(Arrays.asList(hobbys).contains("게임")){%> checked<%} %>/>
		<label for="hobby4">게임</label>
		<input type="checkbox" name="hobby" id="hobby5"  value ="여행"<% if(Arrays.asList(hobbys).contains("여행")){%> checked<%} %>/>
		<label for="hobby5">여행</label>
		</td>
	</tr>
	<tr>
		<th>가입일</th>
		<td><input type="text" name="enroll" id="enroll"  value="<%= m.getEnrollDate() %>" readonly="readonly"/></td>
	</tr>	

</table>
<input type="submit" value="수정" />
<input type="reset" value="초기화" />
<input type="button" value="비밀번호변경" onclick="fn_update_password()"/>
<input type="button" value="탈퇴하기" onclick="location.href='<%= request.getContextPath() %>/member/memberDelete'" />
</form>


<form name="updatePasswordFrm" method="post">
	<input type="hidden" name="userId" id="userIds"/>
</form>
		
<script>
function fn_update_password(){
	
	var userId = $("#user_Id").val().trim();
	
	var url = "<%=request.getContextPath()%>/member/UpdatePassword";
	var title = "updatePassword";
	var status = "left=500px, top=200px, width=400px, height=210px";
	var popup = window.open(url, title, status);
	
	
	var updatePasswordFrm = document.updatePasswordFrm;
	updatePasswordFrm.userId.value = $("#user_Id").val().trim();
	
	
	updatePasswordFrm.target = title;
	updatePasswordFrm.action = url;
	updatePasswordFrm.submit();
}

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
