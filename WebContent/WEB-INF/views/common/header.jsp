<%-- <%@page import="com.kh.afm.member.model.service.MemberService"%> --%>
<%@page import="com.kh.afm.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@page import="com.kh.afm.common.vo.Member"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">

<!-- favicon -->
<link rel="icon" href="<%= request.getContextPath() %>/images/common/logofont.png">

<style>
/* @media all and (min-width:820px) and (max-width:1044px){ */
body{font-family: 'Noto Sans KR', sans-serif; overflow-x: hidden;padding:0;margin:0;padding-top: 20vh;}
a{text-decoration:none;}
header{position: fixed;z-index:99999;}
.mainHeader{background-color:#FBF6F0;top: 0;left: 0;font-weight: bold;justify-content: space-between;width: 100%;height: 20%;display: flex;position: fixed;}
.homepageName{padding:1vw;color:green;display: flex;top: 0;width: 100%;}
.profileImg{background-color:#FBF6F0;padding: 0.1vw;border: none;}
.profileImg a{background-color:#FBF6F0;font-size:1.2vw;}
.cartImg{background-color:#FBF6F0;padding: 0.1vw;border: none;}
.cartImg a{background-color:#FBF6F0;font-size:1.2vw;}
.settingImg{background-color:#FBF6F0;padding: 0.1vw;border: none;}
.settingImg a{background-color:#FBF6F0;font-size:1.2vw;}
.indexlogoutImg{background-color:#FBF6F0;padding: 0.1vw;border: none;}
.indexlogoutImg a{background-color:#FBF6F0;font-size:1.2vw;}
.indexlogout{padding-top:1.5vw;padding-left:0.5vw;width:4vw;}
.profile{padding-top:1.5vw;padding-left:0.5vw;width:4vw;}
.cart{padding-top:1.5vw;padding-left:0.5vw;width:4vw;}
.setting{padding-top:1.5vw;padding-left:0.5vw;width:4vw;}
.logoImg{width:30%;height:30%}
.nickName{font-size:4.5vw;padding:0;margin:0;padding-top:1vh;}
.main-nav{background-color:#FFA45B;padding:0px;margin:auto;display:flex;position:fixed;top:18%;left:50%;transform:translate(-50%,-50%);width:100%;height:4%;font-size:1.1vw;}
.main-nav div{width: 100%;float: left;text-align: center;border-left: 1px #47C83E solid;height:100%;}
.main-nav div:first-of-type{border-left:none;}
.main-nav div a {display: block;padding:.4vw;text-decoration: none;color: black;font-weight: bold;}
.main-nav div:hover{background-color:#00C652;}
.main-nav div:hover > a{color:#FFCD12;}
.dropdown{display: flex;justify-content: flex-end;}
.buttonImgName{display: flex;font-weight: 400;}
.search{display: flex;padding-top:4vh;width: 40%;justify-content: center;}
.searchform{width:90%}
.searchImg{width:4vw;vertical-align: middle;}
.btnSearch{background-color:#FBF6F0;padding: 1vw;border: none;}
.btnSearch:hover{cursor:pointer;}
.searchinput{border:1px green solid;width: 60%;height: 30%;border-radius:5vw;padding-top:0.5vh;font-size:12px;color:#777777;}
.logofont{padding-top:0.5vw;height:5vw;}
/* } */
</style>
<%
	User loginUser = (User)session.getAttribute("loginUser");
System.out.println("indexpage"+loginUser);
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
					  <a href="<%= request.getContextPath() %>/user/userLogin"><img class="profile" src="<%= request.getContextPath() %>/images/common/profile.png">
<% if(loginUser == null){ %>
					 	 <span class="buttonImgName">&nbsp;&nbsp;로그인</span>
<% }else{ %>
					 	 <span class="buttonImgName"><%= loginUser.getUserName() %>님</span>
<% } %>
					  </a>
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
    