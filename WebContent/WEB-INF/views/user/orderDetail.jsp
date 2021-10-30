<%@page import="com.kh.afm.user.model.vo.OrderDetail"%>
<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.user.model.vo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css" />


<section id="orderList-container">
<h1>구매내역</h1>

<table id="tbl-order">
	<tr>
		<th>상품명</th>
		<th>수량</th>
		<th>가격</th>
		<th>결제상태</th>
	</tr>
<%
	/* for(Order order : list) { */
%>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
<%
	/* } */
%>
</table>

<!-- 페이지바 영역 -->
<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	

</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>