<%@page import="com.kh.afm.user.model.vo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>

<%
	List<User> list = (List<User>) request.getAttribute("list");
%>
<style>
#tbl-user {
	border: 1px solid black;
	border-collapse: collapse;
	margin: 0 auto;
}

#tbl-user > tr, th, td {
	border: 1px solid black;
	padding: 10px;
}

/*페이징 : 페이지바 */
div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
div#pageBar span.cPage{color: #0066ff; margin-right: 5px;}
div#pageBar a{margin-right: 5px; text-decoration: none;}
div#pageBar a:hover {text-decoration: underline;}
</style>

<br><br><br>
<h1>회원목록</h1>

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
        </tr>
    </thead>
    
    <tbody>
<%
	for(User user : list){
%>
	<tr>
		<td><%=user.getUserId() %></td>
		<td><%=user.getUserName() %></td>
		<td><%=user.getUserEmail() %></td>
		<td><%=user.getBirthday() %></td>
		<td><%=user.getPhone() %></td>
		<td><%=user.getUserRole() %></td>
		<td><%=user.getUserExpose() %></td>
		<td><%=user.getUserEnrollDate() %></td>

		
	
	</tr>

<%
    }
%>
	</tbody>	
</table>	

<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	
<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		