<%@page import="com.kh.afm.csboard.model.vo.CsboardComment"%>
<%@page import="com.kh.afm.user.model.service.UserService"%>
<%@page import="com.kh.afm.csboard.model.vo.Csboard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	Csboard csboard = (Csboard) request.getAttribute("csboard");
	// 일관된 흐름을 위해 editable을 따로 선언하고 곳곳에 적용시킨다.
	// loginUser가 null이 아니고 loginUser의 userId와 csboard의 UserId가 같거나 또는 관리자 아이디일 때 적용
	boolean editable = loginUser != null && (
					  loginUser.getUserId().equals(csboard.getUserId())
					  || UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
					);
	System.out.println("editable@csboardView.jsp = " + editable);
	
	List<CsboardComment> commentList = (List<CsboardComment>) request.getAttribute("commentList");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/csboard.css" />
<section id="csboardView-container" class="csboard-container">
	<h2>고객센터 게시판</h2>
	<table id="tbl-csboard-view">
		<tr>
			<th>글번호</th>
			<td><%= csboard.getBoardNo() %></td>
		</tr>
		<tr>
			<th>제 목</th>
			<td><%= csboard.getBoardTitle() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%= csboard.getUserId() %></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><%= csboard.getBoardReadcount() %></td>
		</tr>
		<tr>
			<th>내 용</th>
			<td>
				<%= csboard.getBoardContent() %>
			</td>
		</tr>
		
		<%-- 로그인한 유저가 작성자거나 관리자거나 --%>
<% if(editable){ %>
		<tr>
			<%-- 작성자와 관리자만 마지막행 수정/삭제 버튼이 보일 수 있게 할 것 --%>
			<th colspan="2">
				<input type="button" value="수정하기" onclick="updateCsboard()"/>
				<input type="button" value="삭제하기" onclick="deleteCsboard()"/>
			</th> 
		</tr> 
<% } %> 
	</table>
	
	<%-- 댓글 --%>
	<hr style="margin-top:30px"/>
	
	<div class="comment-container">
		<div class="comment-editor">
			<form 
			action="<%= request.getContextPath() %>/csboard/csboardCommentEnroll"
			name="csboardCommentFrm"
			method="POST">
			<textarea name="cbContent" id="" cols="30" rows="3"></textarea>
			<button id="btn-insert">등록</button>
			
			<input type="hidden" name="cbLevel" value="1" />
			<input type="hidden" name="userId" value="<%= loginUser != null ? loginUser.getUserId() : "" %>" />
			<input type="hidden" name="cbBoardNo" value="<%= csboard.getBoardNo() %>" />
			<input type="hidden" name="cbCommentRef" value="0" />
			</form>
		</div>
		
		<table id="tbl-comment">
<%
// .isEmpty()는 비어있을 때 true를 반환한다.
if(commentList != null && !commentList.isEmpty()){
	for(CsboardComment cbc : commentList){
		boolean removable = 
				loginUser != null &&
				(
					loginUser.getUserId().equals(cbc.getUserId())
				|| UserService.ADMIN_ROLE.equals(loginUser.getUserRole())
				);
				
		System.out.println(cbc);
		// 댓글 
		if(cbc.getCbLevel() == 1){
%>
<%-- 댓글 --%>
		<tr class="level1">
			<td>
				<sub class="comment-writer"><%= cbc.getUserId() %></sub>
				<sub class="comment-date"><%= cbc.getRegDate() %></sub>
				<br />
				<%-- 댓글 내용 --%>
				<%= cbc.getCbContent() %>
			</td>
			<td>
				    <button class="btn-reply" value="<%= cbc.getCbNo() %>">답글</button>
<% if(removable){ %><button class="btn-delete" value="<%= cbc.getCbNo()  %>">삭제</button><% } %>
			</td>
		</tr>

<%
		}
		// 대댓글
		else {
%>
		<%-- 대댓글(답글) --%>
		<tr class="level2">
			<td>
				<sub class="comment-writer"><%= cbc.getUserId() %></sub>
				<sub class="comment-date"><%= cbc.getRegDate() %></sub>
				<br />
				<%-- 댓글 내용 --%>
				<%= cbc.getCbContent() %>
			</td>
			<td>
<% if(removable){ %> <button class="btn-delete" value="<%= cbc.getCbNo() %>">삭제</button><% } %>
			</td>
		</tr>
<%		
		}
	}
}
%>
				
		</table>
		
	</div>
<form 
	action="<%= request.getContextPath() %>/csboard/csboardCommentDelete"
	name="csboardCommentDelFrm"
	method="POST">
	<input type="hidden" name="cbNo" />
	<input type="hidden" name="cbBoardNo" value="<%= csboard.getBoardNo() %>" />
</form>

<script>
$(".btn-delete").click(function(e){
	if(confirm("해당 댓글을 삭제하시겠습니까?")){
		var $frm = $(document.csboardCommentDelFrm);
		var cbNo = $(this).val();
		$frm.find("[name=cbNo]").val(cbNo);
		$frm.submit();
	}
});
</script>

</section>
<%-- editable 함수로 감싸므로써, 글쓴이 해당 아이디거나 관리자일 때만 deleteCsboard 권한이 생기도록 한다. --%>
<% if(editable){%>
<form action="<%= request.getContextPath() %>/csboard/csboardDelete" name="deleteCsboardFrm">
	<input type="hidden" name="boardNo" value="<%= csboard.getBoardNo() %>" />
</form>
<script>
// 실행코드가 한줄인 경우 화살표 함수 안에서 중괄호를 생략할 수 있다.
const updateCsboard = () => location.href = "<%= request.getContextPath() %>/csboard/csboardUpdate?boardNo=<%= csboard.getBoardNo() %>";
// POST 형식 : DB 상태가 바뀌므로. --> 무조건 form 태그
const deleteCsboard = () => {
	// 게시물 삭제는 돌이길 수 없으므로 다시한번 물어보도록 처리한다.
	if(confirm("정말 이 게시물을 삭제하시겠습니까?")){
		$(document.deleteCsboardFrm).submit();
	}
};
</script>
<% } %>


<script>
$(".btn-reply").click((e) => {
	//console.log($(e.target).val());
	const commentRef = $(e.target).val();
	const tr = `<tr>
	<td colspan="2" style="text-align: left;">
		<form 
			action="<%= request.getContextPath() %>/csboard/csboardCommentEnroll"
			method="POST">
			<textarea name="cbContent" id="" cols="30" rows="1"></textarea>
			<button class="btn-insert2">등록</button>
			
			<input type="hidden" name="cbLevel" value="2" />
			<input type="hidden" name="userId" value="<%= loginUser != null ? loginUser.getUserId() : "" %>" />
			<input type="hidden" name="cbBoardNo" value="<%= csboard.getBoardNo() %>" />
			<input type="hidden" name="cbCommentRef" value="\${commentRef}" />
		</form>
	</td>
</tr>`;
	console.log(tr);
	
	const $trOfBtn = $(e.target).parent().parent();
	//$trOfBtn.after(tr);
	$(tr)
		.insertAfter($trOfBtn)
		.find("form")
		.submit((e) => {
<% if(loginUser == null){%>
			loginAlert();
			// return false가 없으면 loginAlert 띄우고 제출이 될 것이다.
			return false;
<% } %>	
			
			// 내용검사
			// const textarea = $("[name=content]", document.csboardCommentFrm);
			// submit 이벤트의 주체가 document.csboardCommentFrm이므로
			// e.target : form
			const $textarea = $("[name=cbContent]", e.target);
			
			if(!/^(.|\n)+$/.test($textarea.val())){
				alert("댓글 내용을 작성해주세요.");
				$textarea.focus();
				return false;
			}
		})
		// focusing이 글로 바로 다시 가게끔 설정
		.find("[name=cbContent]")
		.focus();
	
	// 현재버튼의 handler 제거
	// e.target : button
	$(e.target).off('click');
});



$("[name=cbContent]", document.csboardCommentFrm).focus((e) => {
	<%-- console.log("focus!"); --%>
<% if(loginUser == null){%>
	loginAlert();
<% } %>
});

$(document.csboardCommentFrm).submit((e) => {
<% if(loginUser == null){%>
	loginAlert();
	// return false가 없으면 loginAlert 띄우고 제출이 될 것이다.
	return false;
<% } %>	
	
	// 내용검사
	// const textarea = $("[name=content]", document.csboardCommentFrm);
	// submit 이벤트의 주체가 document.csboardCommentFrm이므로
	const $textarea = $("[name=cbContent]", e.target);
	
	if(!/^(.|\n)+$/.test($textarea.val())){
		alert("댓글 내용을 작성해주세요.");
		$textarea.focus();
		return false;
	}
});	

const loginAlert = (e) => {
	alert("로그인 후 이용할 수 있습니다.");
	location.href="<%= request.getContextPath() %>/user/userLogin";
};


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

