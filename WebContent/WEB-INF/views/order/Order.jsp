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

	<div class="displayProductHeader">
		<div class="orderHeaderImg">상품 이미지</div>
		<div class="orderHeaderName">상품명</div>
		<div class="orderHeaderCnt">주문 수량</div>
		<div class="orderHeaderPrice">주문 금액</div>
	</div>
	<div class="displayProduct">
		<div class="displayProductImg">
		<% if(productRenamedFilename != null){ %>
		<img class="orderProductThumbnail" src="<%= request.getContextPath() %>/upload/product/<%= productRenamedFilename %>" alt="" />
		<% }else{ %>
		<img class="orderProductThumbnail" src="<%= request.getContextPath() %>/upload/product/20211027_134914715_460.gif" alt="" />
		<% } %>
		</div>
		<div class="displayProductName"><%= order.getProductName() %></div>
		<div class="displayProductQuantity"><%= productQuantity %> 개</div>
		<div class="displayProductPrice"><%= (productPrice * productQuantity) %> 원</div>
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