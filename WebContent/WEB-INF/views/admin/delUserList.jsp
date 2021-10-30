<%@page import="com.kh.afm.user.model.vo.DelUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>

<%
	List<DelUser> list = (List<DelUser>) request.getAttribute("list");
%>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<section id="delUserList-container" class="admin-container">

<div class="del-box">
	<button onclick="fnCheckAll();">전체선택</button>
	<button onclick="fnCheckNotAll();">전체해제</button>
	<button onclick="fnDeleteUser();">회원삭제</button>
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


<!-- 회원삭제 전송폼 -->
<%-- <form action="<%= request.getContextPath() %>/admin/deleteDelUser" name="userDeleteFrm" method="POST">

</form> --%>

<script>
// 전체선택 버튼
function fnCheckAll() {
    let delUserDatas = document.getElementsByName("delUserData");
    
    for(let i=0; i<delUserDatas.length; i++){
    	delUserDatas[i].checked = true;
    }
}

// 전체해제 버튼
function fnCheckNotAll() {
    let delUserDatas = document.getElementsByName("delUserData");
    
    for(let i=0; i<delUserDatas.length; i++){
    	delUserDatas[i].checked = false;
    }
}

// 회원삭제 버튼
function fnDeleteUser() {

	// 체크박스에 체크를 안한 경우 
	$is_chk = $('input:checkbox[name="delUserData"]').is(":checked");
	
	if($is_chk == ""){
		alert("삭제할 회원을 체크해주십시오.");
		return false;
	}
	
	// 삭제할 회원 목록이 없는 경우 (되는지 확인 X)
	<% if(list == null){ %>
	
		alert("삭제할 회원이 없습니다.")
		return false;
	<% } %>	
	
	// 선택된 목록 가져오기
	const chkuser = 'input[name="delUserData"]:checked';
	const chkusers = document.querySelectorAll(chkuser);
	
	// 삭제할 회원 아이디 담을 배열
	var delUser_arr = new Array();
  	
  	// 다중값 배열에 담기 
  	chkusers.forEach((e) => {
    	delUser_arr.push(e.value);
  	});
  	
 	// confirm에 사용할 메세지
 	const msg = "삭제할 회원 목록 : "+delUser_arr
		+"\n-----------------------------------------------------------\n"
		+"정말로 삭제하시겠습니까?";
 	
 	// 삭제할건지 확인 받기
 	if(confirm(msg)){
 		
 		//동적으로 폼 생성 
 		const $frm = $('<form></form');
 		$frm.attr('action', '<%= request.getContextPath() %>/admin/deleteDelUser');
 		$frm.attr('method', 'POST');
 		$frm.appendTo('body');
 		
 		for(i=0; i<delUser_arr.length; i++){
 			
 			$frm.append(`<input type="hidden" name="userId" id="userId\${i}" />`);
 			$frm.find('[id=userId'+i+']').val(delUser_arr[i]);
 			
 		}
 		console.log($frm);
 		//폼전송이 안됨 ;;;
 		console.log("서브밋전");
 		
 		$frm.submit();
 		
 		console.log("서브밋후");
		
 	}else{
 		return false;
 	}
}

</script>

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>