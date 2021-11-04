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
<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productView.css" />

<!-- bxslider -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script>
/**
 * 이미지 슬라이드
 */
$(document).ready(function(){ 

	var main = $('.bxslider').bxSlider({ 
	
	mode: 'fade', 
	
	auto: true,	//자동으로 슬라이드 
	
	controls : true,	//좌우 화살표	
	
	autoControls: true,	//stop,play 
	
	pager:true,	//페이징 
	
	pause: 3000, 
	
	autoDelay: 0,	
	
	slideWidth: 800, //이미지 박스 크기설정
	
	speed: 500, 
	
	stopAutoOnclick:true 

}); 

	   

$(".bx-stop").click(function(){	// 중지버튼 눌렀을때 
    main.stopAuto(); 
    $(".bx-stop").hide(); 
    $(".bx-start").show(); 
    return false; 
}); 


$(".bx-start").click(function(){	//시작버튼 눌렀을때 

    main.startAuto(); 
    $(".bx-start").hide(); 
    $(".bx-stop").show(); 
    return false; 
}); 



$(".bx-start").hide();	//onload시 시작버튼 숨김. 

}); 

</script>

<!-- 상품상세보기 페이지 전체 컨테이너 박스 -->
<section id="productView-page-container">

<!-- 상품글 컨테이너 박스 -->
<section id="productView-container">

<!-- 상단 영역 박스 -->
<div id="prod-top-box">
<form 
	name="productOrderFrm"
	method="POST">
	<input type="hidden" name="productNo" value="<%= product.getpNo() %>"/>
	<input type="hidden" name="productRenamedFilename" value="<%= product.getAttach1().getRenamedFileName() %>"/>
	<input type="hidden" name="productPrice" value="<%= product.getpPrice() %>"/>
	<table>
		<tr>
		
			<!--------------- 썸네일 이미지 -------------->
		    <td colspan="2" rowspan="7">
		    	<ul class="bxslider">
		          <li>
		          	<img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>">
		          </li>
		          
		          <li>
		         	<img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>">
		          </li>
		    	</ul>
		    
		    	<%-- <img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" alt="대표이미지" width="500px" height="500px"/> --%>
		    </td>
		    
		    <!-- 상품명 -->
		    <td colspan="6" id="productName"> 
		    	<%= product.getpTitle() %>
		    </td>
		    
		    
		    
	    </tr>
	    
	    <tr>
	    	<td colspan="6"> 
		    	판매자 : <%= product.getUserId() %>
		    </td>
	    </tr>
	    <tr>
		    <td colspan="6"> 가격 : <%= product.getpPrice() %>원</td>
	    </tr>
	    <tr>
		    <td colspan="6"> 배송비 : <%= product.getpPost() %></td>
	    </tr>
	    <tr>
		    <td colspan="6"> 재고 : <%= product.getpCnt() %></td>
	    </tr>
	    <tr>
		    <td colspan="6" class="test"> 좋아요 : <%= product.getpRecommend() %></td>
	    </tr>
	    
	    <tr>
		    <td colspan="6">구매개수 : <input type="number" name="productQuantity" value="" placeholder="최소 1개 이상"/></td>
	    </tr>
	    	
	    <tr>
	    	<td>
	    		<input type="button" id="like" value="좋아요!"/>
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
		    	<input type="submit"
		    		   value="신고하기" 
		    		   formaction="<%= request.getContextPath() %>/csboard/productReportForm"/>
		    		   
		    </td>
		    <td>
		    	<input 
					type="submit" 
					value="장바구니" 
					formaction="<%= request.getContextPath() %>/cart/cartInsert" />
		    </td>
		    <td>
		    	<input 
					type="submit" 
					value="결제하기" 
					formaction="<%= request.getContextPath() %>/order/orderProduct" />
		    </td>
	    </tr>
	</table>
</form>	
</div>


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
				name="productCommentFrm" method="POST">
				
				<div class="co-tag-box">
					<img src="<%=request.getContextPath()%>/images/product/comments.png" />
				</div>
				
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
</section>
</section>
<form 
	action="<%= request.getContextPath() %>/product/productCommentDelete"
	name="productCommentDelFrm"
	method="POST">
	<input type="hidden" name="commentNo" />
	<input type="hidden" name="pNo" value="<%= product.getpNo() %>"/>
</form>

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

$(".btn-delete").click(function(e){
	
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.productCommentDelFrm);
		var commentNo = $(this).val();
		$frm.find("[name=commentNo]").val(commentNo);
		$frm.submit();
	}
});	

</script>


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
