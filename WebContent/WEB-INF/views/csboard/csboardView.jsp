<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Csboard csboard = (Csboard) request.getAttribute("csboard");
	// 일관된 흐름을 위해 editable을 따로 선언하고 곳곳에 적용시킨다.
	// loginUser가 null이 아니고 loginUser의 userId와 csboard의 UserId가 같거나 또는 관리자 아이디일 때 적용
	boolean editable = loginUser != null && (
				     loginUser.getUserId().equals(csboard.getUserId())
					 || UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
					);
	System.out.println("editable@csboardView.jsp = " + editable);
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/csboard.css" />
<section id="csboardView-container" class="csboard-container">
	<h2>고객센터 게시판</h2>
	<table id="tbl-csboard-view">
		<tr>
			<th>글번호</th>
			<td><%= csboard.getBoardNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= csboard.getBoardTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= csboard.getUserId() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= csboard.getBoardReadcount() %></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>
				<%= csboard.getBoardContent() %>
			</td>
		</tr>
		
		<%-- 로그인한 유저가 작성자거나 관리자거나 --%>
<% if(editable){ %>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제 버튼이 보일 수 있게 할 것 --%>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateCsboard()"/>
				<input type="button" value="삭제하기" onclick="deleteCsboard()"/>
			</th> 
		</tr> 
		<% } %> 
	</table>
</section>
<%-- editable 함수로 감싸므로써, 글쓴이 해당 아이디거나 관리자일 때만 deleteCsboard 권한이 생기도록 한다. --%>
<% if(editable){%>
<form action="<%= request.getContextPath() %>/csboard/csboardDelete" name="deleteCsboardFrm">
	<input type="hidden" name="boardNo" value="<%= csboard.getBoardNo() %>" />
</form>
<script>
const updateCsboard = () => location.href = "<%= request.getContextPath() %>/csboard/csboardUpdate?boardNo=<%= csboard.getBoardNo() %>";
// POST 형식 : DB 상태가 바뀌므로. --> 무조건 form 태그
const deleteCsboard = () => {
	// 게시물 삭제는 돌이길 수 없으므로 다시한번 물어보도록 처리한다.
	if(confirm("정말 이 게시물을 삭제하시겠습니까?")){
		$(document.deleteCsboardFrm).submit();
	}
};
</script>
<% } %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

