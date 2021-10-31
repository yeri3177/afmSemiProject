<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Csboard> list = (List<Csboard>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/csboard.css" />

<section id="csboardList-container" class="csboard-container">
	<h2>고객센터</h2>
	
	<table id="tbl-csboard">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
		<%
			for (Csboard csboard : list){
		%>
			<% if(csboard.getBoardNotice().equals("Y")){ %>
			<tr>
				<td>공지사항</td>
				<td><a href="<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard.getBoardNo() %>"><%= csboard.getBoardTitle() %></a></td>
				<td><%= csboard.getUserId() %></td>
				<td><%= csboard.getBoardRegDate() %></td>
				<td><%= csboard.getBoardReadcount() %></td>
			</tr>
			<% } %>
		
		<%
			}
		%>
<%
	for(Csboard csboard2 : list){
%>
	<tr>
		<td><%=csboard2.getRowNum()%></td>
		<% if(csboard2.getBoardLock().equals("Y")){ %>
			<td>
				<img src=""/>
				<a href="<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard2.getBoardNo() %>">
					<%= csboard2.getBoardTitle() %>
					이건 Y입니다.
				</a>
			</td>
		<% } %>
		<% if(csboard2.getBoardLock().equals("N")) { %>
			<td>
				<a href="<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard2.getBoardNo() %>">
					<%= csboard2.getBoardTitle() %>
					이건 N입니다.
				</a>
			</td>
		<% } %>
		<td><%= csboard2.getUserId() %></td>
		<td><%= csboard2.getBoardRegDate() %></td>
		<td><%= csboard2.getBoardReadcount() %></td>
	</tr>
	<input type="hidden" name="board_password" value="<%=csboard2.getBoardPassword()%>"/>
<%	
	}
%>			
	</table>
		<%-- 글쓰기 버튼 --%>
	<div style="float:right;">
		<% if(loginUser != null){ %>
			<input type="button" value="글쓰기" id="btn-add" onclick="location.href='<%= request.getContextPath() %>/csboard/csboardForm';" />
	    <% } %>
	</div>
	<div id='pageBar'>
		<%= request.getAttribute("pagebar")%>
	</div>
	
	
</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

