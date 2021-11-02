<%@page import="com.kh.afm.order.model.vo.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css" />
<%
	List<Order> list = (List<Order>) request.getAttribute("orderList");
%>

<% if(list.isEmpty()){ %>
	<h1 class="orderCompleteisEmpty">텅</h1>
    <h3 class="orderCompleteisEmptyh3">구매하신 내역이 없습니다.</h3>
<% 
}else{
%>


<section id="OrderComplete-container">
<h1 class="orderCompleteisEmptyh1">order</h1>


<table id="tbl-orderComplete">
	<tr>
		<th>구매번호</th>
		<th>구매일</th>
		<th>총결제금액</th>
		<th id="orderbtn">상세보기</th>
	</tr>
<%
	for(Order order : list) {
%>
	<tr>
		<td><%= order.getOrderNo() %></td>
		<td><%= order.getOrderDate() %></td>
		<td><%= order.getTotalPrice() %></td>
		<td><button onclick="location.href='<%= request.getContextPath() %>/order/orderCompleteDetail?orderNo=<%= order.getOrderNo() %>';">상세보기</button></td>
	</tr>
<%
	} 
%>
</table>

</section>

<%
	}
%>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>