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
	<br />
	<div>
		<form 
			action="<%= request.getContextPath() %>/product/productOrder" 
			name="productOrderFrm"
			method="POST">
			<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
			<input type="hidden" name="productRenamedFilename" value="<%= product.getAttach1().getRenamedFileName() %>"/>
			<input type="hidden" name="productPrice" value="<%= product.getpPrice() %>"/>
		</form>
	</div>
	<table>
		<tr>
		    <td colspan="2" rowspan="7"><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" alt="대표이미지" width="300px" height="300px"/></td>
		    <td colspan="5"> 상품명 : <%= product.getpTitle() %></td>
	    </tr>
	    <tr>
		    <td colspan="5"> 가격 : <%= product.getpPrice() %></td>
	    </tr>
	    <tr>
		    <td colspan="5"> 배송 : <%= product.getpPost() %></td>
	    </tr>
	    <tr>
		    <td colspan="5"> 남은 수량 : <%= product.getpCnt() %></td>
	    </tr>
	    <tr>
		    <td colspan="5"><input type="number" placeholder="최소 1개 이상"/></td>
	    </tr>
	    	
	    <tr>
	    	<td>
	    		<input 
	    			type="button"
	    			value="추천!"/>
<% 
if(editable){ 
%>	
	    	</td>
		    <td>
		    	<input 
					type="button" 
					value="수정하기" 
					onclick="updateProduct()" />
		    </td>
		    <td>
			    <input 
					type="button" 
					value="삭제하기" 
					onclick="deleteProduct()" />
		    </td>
<%
}
%>
		    <td>
		    	<input type="button"
		    		   value="신고하기" />
		    </td>
		    <td>
		    	<input 
					type="button" 
					value="장바구니" 
					onclick="cartProduct()" />
		    </td>
		    <td>
		    	<input 
					type="button" 
					value="결제하기" 
					onclick="orderProduct()" />
		    </td>
	    </tr>
	</table>
	
	<br />
	<br />
	<br />
	<br />
	<br />
	
	<div>
	판매자의 말
	<br /><br />
	<%= product.getpContent() %>
	</div>
	
	<br />
	<hr />
	<br />
	
	<div>
	<img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>" alt="상세이미지" />
	</div>
	
	<br />
	<hr />
	<br />
		
	
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
				<input type="hidden" name="userId" value="<%= loginUser != null ? loginUser.getUserId() : ""%>"/>
				<input type="hidden" name="pNo" value="<%= product.getpNo()%>"/>
				<input type="hidden" name="commentRef" value="0"/>
			</form>
		</div>
		
	<table id="tbl-comment">
		<%-- 댓글 --%>
		<tr class="level1">
			<td>
				<sub class="comment-writer"></sub>
				<sub class="comment-date"></sub>
				<br />
				<%-- 댓글 내용 --%>
				
			</td>
			<td>
				<button class="btn-reply" value="">답글</button>
				<button class="btn-delete" value="">삭제</button>
			</td>
		</tr>
		<tr class="level2">
			<td>
				<sub class="comment-writer"></sub>
				<sub class="comment-date"></sub>
				<br />
				<%-- 댓글 내용 --%>
			</td>
			<td>
				<button class="btn-delete" value="">삭제</button>
			</td>
		</tr>
	</table>
	</div>
	

<form 
	action="<%= request.getContextPath() %>/product/productOrder" 
	name="productOrderFrm"
	method="POST">
	
	<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
</form>
<script>

const orderProduct =
	() => location.href = "<%= request.getContextPath() %>/order/OrderProduct";

const cartProduct =
	() => location.href = "<%= request.getContextPath() %>/cart/cartProduct";

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