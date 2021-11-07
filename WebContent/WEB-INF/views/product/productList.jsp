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

	<!-- 배너 이미지 -->
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
				<input type="submit" value="검색" id="search-btn" class="green-btn" />
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
			<tr class="content-tr">
				<td> <!-- 번호 -->
					<%= product.getpNo() %>
				</td>
			
				<td id="productImg-td"> <!-- 썸네일이미지 -->
					<img id="thumbnail-img" src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>">
				</td>
				
				<td id="productName-td"> <!-- 상품명 -->
					<a href="<%= request.getContextPath() %>/product/productView?pNo=<%= product.getpNo() %>">
						<span><%= (product.getpCnt() > 0 ) ? product.getpTitle() : "판매완료된 상품입니다." %></span>
					</a>
				</td>
				
				<td> <!-- 가격 -->
					<%= NumberFormat.getInstance().format(product.getpPrice()) %>원
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
				<input type="button" value="글쓰기" id="write-btn" class="green-btn" 
					onclick="location.href='<%= request.getContextPath() %>/product/productForm';" />
			
			<%
				} 
			%>
		</div>
	</div>

</section> <!-- 상품게시판 리스트 전체 컨테이너 끝 -->

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
