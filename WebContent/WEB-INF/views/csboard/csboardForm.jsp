<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/csboard.css" />

<script>
/**
* csboardEnrollFrm 유효성 검사
*/

					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	//.(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.


// onload 함수
// submit 핸들러로 csboardValidate 지정
// csboardEnrollFrm이 제줄 직전에 csboardValidate 호출


</script>

<section id="csboardForm-container" class="csboard-container">
<h2>상품 신고</h2>
<form name="productReportFrm" action="<%=request.getContextPath() %>/csboard/productReport" method="POST">
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td>
			<input type="text" name="board_title" style="width:100%" required>
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="user_id" value="<%= loginUser.getUserId() %>" readonly />
			<input type="checkbox" name="boardNotice_yn" value="Y"/>공지사항 여부
		</td>
	</tr>
	<tr>
		<th>분 류</th>
		<td>
			<label for="reportType">신고 분류</label>
			<select name="reportType" id="reportType">
				<option value="reportType_1">욕설, 비방, 혐오</option>
				<option value="reportType_2">도배, 스팸</option>
				<option value="reportType_3">불법 정보</option>
				<option value="reportType_4">음란, 청소년 유해</option>
				<option value="reportType_5">기타</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>신 고 내 용</th>
		<td>
			<textarea rows="5" cols="40" name="board_content" style="width:100%;"></textarea>
		</td>
	</tr>
</table>
<br/>
	<div style="text-align:center;">
		<input type="submit" value="신고" onclick="return csboardCheck()"/>
		<input type="button" value="취소" onclick="history.go(-1);" /> 		
	</div>
</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>