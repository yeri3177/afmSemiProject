<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="com.kh.afm.product.model.vo.Attachment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Product product = (Product) request.getAttribute("product");
%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productUpdate.css"/>
<section id="board-container">
<div>
<img class="listTopImage" src="<%= request.getContextPath() %>/images/product/productUpdateGif.gif" alt="" />
</div>
<div class="form">
<form
	name="productUpdateFrm"
	action="<%=request.getContextPath() %>/product/productUpdate" 
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-product-update" class="tbl-product-update">
	<input type="hidden" name="pNo" value="<%= product.getpNo() %>" readonly>
	<input type="hidden" name="userId" value="<%= loginUser.getUserId() %>" readonly>
	<tr>
		<th>상품 제목</th>
		<td><input type="text" name="pTitle" class="input-text-title" value="<%= product.getpTitle() %>" required></td>
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
		<td>
		<select name="pCategory" value="<%= product.getpCategory() %>" id="pCategory" class="pCategory">
			<option name="곡류" value="곡류">곡류</option>
			<option name="과실류" value="과실류">과실류</option>
			<option name="채소류" value="채소류">채소류</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>배송</th>
		<td>
		<select name="pPost" value="<%= product.getpPost() %>" id="pPost" class="pPost">
			<option name="Y" value="Y">무료배송</option>
			<option name="N" value="N">무착불</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>대표 이미지(필수)</th>
		<td>			
			<input type="file" class="file" multiple name="upFile1">
<%
	Attachment attach1 = product.getAttach1();
	if(attach1 != null){
%>
			<span id="fname">
			이전 이미지 : <img alt="" align="middle" src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach1().getRenamedFileName() %>" width="100px" height="100px">
			</span>
<%
	}
%>
		</td>
	</tr>
		<tr>
		<th>상세 이미지(필수)</th>
		<td>			
			<input type="file" class="file" multiple name="upFile2">
<%
	Attachment attach2 = product.getAttach2();
	if(attach2 != null){
%>
			<span id="fname"> 
			이전 이미지 : <img alt="" align="middle" src="<%= request.getContextPath() %>/upload/product/<%= product.getAttach2().getRenamedFileName() %>" width="100px" height="100px">
			</span>
<%
	}
%>
		</td>
	</tr>

	<tr>
		<th>내 용</th>
		<td><textarea class="textarea" style="resize:none;" rows="40" cols="120" name="pContent" value="<%= product.getpContent() %>"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" class="enroll-btn" value="등록하기">
		</th>
	</tr>
</table>
</div>
</form>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>