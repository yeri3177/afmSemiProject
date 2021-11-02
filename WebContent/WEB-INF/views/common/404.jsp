<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>afm 404</title>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/error404.css" />

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Alfa+Slab+One&family=Balsamiq+Sans:wght@700&display=swap" rel="stylesheet">
<!--------------- favicon --------------->
<link rel="icon" href="<%= request.getContextPath() %>/images/common/favicon_virus.png">

</head>
<body>


<div class="error-404-page">
	
	<div class="leftbox">
		<img id="errortext" src="<%=request.getContextPath()%>/images/error/404_text.PNG" />
	
		<div id="text1">Not Found</div>
		<div id="text2">요청하신 페이지를 찾을수 없습니다.</div>
		
		<button id="homeBtn" onclick="location.href='<%= request.getContextPath() %>/index.jsp'">HOME</button>
	</div>
	
	<div class="rightbox">
		<img id="girlimg" src="<%=request.getContextPath()%>/images/error/girl1.PNG" />
		<img id="angry1" src="<%=request.getContextPath()%>/images/error/angry1.PNG" />
		<img id="angry2" src="<%=request.getContextPath()%>/images/error/angry2.PNG" />
	</div>
	
</div>	
	
</body>
</html>