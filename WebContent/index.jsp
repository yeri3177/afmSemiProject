<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<style>
.infoform{display:flex;justify-content: center;position:absolute;top: 60%;left: 50%;transform: translate( -50%, -50% );}
.infoImg{width:100%;}
.infoImgText{display: flex;flex-direction: column;}
.logofont2{width:100px;}
.infoImgTextp{font-size: 20px;padding: 0;margin:0;}
.apihead{
width: 900px;
height: 10%;
  background: #e9e3d5;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  color: #427f2f;
  text-align:right;
  }
.apidata{
text-align:right;
width: 100%;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  background: #fff;
  width: 350px;
  padding: 10px;
  vertical-align: top;
}
.apitable{
font-family: 'Noto Sans KR', sans-serif;
width:95%;
  text-align: left;
  line-height: 1.5;
  border: 1px solid #427f2f;
  margin: 0 auto;
  margin-top:5px;
  margin-bottom:5px;
}
</style>
<script>
window.onload = function(){
	updateTable();
 };
 $(function() {
		setInterval(updateTable, 100000);
	});

	function updateTable() {
		$.get('./apiServlet', {}, function(data) {
			drawTable(data);
		}, 'xml');
	}

	function drawTable(data) {		
		var $xml = $(data);	

			const $root = $(data).find("data");
				
			const $itemArr = $root.children();
			const $table = $("<table class='apitable'></table>")
				$table.append("<tr class='apihead'><th>상품명</th><th>상태</th><th>품종명</th><th>단위</th><th>조회일가격(원)</th><th>평년 가격(원)</th></tr>");
				
					$itemArr.each((i,item)=>{
					console.log(i,item);
					const $name = $(item).children("item_name");
					const $rank = $(item).children("rank");
					const $kind = $(item).children("kind_name");
					const $unit = $(item).children("unit");
					const $dpr = $(item).children("dpr1");
					const $dpr7 = $(item).children("dpr7");
					if($name != ""){
					$table.append(`<tr class="apidata"><td>\${$name.text()}</td><td>\${$rank.text()}</td><td>\${$kind.text()}</td><td>\${$unit.text()}</td><td>\${$dpr.text()}</td><td>\${$dpr7.text()}</td></tr>`);
					}
				});
		$('.apitable').html($table);
	};
</script>

<div class="infoform">
	<div>
		<img src="<%= request.getContextPath() %>/images/common/logo.png" class="logoImg2"/>
	</div>
	<div class="infoImgText">
		<h2>농산물을 산지에서 직접!</h2>
		<p class="infoImgTextp">산지 직송의 신선함을 느껴보세요.</p>
		<p class="infoImgTextp">A Farmer's Market<img class="logofont2" src="<%= request.getContextPath() %>/images/common/logofont.png"></p>
	</div>
</div>
<img class="infoImg" src="<%= request.getContextPath() %>/images/common/colorful.jpg" alt="" />

<div>
	<h2>베스트 상품</h2>
	
</div>
<div>
	<h2>최신 상품</h2>
</div>
<div>
	<h2>농산물 가격 check</h2>
	<table class="apitable">
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>