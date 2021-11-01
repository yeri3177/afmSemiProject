<%@page import="com.kh.afm.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	User loginUser = (User) session.getAttribute("loginUser");

	String msg = (String) session.getAttribute("msg");
	if(msg != null) session.removeAttribute("msg");
	
	System.out.println(msg);
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>afm 관리자 페이지</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
	
	<!--------------- font --------------->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	
	<!-- font : Alfa Slab One -->
	<link href="https://fonts.googleapis.com/css2?family=Alfa+Slab+One&display=swap" rel="stylesheet">
	
	<!-- font : Abril Fatface -->
	<link href="https://fonts.googleapis.com/css2?family=Abril+Fatface&family=Alfa+Slab+One&family=Ubuntu:wght@300;400;500;700&display=swap" rel="stylesheet">
	
	<!--------------- favicon --------------->
	<link rel="icon" href="<%= request.getContextPath() %>/images/admin/setting_icon.png">

	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
	
<style>
	#msgbox {
		visibility : <%= msg == null ? "hidden" : "visible" %>;
	}
</style>


</head>
<body>

<!-- 관리자페이지 전체 -->
<section class="admin-page"> 

	<!-- 사이드메뉴 전체 -->
	<div class="side-menu-box">
		
		<!-- 사이드메뉴 맨위 텍스트부분 -->
	    <div class="side-menu-profile clearfix" id="adminLogo">
	    	<a href="<%=request.getContextPath()%>/admin/adminMain" >
		    	<img src="<%=request.getContextPath()%>/images/admin/setting_icon.png" />
		    	<span>Settings</span>
	    	</a>
	    </div>
    
	    <!-- 사이드메뉴부분 -->
	    <div class="snb">
	      <ul>
	      	<!----------------- 메뉴1 --------------------->
	        <li class="on">
	          <a href="<%=request.getContextPath()%>/admin/userList">사용자 관리</a>
	          <ul>
	          	<!-------- 소메뉴 1-1 -------->
	            <li>
	              <a href="<%=request.getContextPath()%>/admin/userList">회원 목록</a>
	            </li>
	            <!-------- 소메뉴 1-2 -------->
	            <li>
	              <a href="<%=request.getContextPath()%>/admin/delUserList">회원탈퇴 목록</a>
	            </li>
	          </ul>
	        </li>
	
			<!----------------- 메뉴2 --------------------->
	        <li class="on">
	            <a href="<%=request.getContextPath()%>/admin/productList">상품 관리</a>
	            <ul>
	              <!-------- 소메뉴 2-1 -------->
	              <li>
	                <a href="<%=request.getContextPath()%>/admin/productList">상품 목록</a>
	              </li>
	              <!-------- 소메뉴 2-3 -------->
	              <li>
	                <a href="<%=request.getContextPath()%>/admin/productReportList">상품 신고내역</a>
	              </li>
	            </ul>
	          </li>
	      </ul>  
	    </div><!-- 사이드메뉴부분 끝 -->
	</div><!-- 사이드메뉴 전체 끝-->
	
	<!-- 오른쪽 콘텐츠 부분 시작 -->
	<div class="right-contents">
	
		<!-- 상단박스 시작 -->
		<div class="topbox">
			
			<!-- 페이지명 -->
			<div class="pagename">
				<span>관리자 페이지</span>
				<div class="loginName">
					<mark><%= loginUser.getUserName() +"("+ loginUser.getUserId()%>)</mark>님 안녕하세요.
				</div>
			</div>
			
			<!-- msg session 뜨게 하는곳 -->
			<div id="msgbox">

			</div>
			
			<div class="btnbox">
				<!-- 홈 버튼 -->
				<div class="iconbtn" id="homebtn" onclick="location.href='<%= request.getContextPath() %>';">
					<img src="<%=request.getContextPath()%>/images/admin/home_icon.png" /><br />
				</div>
				
				<!-- 로그아웃 버튼 -->
				<div class="iconbtn" id="logoutbtn" onclick="location.href='<%= request.getContextPath() %>/user/logout';">
					<img src="<%=request.getContextPath()%>/images/admin/logout_icon.png" /><br />
				</div>
			</div>
		</div> <!-- 상단박스 끝 -->
		
		<!-- 오른쪽 하단 콘텐츠 영역 시작 -->
		<section class="right-bottom-contents">
		
<script>


$msgbox = $("#msgbox");

<% if(msg!= null) { %>

	$msgbox.append($("<span> <%= msg %> </span>"));
	
	setTimeout(() => {
		$msgbox.css('visibility','hidden');
	}, 5000)
	
<% } %>

</script>	