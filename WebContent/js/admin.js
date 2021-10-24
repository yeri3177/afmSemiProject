/**
 * 사이드메뉴 클릭했을때 
 */
$('.snb ul li').on('click', function(){
   $(this).toggleClass('on');
});

/**
 * Settings 클릭 
 */
$("#adminLogo").click((e) => {
	$(location).attr('href','<%= request.getContextPath() %>/admin/adminMain');
	//location.href= "<%= request.getContextPath() %>/admin/adminMain";
});