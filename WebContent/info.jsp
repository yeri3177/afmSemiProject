<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<!-- css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/info.css" />

<section id="info-container">

<!-- 소개박스 -->
<div id="introbox" class="info-container-box">
	
	<div class="introbox-img">

		<div> <!-- afm이란?  -->
			<img class="intro-img" id="what-is-afm" src="<%=request.getContextPath()%>/images/info/what_is_afm.png" />
		</div>
		
		<div id="intro-text"> <!-- 소개글 -->
			저희 afm은 농민들이 재배한 우리 농산물을 직거래 할 수 있는 온라인 마켓입니다. <br>
			생산자와 소비자를 직접 연결하여 저렴한 가격으로 신선한 농산물들을 만나보실 수 있습니다. <br>
			afm과 함께 농산물의 건강한 소비문화를 만들어가주세요. <br>
			항상 최선의 서비스로 보답하겠습니다. 감사합니다. 
		</div>
		
		<div> <!-- 풀네임 & 팀명 -->
			<img class="intro-img" src="<%=request.getContextPath()%>/images/info/intro-team.png" />
		</div>
		
	</div>
	
	<!-- 작은 채소 이미지 요소들 -->
	<div>
		<img class="mini-imgs" id="mini1" src="<%=request.getContextPath()%>/images/info/icon1.PNG" />
		<img class="mini-imgs" id="mini2" src="<%=request.getContextPath()%>/images/info/icon2.PNG" />
		<img class="mini-imgs" id="mini3" src="<%=request.getContextPath()%>/images/info/icon3.PNG" />
		<img class="mini-imgs" id="mini4" src="<%=request.getContextPath()%>/images/info/icon4.PNG" />
		<img class="mini-imgs" id="mini5" src="<%=request.getContextPath()%>/images/info/icon5.PNG" />
		<img class="mini-imgs" id="mini6" src="<%=request.getContextPath()%>/images/info/icon6.PNG" />
		<img class="mini-imgs" id="mini7" src="<%=request.getContextPath()%>/images/info/icon7.PNG" />
		<img class="mini-imgs" id="mini8" src="<%=request.getContextPath()%>/images/info/icon8.PNG" />
		<img class="mini-imgs" id="mini9" src="<%=request.getContextPath()%>/images/info/icon9.PNG" />
		<img class="mini-imgs" id="mini10" src="<%=request.getContextPath()%>/images/info/icon10.PNG" />
		<img class="mini-imgs" id="mini11" src="<%=request.getContextPath()%>/images/info/icon11.PNG" />
		<img class="mini-imgs" id="mini12" src="<%=request.getContextPath()%>/images/info/icon12.PNG" />
		<img class="mini-imgs" id="mini13" src="<%=request.getContextPath()%>/images/info/icon13.PNG" />
		<img class="mini-imgs" id="mini14" src="<%=request.getContextPath()%>/images/info/icon14.PNG" />
		<img class="mini-imgs" id="mini15" src="<%=request.getContextPath()%>/images/info/icon15.PNG" />
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

<!-- 지도박스 -->
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


<!-- 팀박스 -->
<div id="bottombox" class="info-container">
	<div class="bottombox-img">
		<img id="teamname" src="<%=request.getContextPath()%>/images/info/team_name.png" /><br />
		<img id="teamone" src="<%=request.getContextPath()%>/images/info/codefarmer_profile.png" />

	</div>
</div>



</section>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>