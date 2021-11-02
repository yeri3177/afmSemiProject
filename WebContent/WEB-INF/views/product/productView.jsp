<%@page import="com.kh.afm.product.model.vo.ProductComment"%>
<%@page import="java.util.List"%>
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
	
	List<ProductComment> commentList = (List<ProductComment>) request.getAttribute("commentList");
%>


<section id="product-container">
	<h2></h2>
	<br />
<form 
	action="<%= request.getContextPath() %>/product/productOrder" 
	name="productOrderFrm"
	method="POST">
	<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
	<input type="hidden" name="productRenamedFilename" value="<%= product.getAttach1().getRenamedFileName() %>"/>
	<input type="hidden" name="productPrice" value="<%= product.getpPrice() %>"/>
	<table>
		<tr>
		    <td colspan="2" rowspan="7"><img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" alt="대표이미지" width="500px" height="500px"/></td>
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
		    <td colspan="5" class="test"> 좋아요 : <%= product.getpRecommend() %></td>
	    </tr>
	    
	    <tr>
		    <td colspan="5">구매할 수량 : <input type="number" name="productQuantity" value="" placeholder="최소 1개 이상"/></td>
	    </tr>
	    	
	    <tr>
	    	<td>
	    		<input 
	    			type="button"
	    			id="like"
	    			value="좋아요!"/>
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
					type="submit" 
					value="장바구니" 
					onclick="javascript: form.action:'/cart/cartProduct';" />
		    </td>
		    <td>
		    	<input 
					type="submit" 
					value="결제하기" 
					onclick="javascript: form.action:'/order/orderProduct';" />
		    </td>
	    </tr>
	</table>
</form>	
	<br />
	<br />
	<br />
	<br />
	<br />
	
	<div>
	<img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>" alt="상세이미지" />
	</div>
	
	<br />
	<hr />
	<br />
	
	<div>
	판매자의 말
	<br /><br />
	<%= product.getpContent() %>
	</div>	
		
	
	<hr style="margin-top:30px"/>
	
	<div class="comment-container">
		<div class="comment-editor">
			<form 
				action="<%= request.getContextPath() %>/product/productCommentEnroll"
				name="productCommentFrm"
				method="POST">
				<div>댓글!</div>
				<textarea name="content" cols="60" rows="3"></textarea>
				<button id="btn-insert">등록</button>
				
				<input type="hidden" name="commentLevel" value="1"/>
				<input type="hidden" name="userId" value="<%= loginUser != null ? loginUser.getUserId() : ""%>"/>
				<input type="hidden" name="pNo" value="<%= product.getpNo()%>"/>
				<input type="hidden" name="commentRef" value="0"/>
			</form>
		</div>
		
	<table id="tbl-comment">
<%
if(commentList != null && !commentList.isEmpty()){
	for(ProductComment pc : commentList){
		boolean removable =
				loginUser != null &&
				(
					loginUser.getUserId().equals(pc.getUserId())
					|| UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
				);
	
		if(pc.getCommentLevel() == 1) {
%>
		<%-- 댓글 --%>
		<tr class="level1">
			<td>
				<sub class="comment-writer"><%= pc.getUserId() %></sub>
				<sub class="comment-date"><%= pc.getRegDate() %></sub>
				<br />
				<%-- 댓글 내용 --%>
				<%= pc.getCommentContent() %>
			</td>
			<td>
					<button class="btn-reply" value="<%= pc.getCommentNo() %>">답글</button>
<% if(removable){ %><button class="btn-delete" value="<%= pc.getCommentNo() %>">삭제</button><% } %>
			</td>
		</tr>
		
<%
		}
		else {
%>
		<%-- 대댓글(답글) --%>
		<tr class="level2">
			<td>
				<sub class="comment-writer"><%= pc.getUserId() %></sub>
				<sub class="comment-date"><%= pc.getRegDate() %></sub>
				<br />
				<%-- 댓글 내용 --%>
				<%= pc.getCommentContent() %>
			</td>
			<td>
<% if(removable){ %><button class="btn-delete" value="<%= pc.getCommentNo() %>">삭제</button><% } %>
			</td>
		</tr>
<%
		}
	}

}
%>		
	</table>
	</div>

<script>
$(like).click((e) => {
	$.ajax({
		url: "<%= request.getContextPath() %>/product/productLike",
		data: {
			pNo: <%= product.getpNo() %>,
			like: <%= product.getpRecommend() %>
		},
		success(data){
			console.log(data);
			const test1 = data;
			$(".test").html(test1);
		},
		error(xhr,textStatus,data){
			console.log(xhr,textStatus,data);
		}
	});
});

</script>

<form 
	action="<%= request.getContextPath() %>/product/productCommentDelete"
	name="productCommentDelFrm"
	method="POST">
	<input type="hidden" name="commentNo" />
	<input type="hidden" name="pNo" value="<%= product.getpNo() %>"/>
</form>

<script>
$(".btn-delete").click(function(e){
	
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.productCommentDelFrm);
		var commentNo = $(this).val();
		$frm.find("[name=commentNo]").val(commentNo);
		$frm.submit();
	}
});	

</script>

</section>
<%
if(editable){
%>
<form 
	action="<%= request.getContextPath() %>/product/productDelete" 
	name="deleteProductFrm">
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
<script>
$(".btn-reply").click((e) => {
	const commentRef = $(e.target).val();
	const tr = `<tr>
	<td colspan="2" style="text-align: left;">
		<form 
			action="<%= request.getContextPath() %>/product/productCommentEnroll"
			method="POST">
			<textarea name="content" cols="60" rows="2"></textarea>
			<button class="btn-insert2">등록</button>
			
			<input type="hidden" name="commentLevel" value="2"/>
			<input type="hidden" name="userId" value="<%= loginUser != null ? loginUser.getUserId() : ""%>"/>
			<input type="hidden" name="pNo" value="<%= product.getpNo()%>"/>
			<input type="hidden" name="commentRef" value="\${commentRef}" />
		</form>
	</td>
</tr>`;
	console.log(tr);
	
	const $trOfBtn = $(e.target).parent().parent();
	
	$(tr)
		.insertAfter($trOfBtn)
		.find("form")
		.submit((e) => {
<% if(loginUser == null) {%>
			loginAlert();
			return false;
<% } %>
			// 내용검사
			const $textarea = $("[name=content]", e.target);
			
			if(!/^(.|\n)+$/.test($textarea.val())){
				alert("댓글 내용을 작성해주세요.");
				$textarea.focus();
				return false;
			}
		})
		.find("[name=content]")
		.focus();
	
	// 현재버튼의 handler 제거
	$(e.target).off('click');
});

$("[name=content]", document.ProductCommentFrm).focus((e) => {
<% if(loginUser == null){%> 
	loginAlert();
<% } %>
});

$(document.ProductCommentFrm).submit((e) => {
<% if(loginUser == null){%> 
	loginAlert();
	return false;
<% } %>	

	// 내용검사
	// const textarea = $("[name=content]", document.boardCommentFrm);	
	const $textarea = $("[name=content]", e.target);	
	
	if(!/^(.|\n)+$/.test($textarea.val())){
		alert("댓글 내용을 작성해주세요.");
		$textarea.focus();
		return false;
	}
	
});

const loginAlert = () => {
	alert("로그인후 이용할 수 있습니다.");
	$("#userId").focus();
};




</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
