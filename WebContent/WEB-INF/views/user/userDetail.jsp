<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.afm.user.model.vo.User"%>
<%
	User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css" />
</head>
<body>

	<section id=enroll-container>
		<h2>회원 정보 보기</h2>
		<form 
			name="userUpdateFrm" 
			action="<%= request.getContextPath() %>/user/userUpdate" 
			method="POST">
			<table>
				<tr>
					<th>아이디<sup>*</sup></th>
					<td>
						<input type="text" placeholder="3글자이상" name="userId" id="_userId" value="<%= loginUser.getUserId() %>" readonly required>
					</td>
				</tr>
				<%-- 
				<tr>
					<th>패스워드<sup>*</sup></th>
					<td>
						<input type="password" name="password" id="_password" value="<%= loginMember.getPassword() %>" required><br>
					</td>
				</tr>
				<tr>
					<th>패스워드확인<sup>*</sup></th>
					<td>	
						<input type="password" id="password2" value="<%= loginMember.getPassword() %>" required><br>
					</td>
				</tr>   
				--%>
				<tr>
					<th>이름<sup>*</sup></th>
					<td>	
					<input type="text"  name="userName" id="userName" value="<%= loginUser.getUserName() %>" required><br>
					</td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td>	
					<input type="date" name="birthday" id="birthday" value="<%= loginUser.getBirthday() != null ? loginUser.getBirthday() : "" %>" ><br />
					</td>
				</tr> 
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="<%= loginUser.getUserEmail() != null ? loginUser.getUserEmail() : "" %>"><br>
					</td>
				</tr>
				<tr>
					<th>휴대폰<sup>*</sup></th>
					<td>	
						<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value="<%= loginUser.getPhone() %>" required><br>
					</td>
				</tr>
				
				<%-- 주소, 계좌 서블릿 수정 전이라 주석 처리 --%>
				<%--
				<tr>
					<th>주소</th>
					<td>	
						 <input type="text" placeholder="" name="address" id="address" value="<%= loginUser.getAddress() != null ? loginUser.getAddress() : "" %>"><br> 
					</td>
				</tr>
				<tr>
					<th>계좌 </th>
					<td>
						<input type="text" placeholder="" name="banknum" id="banknum" maxlength="11" value="<%= loginUser.getAccount() %>" required><br>
					</td>
				</tr>
				--%>

			</table>
			<input type="submit" value="저장" >
			<input type="button" onclick="location.href='<%= request.getContextPath() %>/user/updatePassword';" value="비밀번호변경" />
			<input type="button" onclick="deleteMember();" value="탈퇴">
		</form>
	</section>	
</body>
</html>