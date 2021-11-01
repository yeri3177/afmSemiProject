<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/csboard.css" />
<section id="csboard-container">
	<h2>비밀번호 확인</h2>
<div align="center">
		<form 
			name="csboardCheckPass" 
			action="<%= request.getContextPath() %>/csboard/csboardCheckPass" 
			method="POST">
			<table style="width: 80%">
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="board_password" size="20" />
				</td>
			</tr>
			</table>
			<br />
			<input type="submit" value=" 확 인 " onclick="retrurn passCheck()" />
			<br /><br />${message}
		</form>
</div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>