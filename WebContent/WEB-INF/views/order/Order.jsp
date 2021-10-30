<%@page import="com.kh.afm.order.model.vo.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.afm.order.model.vo.OrderAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>상품 주문</title>
<%
List<OrderAddress> adrList = (List<OrderAddress>)request.getAttribute("adrList");

int productNo = (int)request.getAttribute("productNo");
String productRenamedFilename = (String)request.getAttribute("productRenamedFilename");
String productPrice = (String)request.getAttribute("productPrice");
String productQuantity = (String)request.getAttribute("productQuantity");
%>
<script>
function adrCheckSubmit(){
	var address1 = $('#address-0');
	if($(':radio[name="address"]:checked').length<1){
		alert('주소를 선택해 주세요.');
		address1.focus();
		return false;
	}else{
		return true;
	}
}
</script>
<h2>상품 주문</h2>
<form name="order" id="order" method="post" class="order" action="<%= request.getContextPath() %>/order/productOrder" onsubmit="return adrCheckSubmit();">

	<div>
		<div><%= order.getRenamedFilename() %></div>
		<div>상품명 : <%= order.getProductName() %></div>
		<div>수량 : <%= order.getProductQuantity() %></div>
		<div>가격 : <%= order.getProductPrice() * order.getProductQuantity() %></div>
	</div>
	<div>
		<h2>배송 주소를 선택해 주세요.</h2>
	<% 
	int j = 0;
	for(OrderAddress adr : adrList){
	%>
		<input class="orderAddressRadio" type="radio" name="address" id="address-<%= j %>" value="<%= adr.getAddressNo() %>">
        <label class="orderAddressRadioLabel" for="address-<%= j %>"><%= adr.getAddressName() %></label>
        	<div class="orderAddressRoad">주소 : <%= adr.getAddressRoad() %></div>
        	<div class="orderAddressDetail">상세 주소 : <%= adr.getAddressDetail() %></div>
	<% j++;} %>
	</div>
	<div>
		<p class="orderSumcount">주문 상품 갯수 : <%= order.getProductQuantity() %></p>
		<p class="orderSum">주문 금액 : <%= order.getProductPrice() * order.getProductQuantity() %></p>
	</div>
	<div>
		<input type="hidden" name="totalQuantity" value="<%= order.getProductQuantity() %>" readonly/>
		<input type="hidden" name="totalPrice" value="<%= order.getProductPrice() * order.getProductQuantity() %>" readonly/>
	</div>
	<button type="submit">주문하기</button>
</form>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>