<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/csboard.css" />
<%
	Csboard csboard = (Csboard) request.getAttribute("csboard");
	// 일관된 흐름을 위해 editable을 따로 선언하고 곳곳에 적용시킨다.
	// loginUser가 null이 아니고 loginUser의 userId와 csboard의 UserId가 같거나 또는 관리자 아이디일 때 적용
	boolean editable = loginUser != null && (
				     UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
					);
%>
<script>
/**
* csboardEnrollFrm 유효성 검사
*/
function csboardValidate(e){
	// writer는 이미 값이 들어와 있이므로 유효성 검사는 하지 않는다.
	const $board_title = $("[name=board_title]");
	const $board_content = $("[name=board_content]");
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	// ^ : 입력의 시작 문자열에 매칭
	// $ : 입력의 끝 문자열에 매칭
	// . : 다음 줄 문자(개행 문자)를 제외한 문자열에 매칭(임의의 글자)
	// + : 1번 이상 반복되는 문자열에 매칭(한글자 이상)
	if(!/^.+$/.test($board_title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	//.(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
	if(!/^(.|\n)+$/.test($board_content.val())){
		alert("내용을 입력하세요.");
		return false;
	}
	return true;
}

// onload 함수
// submit 핸들러로 csboardValidate 지정
// csboardEnrollFrm이 제줄 직전에 csboardValidate 호출
$(() => {
	$(document.csboardEnrollFrm).submit(csboardValidate);
});

</script>

<section id="csboardForm-container" class="csboard-container">
<h2>고객센터 게시글 작성</h2>
<form name="boardEnrollFrm" action="<%=request.getContextPath() %>/csboard/csboardEnroll" method="POST">
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td>
			<input type="text" name="board_title" style="width: 400px" required>
		</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="user_id" value="<%= loginUser.getUserId() %>" style="width: 400px"readonly />
			<% if(editable){ %>
			<input type="checkbox" name="boardNotice_yn" value="Y"/>공지사항 여부
			<% } %>
		</td>
	</tr>
	<tr>
		<% if(!editable) { %>
		<th>비밀번호</th>
		<td>
			<input type="password" name="board_password" /> <input type="checkbox" name="boardLock_yn" value="Y" /> 비밀글 여부
		</td>
		<% } %>  
	</tr>
	<tr>
		<th>내 용</th>
		<td>
			<textarea rows="10" cols="40" name="board_content" style="width:98%;"></textarea>
		</td>
	</tr>
</table>
<br/>
	<div style="text-align:center;">
		<input type="submit" class="insert-btn" value="등록" onclick="return csboardCheck()"/> 
		<input type="reset" class="reset-btn" value="다시 작성" />
		<input type="button" class="listback-btn" value="목록" onclick="location.href='CsboardServlet?command=csboard_list'"/>
	</div>
</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>