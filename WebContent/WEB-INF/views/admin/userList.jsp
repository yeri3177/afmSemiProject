<%@page import="com.kh.afm.user.model.vo.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>

<%
	List<User> list = (List<User>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	String sortType = request.getParameter("sortType");
	String sortKeyword = request.getParameter("sortKeyword");
%>
<style>
div#search-userId {
	display: <%= searchType == null || "userId".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-userName {
	display: <%= "userName".equals(searchType) ? "inline-block" : "none" %>;
}
div#search-userRole {
	display: <%= "userRole".equals(searchType) ? "inline-block" : "none" %>;
}
</style>
<section id="userList-container" class="admin-container">

<div class="data-box">
	
	<!-- 데이터 검색 -->
	<div class="serarchbox">
		
		<!-- 검색타입 -->
		<span>search</span>
		<select id="searchType">
		    <option value="userId" <%= "userId".equals(searchType) ? "selected" : "" %>>
		    	아이디
		    </option>		
		    <option value="userName" <%= "userName".equals(searchType) ? "selected" : "" %>>
		    	회원명
		    </option>
		    <option value="userRole" <%= "userRole".equals(searchType) ? "selected" : "" %>>
		    	회원권한
		    </option>
		</select>
		
		<!-- 아이디 검색 -->
		<div id="search-userId" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/userFinder">
				<input type="hidden" name="searchType" value="userId"/>
				<input type="text" name="searchKeyword" 
					placeholder="검색할 아이디를 입력하세요." size="25" 
					value="<%= "userId".equals(searchType) ? searchKeyword : "" %>"/>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 회원명 검색 -->
		<div id="search-userName" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/userFinder">
				<input type="hidden" name="searchType" value="userName"/>
				<input type="text" name="searchKeyword" 
					placeholder="검색할 이름을 입력하세요." size="25" 
					value="<%= "userName".equals(searchType) ? searchKeyword : "" %>"/>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 회원권한 검색 -->
		<div id="search-userRole" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/userFinder">
				<input type="hidden" name="searchType" value="userRole"/>
		        <input type="radio" name="searchKeyword" value="A" 
		        	<%= "userRole".equals(searchType) && "A".equals(searchKeyword) ? "checked" : "" %>> admin
		        <input type="radio" name="searchKeyword" value="U" 
		        	<%= "userRole".equals(searchType) && "U".equals(searchKeyword) ? "checked" : "" %>> user
		        <input type="radio" name="searchKeyword" value="S" 
		        	<%= "userRole".equals(searchType) && "S".equals(searchKeyword) ? "checked" : "" %>> seller
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
	</div>
	
	<!-- 데이터 정렬 -->
	<div class="sortbox">
		
		<form action="<%=request.getContextPath()%>/admin/userSort">
			<span>sort</span>
			<!-- 정렬 타입 -->
			<input type="radio" name="sortType" value="asc" <%= "asc".equals(sortType) ? "checked" : "" %> />오름차순
			<input type="radio" name="sortType" value="desc" <%= "desc".equals(sortType) ? "checked" : "" %> />내림차순
			
			<!-- 정렬 키워드 -->
			<select name=sortKeyword>
				<option value="userId" <%= "userId".equals(sortKeyword) ? "selected" : "" %>>아이디</option>
				<option value="userName" <%= "userName".equals(sortKeyword) ? "selected" : "" %>>회원명</option>
				<option value="userRole" <%= "userRole".equals(sortKeyword) ? "selected" : "" %>>회원권한</option>
			</select>
			
			<button type="submit" class="sort-btn">정렬</button>
		</form>
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

<!-- 페이지바 영역 -->
<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	

</section>


<script>
/* 검색유형 체인지 이벤트 */
$("#searchType").change((e) => {
  	const type = $(e.target).val();
  	
  	// 1. .search-type 감추기
  	$(".search-type").hide();
  	
  	// 2. #search-${type} 보여주기 (display : inline-block)
  	$(`#search-\${type}`).css("display", "inline-block");
}); 

/* 정렬유형 체인지 이벤트 */
/* $("#sortType").change((e) => {
	const $this = $(e.target);
	const sortType = $this.val();
	console.log("sortType : " + sortType);
	
	const $frm = $(document.userSortFrm);
	$frm.find("[name=sortType]").val(sortType);
	$frm.submit();
}); */
</script>
<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		