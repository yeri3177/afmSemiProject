<%@page import="com.kh.afm.user.model.vo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>

<%
	List<User> list = (List<User>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<style>
div#search-memberId {
	display: <%= searchType == null || "memberId".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-memberName{
	display: <%= "memberName".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-gender{
	display: <%= "gender".equals(searchType) ? "inline-block" : "none" %>;
}
</style>

<div class="data-box">
	
	<!-- 데이터 검색 -->
	<div class="serarchbox">
		
		<!-- 검색타입 -->
		search
		<select id="searchType">
		    <option value="userId" <%= "userId".equals(searchType) ? "selected" : "" %>>아이디</option>		
		    <option value="userName" <%= "userName".equals(searchType) ? "selected" : "" %>>회원명</option>
		    <option value="userRole" <%= "userRole".equals(searchType) ? "selected" : "" %>>회원권한</option>
		</select>
		
		
		<input type="text" />
		
		
		<button>검색</button>
		
	</div>
	
	<!-- 데이터 정렬 -->
	<div class="sortbox">
		sort
		<select name="" id="">
			<option value="">아이디</option>
			<option value="">회원명</option>
			<option value="">회원권한</option>
		</select>
	</div>
</div>

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

<script>
$("#searchType").change((e) => {
  	// e.target 이벤트발생객체 -> #searchType
  	const type = $(e.target).val();
  	console.log(type);
  	
  	// 1. .search-type 감추기
  	$(".search-type").hide();
  	
  	// 2. #search-${type} 보여주기 (display : inline-block)
  	$(`#search-\${type}`).css("display", "inline-block");
}); 
</script>
<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		