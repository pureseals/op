<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.member.model.vo.*"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<% List<Member> list = (List<Member>)request.getAttribute("list"); 
	System.out.println("list@memberFinder.jsp="+list);
	
	String memberFiderUrl = request.getContextPath()+"/admin/memberFinder";
	
	String searchType = (String)request.getAttribute("searchType");
	String searchKeyword = (String)request.getAttribute("searchKeyword");

%>
<style>
#tbl-member{border-collapse: collapse; width:100%;}
#tbl-member th, #tbl-member td {padding:10px; border:1px solid #bbb; font-size:14px; }
#tbl-member tr:hover{background:#333; color:white;}
#tbl-member th{}
#tbl-member td{}
#search-userId input{}
#search-userId button{ }
div#search-container{padding:3px; margin:0 0 10px 0; background:rgba(0, 188, 212, 0.3);}

#search-userId  {display:none;}
#search-userName  {display:none;}
#search-gender  {display:none;}

div#pageBar{margin-top:10px; text-align:center; background:rgba(0, 188, 212, 0.3);}
div#pageBar span{display:inline-block; padding:10px; background:#333; color:#fff; margin:0px 2px;}
div#pageBar span:hover{background:red;}

</style>
<script>
$(function(){
	console.log("<%=searchType %>");
	console.log("<%=searchKeyword %>");
	$("#searchType").change(function(){
		$("#search-userId").hide();
		$("#search-userName").hide();
		$("#search-gender").hide();
		
		$("#search-"+$(this).val()).css("display","block");
		
	});
	
});

</script>
<section id="memberList-container">
	<h2>회원관리 검색</h2>
	<!-- 검색영역 -->
	
	<div id="search-container">
		검색타입 : 
		<select name="searchType" id="searchType">
			<option value="userId" <%="userId".equals(searchType) ? "selected" : "" %>>아이디</option>
			<option value="userName" <%="userName".equals(searchType) ? "selected" : "" %>>회원명</option>
			<option value="gender" <%="gender".equals(searchType) ? "selected" : "" %>>성별</option>
		</select>
		<div id="search-userId">
			<form action="<%= memberFiderUrl %> ">
				<input type="hidden" name="searchType" value="userId"/>
				<input type="search" name="searchKeyword" id="" size="25" value= "<%="userId".equals(searchType) ? searchKeyword : "" %>" placeholder="검색할 아이디를 입력하세요." required />
				<button type ="submit">검색</button>
			</form>
		</div>
		<div id="search-userName">
			<form action="<%= memberFiderUrl %>">
				<input type="hidden" name="searchType" value="userName" />
				<input type="search" name="searchKeyword" id="" size="25" value= "<%="userName".equals(searchType) ? searchKeyword : "" %>" placeholder="검색할 회원명을 입력하세요." required />
				<button type ="submit">검색</button>
			</form>
		</div>
		<div id="search-gender">
			<form action="<%= memberFiderUrl %>">
				<input type="hidden" name="searchType" value="gender" />
				<input type="radio" name="searchKeyword" id=""   value="M" <%="gender".equals(searchType)&& "M".equals(searchKeyword) ? "checked" : "" %> /> 남
				<input type="radio" name="searchKeyword" id=""  value="F" <%="gender".equals(searchType)&& "F".equals(searchKeyword) ? "checked" : "" %> /> 여
				
				<button type ="submit">검색</button>
			</form>
		</div>
	
	</div>
	<table id="tbl-member">
	
		<tr>
			<th>아이디</th>
			<th>이름</th>
			<th>성</th>
			<th>나이</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>주소</th>
			<th>취미</th>
			<th>가입날짜</th>
		</tr>
		<%
			if(list==null || list.isEmpty()){
		%>
		
		<tr>
			<td colspan="9" align="center">데이터가 존재하지 않습니다.</td>
		</tr>
				
		<% }else{		
			for(Member m : list){
		%>	
			<tr>
				<td><a href="<%= request.getContextPath()%>/member/memberView?userId=<%=m.getUserId() %>"><%=m.getUserId() %></a></td>
				<td><%=m.getUserName()  %></td>
				<td><%="M".equals(m.getGender()) ? "남" : "여"  %></td>
				<td><%=m.getAge()  %></td>
				<td><%=m.getEmail()  %></td>
				<td><%=m.getPhone()  %></td>
				<td><%=m.getAddress()  %></td>
				<td><%=m.getHobby()  %></td>
				<td><%=m.getEnrollDate()  %></td>
			</tr>	 
			
			<%}
		}%>
		
	
	</table>

<div id="pageBar"><%=request.getAttribute("pageBar") %></div>
</section>
<style>
 #search-userId{display:<%="userId".equals(searchType) ? "block" : "none" %>}
 #search-userName{display:<%="userName".equals(searchType) ? "block" : "none" %>}
 #search-gender{display:<%="gender".equals(searchType) ? "block" : "none" %>}
#memberList-container input{padding:10px;}

</style>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>		
		
		
	