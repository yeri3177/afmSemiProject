<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Csboard csboard = (Csboard) request.getAttribute("csboard");

	boolean editable = loginUser != null && (
	     UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
		);
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/csboard.css" />
<section id="csboard-container">
<h2>고객센터 게시판 수정</h2>
<form 
	name="csboardUpdateFrm" 
	action="<%= request.getContextPath() %>/csboard/csboardUpdate" 
	method="POST">
	<%-- hidden 처리로 화면에 보이진 않지만 form 전송 시 같이 날아가게 된다. --%>
	<%-- PK 없이 update(delete) 할 수 없다. --%>
	<input type="hidden" name="boardNo" value="<%= csboard.getBoardNo() %>" />
	
	<table id="tbl-csboard-view">
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="user_id" value="<%= csboard.getUserId() %>" readonly />
			<% if(editable){ %>
			<input type="checkbox" name="boardNotice_yn" value="Y"/>공지사항 여부
			<% } %>
		</td>
	</tr>
	<tr>
		<% if(!editable) { %>
		<th>비밀번호</th>
		<td>
			<input type="password" name="boardPassword" /> <input type="checkbox" name="boardLockYN" value="Y" /> 비밀글 여부
		</td>
		<% } %>  
	</tr>
	<tr>
		<th>제 목</th>
		<td>
			<input type="text" name="boardTitle" value="<%= csboard.getBoardTitle() %>" required />
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td>
			<textarea name="boardContent" id="" cols="40" rows="5"><%= csboard.getBoardContent() %></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="수정하기"/>
			<input type="button" value="취소" onclick="history.go(-1);" />
		</th>
	</tr>
	</table>
</form>
</section>
<script>
$(document.csboardUpdateFrm).submit(function (){
	const $title = $("[name=title]");
	const $content = $("[name=content]");
	// 제목을 작성하지 않은 경우 폼제출을 할 수 없다.
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.")
		return false;
	}
	
	// 내용을 작성하지 않은 경우 폼제출을 할 수 없다.
	// .(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
	if(!/^(.|\n)+$/.test($content.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
})
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>