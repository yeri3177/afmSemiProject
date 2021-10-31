<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
	boolean editable = loginUser != null && (
					   (loginUser.getUserId().equals(product.getUserId())) ||
					   UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
					   );
			
%>

<section id="product-container">
	<h2></h2>
	<div><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" alt="대표이미지" width="300px" height="300px"/></div>	
	<br />
	<div>
		<form 
			action="<%= request.getContextPath() %>/product/productOrder" 
			name="productOrderFrm"
			method="POST">
			<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
			<input type="hidden" name="productRenamedFilename" value="<%= product.getpNo() %>"/>
			<input type="hidden" name="productPrice" value="<%= product.getpNo() %>"/>
			<input type="hidden" name="productQuantity" value="<%= product.getpNo() %>"/>
		</form>
	</div>
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
	
	<hr style="margin-top:30px"/>
	
	<div class="comment-container">
		<div class="comment-editor">
			<form 
				action="<%= request.getContextPath() %>/product/productCommentEnroll"
				name="productCommentFrm"
				method="POST">
				<div>댓글!</div>
				<textarea name="content"  cols="60" rows="3"></textarea>
				<button id="btn-insert">등록</button>
				
				<input type="hidden" name="commentLevel" value="1"/>
				<input type="hidden" name="userId" value=""/>
				<input type="hidden" name="pNo" value=""/>
				<input type="hidden" name="commentRef" value="0"/>
			</form>
		</div>
	</div>

<form 
	action="<%= request.getContextPath() %>/product/productOrder" 
	name="productOrderFrm"
	method="POST">
	<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
	<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
</form>
<script>
$(".btn-delete").click(function(e){

	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.boardCommentDelFrm);
		var no = $(this).val();
		$frm.find("[name=no]").val(no);
		$frm.submit();
	}
});	
</script>
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
</script>
<%
}
%>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>