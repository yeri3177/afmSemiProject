<%@page import="com.kh.afm.user.model.vo.Address"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Address> list = (List<Address>) request.getAttribute("addressList");
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>

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
		<th class="adrArea">주소번호</th>
		<th class="adrArea">주소명칭</th>
		<th class="adrArea">주소검색</th>
		<th class="adrArea">도로명주소</th>
		<th class="adrArea">상세주소</th>
		<th class="btnArea">주소수정</th>
		<th class="btnArea">주소삭제</th>
	
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
			<input id="user_addr-<%= i %>" type="text" name="address1-<%= i %>" 
				placeholder="도로명 주소" value="<%= address.getAdrRoad() %>" readonly> <br>
		</td>
		
		<td> <!-- 상세주소 -->
			<input type="text" name="address2-<%= i %>" id="address2" 
				placeholder="상세 주소" value="<%= address.getAdrDetail() %>">
		</td>
		
		
		<td> <!-- 주소 수정 버튼 -->
			<input type="button" class="adrUpDelBtn" id="updateAddressBtn-<%= i %>" data-address-no="<%= address.getAdrNo() %>" value="수정"/>
		</td>
		
		<td> <!-- 주소 삭제 버튼 -->
			<input type="button" class="adrUpDelBtn" id="deleteAddressBtn-<%= i %>" data-address-no="<%= address.getAdrNo() %>" value="삭제"/>
		</td>
	</tr>
	
<% 
	i++;
	} 
%>

</table>

<!-- 주소 추가 버튼 -->
<input type="button" id="enrollAddressBtn" class="enrollAdrBtn" value="주소추가"/>

<!-- 확인 버튼 : 내 정보 보기 페이지로 넘어감 -->
<input type="button" id="enrollAddressOk" value="확인" class="enrollOkBtn" onclick="location.href='/afm/user/userDetail';"/>

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
				<td> <!-- 주소명칭 -->
					<input type="text" name="adr_name" id="_adrname"placeholder="주소명칭" />
				</td> 
				<td> <!-- 주소검색(우편번호) -->
					<input type="text" id="user_post" onclick="findAddr()" placeholder="주소검색" readonly/>
				</td>
				<td> <!-- 도로명주소 -->
					<input type="text" id="user_addr" name="adr_road" placeholder="도로명주소"/>
				</td>
				<td> <!-- 상세주소 -->
					<input type="text" name="adr_detail" id="user_addrdetail" placeholder="상세주소" required />
				</td>
				<td>
					<input type="button" value="추가" id="enrollBtn" />
				</td>
					<!-- 아이디 -->
					<input type="hidden" name="userId" id="_userId" value="<%= list.get(0).getUserId() %>"/>
			</tr>
		</table>
	</form>
</div>


<!-- 주소 수정 폼 -->
<form id="addressUpdateFrm" name="addressUpdateFrm" 
	action="<%=request.getContextPath()%>/user/addressUpdate" method="POST">
	
	<input type="hidden" name="userId" value="<%= list.get(0).getUserId() %>"/>
	<input type="hidden" name="addressNo"/>
	<input type="hidden" name="addressName"/>
	<input type="hidden" name="address1" id="_address1"/>
	<input type="hidden" name="address2" id="_address2"/>
</form>

<!-- 주소 삭제 폼 -->
<form id="addressDeleteFrm" name="addressDeleteFrm" 
	action="<%=request.getContextPath()%>/user/addressDelete" method="POST">
	
	<input type="hidden" name="addressNo" />
</form>


<script>
/* 주소 리스트 개수 */
const listSize = $("#listSize").val();


/* 추가 버튼 클릭 이벤트 */
$("#enrollAddressBtn").click((e) => {
	
	if(listSize < 3){
		// 주소추가 가능
		$(".addressEnroll").css('display','block');
		
		$("#enrollBtn").click((e) => {
	
			// 주소입력값 유효성검사 
			// (1) 주소명칭 입력 했는지
				const $addressName = $("#_adrname");
				const $address1 = $("#user_addr");
				const $address2 = $("#user_addrdetail");
				
				if(/^[가-힣]{1,}$/.test($addressName.val()) == false){
					alert("주소 명칭을 입력해주세요.");
					$addressName.select();
					return false;
				}
			// (2) 도로명주소 입력 했는지
				if(/^[가-힣-0-9\s]{4,}$/.test($address1.val()) == false){
					alert("도로명 주소를 입력해주세요.");
					$address1.select();
					return false;
				}
			// (3) 상세주소 입력 했는지 
				if(/^[a-zA-Z가-힣-0-9\s]{2,}$/.test($address2.val()) == false){
					alert("상세 주소를 입력해주세요.");
					$address2.select();
					return false;
				}
			// 폼전송
			const $frm = $(document.addressEnrollFrm);
			$frm.submit();
		});
	}else{
		// 주소추가 불가능
		alert("주소는 최대 3개까지만 가능합니다.");
	}
});


	
	
/**
 * 카카오 주소 api (주소추가폼)
 */
function findAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
        	
	       	console.log(data);
	       	
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
           // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var roadAddr = data.roadAddress; // 도로명 주소 변수
           
           var jibunAddr = data.jibunAddress; // 지번 주소 변수
           
           
           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           document.getElementById('user_post').value = data.zonecode;
           if(roadAddr !== ''){
               document.getElementById("user_addr").value = roadAddr;
           } 
           else if(jibunAddr !== ''){
               document.getElementById("user_addr").value = jibunAddr;
           }
        }
    }).open();
}


<%
	for(int j = 0; j < list.size(); j++) {
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


/* 카카오 주소 api (주소 수정폼) */
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