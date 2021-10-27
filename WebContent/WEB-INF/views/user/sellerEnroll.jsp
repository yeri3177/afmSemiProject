<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
</head>
<body>

<!--  
카카오도로명주소
https://tyrannocoding.tistory.com/48
-->

<section id=user-enroll-container>

	
	<!-- 로고이미지 -->
	<a href="<%= request.getContextPath() %>/index.jsp"><img class="logo-img" src="<%=request.getContextPath()%>/images/common/logofont.png" /></a>
		
	
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
					<div style="width:100%; height:80px; overflow:auto">
						<span class="chk">
							<input type="checkbox"/>
						</span>
						<span class="enrollchk">
							AFM 방문을 환영합니다.
							AFM 서비스 및 제품(이하 ‘서비스’)을 이용해 주셔서 감사합니다. 본 약관은 다양한 AFM 서비스의 이용과 관련하여 AFM서비스를 제공하는 AFM 주식회사(이하 ‘AFM’)와 이를 이용하는 AFM 서비스 회원(이하 ‘회원’) 또는 비회원과의 관계를 설명하며, 아울러 여러분의 AFM 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
							여러분이 무심코 게재한 게시물로 인해 타인의 저작권이 침해되거나 명예훼손 등 권리 침해가 발생할 수 있습니다. 네이버는 이에 대한 문제 해결을 위해 ‘정보통신망 이용촉진 및 정보보호 등에 관한 법률’ 및 ‘저작권법’ 등을 근거로 권리침해 주장자의 요청에 따른 게시물 게시중단, 
							원 게시자의 이의신청에 따른 해당 게시물 게시 재개 등을 내용으로 하는 게시중단요청서비스를 운영하고 있습니다. 보다 상세한 내용 및 절차는 고객센터 내 게시중단요청서비스 소개를 참고해 주세요
						</span>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div style="width:100%; height:80px; overflow:auto">
						<span class="chk">
							<input type="checkbox"/>
						</span>
						<span class="enrollchk">
						개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.
	
						1. 수집하는 개인정보
						이용자는 회원가입을 하지 않아도 정보 검색, 뉴스 보기 등 대부분의 AFM 서비스를 회원과 동일하게 이용할 수 있습니다. 이용자가 메일, 캘린더, 카페, 블로그 등과 같이 개인화 혹은 회원제 서비스를 이용하기 위해 회원가입을 할 경우, 네이버는 서비스 이용을 위해 필요한 최소한의 개인정보를 수집합니다.

						회원가입 시점에 네이버가 이용자로부터 수집하는 개인정보는 아래와 같습니다.
						- 회원 가입 시에 ‘아이디, 비밀번호, 이름, 생년월일, 성별, 휴대전화번호’를 필수항목으로 수집합니다. 만약 이용자가 입력하는 생년월일이 만14세 미만 아동일 경우에는 법정대리인 정보(법정대리인의 이름, 생년월일, 성별, 중복가입확인정보(DI), 휴대전화번호)를 추가로 수집합니다. 그리고 선택항목으로 이메일 주소, 프로필 정보를 수집합니다.
						- 단체아이디로 회원가입 시 단체아이디, 비밀번호, 단체이름, 이메일주소, 휴대전화번호를 필수항목으로 수집합니다. 그리고 단체 대표자명을 선택항목으로 수집합니다.
						</span>
					</div>
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
					계좌번호<sup>*</sup> <br />
					<input type="text" placeholder="(-없이)67270201428283" name="banknum" id="banknum" maxlength="14" required />
					<input type="text" placeholder="은행명" name="bankname" id="bankname"/>
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