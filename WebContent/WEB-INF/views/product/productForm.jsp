<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- css 추가 -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/productForm.css"/>

<!-- 페이지 전체 컨테이너 -->
<section id="boardEnrollForm-container">

	<!-- 상단 배너 이미지영역 -->
	<div id="banner-box">
		<img id="top-banner-img" src="<%= request.getContextPath() %>/images/product/productEnrollBanner.gif" />
	</div>

	<!-- 글쓰기 폼 영역 DIV -->
	<div id="productEnrollForm-div">
	
		<form name="productEnrollFrm" action="<%=request.getContextPath() %>/product/productEnroll" 
			method="post" enctype="multipart/form-data">
			
			<!-- 글쓰기 테이블 -->
			<table id="tbl-product-form" class="tbl-product-form">
			
				<input type="hidden" name="userId" value="<%= loginUser.getUserId() %>" readonly>
			
			<tr>
				<th>상품명<sup>*</sup></th>
				<td><input type="text" name="pTitle" class="input-text-title" placeholder="제목을 입력해주세요." required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td>
					<input type="text" name="pPrice" class="input-text-price" placeholder="가격을 입력해주세요." value="" required/>
				</td>
			</tr>
			<tr>
				<th>수량</th>
				<td>
					<input type="number" name="pCnt" class="input-number" min=1 placeholder="최소 1개 이상" required>		
				</td>
			</tr>
			<tr>
				<th>카테고리</th>
				<td class="select-td">
				<select name="pCategory" id="pCategory" class="pCategory">
					<option name="곡류" value="곡류" class="pCategory-option">곡류</option>
					<option name="과실류" value="과실류" class="pCategory-option">과실류</option>
					<option name="채소류" value="채소류" class="pCategory-option">채소류</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>배송비</th>
				<td class="select-td">
				<select name="pPost" id="pPost" class="pPost">
					<option name="Y" value="Y">무료배송</option>
					<option name="N" value="N">착불 (3000원)</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>대표 이미지</th>
				<td class="file-td">			
					<input type="file" class="file" multiple name="upFile1">
				</td>
			</tr>
				<tr>
				<th>상세 이미지</th>
				<td  class="file-td">			
					<input type="file" class="file" multiple name="upFile2">
				</td>
			</tr>
			
			<tr>
				<th>글 내용</th>
				<td>
					<textarea class="textarea" style="resize:none;" rows="10" cols="40" name="pContent"></textarea>
				</td>
			</tr>
			
			<tr>
				<th colspan="2">
					<input type="submit" class="enroll-btn" value="상품등록">
				</th>
			</tr>
		</table>
		</form>
	</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>