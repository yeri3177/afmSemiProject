<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.order.model.vo.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>주문 확인</title>
<%
List<Order> orderList = (List<Order>)request.getAttribute("orderList");
%>
<h2 class="orderCheckH21">주문 확인</h2>
<% if(orderList.isEmpty()){ %>
	<h1 class="orderCheckisEmpty">텅</h1>
    <h3 class="orderCheckisEmptyh3">결제하실 내역이 없습니다.</h3>
<% 
}else{
%>
	<div>중복입급에 주의해 주세요.</div>
<%
int i = 0;
	for(Order order : orderList){
%>
	<div class="orderListSimpleInfo<%= i %>">
		<div>주문 일자 : <%= (Date)order.getOrderDate() %></div>
		<div>총 주문 금액 : <%= order.getTotalPrice() %> 원</div>
		<input type="hidden" name="orderNo<%=i%>" value="<%= order.getOrderNo() %>"/>
		<script>
		window.addEventListener('load', function(){
			var inputOrderNo = document.querySelector('input[name=orderNo'+<%=i%>+']');
	        var orderNo = inputOrderNo.getAttribute('value');
			$.ajax({
				url: "<%= request.getContextPath() %>/order/orderDetailCheck",
				data:{'orderNo':orderNo},
				dataType: "text",
				success(data){
					console.log(data);
					const $RDdiv = $(`<div class='orderDetailList'></div>`);
					
					const arr = data.split("\n");
					console.log(arr);
					$(arr).each((i, str)=>{
						console.log(i, str);
						let temp = str.split(", ");

						const pNo = temp[2];
						console.log(pNo);
						let productNo = pNo.split("=");
						const no = productNo[1];

						const pCnt = temp[3];
						let productCnt = pCnt.split("=");
						const pQuantity = productCnt[1];

						const pPrice = temp[4];
						let productPrice = pPrice.split("=");
						const price = productPrice[1];
						
						let html = `<div class="orderDetailListData"><a href="#" class="orderDetailListDataA">상품명 : \${no}</a><p class="orderDetailListDataP">가격 : \${price}</p></div>`;
						$RDdiv.append(html);
					});
					$(".orderDetailListBody").html($RDdiv);
				},
				error(xhr,textStatus,err){
					console.log(xhr,textStatus,err);
				}
			})
		});
		</script>
		<div class="orderDetailListBody">
		</div>
	</div>
<%
		i++;}
	}
%>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>