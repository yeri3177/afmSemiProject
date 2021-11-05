<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="com.kh.afm.product.model.vo.Attachment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	Product product = (Product) request.getAttribute("product");
%>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productForm.css"/>

<!-- 페이지 전체 컨테이너 -->
<section id="boardUpdateForm-container">

	<!-- 상단 배너 -->
	<div id="banner-box">
		<img id="top-banner-img" src="<%= request.getContextPath() %>/images/product/productUpdateBanner.gif" />
	</div>

	<!-- 수정폼 영역 DIV -->
	<div class="productForm-div">
	
		<form name="productUpdateFrm"
			action="<%=request.getContextPath() %>/product/productUpdate" 
			method="post" enctype="multipart/form-data">
			
			<table id="tbl-product-form" class="tbl-product-form">
			
				<input type="hidden" name="pNo" value="<%= product.getpNo() %>" readonly>
				<input type="hidden" name="userId" value="<%= loginUser.getUserId() %>" readonly>
				
				<tr>
					<th>상품명</th>
					<td>
						<input type="text" name="pTitle" class="input-text-title" value="<%= product.getpTitle() %>" required>
					</td>
				</tr>
				<tr>
					<th>상품 가격</th>
					<td>
						<input type="text" name="pPrice" class="input-text-price" value="<%= product.getpPrice()%>" required/>
					</td>
				</tr>
				<tr>
					<th>상품 수량</th>
					<td>
						<input type="number" name="pCnt" class="input-number" min=1 placeholder="최소 1개 이상" value="<%= product.getpCnt()%>" required>		
					</td>
				</tr>
				<tr>
					<th>카테고리</th>
					<td class="select-td">
					<select name="pCategory" value="<%= product.getpCategory() %>" id="pCategory" class="pCategory">
						<option name="곡류" value="곡류">곡류</option>
						<option name="과실류" value="과실류">과실류</option>
						<option name="채소류" value="채소류">채소류</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>배송비</th>
					<td class="select-td">
					<select name="pPost" value="<%= product.getpPost() %>" id="pPost" class="pPost">
						<option name="Y" value="Y">무료배송</option>
						<option name="N" value="N">무착불</option>
					</select>
					</td>
				</tr>
				
					
					
<%
	Attachment attach1 = product.getAttach1();
	if(attach1 != null){
%>
				<tr>
					<th>이전 대표 이미지</th>
					<td class="prev-img-td">
						<span class="prev-product-img">
							<img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" />
						</span>
					</td>
				</tr>
<%
	}
%>

				<tr>
					<th>변경할 대표 이미지</th>
					<td class="file-td">			
						<input type="file" class="file" multiple name="upFile1">
					</td>
				</tr>			
				
				
<%
	Attachment attach2 = product.getAttach2();
	if(attach2 != null){
%>
			<tr>
				<th>이전 상세 이미지</th>
				<td class="prev-img-td">
					<span class="prev-product-img"> 
						<img src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>" />
					</span>
				</td>
			</tr>
<%
	}
%>
			<tr>
				<th>변경할 상세 이미지</th>
				<td class="file-td">			
					<input type="file" class="file" multiple name="upFile2">
				</td>
			</tr>
		
			<tr>
				<th>글 내용</th>
				<td>
					<textarea class="textarea" rows="10" cols="40" name="pContent" value="<%= product.getpContent() %>"></textarea>
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="submit" class="enroll-btn" value="상품수정">
				</th>
			</tr>
			
		</table>
		
		</form>
	
	</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>