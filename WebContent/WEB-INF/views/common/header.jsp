<%@page import="com.kh.afm.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<!-- google font -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Abril+Fatface&family=Alfa+Slab+One&family=Black+Han+Sans&family=Ubuntu:wght@300;400;500;700&display=swap&family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/header.css"/>

<!-- favicon -->
<link rel="icon" href="<%= request.getContextPath() %>/images/common/logo_favicon.png">

<%
	User loginUser = (User)session.getAttribute("loginUser");
%>
<script>
<%
	String msg = (String)session.getAttribute("msg");
System.out.println("msg = " + msg);
	if(msg != null) session.removeAttribute("msg");

%>
<% if(msg != null) {%>
	alert("<%= msg %>");
<% } %>
</script>
<title>a farmer's market</title>

</head>


    <!-- Header -->
    <header>
    <div class="mainHeader">
          
          <!-- 로고영역 -->
          <h2 class="nickName">
          	<a class="homepageName" href="<%= request.getContextPath() %>/index.jsp">
          		<!-- 과일바구니 이미지 -->
          		<%-- <img src="<%= request.getContextPath() %>/images/common/logo.png" class="logoImg"/> --%>
          		
          		<!-- afm 텍스트 -->
          		<img class="logofont" src="<%= request.getContextPath() %>/images/common/logofont.png">
          	</a>
          </h2>
      
      <!-- 상품 검색 영역 시작-->
      <div class="search">
	      <!-- 검색폼 시작 -->
	      <form class="searchform" action="<%= request.getContextPath() %>/index/indexSearch">
		      <input type="text" id="search" name="search" class="searchinput" placeholder="상품명을 입력하세요."/>
		      
		      <!-- 검색버튼 -->
		      <button class="btnSearch" type="submit"><img class="searchImg" 
		      	src="<%= request.getContextPath() %>/images/common/search.png"></button>
	      </form> <!-- 검색폼 끝 -->
      </div> <!-- 상품 검색 영역 끝-->
      
      <nav class="header-navMenu">
        <div class="container">
        	<div class="dropdown">
	        	<div class="button">
				  <button class="profileImg">
				  
<% if(loginUser == null){ %>
						
					  <!-- 로그인 버튼 -->
					  <a href="<%= request.getContextPath() %>/user/userLogin">
					  
					  		<!-- 기존 로그인 아이콘 이미지 -->
					  		<%-- <img class="profile" src="<%= request.getContextPath() %>/images/common/profile.png"> --%>
					 		
					 		<!-- 과일바구니 이미지 -->
          					<img src="<%= request.getContextPath() %>/images/common/logo.png" class="login-icon-img"/>
					 		
					 		<span class="buttonImgName">&nbsp;&nbsp;&nbsp;&nbsp;로그인</span>
					  </a>
<% }else{ %>
					<div class="headerDropDown">
					  <div class="headerDropDownBtn">
					  	<!-- 마이페이지 아이콘 이미지 -->
					  	<img class="mypage-icon-img" src="<%= request.getContextPath() %>/images/common/mypage.png">
					 	 <span class="buttonImgName"><%= loginUser.getUserName() %>님</span></div>
					 	 <div class="headerDropDownMenu">
						 	 <a href="<%= request.getContextPath() %>/user/userDetail">내 정보 보기</a>
						 	 <a href="<%= request.getContextPath() %>/order/orderComplete">구매 내역</a>
						 	 <a href="<%= request.getContextPath() %>/order/orderCheck">주문 확인</a>
					 	 <% if("S".equals(loginUser.getUserRole())){ %>
						 	 <a href="<%= request.getContextPath() %>/order/sellerProductList">판매 내역</a>
						 	 <% } %>
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

<!-- 네비게이션 영역 -->
<div>
	<div class="mainNav">
		<!-- 홈메뉴 -->
		<div class="home">
			<a href="<%= request.getContextPath() %>">
				<span>Home</span>
			</a>
		</div>
	
		<!-- 소개 메뉴 -->
		<div class="info">
			<a href="<%= request.getContextPath() %>/info.jsp">
				<span>About</span>
			</a>
		</div>
	
		<!-- 상품 메뉴 -->
		<div class="product">
			<a href="<%= request.getContextPath() %>/product/productList">
				<span>Shopping</span>
			</a>
		</div>
	
		<!-- 고객센터 메뉴 -->
		<div class="cs">
			<a href="<%= request.getContextPath() %>/csboard/csboardList">
				<span>QA</span>
			</a>
		</div>
	</div>
</div>
</header>