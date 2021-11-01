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
		<th>비밀번호</th>
		<td>
			<input type="password" name="board_password" /> * 필수 (게시물 삭제시 필요합니다.)
		</td>
	</tr>
	<tr>
		<th>제 목</th>
		<td>
			<input type="text" name="board_title" value="<%= csboard.getBoardTitle() %>" required />
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td>
			<textarea name="board_content" id="" cols="40" rows="5"><%= csboard.getBoardContent() %></textarea>
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
	var $content = $("[name=content]");
	if(/^(.|\n)+$/.test($content.val()) == false){
		return false;
	}
	return true;
})
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>