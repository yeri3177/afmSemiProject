<%@page import="com.kh.afm.order.model.vo.OrderAddress"%>
<%@page import="com.kh.afm.cart.model.vo.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cartOrder.css"/>
<title>장바구니 상품 주문</title>
<%
List<Cart> list = (List<Cart>)request.getAttribute("list");
List<OrderAddress> adrList = (List<OrderAddress>)request.getAttribute("adrList");
/* int[] pNoCheck = request.getAttribute("pNoCheck"); */
%>
<script>
function adrCheckSubmit(){
	var address1 = $('#address-0');
	if($(':radio[name="address"]:checked').length<1){
		alert('주소를 선택해 주세요.');
		address1.focus();
		return false;
	}else{
		<%-- <% 
		int k = 0;
		for(Cart order : list){
				if(pNoCheck[i] < order.getProductNo()){ %>
					return false;
				<% } %>
		
			
				<% k++} %>
		} --%>
		return true;
	}
}

</script>
<h2 class="orderH21">장바구니 상품 주문</h2>
<form name="order" id="order" method="post" class="order" action="<%= request.getContextPath() %>/order/order" onsubmit="return adrCheckSubmit();">
	<div class="displayProductHeader">
		<div class="orderHeaderImg">상품 이미지</div>
		<div class="orderHeaderName">상품명</div>
		<div class="orderHeaderCnt">주문 수량</div>
		<div class="orderHeaderPrice">주문 금액</div>
	</div>
	<%
	int sum = 0;
	int sumcount = 0;
	int i = 0;
		for(Cart order : list){
			sum += (order.getProductPrice() * order.getProductQuantity());
			sumcount += order.getProductQuantity();
	%>
	<div class="displayProduct">
		<div class="displayProductImg">
		<% if(order.getRenamedFilename() != null){ %>
		<img class="orderProductThumbnail" src="<%= request.getContextPath() %>/upload/product/<%= order.getRenamedFilename() %>" alt="" />
		<% }else{ %>
		<img class="orderProductThumbnail" src="<%= request.getContextPath() %>/upload/product/20211027_134914715_460.gif" alt="" />
		<% } %>
		</div>
		<div class="displayProductName"><%= order.getProductName() %></div>
		<div class="displayProductQuantity"><%= order.getProductQuantity() %> 개</div>
		<div class="displayProductPrice"><%= order.getProductPrice() * order.getProductQuantity() %> 원</div>
	</div>
	<% i++;} %>
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
		<p class="orderSumcount">주문 상품 갯수 : <%= sumcount %> 개</p>
		<p class="orderSum">주문 금액 : <%= sum %> 원</p>
	</div>
	<div class="hiddenProduct2">
		<input type="hidden" name="totalQuantity" value="<%= sumcount %>" readonly/>
		<input type="hidden" name="totalPrice" value="<%= sum %>" readonly/>
	</div>
	<button class="orderProductBtn" type="submit">주문하기</button>
</form>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>