<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Csboard> list = (List<Csboard>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/csboard.css" />

<section id="csboard-container">
	<h2>고객센터</h2>

<%-- loginMember가 null이 아니라면 : 회원이라면 --%>	
<%-- <% if(loginUser != null){ --%>
	<%-- 글쓰기 버튼 --%>
	<input 
		type="button" 
		value="글쓰기" 
		id="btn-add" 
		onclick="location.href='<%= request.getContextPath() %>/csboard/csboardForm';" />
<%-- <% } --%>

	<table id="tbl-csboard">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
<%
	for(Csboard csboard : list){
%>
	<tr>
		<td><%= csboard.getBoardNo() %></td>
		<td>
			<a href="<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard.getBoardNo() %>"><%= csboard.getBoardTitle() %></a>
		</td>
		<td><%= csboard.getUserId() %></td>
		<td><%= csboard.getBoardRegDate() %></td>
		<td><%= csboard.getBoardReadcount() %></td>
	</tr>
<%	
	}
%>			
	</table>
	
	<div id='pageBar'><%= request.getAttribute("pagebar")%></div>
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

