<%@page import="com.kh.afm.user.model.vo.Address"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	List<Address> list = (List<Address>) request.getAttribute("addressList");
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />

<section id = "userAddressList-container">

<h1><%= list.get(0).getUserId() %>님의 주소목록</h1>

- 주소 총 <%= list.size() %> 개 -
<br>



<table>
	<tr>
		<th>주소번호</th>
		<th>주소명칭</th>
		<th>우편번호</th>
		<th>도로명주소</th>
		<th>상세주소</th>
		<th>주소수정</th>
		<th>주소삭제</th>
	
	</tr>

<% for(Address address : list) { %>
	<tr>
		<td><%= address.getAdrNo() %></td>
		
		<td> <!-- 주소명칭 -->
			<input type="text" value="<%= address.getAdrName() %>" />
		</td>
		
		<td> <!-- 우편번호 -->
			<input id="member_post"  type="text" placeholder="우편번호" readonly onclick="findAddr()"><br>
		</td>
		
		<td>
			
			<input id="member_addr" type="text" name="address1" id="address1" placeholder="도로명 주소" value="<%= address.getAdrRoad() %>" readonly> <br>
		</td>
		
		<td>
		
			<input type="text" name="address2" id="address2" placeholder="상세 주소" value="<%= address.getAdrDetail() %>">
		</td>
		
		
		<td><input type="button" id="updateAddressBtn" data-address-no="<%= address.getAdrNo() %>" value="수정"/></td>
		<td><input type="button" id="deleteAddressBtn" data-address-no="<%= address.getAdrNo() %>" value="삭제"/></td>
	</tr>
<% } %>

</table>

<script>
$("#updateAddressBtn").click((e) => {
	const $this = $(e.target);
	const addressNo = $this.data("addressNo");
	
	console.log("수정버튼 = " + addressNo);
});

$("#deleteAddressBtn").click((e) => {
	const $this = $(e.target);
	const addressNo = $this.data("addressNo");
	
	console.log("삭제버튼 = " + addressNo);
});


/**
 * 카카오 주소 api
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
           document.getElementById('member_post').value = data.zonecode;
           if(roadAddr !== ''){
               document.getElementById("member_addr").value = roadAddr;
           } 
           else if(jibunAddr !== ''){
               document.getElementById("member_addr").value = jibunAddr;
           }
        }
    }).open();
}

</script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>