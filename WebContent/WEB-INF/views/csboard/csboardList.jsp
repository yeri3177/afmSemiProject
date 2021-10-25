<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/csboard.css" />

<section id="csboard-container">
	<h2>고객센터</h2>
	<table id="tbl-csboard">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
	</table>
	
	<div id='pageBar'></div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

