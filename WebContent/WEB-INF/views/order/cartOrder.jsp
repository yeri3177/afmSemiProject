<%@page import="com.kh.afm.order.model.vo.OrderAddress"%>
<%@page import="com.kh.afm.cart.model.vo.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>장바구니 상품 주문</title>
<%
List<Cart> list = (List<Cart>)request.getAttribute("list");
List<OrderAddress> adrList = (List<OrderAddress>)request.getAttribute("adrList");
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
<h2>장바구니 상품 주문</h2>
<form name="order" id="order" method="post" class="order" action="<%= request.getContextPath() %>/order/order" onsubmit="return adrCheckSubmit();">
	
	<%
	int sum = 0;
	int sumcount = 0;
	int i = 0;
		for(Cart order : list){
			sum += (order.getProductPrice() * order.getProductQuantity());
			sumcount += order.getProductQuantity();
	%>
	<div>
		<div><%= order.getRenamedFilename() %></div>
		<div>상품명 : <%= order.getProductName() %></div>
		<div>수량 : <%= order.getProductQuantity() %></div>
		<div>가격 : <%= order.getProductPrice() * order.getProductQuantity() %></div>
	</div>
	<% i++;} %>
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
		<input type="hidden" name="orderProductList" value="<%= list %>"/>
		<p class="orderSumcount">주문 상품 갯수 : <%= sumcount %></p>
		<p class="orderSum">주문 금액 : <%= sum %></p>
	</div>
	<div>
		<input type="hidden" name="totalQuantity" value="<%= sumcount %>" readonly/>
		<input type="hidden" name="totalPrice" value="<%= sum %>" readonly/>
	</div>
	<button type="submit">주문하기</button>
</form>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>