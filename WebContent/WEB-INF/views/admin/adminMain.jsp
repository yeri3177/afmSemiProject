<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	Map<String, Integer> param = (Map<String, Integer>) request.getAttribute("param");
%>

<section id="adminmain-container" class="admin-container">

	
	<div>
		최근 한달내에 가입한 회원수 
		<span> <%= param.get("recentlyUserCnt") %> </span>
	</div>
		
	<div>
		전체 회원수
		<span> <%= param.get("allUserCnt") %> </span>
	</div>

	<div>
		최근 한달내에 추가한 상품수
		<span> <%= param.get("recentlyProdCnt") %> </span>
	</div>
	
	<div>
		전체 상품수 
		<span> <%= param.get("allProdCnt") %> </span>
	</div>




</section>	

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		

