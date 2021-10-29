<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	Map<String, Integer> param = (Map<String, Integer>) request.getAttribute("param");
%>

<section id="adminmain-container" class="admin-container">

	<table>
		<tr>
			<td class="text1">
				최근 일주일간 가입한 회원수
			</td>
			<td class="text2">
				<%= param.get("recentlyUserCnt") %>
			</td>
		</tr>
		
		<tr>
			<td class="text1">
				전체 회원수
			</td>
			<td class="text2">
				<%= param.get("allUserCnt") %>
			</td>
		</tr>
		
		<tr>
			<td class="text1">
				최근 일주일간 추가한 상품수
			</td>
			<td class="text2">
				<%= param.get("recentlyProdCnt") %>
			</td>
		</tr>
		
		<tr>
			<td class="text1">
				전체 상품수 
			</td>
			<td class="text2">
				<%= param.get("allProdCnt") %>
			</td>
		</tr>
	
	</table>


</section>	

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		

