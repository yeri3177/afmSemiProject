<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />


<section id="info-container">

<!-- 소개박스 -->
<div id="introbox" class="info-container-box">
	<div class="introbox-img">
		<img id="intro-text" src="<%=request.getContextPath()%>/images/info/introduce.png" /><br>
		<img id="afmfullname" src="<%=request.getContextPath()%>/images/info/afm_full_name.png" />
	</div>
</div>

	
<!-- 노랑색 상단박스 -->
<div id="topbox" class="info-container-box">
	<div class="topbox-img">
		<img id="img1" src="<%=request.getContextPath()%>/images/info/vegetable.png" />
		<img id="img2" src="<%=request.getContextPath()%>/images/info/afmboy2.png" />
		<img id="img3" src="<%=request.getContextPath()%>/images/info/bubble_text.png" />
	</div>
</div>

<!-- 중간박스 -->
<div id="middlebox" class="info-container">
	<div class="middlebox-img">
		<img id="maptext" src="<%=request.getContextPath()%>/images/info/maptext.png" />
		<img id="mapimg" src="<%=request.getContextPath()%>/images/info/map.PNG" />
		<img id="map-spec-gg" class="mapinfo" src="<%=request.getContextPath()%>/images/info/map_info_1.PNG" />
		<img id="map-spec-hs" class="mapinfo" src="<%=request.getContextPath()%>/images/info/map_info_2.PNG" />
		<img id="map-spec-hn" class="mapinfo" src="<%=request.getContextPath()%>/images/info/map_info_3.PNG" />
		<img id="map-spec-gd" class="mapinfo" src="<%=request.getContextPath()%>/images/info/map_info_4.PNG" />
		<img id="map-spec-yn" class="mapinfo" src="<%=request.getContextPath()%>/images/info/map_info_5.PNG" />
	</div>
</div>


<!-- 하단박스 -->
<div id="bottombox" class="info-container">
	<div class="bottombox-img">
		<img id="teamname" src="<%=request.getContextPath()%>/images/info/team_name.png" /><br />
		<img id="teamone" src="<%=request.getContextPath()%>/images/info/codefarmer_profile.png" />

	</div>
</div>



</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>