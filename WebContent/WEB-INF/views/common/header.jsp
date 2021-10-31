<%@page import="com.kh.afm.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css"/>

<!-- favicon -->
<link rel="icon" href="<%= request.getContextPath() %>/images/common/logofont.png">

<%
	User loginUser = (User)session.getAttribute("loginUser");
%>
<script>
<%
	String msg = (String)session.getAttribute("msg");
	//System.out.println("msg@header.jsp = " + msg);
	if(msg != null) session.removeAttribute("msg");

%>
<% if(msg != null) {%>
	alert("<%= msg %>");
<% } %>
</script>
<title>a farmer's market</title>

</head>
<body>
    <!-- Header -->
    <header class="">
    <div class="mainHeader">
          <h2 class="nickName"><a class="homepageName" href="<%= request.getContextPath() %>/index.jsp"><img src="<%= request.getContextPath() %>/images/common/logo.png" class="logoImg"/><img class="logofont" src="<%= request.getContextPath() %>/images/common/logofont.png"></a></h2>
      <div class="search">
	      <form class="searchform" action="">
	      <label for="search">검색 : </label>
	      <input type="text" id="search" name="search" class="searchinput" placeholder="검색어를 입력하세요."/>
	      <button class="btnSearch" type="submit"><img class="searchImg" src="<%= request.getContextPath() %>/images/common/search.png"></button>
	      </form>
      </div>
      <nav class="header-navMenu">
        <div class="container">
        	<div class="dropdown">
	        	<div class="button">
				  <button class="profileImg">
<% if(loginUser == null){ %>
					  <a href="<%= request.getContextPath() %>/user/userLogin"><img class="profile" src="<%= request.getContextPath() %>/images/common/profile.png">
					 	 <span class="buttonImgName">&nbsp;&nbsp;로그인</span>
					  </a>
<% }else{ %>
					<div class="headerDropDown">
					  <div class="headerDropDownBtn"><img class="profile" src="<%= request.getContextPath() %>/images/common/mypage.png">
					 	 <span class="buttonImgName"><%= loginUser.getUserName() %>님</span></div>
					 	 <div class="headerDropDownMenu">
						 	 <a href="<%= request.getContextPath() %>/user/userDetail">내 정보 보기</a>
						 	 <a href="#">주문 확인</a>
						 	 <a href="#">주문 내역</a>
						 	 <a href="#">판매 내역</a>
					 	 </div>
					</div>
<% } %>
				  </button>
				</div>
				<div class="button">
				  	<button class="cartImg">
				  	<% if(loginUser == null){ %>
					  <a href="<%= request.getContextPath() %>/user/userLogin"><img class="cart" src="<%= request.getContextPath() %>/images/common/cart3.png">
				  <% }else{ %>
					  <a href="<%= request.getContextPath() %>/cart/cartList"><img class="cart" src="<%= request.getContextPath() %>/images/common/cart3.png">
				  <% } %>
					  	<span class="buttonImgName">&nbsp;장바구니</span>
					  </a>
					</button>
				  </div>
				<div class="button">
				  <% if((loginUser != null) && ("A").equals(loginUser.getUserRole())){ %>
				  <button class="settingImg">
					  <a href="<%= request.getContextPath() %>/admin/adminMain"><img class="setting" src="<%= request.getContextPath() %>/images/common/settings.png">
					  	<span class="buttonImgName">&nbsp;&nbsp;관리자</span>
					  </a>
				  </button>
				</div>
				  <% } %>
				<div class="button">
				  <% if(loginUser != null){ %>
					  <button class="indexlogoutImg">
						  <a href="<%= request.getContextPath() %>/user/logout"><img class="indexlogout" src="<%= request.getContextPath() %>/images/common/logout_icon.png">
						 	 <span class="buttonImgName">로그아웃</span>
					  <% } %>
						  </a>
					  </button>
				  </div>
			</div>
		</div>
      </nav>
	</div>
          <div>
				<div class="main-nav">
					<div class="home"><a href="<%= request.getContextPath() %>">Home</a></div>
					<div class="info"><a href="<%= request.getContextPath() %>/info.jsp">소개</a></div>
					<div class="product"><a href="<%= request.getContextPath() %>/product/productList">상품</a></div>
					<div class="cs"><a href="<%= request.getContextPath() %>/csboard/csboardList">고객센터</a></div>
				</div>
		</div>
    </header>
    