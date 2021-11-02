<%@page import="com.kh.afm.user.model.vo.Address"%>
<%@page import="java.util.List"%>
<%@page import="com.kh.afm.user.model.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	List<Address> list = (List<Address>) request.getAttribute("addressList");
%>

</head>
<body>
	<h1>주소상세보기</h1>
	
<h1><%= list.get(0).getUserId() %>님의 주소목록</h1>

주소 총 <%= list.size() %> 개 
<br>

<% for(Address address : list) { %>


<%= address.getAdrNo() %>
<%= address.getAdrName() %>
<%= address.getAdrRoad() %>
<%= address.getAdrDetail() %>

<br/><br/><br/>
<% } %>
	
</body>
</html>