<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productList.css"/>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<style>
</style>
<section id="board-container">
<br />
<br />
<%--
<select name="category" id="category" class="category" value="category" style="width:100px;height:50px;">
	<option id="모두보기" value="모두보기" selected>모두보기</option>
	<option id="곡류" value="곡류">곡류</option>
	<option id="과실류" value="과실류">과실류</option>
	<option id="채소류" value="채소류">채소류</option>
</select>
 --%>

<!-- 카테고리 검색 -->
<div class="productHeading">
<p>상품 목록</p>
</div>

<div id="seearch-pCategory" class="search-type">
	<form action="<%= request.getContextPath() %>/product/productFinder" name = "searchFrm">
			<input type="hidden" name="searchType" value="pCategory"/>
			
			<input type="radio" name="searchKeyword" value="모두보기"> 모두보기
			<input type="radio" name="searchKeyword" value="곡류"> 곡류
			<input type="radio" name="searchKeyword" value="과실류"> 과실류
			<input type="radio" name="searchKeyword" value="채소류"> 채소류 

			<button type="submit" class="search-btn"><img class="search-img" src="<%= request.getContextPath() %>/images/product/search-icon-64.png" alt=""></button>
	</form>
</div>

	<table id="product-board" class="product-board">
	<thead class="thead">
		<tr>
			<th>사진</th>
			<th>상품</th>
			<th>가격</th>
			<th>판매자</th>
			<th>작성일</th>
			<th>추천수</th>
		</tr>
	</thead>
	<tbody class="tbody">
<%
	for(Product _product : list){
%>
		<tr>
			<th><img alt="" src="<%= request.getContextPath() %>/upload/product/<%= _product.getAttach1().getRenamedFileName() %>" width="300px" height="300px"></th>
			<th><a href="<%= request.getContextPath() %>/product/productView?pNo=<%= _product.getpNo() %>"><%= _product.getpTitle() %></a></th>
			<th><%= _product.getpPrice() %></th>
			<th><%= _product.getUserId() %></th>
			<th><%= (Date)_product.getpRegDate() %></th>
			<th><%= _product.getpRecommend() %></th>
		</tr>
<%
	}
%>
	</tbody>
	</table>

		<div class="test">
		</div>

<% 
	if(loginUser != null){ 
%>	
<div>
<input 
		type="button" 
		value="글쓰기" 
		id="btn-add"
		class="wtite-btn" 
		onclick="location.href='<%= request.getContextPath() %>/product/productForm';" />
</div>
<%
	} 
%>
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
<div id='pageBar' class="pagebar"><%= request.getAttribute("pagebar") %></div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
