<%@page import="java.util.List"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	List<Product> list = (List<Product>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	String sortType = request.getParameter("sortType");
	String sortKeyword = request.getParameter("sortKeyword");
%>	
<style>
div#search-pNo {
	display: <%= searchType == null || "pNo".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-userId {
	display: <%= "userId".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-pCategory {
	display: <%= "pCategory".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-pExpose {
	display: <%= "pExpose".equals(searchType) ? "inline-block" : "none" %>;
}
</style>
<section id="productList-container" class="admin-container">

<div class="data-box">
	<!-- 데이터 검색 -->
	<div class="serarchbox">
		
		<!-- 검색타입 -->
		<span>search</span>
		<select id="searchType">
		    <option value="pNo" <%= "pNo".equals(searchType) ? "selected" : "" %>>
		    	상품번호
		    </option>		
		    <option value="userId" <%= "userId".equals(searchType) ? "selected" : "" %>>
		    	판매자아이디
		    </option>
		    <option value="pCategory" <%= "pCategory".equals(searchType) ? "selected" : "" %>>
		    	카테고리
		    </option>
		    <option value="pExpose" <%= "pExpose".equals(searchType) ? "selected" : "" %>>
		    	상품노출여부
		    </option>
		</select>
		
		<!-- 상품번호 검색 -->
		<div id="search-pNo" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/productFinder" name="searchFrm">
				<input type="hidden" name="searchType" value="pNo"/>
				<input type="text" name="searchKeyword" 
					placeholder="검색키워드 입력" size="13" id="searchInput"
					value="<%= "pNo".equals(searchType) ? searchKeyword : "" %>"/>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 판매자아이디 검색 -->
		<div id="search-userId" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/productFinder" name="searchFrm">
				<input type="hidden" name="searchType" value="userId"/>
				<input type="text" name="searchKeyword"
					placeholder="검색키워드 입력" size="13" id="searchInput"
					value="<%= "userId".equals(searchType) ? searchKeyword : "" %>"/>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 상품카테고리 검색 -->
		<div id="search-pCategory" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/productFinder" name="searchFrm">
					<input type="hidden" name="searchType" value="pCategory"/>
			    <label>
			        <input type="radio" name="searchKeyword" value="채소류" checked
			        	<%= "pCategory".equals(searchType) && "채소류".equals(searchKeyword) ? "checked" : "" %>> 채소류
			    </label>
			    <label> 
			        <input type="radio" name="searchKeyword" value="과실류" 
			        	<%= "pCategory".equals(searchType) && "과실류".equals(searchKeyword) ? "checked" : "" %>> 과실류
			    </label>
			    <label>
			        <input type="radio" name="searchKeyword" value="곡류" 
			        	<%= "pCategory".equals(searchType) && "곡류".equals(searchKeyword) ? "checked" : "" %>> 곡류
			    </label>    	
					<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 상품노출여부 검색 -->
		<div id="search-pExpose" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/productFinder" name="searchFrm">
				<input type="hidden" name="searchType" value="pExpose"/>
				<label>
			        <input type="radio" name="searchKeyword" value="Y" checked
			        	<%= "pExpose".equals(searchType) && "Y".equals(searchKeyword) ? "checked" : "" %>> 노출
			    </label>
			    <label> 
			        <input type="radio" name="searchKeyword" value="N" 
			        	<%= "pExpose".equals(searchType) && "N".equals(searchKeyword) ? "checked" : "" %>> 비공개
				</label>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
	</div>
	
	<!-- 데이터 정렬 -->
	<div class="sortbox">
		
		<form action="<%=request.getContextPath()%>/admin/productSort">
			<span>sort</span>
			<!-- 정렬 타입 -->
			<label>	
				<input type="radio" name="sortType" value="asc" checked <%= "asc".equals(sortType) ? "checked" : "" %> />오름차순
			</label>
			<label>	
				<input type="radio" name="sortType" value="desc" <%= "desc".equals(sortType) ? "checked" : "" %> />내림차순
			</label>
			
			<!-- 정렬 키워드 -->
			<select name=sortKeyword>
				<option value="pNo" <%= "pNo".equals(sortKeyword) ? "selected" : "" %>>상품번호</option>
				<option value="userId" <%= "userId".equals(sortKeyword) ? "selected" : "" %>>판매자아이디</option>
				<option value="pExpose" <%= "pExpose".equals(sortKeyword) ? "selected" : "" %>>상품노출여부</option>
			</select>
			
			<button type="submit" class="sort-btn">정렬</button>
		</form>
	</div>
</div>

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
		<td><a href="<%=request.getContextPath()%>/product/productView?pNo=<%= product.getpNo() %>"><%= product.getpNo() %></a></td>
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

/* 검색유형 체인지 이벤트 */
$("#searchType").change((e) => {
  	const type = $(e.target).val();
  	
  	// 1. .search-type 감추기
  	$(".search-type").hide();
  	
  	// 2. #search-${type} 보여주기 (display : inline-block)
  	$(`#search-\${type}`).css("display", "inline-block");
}); 


</script>

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>