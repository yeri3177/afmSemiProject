<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/csboard.css" />

<script>
/**
* csboardEnrollFrm 유효성 검사
*/
function csboardValidate(){
	// writer는 이미 값이 들어와 있이므로 유효성 검사는 하지 않는다.
	const $title = $("[name=title]");
	const $title = $("[name=content]");
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	// ^ : 입력의 시작 문자열에 매칭
	// $ : 입력의 끝 문자열에 매칭
	// . : 다음 줄 문자(개행 문자)를 제외한 문자열에 매칭(임의의 글자)
	// + : 1번 이상 반복되는 문자열에 매칭(한글자 이상)
	if(!/^.+$/.test($title.val())){
		alert("제목을 입력하세요.");
		return false;
	}
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	//.(임의의 문자)에는 \n(개행문자)가 포함되지 않는다.
	if(!/^(.|\n)+$/.test($content.val())){
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

<section id="csboard-container">
<h2>고객센터 게시글 작성</h2>
<form
	name="boardEnrollFrm"
	action="<%=request.getContextPath() %>/csboard/csboardEnroll" 
	method="POST"
	enctype="application/www-form-urlencoded">
	<table id="tbl-board-view">
	<tr>
		<th>작성자</th>
		<td>
			<input type="text" name="user_id" />
			<%-- value="loginUser.getUserId()로 수정예정" readonly --%>
		</td>  
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="board_password" /> * 필수 (게시물 삭제시 필요합니다.)</td>
	</tr>
	<tr>
		<th>제 목</th>
		<td><input type="text" name="board_title" required></td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="board_content"></textarea></td>
	</tr>
</table>
<br />
<br /><input type="submit" value="등록" 
	onclick="return csboardCheck()"/> <input type="reset"
	value="다시 작성" /><input type="button" value="목록" 
	onclick="location.href='CsboardServlet?command=csboard_list'"/>
</form>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>