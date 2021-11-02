<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
%>
<title>검색 목록</title>
<h2>[<%= searchKeyword %>] 검색목록</h1>
	<table id="tbl-board">
		<tr>
			<th>번호</th>
			<th>사진</th>
			<th>상품</th>
			<th>가격</th>
			<th>판매자</th>
			<th>작성일</th>
			<th>추천수</th>
		</tr>
<%
	for(Product _product : list){
%>
		<tr>
			<th><%= _product.getpNo() %></th>
			<th><img alt="" src="<%= request.getContextPath() %>/upload/product/<%= _product.getAttach1().getRenamedFileName() %>" width="300px" height="300px"></th>
			<th><a href="<%= request.getContextPath() %>/product/productView?pNo=<%= _product.getpNo() %>"><%= _product.getpTitle() %></a></th>
			<th><%= _product.getpPrice() %></th>
			<th><%= _product.getUserId() %></th>
			<th><%= (Date)_product.getpRegDate() %></th>
			<th><%= _product.getpRecommend() %></th>
		</tr>
<%
	}
%>
	</table>
<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>