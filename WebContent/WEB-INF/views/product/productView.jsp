<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
	boolean editable = loginUser != null && (loginUser.getUserId().equals(product.getUserId()));
%>
<section id="board-container">

	<h2>게시판</h2>
	<div><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" alt="대표이미지" width="300px" height="300px"/></div>	
	<br />
<%
if(editable){ 
%>	
	<input 
		type="button" 
		value="수정하기" 
		onclick="updateProduct()" />
	<input 
		type="button" 
		value="삭제하기" 
		onclick="deleteProduct()" />
		
<%
}
%>
	<input 
		type="button" 
		value="장바구니" 
		id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/board/boardForm';" />
	<input 
		type="button" 
		value="결제하기" 
		id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/board/boardForm';" />
	
	<hr />
	<br />
	<div>글번호 : <%= product.getpNo() %></div>
	<div>작성자 : <%= product.getUserId() %></div>
	<div>제목 : <%= product.getpTitle() %></div>
	<div>가격 : <%= product.getpPrice() %></div>
	<div>배송 : <%= product.getpPost() %></div>
	<div>남은 수량 : <%= product.getpCnt() %></div>
	<div>카테고리 : <%= product.getpCategory() %></div>
	<div>추천수 : <%= product.getpRecommend() %></div>
	<br />
	<hr />
	<br />
	<div><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>" alt="상세이미지" width="300px" height="300px"/></div>	
	<div>본문 : <%= product.getpContent() %></div>
</section>
<%
if(editable){
%>
<form action="<%= request.getContextPath() %>/product/productDelete" name="deleteProductFrm">
	<input type="hidden" name ="pNo" value="<%= product.getpNo() %>"/>
</form>
<script>
const updateProduct =
() => location.href = "<%= request.getContextPath() %>/product/productUpdate?pNo=<%= product.getpNo() %>";

const deleteProduct = () => {
	if(confirm("이 상품을 정말로 삭제하시겠습니까?")){
		$(document.deleteProductFrm).submit();
	}
};
<%
}
%>
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>