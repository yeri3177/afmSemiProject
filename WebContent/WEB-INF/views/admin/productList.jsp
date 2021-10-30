<%@page import="java.util.List"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	List<Product> list = (List<Product>) request.getAttribute("list");
	System.out.println("productList.jsp@list = " + list);
%>	

<section id="productList-container" class="admin-container">
<%-- 
<%
	for(Product product : list){
%>

<%= product.getpNo() %>	
<%= product.getpTitle() %>
<%= product.getAttach1().getAttachNo() %>
<%= product.getAttach2().getAttachNo() %>

<%
	}
%> --%>

<!-- 페이지바 영역 -->
<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	

</section>

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>