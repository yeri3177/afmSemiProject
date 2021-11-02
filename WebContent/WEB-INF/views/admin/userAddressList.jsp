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
</head>
<body>
<h1><%= list.get(0).getUserId() %>님의 주소목록</h1>

<% for(Address address : list) { %>


<%= address.getAdrNo() %>
<%= address.getAdrName() %>
<%= address.getAdrRoad() %>
<%= address.getAdrDetail() %>

<br/><br/><br/>
<% } %>
</body>
</html>

