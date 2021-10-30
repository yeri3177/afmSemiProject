<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="com.kh.afm.user.model.vo.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.kh.afm.user.model.service.UserService"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />

<section id=enroll-container>
	
	<h2>내 정보 보기</h2>
	<form name="userUpdateFrm" action="<%=request.getContextPath()%>/user/userUpdate" method="POST">
		<table>
			
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="3글자이상" name="userId" id="_userId" value="<%= loginUser.getUserId() %>" readonly required>
				</td>
			</tr>

			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="userName" id="userName" value="<%= loginUser.getUserName() %>" readonly="readonly"><br>
				</td>
			</tr>
			
			<tr>
				<th>회원권한<sup>*</sup></th>
				<td>	
				<input type="text"  name="userRole" id="userRole" value="<%= "U".equals(loginUser.getUserRole()) ? "일반회원" : "S".equals(loginUser.getUserRole()) ? "판매자" : "관리자" %>" readonly="readonly"><br>
				</td>
			</tr>
			
			<tr>
				<th>이메일<sup>*</sup></th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginUser.getUserEmail() != null ? loginUser.getUserEmail() : "" %>"><br>
				</td>
			</tr>
			
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="<%= loginUser.getBirthday() != null ? loginUser.getBirthday() : "" %>" readonly="readonly" ><br />
				</td>
			</tr> 
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%= loginUser.getPhone() %>" required><br>
				</td>
			</tr>
			
		
			<tr>
				<th>주소<sup>*</sup></th>
				<td>
					<input type="text" name="adrName" id="adrName" value="<%= loginUser.getAddress().getAdrName() != null ? loginUser.getAddress().getAdrName() : "" %>"><br>
					<input id="member_post"  type="text" placeholder="우편번호" readonly onclick="findAddr()"><br>		
					<input id="member_addr" type="text" name="address1" id="address1" placeholder="도로명 주소" value="<%= loginUser.getAddress() != null ? loginUser.getAddress().getAdrRoad() : "" %>" readonly> <br>
					<input type="text" name="address2" id="address2" placeholder="상세 주소" value="<%= loginUser.getAddress() != null ? loginUser.getAddress().getAdrDetail() : "" %>" > 
				</td>
			</tr>
<!-- 로그인한 사람이 판매자일 경우에만 계좌정보 나오게 하기 -->
<%
	if(UserService.SELLER_ROLE.equals(loginUser.getUserRole())) {
%>
			<tr>
				<th>계좌<sup>*</sup></th>
				<td>
					<input type="text" placeholder="계좌은행" name="bankname" id="bankname" maxlength="11" value="<%= loginUser.getAccount().getBankName() %>" required><br>
					<input type="text" placeholder="계좌번호" name="banknumer" id="banknumer" maxlength="11" value="<%= loginUser.getAccount().getAccountNumber() %>" required><br>
				</td>
			</tr>
<%
	}
%>		 
		</table>
		
		<input type="submit" value="저장" >
		<input type="button" onclick="location.href='<%= request.getContextPath() %>/user/updatePassword';" value="비밀번호변경" />
		<input type="button" onclick="deleteUser();" value="탈퇴">
	</form>
</section>	
	
	<!-- 회원탈퇴폼 -->
	<form name="userDelFrm"
		action="<%=request.getContextPath()%>/user/userDelete"
		method="POST">
		<input type="hidden" name="userId"
			value="<%=loginUser.getUserId()%>" />
	</form>
	<script type="text/javascript">
		function deleteUser() {
			if (confirm("정말로 탈퇴하시겠습니까?")) {
				$(document.userDelFrm).submit();
			}
		}
		
		/**
		* userUpdateFrm 유효성 검사
		* 1. 필수항목 값입력 확인
		* 2. 아이디/비번 4글자이상
		* 3. 비밀번호 일치 확인
		*/
		$("[name=userUpdateFrm]").submit((e) => {
			//userName
			const $userName = $("#userName");
			if(/^[가-힣]{2,}$/.test($userName.val()) == false){
				alert("이름은 한글 2글자 이상이어야 합니다.");
				$userName.select();
				return false;
			}
			
			//phone
			const $phone = $("#phone");
			//-제거하기
			$phone.val($phone.val().replace(/[^0-9]/g, ""));//숫자아닌 문자(복수개)제거하기
			
			if(/^010[0-9]{8}$/.test($phone.val()) == false){
				alert("유효한 전화번호가 아닙니다.");
				$phone.select();
				return false;
			}
			
			return true;
		});
		
		//주소api
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
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	