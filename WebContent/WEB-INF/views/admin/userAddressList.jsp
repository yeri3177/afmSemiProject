<%@page import="com.kh.afm.user.model.vo.Address"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<Address> list = (List<Address>) request.getAttribute("addressList");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주소상세보기</title>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/admin.css" />

</head>
<body>
<section id="addressList-container">

<p><span><%= list.get(0).getUserId() %></span>님의 주소목록</p>
<br>
<div id="adr-cnt">✅ 주소 총 <span><%= list.size() %></span>개 </div>


<table id="adrList-tbl">
	<tr>
		<th>no</th>
		<th>주소명칭</th>
		<th>도로명주소</th>
		<th>상세주소</th>
	</tr>
<% for(Address address : list) { %>
	
	<tr>
		<td><%= address.getAdrNo() %></td>
		<td><%= address.getAdrName() %></td>
		<td><%= address.getAdrRoad() %></td>
		<td><%= address.getAdrDetail() %></td>
	
	</tr>
<% } %>
</table>


</section>
</body>
</html>


