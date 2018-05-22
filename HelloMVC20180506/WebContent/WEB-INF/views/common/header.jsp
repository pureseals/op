<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.member.model.vo.*"%>
    <%
    
    //Member memberLoggedIn = (Member)request.getAttribute("memberLoggedIn");
    Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
   	System.out.println("테스터스"+memberLoggedIn);
   	
   	String contextPath = request.getContextPath();
   	
   	//쿠키관련 (클라이언트가 부낸 쿠키 처리)
   	
   	Cookie[] cookies = request.getCookies();
   	//최초접속시 cookie는 null임.
   	
   	boolean saveId = false;
   	String userIdSaved = "";
   	
   	if(cookies !=null){
   		System.out.println("--------------------------------");
   		System.out.println("브라우져가 전송한 쿠키목록");
   		System.out.println("--------------------------------");
		for(Cookie c : cookies ){
			String key = c.getName();
			String value = c.getValue();
	   		System.out.println("쿠키가요 ===> " + key + " : " + value );
	   		
	   		//아이디저장 쿠키검사
	   		if("saveId".equals(key)){
	   			saveId = true;
	   			userIdSaved = value;
	   		}else{
	   			
	   		}
		}   		
   		System.out.println("--------------------------------");
   	}
   	
   String del = (String)request.getAttribute("complete");
   	
   
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<script src="<%=request.getContextPath() %>/js/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script>

function fn_loginValidate(){
	console.log("<%= memberLoggedIn %>")
	//아이디
	if($("#userId").val().trim().length==0){
		alert("아이디를 입력하세요.");
		$("#userId").focus();
		return false;
	}
	
	//패스워드 검사
	if($("#password").val().trim().length==0){
		alert("비밀번호를 입력하세요");
		$("#password").focus();
		return false;
	}
	
	return true;
};


function fn_logout(){
	
	
};

</script>
</head>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<!-- 로그인시작 -->
			<div class="login-container">
				<% if(memberLoggedIn==null) {%>
				<form id="loginFrm" action="<%=request.getContextPath() %>/member/MemberLoginServlet" method="post" onsubmit="return fn_loginValidate();">
				<table>
					<tr>
						<td><input type="text" name="userId" id="userId" placeholder="아이디" value = '<%= saveId ? userIdSaved : "" %>' /></td>
						<td></td>
					</tr>
					<tr>
						<td><input type="password" name="password" id="password" placeholder="비밀번호"/></td>
						<td><input type="submit" value="로그인" /></td>
						
					</tr>
					<tr>
						<td colspan="2">
							<input type="checkbox" name="saveId" id="saveId" <%=saveId ? "checked":"" %>/>
							<label for="saveid">아이디저장</label>
							<input type="button" value="회원가입" onclick="location.href='<%=request.getContextPath()%>/member/memberEnroll'" />
						</td>
					</tr>
				</table>
				</form>
				<%}else{ %>
			
					<table id="logged-in">
						<tr>
							<td><%= memberLoggedIn.getUserName() %>님, 안녕하세요.</td>
						</tr>
						<tr>
							<td><input type="button" value="내정보 보기" onclick='location.href="<%=request.getContextPath()%>/member/memberView?userId=<%=memberLoggedIn.getUserId()%>"'>&nbsp;
							<input type="button" value="로그아웃" onclick='location.href="<%=request.getContextPath()%>/member/logout"'></td>
						</tr>
					
					</table>
			
				<%} %>
				
				
			</div>
			<!-- 로그인 끝 -->
			<!-- 메인메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="<%=contextPath%>">Home</a></li>
					<li id="notice"><a href="<%= request.getContextPath() %>/notice/noticeList">공지사항</a></li>
					<li id="board"><a href="<%= request.getContextPath() %>/board/boardList">게시판</a></li>
					<% if(memberLoggedIn != null && "admin".equals(memberLoggedIn.getUserId())){ %>
					<li id="board"><a href="<%= request.getContextPath() %>/admin/memberList">회원관리</a></li>
					<%} %>				
					<li id="board"><a href="<%= request.getContextPath() %>/movie/onScreen">상영등록</a></li>
					<li id="board"><a href="<%= request.getContextPath() %>/movie/regMovie">영화등록</a></li>
					
				</ul>
			</nav>
			<!-- 메인메뉴 끝 -->
		</header>
		
		<section id="content">