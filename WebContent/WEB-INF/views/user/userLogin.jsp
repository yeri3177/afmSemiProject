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
		<img class="frmlogo" src="<%=request.getContextPath()%>/images/common/logofont.png" />
		<h1 class="loginFrm-headline">Login</h1>
	<div class="loginFrmDiv">
		<form class="loginFrm" method="POST" action="<%= request.getContextPath() %>/user/userLogin">
		<label for="inputEmail" class="loginFrmId">아이디</label>
        <input type="text" id="userId" class="frmid" placeholder="아이디를 입력하세요."  required autofocus><BR>
        <label for="inputPassword" class="frmpassword">비밀번호</label>
        <input type="password" id="upw" class="form-control" placeholder="비밀번호를 입력하세요." required><br>
        <div class="logincheckbox">
          <input 
				type="checkbox" 
				name="saveId" 
				id="saveId" />
				<label for="saveId">아이디저장</label>
        </div>
        <div class="join">
        	<a href="<%=request.getContextPath()%>/user/userJoin.jsp">회원가입</a> 
        </div>
                <button id="loginFrmbtn" class="loginFrmbtn" type="submit">로 그 인</button>
		</form>
	
	</div>
</body>
</html>