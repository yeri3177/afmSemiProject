<%@page import="com.kh.afm.user.model.vo.DelUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>

<%
	List<DelUser> list = (List<DelUser>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>

<section id="delUserList-container" class="admin-container">

<div class="del-box">
	<button onclick="fnCheckAll();">전체선택</button>
	<button onclick="fnCheckNotAll();">전체해제</button>
	<button>데이터삭제</button>
</div>

<table id="tbl-deluser">
    <thead>
        <tr>
        	<th>no</th>
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
		<td><input type="checkbox" value="<%=user.getDeleteUId()%>" name="delUserData" /></td>
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

<!-- 페이지바 영역 -->
<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	

</section>

<script>
// 전체선택 버튼
function fnCheckAll() {
    let delUserDatas = document.getElementsByName("delUserData");
    
    for(let i=0; i<delUserDatas.length; i++){
    	delUserDatas[i].checked = true;
    }
}

//전체해제 버튼
function fnCheckNotAll() {
    let delUserDatas = document.getElementsByName("delUserData");
    
    for(let i=0; i<delUserDatas.length; i++){
    	delUserDatas[i].checked = false;
    }
}
</script>

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>