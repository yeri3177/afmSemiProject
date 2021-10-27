<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>user Join page</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
</head>
<body>

<!--  
카카오도로명주소
https://tyrannocoding.tistory.com/48
-->

<section id=user-enroll-container>

	
	<!-- 로고이미지 -->
	<img class="logo-img" src="<%=request.getContextPath()%>/images/common/logofont.png" />
		
	
	<form id="userEnrollFrm" name="userEnrollFrm" action="<%= request.getContextPath() %>/user/userEnroll" method="POST">
	
		<table>
			<tr>
				<td>
					<span>약관동의</span>
				</td>
			</tr>
			
			<tr>
				<td>
					<span class="chk">
						<input type="checkbox"/>
					</span>
					<span class="enrollchk">
						AFM의 이용약관, 개인정보 수집 및 이용, 위치정보 이용약관(선택),<br> 
						프로모션 정보 수신(선택)에 모두 동의합니다.
					</span>
				</td>
			</tr>
			
			<tr>
				<td>
					<span class="chk">
						<input type="checkbox"/>
					</span>
					<span class="enrollchk">
						AFM 방문을 환영합니다.
						AFM 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 AFM 서비스의 이용과 관련하여 AFM서비스를 제공하는 AFM 주식회사(이하 ‘AFM’)와 이를 이용하는 AFM 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 AFM 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
					</span>
				</td>
			</tr>
			
			<tr>
				<td>
					<span class="chk">
						<input type="checkbox"/>
					</span>
					<span class="enrollchk">
						AFM 방문을 환영합니다.
						AFM 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 AFM 서비스의 이용과 관련하여 AFM서비스를 제공하는 AFM 주식회사(이하 ‘AFM’)와 이를 이용하는 AFM 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 AFM 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
					</span>
				</td>
			</tr>
			
			<tr >
				<td id="blank"></td>
			</tr>
				
			<tr>
				<td>
					일반회원 회원가입
				</td>
			</tr>
			<tr>
				<td>
					아이디<sup>*</sup> <br />
					<input type="text" name="userId" id="_userId" required/>
					
					<input type="button" value="중복검사" id="btnCheckId" onclick="checkIdDuplicate();" />
					<input type="hidden" id="idValid" value="0" />
				</td>
			</tr>
			<tr>
				<td>
					비밀번호<sup>*</sup> <br />
					<input type="password" name="password" id="_password" required />
				</td>
			</tr>
			<tr>
				<td>
					비밀번호 확인<sup>*</sup> <br />
					<input type="password" id="password2" required />
				</td>
			</tr>
			<tr>
				<td>
					이메일<sup>*</sup> <br />
					<input type="text" placeholder="abc@xyz.com" name="email" id="email" />
				</td>
			</tr>
			
			<tr>
				<td>
					이름<sup>*</sup> <br />
					<input type="text" name="userName" id="userName" required />
				</td>
			</tr>
			
			<tr>
				<td>
					전화번호<sup>*</sup> <br />
					<input type="text" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required />
				</td>
			</tr>
		
			<tr>
				<td>
					생일 <br />
					<input type="text" placeholder="년(4자)" name="birthyear" id="birthyear" maxlength="4" />
					<input type="number" placeholder="월" name="birthmonth" id="birthmonth" maxlength="2" />
					<input type="text" placeholder="일" name="birthday" id="birthday" maxlength="2" />
				</td>
			</tr>
			
			<tr>
				<td>
					주소<sup>*</sup> <br />
					<input id="member_post"  type="text" placeholder="우편번호" readonly onclick="findAddr()"><br>
					
					<input id="member_addr" type="text" name="address1" id="address1" placeholder="도로명 주소" readonly> <br>
					
					<input type="text" name="address2" id="address2" placeholder="상세 주소">
				</td>
			</tr>

			<tr>
				<td>
					<input type="submit" value="가입하기" >
				</td>
			</tr>
		</table>
		
		<div id="idChkBox">
			
		</div>
	</form>
</section>	



<script>
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



</body>
</html>