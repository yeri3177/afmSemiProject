<%@page import="java.text.NumberFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productList.css"/>

<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>


<!-- 상품게시판 리스트 전체 컨테이너 시작 -->
<section id="board-container">


<!-- 상단 배너 이미지 -->
<div>
	<img class="listTopImage" src="<%= request.getContextPath() %>/images/product/productListBanner.gif" />
</div>




<!-- 카테고리 검색 시작 -->
<div id="search-pCategory" class="search-type">
	<form action="<%= request.getContextPath() %>/product/productFinder" name = "searchFrm">
			<input type="hidden" name="searchType" value="pCategory"/>
			
			<label>
				<input type="radio" name="searchKeyword" value="모두보기" checked
				<%= "모두보기".equals(searchKeyword) ? "checked" : "" %>> 모두보기
			</label>
			<label>
				<input type="radio" name="searchKeyword" value="곡류"
				<%= "곡류".equals(searchKeyword) ? "checked" : "" %>> 곡류
			</label>
			<label>	
				<input type="radio" name="searchKeyword" value="과실류"
				<%= "과실류".equals(searchKeyword) ? "checked" : "" %>> 과실류
			</label>
			<label>	
				<input type="radio" name="searchKeyword" value="채소류"
				<%= "채소류".equals(searchKeyword) ? "checked" : "" %>> 채소류 
			</label>
			
			<!-- 검색 버튼 -->
			<button type="submit" class="search-btn">
				<img class="search-img" src="<%= request.getContextPath() %>/images/product/search-icon-64.png" />
			</button>
	</form>
</div>
<!-- 카테고리 검색 끝 -->



<!-- 상품 리스트 테이블 시작 -->
<table id="product-board" class="product-board">

	<thead class="thead">
		<tr>
			<th>번호</th>
			<th>사진</th>
			<th>상품명</th>
			<th>가격</th>
			<th>판매자</th>
			<th>판매상태</th>
			<th>카테고리</th>
			<th>등록일</th>
			<th>추천수</th>
		</tr>
	</thead>
	
	<tbody class="tbody">
<%
	for(Product product : list){
%>
		<tr>
			<td> <!-- 번호 -->
				<%= product.getpNo() %>
			</td>
		
			<td id="productImg-td"> <!-- 썸네일이미지 -->
				<img id="thumbnail-img" src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>">
			</td>
			
			<td id="productName-td"> <!-- 상품명 -->
				<a href="<%= request.getContextPath() %>/product/productView?pNo=<%= product.getpNo() %>">
					<span><%= product.getpTitle() %></span>
				</a>
			</td>
			
			<td> <!-- 가격 -->
				<%= NumberFormat.getInstance().format(product.getpPrice()) %>(원)
			</td>
			
			<td> <!-- 판매자 -->
				<%= product.getUserId() %>
			</td>
			
			<td> <!-- 판매상태 -->
				<%= (product.getpCnt() > 0 ) ? "판매중" : "판매완료" %>
			</td>
			
			<td> <!-- 카테고리 -->
				<%= product.getpCategory() %>
			</td>
			
			<td> <!-- 등록일 -->
				<%= product.getpRegDate() %>
			</td>
			
			<td> <!-- 추천수 -->
				<%= product.getpRecommend() %>
			</td>
		</tr>
<%
	}
%>
	</tbody>
</table>
<!-- 상품 리스트 테이블 끝 -->

	<div class="test"></div>



<%--
<script>
window.addEventListener('load', function(){
	const value = "모두보기";
	console.log(value);
	$.ajax({
		url: "<%= request.getContextPath() %>/product/selectCategory",
		data: {pValue : value},
		dataType: "text",
		success(data){
			const $RDdiv = $(`<div class='productList'></div>`);
			const arr = data.split("\n");
			
			 $(arr).each((i, str)=> {
             	console.log(str);
             	let temp = str.split(", ");
             	
            	var productNo = temp[0];
            	var product = productNo.split("=");
            	var pNo = product[1];
            	
            	var pUserId = temp[1];
            	console.log(pUserId);
            	var user = pUserId.split("=");
            	var userId = user[1];
            	
            	var RegDate = temp[2];
            	var reg = RegDate.split("=");
            	var pRegDate = reg[1];
            	
            	var productTitle = temp[3];
            	var title = productTitle.split("=");
            	var pTitle = title[1];
            	
            	var productPrice = temp[6];
            	var price = productPrice.split("=");
            	var pPrice = price[1];
            	
            	var productRecommend = temp[11];
            	var Recommend = productRecommend.split("=");
            	var pRecommend = Recommend[1];
            	
            	var renamedName = temp[15];
            	var rename = renamedName.split("=");
            	var renamedFileName = rename[1];

            	var html =
                	`
                	<tr>
        			<th>\${pNo}</th>
        			<th><img alt="" src="<%= request.getContextPath() %>/upload/product/\${renamedFileName}" width="300px" height="300px"></th>
        			<th><a href="<%= request.getContextPath() %>/product/productView?pNo=\${pNo}">\${pTitle}</a></th>
        			<th>\${pPrice}</th>
        			<th>\${userId}</th>
        			<th>\${pRegDate}</th>
        			<th>\${pRecommend}</th>
        			</tr>
        			`;
        			$RDdiv.append(html);
				})
					$(".test").html($RDdiv);
			},
			error : console.log 
	
	});
});

$(document).ready(function () { 
    $("#category").change((e) => {
        const {value} = e.target;
        $.ajax({
            url: "<%= request.getContextPath() %>/product/selectCategory",
            data: {pValue : value},
            dataType:"text",
            success(data){
/*                 const {pValue} = data; */
				console.log(data);
                const $RDdiv = $(`<div class='productList'></div>`);
                const arr = data.split("\n");
                console.log(arr);
                $(arr).each((i, str)=> {
                	console.log(str);
                	let temp = str.split(", ");
                	
                	var productNo = temp[0];
                	var product = productNo.split("=");
                	var pNo = product[1];
                	
                	var pUserId = temp[1];
                	console.log(pUserId);
                	var user = pUserId.split("=");
                	var userId = user[1];
                	
                	var RegDate = temp[2];
                	var reg = RegDate.split("=");
                	var pRegDate = reg[1];
                	
                	var productTitle = temp[3];
                	var title = productTitle.split("=");
                	var pTitle = title[1];
                	
                	var productPrice = temp[6];
                	var price = productPrice.split("=");
                	var pPrice = price[1];
                	
                	var productRecommend = temp[11];
                	var Recommend = productRecommend.split("=");
                	var pRecommend = Recommend[1];
                	
                	var renamedName = temp[15];
                	var rename = renamedName.split("=");
                	var renamedFileName = rename[1];
                	
                	var html =
                	`
                	<tr>
        			<th>\${pNo}</th>
        			<th><img alt="" src="<%= request.getContextPath() %>/upload/product/\${renamedFileName}" width="300px" height="300px"></th>
        			<th><a href="<%= request.getContextPath() %>/product/productView?pNo=\${pNo}">\${pTitle}</a></th>
        			<th>\${pPrice}</th>
        			<th>\${userId}</th>
        			<th>\${pRegDate}</th>
        			<th>\${pRecommend}</th>
        			</tr>
        			`;
        			$RDdiv.append(html);
                })
                $(".test").html($RDdiv);
            },
            error: console.log
        });
    });
});

<%--
$(category).change((e) => {
	let pValue = this.value;
	const value = "";
	$.ajax({
		url: "<%= request.getContextPath() %>/product/selectCategory",
		data: {
			//value: this.value
			//value: pValue
			value: pValue
			
		},
		success(data){
			
		}
	});
});
--%>

<%--
$(document).ready(function () {
	category_val = $('select.category').attr('data-type');
	  $('select.category option[value=' + category_val + ']').attr('selected', 'selected');
	});
	
$(document).ready(function(){
	$('#category').on('change', function(){
		console.log(this.value);
	});

	$.ajax({
		url: "<%= request.getContextPath() %>/product/selectCategory",
		data: {
			con: pValue
		},
		success(data){
			
		}
	});
});


</script>
--%>

<div id="bottom-box">
	<div></div>
	
	<!-- 페이징 영역 -->
	<div id='pageBar' class="pagebar">
		<%= request.getAttribute("pagebar") %>
	</div>

<!-- 글쓰기 버튼 영역-->
<div>
	<% 
		if(loginUser != null){ 
	%>	
		<input type="button" value="글쓰기" id="btn-add"class="wtite-btn" onclick="location.href='<%= request.getContextPath() %>/product/productForm';" />
	
	<%
		} 
	%>
</div>
</div>

</section> <!-- 상품게시판 리스트 전체 컨테이너 끝 -->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
