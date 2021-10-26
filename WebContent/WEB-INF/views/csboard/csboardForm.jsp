<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/csboard.css" />

<section id="csboard-container">
<h2>고객센터 게시글 작성</h2>
<form 
	name="scboardEnrollFrm"
	action="<%=request.getContextPath() %>/csboard/csboardEnroll"
	method="post"
	<%-- 파일전송이 없으므로 enctype을 디폴드 값으로 설정했다. --%>
	enctype="application/www-form-urlencoded">
	<table id="tbl-csboard-view">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="name" /> * 필수</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pass" /> * 필수 (게시물
				삭제시 필요합니다.)</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" /></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" size="70" name="title" /> * 필수</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="content" id="" cols="70" rows="15"></textarea></td>
		</tr>
	</table>
	<br />
	<br /> <input type="submit" value="등록" />
</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>