<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.order.model.vo.Order"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<title>주문 확인</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/orderCheck.css"/>
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
	<div class="orderCheckDiv">중복입급에 주의해 주세요.</div>
<%
int i = 0;
	for(Order order : orderList){
%>
	<div class="orderListInfo<%= i %>">
		<div class="orderListSimpleInfo">
			<div>주문 일자 : <%= (Date)order.getOrderDate() %></div>
			<div>총 주문 금액 : <%= order.getTotalPrice() %> 원</div>
		</div>
		<input type="hidden" name="orderNo<%=i%>" value="<%= order.getOrderNo() %>"/>
		<script>
		window.addEventListener('load', function(){
			for(var j = 0; j < <%= orderList.size() %>; j++){
			var inputOrderNo = document.querySelector('input[name=orderNo'+<%=i%>+']');
	        var orderNo = inputOrderNo.getAttribute('value');
			$.ajax({
				url: "<%= request.getContextPath() %>/order/orderDetailCheck",
				data:{'orderNo':orderNo},
				dataType: "text",
				success(data){
					const $RDdiv<%= i %> = $(`<div class='orderDetailList'></div>`);
					
					const arr = data.split("\n");
					$(arr).each((i, str)=>{
						let temp = str.split(", ");

						var sellerName = temp[1];
						var name = sellerName.split("=");
						var seller = name[1];
						
						var pNo = temp[2];
						var productNo = pNo.split("=");
						var no = productNo[1];

						var pTitle = temp[3];
						var productTitle = pTitle.split("=");
						var title = productTitle[1];

						var pCnt = temp[4];
						var productCnt = pCnt.split("=");
						var count = productCnt[1];
						
						var pPrice = temp[5];
						var productPrice = pPrice.split("=");
						var price = productPrice[1];
						
						var aNo = temp[7];
						var accountNo = aNo.split("=");
						var account = accountNo[1];
						
						var bankName = temp[8];
						var Name = bankName.split("=");
						var bank = Name[1];
						var bName = bank.replace(']','');
						
						var html<%= i %> = `<div class="orderDetailListData<%=i%>" style="border-bottom: 1px dashed black;display:flex;justify-content: space-evenly;align-items: center;width:100%;">
										<a href="<%= request.getContextPath() %>/product/productView?pNo=\${no}" class="orderDetailListDataA1">\${title}</a>
										<p class="orderDetailListDataP2">ID : \${seller}</p>
										<p class="orderDetailListDataP3">\${count} 개</p>
										<p class="orderDetailListDataP4">\${price} 원</p>
										<p class="orderDetailListDataP5">\${bName}</p>
										<p class="orderDetailListDataP6">\${account}</p>
									</div>`;
						$RDdiv<%= i %>.append(html<%= i %>);
					});
					$(".orderDetailListBody<%= i %>").html($RDdiv<%= i %>);
				},
				error(xhr,textStatus,err){
					console.log(xhr,textStatus,err);
				}
			})
			}
		});
		</script>
			<div class="orderListHeaderName">
				<div class="subdiv">상품명</div>
				<div class="subdiv">판매자 아이디</div>
				<div class="subdiv">주문 수량</div>
				<div class="subdiv">입금 금액</div>
				<div class="subdiv">입금 은행</div>
				<div class="subdiv">입금 계좌 번호</div>
			</div>
			<div class="orderDetailListBody<%=i%>">
			</div><br />
<%
		i++;}
	}
%>
		
	</div>
	<br />
	<br />
	<br />
<%@ include file="/WEB-INF/views/common/footer.jsp" %>