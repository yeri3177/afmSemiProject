<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />

<section id="board-container">

<h1>Category</h1>
<ul>
	<li><a href="">곡류</a></li>
	<li><a href="">과실류</a></li>
	<li><a href="">채소류</a></li>
</ul>

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
			<td><%= _product.getpNo() %></td>
			<td><img alt="<%= request.getContextPath() %>/images/common/logo.png" src="" width="32px"></td>
			<td><%= _product.getpTitle() %></td>
			<td><%= _product.getpWriter() %></td>
			<td><%= _product.getpRegDate() %></td>
			<td><%= _product.getpRecommend() %></td>
		</tr>
<%
	}
%>
	</table>


<input 
		type="button" 
		value="글쓰기" 
		id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/product/productForm';" />
		
<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
