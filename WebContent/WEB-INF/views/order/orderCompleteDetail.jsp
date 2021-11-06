<%@page import="com.kh.afm.order.model.vo.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css" />
<%
	List<OrderDetail> list = (List<OrderDetail>) request.getAttribute("orderDetailList");
%>
<% if(list.isEmpty()){ %>
	<h1 class="orderCompleteDetailisEmpty">텅</h1>
    <h3 class="orderCompleteDetailisEmptyh3">구매하신 내역이 없습니다.</h3>
<% 
}else{
%>



<section id="OrderCompleteDeatil-container">
<h1> 구매 상세 내역</h1>

<table id="tbl-orderCompleteDetail">
	<tr>
		<th>상품구매번호</th>
		<th>상품번호</th>
		<th>주문번호</th>
		<th>수량</th>
		<th>가격</th>
		<th>결제상태</th>
	</tr>

<%
	for(OrderDetail orderDetail : list) {
%>
	<tr>
		<td><%= orderDetail.getOrderDetailNo()%></td>
		<td><%= orderDetail.getProductNo()%></td>
		<td><%= orderDetail.getOrderNo()%></td>
		<td><%= orderDetail.getpCnt()%></td>
		<td><%= orderDetail.getpPrice() %></td>
		<td><%= "Y".equals(orderDetail.getPayStatus()) ? "결제확인" : "결제대기"%></td>
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