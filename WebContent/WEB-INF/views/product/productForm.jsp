<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id="board-container">
<h2>상품 등록</h2>
<form
	name="productEnrollFrm"
	action="<%=request.getContextPath() %>/product/productEnroll" 
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-board-view">
	<tr>
		<th>상품 이름</th>
		<td><input type="text" name="title" required></td>
	</tr>
	<tr>
		<th>상품 가격</th>
		<td>
			<input type="text" name="price" value="" required/>
		</td>
	</tr>
	<tr>
		<th>상품 수량</th>
		<td>
			<input type="number" name="amount" min=1 placeholder="최소 1개 이상" required>		
		</td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td>
		<select name="category" id="category">
			<option value="cereals">곡류</option>
			<option value="fruits">과실류</option>
			<option value="vegetables">채소류</option>
		</select>
		</td>
	</tr>
		<tr>
		<th>배송</th>
		<td>
		<select name="post" id="post">
			<option value="later">착불</option>
			<option value="free">무료배송</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>대표 이미지</th>
		<td>			
			<input type="file" name="upFile">
		</td>
	</tr>
	<tr>
		<th>상세 이미지</th>
		<td>			
			<input type="file" name="upFile">
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기">
		</th>
	</tr>
</table>
</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>