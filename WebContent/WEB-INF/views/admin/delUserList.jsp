<%@page import="com.kh.afm.user.model.vo.DelUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
	<h1>탈퇴회원목록</h1>

<%
	List<DelUser> list = (List<DelUser>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<table id="tbl-user">
    <thead>
        <tr>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>생년월일</th>
			<th>전화번호</th>
			<th>회원권한</th>
			<th>공개여부</th>
			<th>가입일</th>
			<th>탈퇴일</th>
        </tr>
    </thead>
    
    <tbody>
<%
	for(DelUser user : list){
%>
	<tr>
		<td><%=user.getDeleteUId()%></td>
		<td><%=user.getDeleteUName()%></td>
		<td><%=user.getDeleteUEmail()%></td>
		<td><%=user.getDeleteBirthday()%></td>
		<td><%=user.getDeletePhone()%></td>
		<td><%=user.getUserRole()%></td>
		<td><%=user.getUserExpose()%></td>
		<td><%=user.getDeleteUEnroll_date()%></td>
		<td><%=user.getDeleteUDate()%></td>
		

		
	
	</tr>

<%
    }
%>
	</tbody>	
</table>	
<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>