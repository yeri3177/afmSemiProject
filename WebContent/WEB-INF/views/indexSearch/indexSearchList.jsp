<%@page import="java.sql.Date"%>
<%@page import="com.kh.afm.product.model.vo.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	List<Product> list = (List<Product>)request.getAttribute("list");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
%>
<title>검색 목록</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/indexSearch.css"/>
<h2 class="searchListH2">[<%= searchKeyword %>] 검색목록</h1>
	<div>
		<div class="searchListHeader">
			<div class="searchListHeaderDiv">번호</div>
			<div class="searchListHeaderDiv">사진</div>
			<div class="searchListHeaderDiv">상품</div>
			<div class="searchListHeaderDiv">가격</div>
			<div class="searchListHeaderDiv">판매자</div>
			<div class="searchListHeaderDiv">작성일</div>
			<div class="searchListHeaderDiv">추천수</div>
		</div>
<%
	for(Product _product : list){
%>
		<div class="searchListData">
			<div class="searchListDataDiv"><%= _product.getpNo() %></div>
			<div class="searchListDataDiv"><img class="searchListDataImg" alt="" src="<%= request.getContextPath() %>/upload/product/<%= _product.getAttach1().getRenamedFileName() %>" widdiv="300px" height="300px"></div>
			<div class="searchListDataDiv"><a href="<%= request.getContextPath() %>/product/productView?pNo=<%= _product.getpNo() %>"><%= _product.getpTitle() %></a></div>
			<div class="searchListDataDiv"><%= _product.getpPrice() %></div>
			<div class="searchListDataDiv"><%= _product.getUserId() %></div>
			<div class="searchListDataDiv"><%= (Date)_product.getpRegDate() %></div>
			<div class="searchListDataDiv"><%= _product.getpRecommend() %></div>
		</div>
<%
	}
%>
	</div>
<div id='pageBar'><%= request.getAttribute("pagebar") %></div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>