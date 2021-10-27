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
		<th>상품 제목</th>
		<td><input type="text" name="pTitle" required></td>
	</tr>
	<tr>
		<th>(login기능 구현 전 임시로 구현)작성자</th>
		<td><input type="text" name="userId" required></td>
	</tr>
	<tr>
		<th>상품 가격</th>
		<td>
			<input type="text" name="pPrice" value="" required/>
		</td>
	</tr>
	<tr>
		<th>상품 수량</th>
		<td>
			<input type="number" name="pCnt" min=1 placeholder="최소 1개 이상" required>		
		</td>
	</tr>
	<tr>
		<th>카테고리</th>
		<td>
		<select name="pCategory" id="pCategory">
			<option name="곡류" value="곡류">곡류</option>
			<option name="과실류" value="과실류">과실류</option>
			<option name="채소류" value="채소류">채소류</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>배송</th>
		<td>
		<select name="pPost" id="pPost">
			<option name="Y" value="Y">무료배송</option>
			<option name="N" value="N">무착불</option>
		</select>
		</td>
	</tr>
	<tr>
		<th>대표 이미지(필수)</th>
		<td>			
			<input type="file" multiple name="upFile1">
		</td>
	</tr>
		<tr>
		<th>상세 이미지(필수)</th>
		<td>			
			<input type="file" multiple name="upFile2">
		</td>
	</tr>
	
	<tr>
		<th>내 용</th>
		<td><textarea style="resize:none;" rows="40" cols="120" name="pContent"></textarea></td>
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