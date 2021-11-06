<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>afm enroll</title>

<!-- favicon -->
<link rel="icon" href="<%= request.getContextPath() %>/images/common/logo_favicon.png">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
</head>
<body>
	<div id="enrolltype-container">
		
		<!-- 로고이미지 -->
		<a href="<%= request.getContextPath() %>/index.jsp"><img class="logo-img" src="<%=request.getContextPath()%>/images/common/logofont.png" /></a>
		<br />
		
		<!-- 일반회원 가입 -->
		<a href='<%= request.getContextPath() %>/user/userEnroll'>
			<img class="usertype" src="<%=request.getContextPath()%>/images/user/usertype.png" />
		</a>
		
		<!-- 판매자회원 가입 -->
		<a href='<%= request.getContextPath() %>/user/sellerEnroll'>
			<img class="sellertype" src="<%=request.getContextPath()%>/images/user/sellertype.png" />
		</a>
	</div>
</body>
</html>