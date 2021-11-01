<%@page import="com.kh.afm.order.model.vo.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.afm.order.model.vo.OrderAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cartOrder.css"/>
<title>상품 주문</title>
<%
List<OrderAddress> adrList = (List<OrderAddress>)request.getAttribute("adrList");

String productName = (String)request.getAttribute("productName");
int productNo = (int)request.getAttribute("productNo");
String productRenamedFilename = (String)request.getAttribute("productRenamedFilename");
int productPrice = (int)request.getAttribute("productPrice");
int productQuantity = (int)request.getAttribute("productQuantity");
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
<h2 class="orderH21">상품 주문</h2>
<form name="order" id="order" method="post" class="order" action="<%= request.getContextPath() %>/order/productOrder" onsubmit="return adrCheckSubmit();">
	<input type="hidden" name="ProductNo" value="<%= productNo %>" readonly/>
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
		<div class="displayProductName"><%= productName %></div>
		<div class="displayProductQuantity"><%= productQuantity %> 개</div>
		<div class="displayProductPrice"><%= (productPrice * productQuantity) %> 원</div>
	</div>
	<div>
		<h2 class="orderH22">배송 주소를 선택해 주세요.</h2>
	<div class="hiddenOrderAddress">
	<% 
	int j = 0;
	for(OrderAddress adr : adrList){
	%>
		<div class="orderAddressSet">
		<input class="orderAddressRadio" type="radio" name="address" id="address-<%= j %>" value="<%= adr.getAddressNo() %>">
        <label class="orderAddressRadioLabel" for="address-<%= j %>"><%= adr.getAddressName() %></label>
        	<div class="orderAddressRoad">주소 : <%= adr.getAddressRoad() %></div>
        	<div class="orderAddressDetail">상세 주소 : <%= adr.getAddressDetail() %></div>
        </div>
	<% j++;} %>
	</div>
	<div class="hiddenProduct1">
		<p class="orderSumcount">주문 상품 갯수 : <%= productQuantity %></p>
		<p class="orderSum">주문 금액 : <%= (productPrice * productQuantity) %></p>
	</div>
	<div class="hiddenProduct2">
		<input type="hidden" name="totalQuantity" value="<%= productQuantity %>" readonly/>
		<input type="hidden" name="totalPrice" value="<%= (productPrice * productQuantity) %>" readonly/>
	</div>
	<button class="orderProductBtn" type="submit">주문하기</button>
</form>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>