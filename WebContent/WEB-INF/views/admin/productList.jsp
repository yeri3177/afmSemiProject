<%@page import="java.util.List"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	List<Product> list = (List<Product>) request.getAttribute("list");
%>	

<section id="productList-container" class="admin-container">

<table id="tbl-product">
    <thead>
        <tr>
			<th>상품번호</th>
			<th>판매자아이디</th>
			<th>상품명</th>
			<th>가격</th>
			<th>카테고리</th>
			<th>개수</th>
			<th>무료배송</th>
			<th>상품노출여부</th>
			<th>상품등록일</th>
        </tr>
    </thead>
    
    <tbody>

<%
	for(Product product : list){
%>
	<tr>
		<td><%= product.getpNo() %></td>
		<td><%= product.getUserId() %></td>
		<td><%= product.getpTitle() %></td>
		<td><%= product.getpPrice() %></td>
		<td><%= product.getpCategory() %></td>
		<td><%= product.getpCnt() %></td>
		<td><%= product.getpPost() %></td>
		
		<td> <!-- 상품노출여부 select-option태그 -->	
			<select class="p_expose" data-p-no="<%= product.getpNo() %>">
				<option value="Y"
					<%= "Y".equals(product.getpExpose()) ? "selected" : "" %>>노출
				</option>
				
				<option value="N"
					<%= "N".equals(product.getpExpose()) ? "selected" : "" %>>비공개
				</option>
			</select>
		</td>
		
		<td><%= product.getpRegDate() %></td>
	</tr>
<%
    }
%>
	</tbody>	
</table>

<!-- 페이지바 영역 -->
<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	

</section>

<!-- 상품노출여부 변경시 폼전송 -->
<form action="<%=request.getContextPath()%>/admin/updateProductExpose" method="POST" name="updateProductExposeFrm">
	<input type="hidden" name="pNo" />
	<input type="hidden" name="pExpose" />
</form>

<script>
/* 노출여부값 변경시 체인지 이벤트 */
$(".p_expose").change((e) => {
	const $this = $(e.target);
	const pNo = $this.data("pNo");
	const pExpose = $this.val();
	const pExpose_kr = pExpose=="Y"?"노출":"비공개";
	
	const msg = `상품번호 [\${pNo}]의 상품노출설정을 [\${pExpose_kr}]로 하시겠습니까?`;
	
	if(confirm(msg)){
		const $frm = $(document.updateProductExposeFrm);
		$frm.find("[name=pNo]").val(pNo);
		$frm.find("[name=pExpose]").val(pExpose);
		$frm.submit();
	}
	else{
		$this.find("[selected]").prop("selected", true);
	}
});

</script>

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>