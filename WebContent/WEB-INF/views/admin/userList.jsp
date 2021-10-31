<%@page import="com.kh.afm.user.model.service.UserService"%>
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
			<form action="<%=request.getContextPath()%>/admin/userFinder" name="searchFrm">
				<input type="hidden" name="searchType" value="userId"/>
				<input type="text" name="searchKeyword" 
					placeholder="검색할 아이디를 입력하세요." size="22" id="searchInput"
					value="<%= "userId".equals(searchType) ? searchKeyword : "" %>"/>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 회원명 검색 -->
		<div id="search-userName" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/userFinder" name="searchFrm">
				<input type="hidden" name="searchType" value="userName"/>
				<input type="text" name="searchKeyword" 
					placeholder="검색할 이름을 입력하세요." size="22" id="searchInput"
					value="<%= "userName".equals(searchType) ? searchKeyword : "" %>"/>
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
		<!-- 회원권한 검색 -->
		<div id="search-userRole" class="search-type">
			<form action="<%=request.getContextPath()%>/admin/userFinder" name="searchFrm">
				<input type="hidden" name="searchType" value="userRole"/>
		        
		        <label>
			        <input type="radio" name="searchKeyword" value="A" checked
			        	<%= "userRole".equals(searchType) && "A".equals(searchKeyword) ? "checked" : "" %>> admin
		        </label>
		        	
		        <label>
			        <input type="radio" name="searchKeyword" value="U" 
			        	<%= "userRole".equals(searchType) && "U".equals(searchKeyword) ? "checked" : "" %>> user
		        </label>
		        
		        <label>
			        <input type="radio" name="searchKeyword" value="S" 
			        	<%= "userRole".equals(searchType) && "S".equals(searchKeyword) ? "checked" : "" %>> seller
				</label>
				
				<button type="submit" class="search-btn">검색</button>
			</form>
		</div>
		
	</div>
	
	<!-- 데이터 정렬 -->
	<div class="sortbox">
		
		<form action="<%=request.getContextPath()%>/admin/userSort">
			<span>sort</span>
			<!-- 정렬 타입 -->
			<label><input type="radio" name="sortType" value="asc" checked <%= "asc".equals(sortType) ? "checked" : "" %> />오름차순</label>
			<label><input type="radio" name="sortType" value="desc" <%= "desc".equals(sortType) ? "checked" : "" %> />내림차순</label>
			
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
        	<!---- user ---->
			<th class="user-th">아이디</th>
			<th class="user-th">이름</th>
			<th class="user-th">이메일</th>
			<th class="user-th">전화번호</th>
			<th class="user-th">회원권한</th>
			<th class="user-th">공개여부</th>
			<th class="user-th">가입일</th>
			
			<!---- address ---->
			<th class="address-th">주소NO</th>
			<th class="address-th" id="adr-th">주소지</th>
			
			<!---- account ---->
			<th class="account-th">계좌NO</th>
			<th class="account-th">은행명</th>
			<th class="account-th">계좌번호</th>
			
        </tr>
    </thead>
    
    <tbody>
<%
	for(User user : list){
%>
	<tr>
		<!---- user ---->
		<td><%= user.getUserId() %></td>
		<td><%= user.getUserName() %></td>
		<td><%= user.getUserEmail() %></td>
		<td><%= user.getPhone() %></td>
		<td>
			<%= "U".equals(user.getUserRole()) ? "일반회원" : "S".equals(user.getUserRole()) ? "판매자" : "관리자" %>
		</td>
		
		<td> <!-- 회원공개여부 select-option태그 -->	
			<select class="user_expose" data-user-id="<%= user.getUserId() %>">
				<option value="Y"
					<%= "Y".equals(user.getUserExpose()) ? "selected" : "" %>>공개
				</option>
				
				<option value="N"
					<%= "N".equals(user.getUserExpose()) ? "selected" : "" %>>차단
				</option>
			</select>
		</td>
		
		<td><%= user.getUserEnrollDate() %></td>
		
		<!---- address ---->
		<td><%= user.getAddress().getAdrNo() %></td>
		<td><%= user.getAddress().getAdrRoad() %> <%= user.getAddress().getAdrDetail() %></td>
		
		<!---- account ---->
		<td><%= user.getAccount().getAccountNo() != 0 ? user.getAccount().getAccountNo() : "" %></td>
		<td><%= user.getAccount().getBankName() != null ? user.getAccount().getBankName() : "" %></td>
		<td><%= user.getAccount().getAccountNumber() != null ? user.getAccount().getAccountNumber() : "" %></td>
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

<!-- 공개여부 변경시 폼전송 -->
<form action="<%=request.getContextPath()%>/admin/updateUserExpose" method="POST" name="updateUserExposeFrm">
	<input type="hidden" name="userId" />
	<input type="hidden" name="userExpose" />
</form>

<script>
/* 검색할때 값 입력 유효성검사 ★★★★★★★★★★★★★★★★★★★★★★★★★보류*/
/* $("[name=searchFrm]").submit((e) => {
	const $searchInput = $("#searchInput");
	const $searchType = $("[name=searchType]");
	console.log($searchType.val());
	
	if($searchType.val() == "userId") {
		if($searchInput.val() == ""){
			alert("검색키워드를 입력하십시오.");
			$searchInput.select();
			return false;
		} 
	}
	return true;
}); */
 
$("[name=searchFrm]").submit((e) => {
	const $searchKeyword = $("[name=searchKeyword]");
	const $searchType = $("[name=searchType]");
	console.log($searchType.val());
	
	if($searchType.val() == "userId") {
		if($searchKeyword.val() == ""){
			alert("검색키워드를 입력하십시오.");
			$searchKeyword.select();
			return false;
		} 
		return true;
	}
});

/* 검색유형 체인지 이벤트 */
$("#searchType").change((e) => {
  	const type = $(e.target).val();
  	
  	// 1. .search-type 감추기
  	$(".search-type").hide();
  	
  	// 2. #search-${type} 보여주기 (display : inline-block)
  	$(`#search-\${type}`).css("display", "inline-block");
}); 


/* 공개여부값 변경시 체인지 이벤트 */
$(".user_expose").change((e) => {
	const $this = $(e.target);
	const userId = $this.data("userId");
	const userExpose = $this.val();
	const userExpose_kr = userExpose=="Y"?"공개":"차단";
	
	const msg = `[\${userId}] 회원의 공개여부 설정을 [\${userExpose_kr}] 하시겠습니까?`;
	
	if(confirm(msg)){
		const $frm = $(document.updateUserExposeFrm);
		$frm.find("[name=userId]").val(userId);
		$frm.find("[name=userExpose]").val(userExpose);
		$frm.submit();
	}
	else{
		$this.find("[selected]").prop("selected", true);
	}
});
</script>
<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		