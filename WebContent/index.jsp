<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index.css"/>
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
				
				let html = `<div class="RDData"><img class="RDDateImg" src="<%= request.getContextPath() %>/upload/product/\${rename}"/><p class="RDDataP">등록일 : \${regDate}</p><a href="<%= request.getContextPath() %>/product/productView?pNo=\${no}" class="RDDataA">상품명 : \${title}</a><p class="RDDataP">가격 : \${price} 원</p></div>`;
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
				
				let html = `<div class="BEData"><img class="BEDateImg" src="<%= request.getContextPath() %>/upload/product/\${rename}"/><p class="BEDataP">추천수 : \${recommend}</p><a href="<%= request.getContextPath() %>/product/productView?pNo=\${no}" class="BEDataA">상품명 : \${title}</a><p class="BEDataP">가격 : \${price} 원</p></div>`;
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
$('.apimaintable').html($table);
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
	<h2 class="BEproductsh2H2">베스트 상품</h2>
	<div class="BEproducts">
	
	</div>
</div>
<div class="RDproductsh2">
	<h2 class="BEproductsh2H2">최신 상품</h2>
	<div class="RDproducts">
	
	</div>
</div>
<div class="apimaintableDiv">
	<h2 class="apimaintableH2">농산물 가격 check</h2>
	<table class="apimaintable">
	</table>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>