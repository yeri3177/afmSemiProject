<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<%
	// isErroPage="true"인 경우, 
	// WAS가 에러페이지로 연결시 
	// 발생한 예외객체에 선언없이 exception으로 접근가능
	String msg = exception.getMessage();
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오류</title>
	<style>
	img{padding:0;margin:0;}
	body {text-align: center;background-repeat: no-repeat;background-size:cover;}
	h1 {font-size: 15em;color: #FFCD4A;margin:0;padding:0;text-shadow: 4px 4px #284D8E;margin-top:-10vh;}
	p{font-size:2em;color: #284D8E;text-shadow: 2px 2px #FFCD4A;}
	#msg {color: red;text-shadow: 2px 2px #284D8E;}
	
	<%-- body {text-align: center;background-image: url('<%=request.getContextPath()%>/images/common/25336.jpg');background-repeat: no-repeat;background-size:cover;}
	h1 {font-size: 15em;margin:0;padding:0;color: white;}
	p{font-size:2em;color: white;}
	#msg {color: red;}  --%>
	
	/* body {text-align: center;}
	h1 {font-size: 15em;margin:0;padding:0;}
	p{font-size:2em;}
	#msg {color: red;} */
	</style>
</head>
<body>
	<img src="<%=request.getContextPath()%>/images/common/error.jpg"/>
	<h1>error</h1>
	<p>이용에 불편을 드려 죄송합니다.</p>
	<p id="msg"><%= msg %></p>
	<p><a href="<%= request.getContextPath() %>">홈으로</a></p>

</body>
</html>