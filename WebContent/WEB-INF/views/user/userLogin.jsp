<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>afm login</title>

<!-- favicon -->
<link rel="icon" href="<%= request.getContextPath() %>/images/common/logo_favicon.png">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
<%
	String saveId = null;
	Cookie[] cookies = request.getCookies();
	
	if(cookies != null){
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
			
			if("saveId".equals(name)){
				saveId = value;
			}
		}
	}
%>

<style>
	body {
		height: 100vh;
		background: url('<%=request.getContextPath()%>/images/user/loginbackground.jpg');
		background-repeat: repeat;
		background-position:center center;	
	}
</style>
</head>
<body>
	<div id="login-container">
		<!-- 로고이미지 -->
		<a href="<%= request.getContextPath() %>/index.jsp"><img class="logo-img" src="<%=request.getContextPath()%>/images/common/logofont.png" /></a>
		
		<!-- 로그인 텍스트 -->
		<!-- <h1 class="loginFrm-headline">Login</h1> -->
		
		
		<!-- 로그인 폼 -->
		<form id="loginFrm" name="loginFrm" action="<%=request.getContextPath() %>/user/userLogin" method="POST">
			<table class="loginFrmTable">
				<!-- 1행 : 아이디 -->
				<tr>
					<td>
						<input type="text" name="userId" id="userId" placeholder="아이디" tabindex="1"  value="<%= saveId != null ? saveId : "" %>" required>
					</td>
				</tr>
				
				<!-- 2행 : 비밀번호 -->
				<tr>
					<td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2" required></td>
				</tr>
				
				<!-- 3행 : 아이디저장 -->
				<tr>
					<td>
						<input type="checkbox" name="saveId" id="saveId" <%= saveId != null? "checked" : "" %> tabindex="3"/>
						<label for="saveId">아이디저장</label>
					</td>
				</tr>
				
				<!-- 4행 : 로그인버튼 -->
				<tr>
					<td>
						<input type="submit" value="로그인" class="login-con-btn" tabindex="4">
					</td>
				</tr>
				
				<!-- 5행 : 회원가입 -->
				<tr>
					<td>
						<input type="button" value="회원가입" class="login-con-btn" onclick="location.href='<%= request.getContextPath() %>/user/userTypeEnroll';">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>