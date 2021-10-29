<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Csboard csboard = (Csboard) request.getAttribute("csboard");
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
		<% if(loginUser != null &&
				(
			     loginUser.getUserId().equals(csboard.getUserId())
				 || UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
				)
		   ){ %>
		   
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
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

