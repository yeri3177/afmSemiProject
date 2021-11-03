<%@page import="com.kh.afm.user.model.vo.Address"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Address> list = (List<Address>) request.getAttribute("addressList");
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
<style>
/* 주소추가폼 안보이게하기 */
.addressEnroll {
	display: none;
}

</style>
<section id = "userAddressList-container">

<h1><%= list.get(0).getUserId() %>님의 주소목록</h1>

- 주소 총 <%= list.size() %> 개 -
<input type="hidden" id="listSize" value="<%= list.size() %>" />
<br>



<table>
	<tr>
		<th>주소번호</th>
		<th>주소명칭</th>
		<th>주소검색</th>
		<th>도로명주소</th>
		<th>상세주소</th>
		<th>주소수정</th>
		<th>주소삭제</th>
	
	</tr>

<% 	int i = 0;
	for(Address address : list) { 
%>
	
	<tr>
		<td>
			<%= address.getAdrNo() %>
			<input type="hidden" name="addressNo-<%= i %>" value="<%= address.getAdrNo() %>" />
		</td>
		
		<td> <!-- 주소명칭 -->
			<input type="text" name="addressName-<%= i %>" value="<%= address.getAdrName() %>" />
		</td>
		
		<td> <!-- 우편번호 -->
			<%-- <input id="user_post-<%= i %>"  type="text" placeholder="주소검색" readonly onclick="findAddr()"> --%>
			<input id="user_post-<%= i %>"  type="text" placeholder="주소검색" readonly>
		</td>
		
		<td> <!-- 도로명주소 -->
			<input id="user_addr-<%= i %>" type="text" name="address1-<%= i %>" id="address1" 
				placeholder="도로명 주소" value="<%= address.getAdrRoad() %>" readonly> <br>
		</td>
		
		<td> <!-- 상세주소 -->
			<input type="text" name="address2-<%= i %>" id="address2" 
				placeholder="상세 주소" value="<%= address.getAdrDetail() %>">
		</td>
		
		
		<td> <!-- 주소 수정 버튼 -->
			<input type="button" id="updateAddressBtn-<%= i %>" data-address-no="<%= address.getAdrNo() %>" value="수정"/>
		</td>
		
		<td> <!-- 주소 삭제 버튼 -->
			<input type="button" id="deleteAddressBtn-<%= i %>" data-address-no="<%= address.getAdrNo() %>" value="삭제"/>
		</td>
	</tr>
	
<% 
	i++;
	} 
%>

</table>

<!-- 주소 추가 버튼 -->
<input type="button" id="enrollAddressBtn" value="주소추가"/>

<!-- 주소 추가 폼 -->
<div class="addressEnroll">
	<form id="addressEnrollFrm" name="addressEnrollFrm" action="<%=request.getContextPath()%>/user/addressEnroll" method="POST">
		<table>
			<tr>
				<th>구분</th>
				<th>주소명칭</th>
				<th>주소검색</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>주소추가</th>
			</tr>
			
			<tr>
				<td>새주소</td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="text" /></td>
				<td><input type="button" value="추가" /></td>
			</tr>
		</table>
	</form>
</div>
<!-- 주소 수정 폼 -->
<form id="addressUpdateFrm" name="addressUpdateFrm" action="<%=request.getContextPath()%>/user/addressUpdate" method="POST">
	<input type="hidden" name="userId" value="<%= list.get(0).getUserId() %>"/>
	<input type="hidden" name="addressNo" />
	<input type="hidden" name="addressName" />
	<input type="hidden" name="address1" />
	<input type="hidden" name="address2" />
</form>

<!-- 주소 삭제 폼 -->
<form id="addressDeleteFrm" name="addressDeleteFrm" action="<%=request.getContextPath()%>/user/addressDelete" method="POST">
	<input type="hidden" name="addressNo" />
</form>


<script>

/* 주소 리스트 개수 */
const listSize = $("#listSize").val();
console.log("listSize = " + listSize);

/* 추가 버튼 클릭 이벤트 */
$("#enrollAddressBtn").click((e) => {
	
	if(listSize < 3){
		// 주소추가 가능
		
		
		
	}else{
		// 주소추가 불가능
		alert("주소는 최대 3개까지만 가능합니다.");
	}
});




/**
 * 96번줄에 반복문 횟수를 3 지정이 아니라 리스트사이즈값을 주고 싶음!!!!!!
 * <%= list.size() %>
 * <input type="hidden" id="listSize" value="<%= list.size() %>" />
 */

<%
	for(int j = 0; j < 3; j++) {
%>
/* 수정 버튼 클릭 이벤트 */
$("#updateAddressBtn-<%=j%>").click((e) => {
	const $this = $(e.target);
	const addressNo = $this.data("addressNo");
	const addressName = $("[name=addressName-<%=j%>]").val();
	const address1 = $("[name=address1-<%=j%>]").val();
	const address2 = $("[name=address2-<%=j%>]").val();
	
	console.log("addressNo = " + addressNo);
	console.log("addressName = " + addressName);
	console.log("address1 = " + address1);
	console.log("address2 = " + address2);

	const $frm = $(document.addressUpdateFrm);
	$frm.find("[name=addressNo]").val(addressNo);
	$frm.find("[name=addressName]").val(addressName);
	$frm.find("[name=address1]").val(address1);
	$frm.find("[name=address2]").val(address2);
	
	$frm.submit();

});


/* 삭제 버튼 클릭 이벤트 */
$("#deleteAddressBtn-<%=j%>").click((e) => {
	const $this = $(e.target);
	const addressNo = $this.data("addressNo");
	
	console.log("addressNo = " + addressNo);
	
	if(listSize > 1){
		const $frm = $(document.addressDeleteFrm);
		$frm.find("[name=addressNo]").val(addressNo);
		
		$frm.submit();
	}else{
		alert("주소가 1개이상 있어야합니다.");
	}
});


/**
 * 카카오 주소 api
 */
$("#user_post-<%=j%>").click((e) => {
/* function findAddr() { */
	new daum.Postcode({
        oncomplete: function(data) {
	       	
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
           // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var roadAddr = data.roadAddress; // 도로명 주소 변수
           var jibunAddr = data.jibunAddress; // 지번 주소 변수
           
           
           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           document.getElementById('user_post-<%=j%>').value = data.zonecode;
           if(roadAddr !== ''){
               document.getElementById("user_addr-<%=j%>").value = roadAddr;
           } 
           else if(jibunAddr !== ''){
               document.getElementById("user_addr-<%=j%>").value = jibunAddr;
           }
        }
    }).open();
});

<%
	}
%>

</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>