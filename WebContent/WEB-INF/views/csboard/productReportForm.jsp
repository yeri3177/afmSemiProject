<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/csboard.css" />

<script>
function ReportValidate(){
	const $title = $("[name=title]");
	const $content = $("[name=content]");
	
	// 제목을 작성하지 않은 경우 폼을 제출 할 수 없다.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
	
	// 내용을 작성하지 않은 경우 폼을 제출 할 수 없다.
	if(!/^(.|\n)+$/.test($content.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
}

$(() => {
	$(document.productReportFrm).submit(ReportValidate);
});
</script>
<section id="productReportForm-container" class="csboard-container">
<h2>상품 신고</h2>
<form name="productReportFrm" 
	  action="<%=request.getContextPath() %>/csboard/productReportEnroll" 
	  method="POST">
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td>
			<input type="text" name="title" style="width:100%" required>
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="user_id" value="<%= loginUser.getUserId() %>" readonly />
		</td>
	</tr>
	<tr>
		<th>분 류</th>
		<td>
			<label for="reportType">신고 분류</label>
			<select name="reportType" id="reportType">
				<option value="swearing">욕설, 비방, 혐오</option>
				<option value="spam">도배, 스팸</option>
				<option value="illegal">불법 정보</option>
				<option value="obscene">음란, 청소년 유해</option>
				<option value="etc">기타</option>
			</select>
		</td>
	</tr>
	<tr>
		<th>신 고 내 용</th>
		<td>
			<textarea rows="5" cols="40" name="content" style="width:100%;"></textarea>
		</td>
	</tr>
</table>
<input type="hidden" name="reportStatus" value="N" />
<input type="hidden" name="productNo" value="<%= request.getAttribute("productNo")%>"/>
<br/>
	<div style="text-align:center;">
		<input type="submit" value="신고" />
		<input type="button" value="취소" onclick="history.go(-1);" /> 		
	</div>
</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>