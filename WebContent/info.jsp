<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />


<section id="info-container">

<!-- 전체박스 -->
<div class="infobox">
	
	<!-- 상단박스 -->
	<div class="topbox">
		<div class="topimg">
			<img src="<%=request.getContextPath()%>/images/info/vegetable.png" alt="" />
		</div>
		<div class="infotext">
			<div class="text1">
				맛도 좋고 건강에도 좋은 <br>
				우리 농산물 afm과 함께 하세요.
			</div>
			<div class="text2">
				못난이 농산물로 맛과 가격까지 알뜰하게 <br>
				장보기 완료하세요.
			</div>
		</div>
	</div>


</div>

</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>