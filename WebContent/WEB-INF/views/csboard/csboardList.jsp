<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Csboard> list = (List<Csboard>) request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/csboard.css" />
<style>
div#search-container {margin:0 0 10px 0; padding:3px; background: #568A35;}
div#search-userId {display: <%= searchType == null || "userId".equals(searchType) ? "inline-block" : "none" %>;}
div#search-userName {display: <%= "userBoard".equals(searchType) ? "inline-block" : "none" %>;}
div#search-boardTitle {display: <%= "boardTitle".equals(searchType) ? "inline-block" : "none" %>;}
</style>
<script type="text/javascript">
function pswdChk(url,password){
	var pswdChk = prompt("비밀번호를 입력해주세요.");// 사용자가 입력한 비밀번호
	var origin = password // 게시글의 비밀번호
	if(pswdChk != origin){
		alert("비밀번호가 틀렸습니다. 확인주세요.");
		return false;
	} else {
		
		location.href=url;
		
		
	}
	
}

</script>

<section id="csboardList-container" class="csboard-container">
	<h2>고객센터</h2>
	<div id="search-container">
		검색타입 : 
		<select name="" id="searchType">
			<option value="userId" <%= "userId".equals(searchType) ? "selected" : "" %>>아이디</option>
			<option value="userName" <%= "userName".equals(searchType) ? "selected" : "" %>>회원명</option>
			<option value="boardTitle" <%= "boardTitle".equals(searchType) ? "selected" : "" %>>제목</option>
		</select>
		<div id="search-userId" class="search-type">
			<form action="<%= request.getContextPath() %>/csboard/boardFinder">
				<input type="hidden" name="searchType" value="userId" />
				<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요." value="<%= "userId".equals(searchType) ? searchKeyword : "" %>" />
				<button type="submit">검색</button>
			</form>
		</div>
		<div id="search-userName" class="search-type">
			<form action="<%= request.getContextPath() %>/csboard/boardFinder">
				<input type="hidden" name="searchType" value="boardNo" />
				<input type="text" name="searchKeyword" size="25" placeholder="검색할 번호를 입력하세요." value="<%= "userName".equals(searchType) ? searchKeyword : "" %>" />
				<button type="submit">검색</button>
			</form>
		</div>
		<div id="search-boardTitle" class="search-type">
			<form action="<%= request.getContextPath() %>/csboard/boardFinder">
				<input type="hidden" name="searchType" value="boardTitle" />
				<input type="text" name="searchKeyword" size="25" placeholder="검색할 제목을 입력하세요." value="<%= "boardTitle".equals(searchType) ? searchKeyword : ""  %>" />
				<button type="submit">검색</button>
			</form>
		</div>
	</div>
	
	<table id="tbl-csboard">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회</th>
		</tr>
		<%
			for (Csboard csboard2 : list){
		%>
			<% if(csboard2.getBoardNotice().equals("Y")){ %>
			<tr>
				<td>공지사항</td>
				<td><a href="<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard2.getBoardNo() %>"><%= csboard2.getBoardTitle()%></a></td>
				<td><%= csboard2.getUserId() %></td>
				<td><%= csboard2.getBoardRegDate() %></td>
				<td><%= csboard2.getBoardReadcount() %></td>
			</tr>
			<% } %>
		
		<%
			}
		%>
<%
	for(Csboard csboard : list){
%>
	<tr>
		<td><%=csboard.getBoardNo()%></td>
		<% if(csboard.getBoardLock().equals("Y")){ %>
			<td>
				<a href="javascript:pswdChk('<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard.getBoardNo() %>','<%=csboard.getBoardPassword()%>');">
					<img src="<%= request.getContextPath() %>/images/common/lock.png" alt="" style="width:16px;height:16px;"/><%= csboard.getBoardTitle() %>
				</a>
			</td>
		<% } %>
		<% if(csboard.getBoardLock().equals("N")) { %>
			<td>
				<a href="<%= request.getContextPath() %>/csboard/csboardView?boardNo=<%= csboard.getBoardNo() %>">
					<%= csboard.getBoardTitle() %>
				</a>
			</td>
		<% } %>
		<td><%= csboard.getUserId() %></td>
		<td><%= csboard.getBoardRegDate() %></td>
		<td><%= csboard.getBoardReadcount() %></td>
	</tr>
	
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
<script>
$("#searchType").change((e) => {
	// e.target : 이벤트 발생 객체 -> #searchType
	const type = $(e.target).val();
	console.log(type);
	
	// 1. .search-type 감추기
	$(".search-type").hide();
	
	// 2. #search-${type} 보여주기(diplay:inline-block)
	// \$ : $를 escaping
	$(`#search-\${type}`).css("display", "inline-block");
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>

