<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user login</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
</head>
<body>
	<div class="login-container">
		<!-- 로고이미지 -->
		<img class="frmlogo" src="<%=request.getContextPath()%>/images/common/logofont.png" />
		
		<!-- 로그인 텍스트 -->
		<h1 class="loginFrm-headline">Login</h1>
		
		<!-- 로그인 폼 -->
		<form id="loginFrm" name="loginFrm" action="<%=request.getContextPath() %>/user/userLogin" method="POST">
			<table>
				<tr>
					<td>
						<input 
							type="text" 
							name="userId" 
							id="userId" 
							placeholder="아이디" 
							tabindex="1" 
							<%-- value="<%= saveId != null ? saveId : "" %>" --%>>
					</td>
					<td><input type="submit" value="로그인" tabindex="3"></td>
				</tr>
				<tr>
					<td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2"></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">
						<%-- <input type="checkbox" name="saveId" id="saveId" <%= saveId != null? "checked" : "" %>/> --%>
						<label for="saveId">아이디저장</label>&nbsp;&nbsp;
						<input 
							type="button" 
							value="회원가입" 
							onclick="location.href='<%= request.getContextPath() %>/user/userEnroll';">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>