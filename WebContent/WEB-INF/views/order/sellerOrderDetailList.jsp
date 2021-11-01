<%@page import="com.kh.afm.order.model.vo.OrderDetail"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<OrderDetail> list = (List<OrderDetail>) request.getAttribute("list");
%>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/user.css" />

<section id="sellerOrderDetailList-container">
<h1>상품 결제내역</h1>

<table id="tbl-sellerOrderDetailList">
	<tr>
		<th>주문상세번호</th>
		<th>상품번호</th>
		<th>주문번호</th>
		<th>수량</th>
		<th>가격</th>
		<th>결제처리상태</th>
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
		
		<td>
			
			<select class="order-status" data-order-detail-no="<%= orderDetail.getOrderDetailNo()%>">
				<option value="N" <%= "N".equals(orderDetail.getPayStatus()) ? "selected" : "" %> disabled>
					결제대기
				</option>
				
				<option value="Y" <%= "Y".equals(orderDetail.getPayStatus()) ? "selected" : "" %>>
					결제확인
				</option>
			</select>
				
		</td>
	</tr>
<%
	}
%>

</table>

</section>

<!-- 결제상태 변경시 폼전송 -->
<form action="<%=request.getContextPath()%>/order/updateOrderStatus" method="POST" name="updateOrderStatusFrm">
	<input type="hidden" name="orderDetailNo" />
	<input type="hidden" name="orderStatus" />
</form>

<script>
/* 결제상태 변경시 체인지 이벤트 */
$(".order-status").change((e) => {
	const $this = $(e.target);
	const orderDetailNo = $this.data("orderDetailNo"); 
	const orderStatus = $this.val();
	
	console.log("orderDetailNo = " + orderDetailNo);
	console.log("orderStatus = " + orderStatus);
	
	
	// jsp의 EL문법과 js의 String Template 충돌 (이스케이프 처리)
	const msg = `주문상세번호 [\${orderDetailNo}]의 결제상태를 [\${orderStatus}]로 변경하시겠습니까?`;
	
	if(confirm(msg)){
		const $frm = $(document.updateOrderStatusFrm);
		$frm.find("[name=orderDetailNo]").val(orderDetailNo);
		$frm.find("[name=orderStatus]").val(orderStatus);
		$frm.submit();
	}
	else{
		// 초기값으로 복귀
		$this.find("[selected]").prop("selected", true);
	}
});


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>