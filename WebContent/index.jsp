<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<style>
.infoform{display:flex;justify-content: center;position:absolute;top: 75%;left: 50%;transform: translate( -50%, -50% );}
.infoImg{width:100%;}
.infoImgText{display: flex;flex-direction: column;}
.logofont2{width:10vw;}
.logoImg2{width:20vw;}
.infoImgText h2{font-size: 2vw;}
.infoImgTextp{font-size: 2vw;padding: 0;margin:0;}
.apihead{width: 900px;height: 10%;background: #e9e3d5;padding: 10vw;font-weight: bold;vertical-align: top;color: #427f2f;text-align:right;}
.apidata{text-align:right;width: 100%;padding: 1vw;font-weight: bold;vertical-align: top;background: #fff;width: 350px;padding: 10px;vertical-align: top;}
.apitable{font-family: 'Noto Sans KR', sans-serif;width:95%;text-align: left;line-height: 1.5;border: 1px solid #427f2f;margin: 0 auto;margin-top:5px;margin-bottom:5px;}
.RDproducts{display:flex;width:100%;justify-content: space-evenly;}
.RDData{display:flex;float: left;flex-direction: column;width:30%;}
.indexRDproducts{width:100%;background-color:#E9E3D5;display:flex;justify-content: space-around;text-align:left;}
.RDproductsh2{background-color:#E9E3D5;}
.RDDateImg{width:100%;hieght:100%;}
.RDDataP{font-size:1.5vw;}
.RDDataA{font-size:1.5vw;}
.BEproducts{display:flex;width:100%;justify-content: space-evenly;}
.BEData{display:flex;float: left;flex-direction: column;width:30%;}
.indexBEproducts{width:100%;display:flex;justify-content: space-around;text-align:left;}
.BEDateImg{width:100%;hieght:100%;}
.BEDataP{font-size:1.5vw;}
.BEDataA{font-size:1.5vw;}
</style>
<script>
window.addEventListener('load', function(){
	updateTable();
});
window.addEventListener('load', function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/index/selectNewProduct",
		dataType: "text",
		success(data){
			const $RDdiv = $(`<div class='indexRDproducts'></div>`);
			
			const arr = data.split("\n");
			$(arr).each((i, str)=>{
				
				let temp = str.split(", ");
				const pNo = temp[0];
				let tempNo = pNo.split("=");
				const no = tempNo[1];
				
				const pUserId = temp[1];
				let tempUserId = pUserId.split("=");
				const userId = tempUserId[1];
				
				const pRegDate = temp[2];
				let tempRegDate = pRegDate.split("=");
				const regDate = tempRegDate[1];
				
				const pTitle = temp[3];
				let tempTitle = pTitle.split("=");
				const title = tempTitle[1];
				
				const pPrice = temp[6];
				let tempPrice = pPrice.split("=");
				const price = tempPrice[1];
				
				const pCategory = temp[8];
				let tempCategory = pCategory.split("=");
				const category = tempCategory[1];
				
				const pRecommend = temp[11];
				let tempRecommend = pRecommend.split("=");
				const recommend = tempRecommend[1];
				
				const pRename = temp[15];
				let tempRename = pRename.split("=");
				const rename = tempRename[1];
				
				let html = `<div class="RDData"><img class="RDDateImg" src="<%= request.getContextPath() %>/upload/product/20211027_134914715_460.gif"/><p class="RDDataP">등록일 : \${regDate}</p><a href="#" class="RDDataA">상품명 : \${title}</a><p class="RDDataP">가격 : \${price}</p></div>`;
				$RDdiv.append(html);
			});
			$(".RDproducts").html($RDdiv);
		},
		error(xhr,textStatus,err){
			console.log(xhr,textStatus,err);
		}
	})
});
window.addEventListener('load', function(){
	$.ajax({
		url: "<%= request.getContextPath() %>/index/selectBestProduct",
		dataType: "text",
		success(data){
			const $BEdiv = $(`<div class='indexBEproducts'></div>`);
			
			const arr = data.split("\n");
			$(arr).each((i, str)=>{
				
				let temp = str.split(", ");
				const pNo = temp[0];
				let tempNo = pNo.split("=");
				const no = tempNo[1];
				
				const pUserId = temp[1];
				let tempUserId = pUserId.split("=");
				const userId = tempUserId[1];
				
				const pTitle = temp[3];
				let tempTitle = pTitle.split("=");
				const title = tempTitle[1];
				
				const pPrice = temp[6];
				let tempPrice = pPrice.split("=");
				const price = tempPrice[1];
				
				const pCategory = temp[8];
				let tempCategory = pCategory.split("=");
				const category = tempCategory[1];
				
				const pRecommend = temp[11];
				let tempRecommend = pRecommend.split("=");
				const recommend = tempRecommend[1];
				
				const pRename = temp[15];
				let tempRename = pRename.split("=");
				const rename = tempRename[1];
				
				let html = `<div class="BEData"><img class="BEDateImg" src="<%= request.getContextPath() %>/upload/product/20211027_134914715_460.gif"/><p class="BEDataP">추천수 : \${recommend}</p><a href="#" class="BEDataA">상품명 : \${title}</a><p class="BEDataP">가격 : \${price}</p></div>`;
				$BEdiv.append(html);
			});
			$(".BEproducts").html($BEdiv);
		},
		error(xhr,textStatus,err){
			console.log(xhr,textStatus,err);
		}
	})
});
$(function() {
	setInterval(updateTable, 3600000);
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
	$table.append("<tr class='apihead'><th>상품명</th><th>상태</th><th>품종명(단위)</th><th>조회일가격(원)</th><th>평년 가격(원)</th></tr>");
	$itemArr.each((i,item)=>{
		const $name = $(item).children("item_name");
		const $rank = $(item).children("rank");
		const $kind = $(item).children("kind_name");
		const $dpr = $(item).children("dpr1");
		const $dpr7 = $(item).children("dpr7");
		if($name != ""){
		$table.append(`<tr class="apidata"><td>\${$name.text()}</td><td>\${$rank.text()}</td><td>\${$kind.text()}</td><td>\${$dpr.text()}</td><td>\${$dpr7.text()}</td></tr>`);
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

<div class="BEproductsh2">
	<h2>베스트 상품</h2>
	<div class="BEproducts">
	
	</div>
</div>
<div class="RDproductsh2">
	<h2>최신 상품</h2>
	<div class="RDproducts">
	
	</div>
</div>
<div>
	<h2>농산물 가격 check</h2>
	<table class="apitable">
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>