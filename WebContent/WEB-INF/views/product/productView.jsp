<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
%>
<section id="board-container">
	<h2>게시판</h2>
	<div><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" alt="대표이미지" width="300px" height="300px"/></div>	
	<br />
	<hr />
	<br />
	<div>작성자 : <%= product.getUserId() %></div>
	<div>제목 : <%= product.getpTitle() %></div>
	<div>가격 : <%= product.getpPrice() %></div>
	<div>제목 : <%= product.getpTitle() %></div>
	<div>배송 : <%= product.getpPost() %></div>
	<div>남은 수량 : <%= product.getpCnt() %></div>
	<div>카테고리 : <%= product.getpCategory() %></div>
	<div>추천수 : <%= product.getpRecommend() %></div>
	<br />
	<hr />
	<br />
	<div><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>" alt="상세이미지" width="300px" height="300px"/></div>	
	<div>본문 : <%= product.getpContent() %></div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>