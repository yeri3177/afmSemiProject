<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>afm 관리자 페이지</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
</head>

<body>

<!-- 관리자페이지 전체 -->
<section class="admin-page"> 

	<!-- 사이드메뉴 전체 -->
	<div class="side-menu-box">
		
		<!-- 사이드메뉴 맨위 텍스트부분 -->
	    <div class="side-menu-profile clearfix" id="adminLogo">
	    	<img src="<%=request.getContextPath()%>/images/admin/setting_icon.png" />
	    	<span>Settings</span>
	    </div>
    
	    <!-- 사이드메뉴부분 -->
	    <div class="snb">
	      <ul>
	      	<!----------------- 메뉴1 --------------------->
	        <li class="on">
	          <a href="#">사용자 관리</a>
	          <ul>
	            <li>
	              <a href="<%=request.getContextPath()%>/admin/userList">회원 목록</a>
	            </li>
	            <li>
	              <a href="#">탈퇴회원 목록</a>
	            </li>
	          </ul>
	        </li>
	
			<!----------------- 메뉴2 --------------------->
	        <li class="on">
	            <a href="javascript:void(0)">상품 관리</a>
	            <ul>
	              <li>
	                <a href="javascript:void(0)">상품 목록</a>
	              </li>
	              <li>
	                <a href="javascript:void(0)">상품 신고내역</a>
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
				관리자 페이지
			</div>
			
			<!-- 로그아웃 -->
			<div class="logoutbox">
				로그아웃
				<img src="<%=request.getContextPath()%>/images/admin/logout_icon.png" />
			</div>
		</div> <!-- 상단박스 끝 -->
		
		<!-- 오른쪽 하단 콘텐츠 영역 시작 -->
		<section class="right-bottom-contents">