<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Product> list = (List<Product>) request.getAttribute("list");
%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css" />


<section id="sellerProductList-container">
<h1>판매하는 상품</h1>

<table id="tbl-sellerProductList">
	<tr>
		<th>상품번호</th>
		<th>상품명</th>
		<th>가격</th>
		<th>카테고리</th>
		<th>개수</th>
		<th>무료배송</th>
		<th>상품노출여부</th>
		<th>상품등록일</th>
		<th>판매내역</th>
	</tr>
<%
	for(Product product : list) {
%>
	<tr>
		<td><%= product.getpNo() %></td>
		<td><%= product.getpTitle() %></td>
		<td><%= product.getpPrice() %></td>
		<td><%= product.getpCategory() %></td>
		<td><%= product.getpCnt() %></td>
		<td><%= product.getpPost() %></td>
		<td><%= product.getpExpose() %></td>
		<td><%= product.getpRegDate() %></td>
	
		<td><button onclick="location.href='<%= request.getContextPath() %>/order/sellerOrderDetailList?productNo=<%= product.getpNo() %>';">상세보기</button></td>
	
	</tr>
<%
	} 
%>
</table>


</section>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>