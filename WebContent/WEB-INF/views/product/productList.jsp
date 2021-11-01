<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
%>

<section id="board-container">

<select name="pCategory" id="pCategory">
	<option name="곡류" value="곡류">곡류</option>
	<option name="과실류" value="과실류">과실류</option>
	<option name="채소류" value="채소류">채소류</option>
</select>
<h2>상품 목록</h1>
	<table id="tbl-board">
		<tr>
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

<% 
	if(loginUser != null){ 
%>	
<input 
		type="button" 
		value="글쓰기" 
		id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/product/productForm';" />
<%
	} 
%>
<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
