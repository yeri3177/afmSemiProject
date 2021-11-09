<%@page import="java.util.List"%>
<%@page import="com.kh.afm.product.model.vo.Report"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	List<Report> list = (List<Report>) request.getAttribute("list");
	int rcnt = (int) request.getAttribute("rcnt");
	int size = list.size();
	double rate = (double)rcnt/size*100;
	
%>

<section id="productReportList-container" class="admin-container">

<!-- 신고내역 건수 영역 -->
<div id="reportcnt">
	 <div>미처리건수 <span><%= rcnt %></span></div>
	 <div>전체신고건수 <span><%= list.size() %></span></div>
</div>

<!-- 신고내역 처리율 -->
<div class="reportRateDiv">
	<div id="rate-text-top-div">
		<span>신고처리율</span>
		<span id="percentCnt"><%= (int)rate %>%</span>
	</div>
	
	<!-- 프로그레스바 -->
	<progress value="<%= rate %>" max="100" id="prg-bar"></progress>
</div>

<br />

<table id="tbl-report">
    <thead>
        <tr>
			<th>신고번호</th>
			<th>신고유형</th>
			<th>신고제목</th>
			<th>신고내용</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>상품번호</th>
			<th>처리상태</th>
			<th>상품노출수정</th>
        </tr>
    </thead>
    
    <tbody>

<%
	for(Report report : list){
%>
	<tr>
		<td><%= report.getReportNo() %></td>
		<td><%= report.getReportType() %></td>
		<td><%= report.getReportTitle() %></td>
		<td><%= report.getReportContent() %></td>
		<td><%= report.getUserId() %></td>
		<td><%= report.getReportRegDate() %></td>
		<td>
			<a href="<%=request.getContextPath()%>/product/productView?pNo=<%= report.getpNo() %>">
				<%= report.getpNo() %>
			</a>
		</td>
		
		<td id="processStatusTd">
			<%= "N".equals(report.getReportStatus()) ? "미처리" : "처리완료" %>
		</td>
		
		<td> <!-- 상품노출수정 select-option태그 -->	
			<select class="p_report_expose" data-report-no="<%= report.getReportNo() %>" data-p-no="<%= report.getpNo() %>">
				<option value="none" disabled selected>-노출변경-</option>
				<option value="Y">노출</option>
				<option value="N">비공개</option>
			</select>
		</td>
	</tr>
<%
    }
%>
	</tbody>	
</table>

<!-- 페이지바 영역 -->
<div id="pageBar">
	<%= request.getAttribute("pagebar") %>
</div>	

</section>

<!-- 상품노출수정칸 변경시 폼전송 -->
<form action="<%=request.getContextPath()%>/admin/processReport" method="POST" name="processReportFrm">
	<input type="hidden" name="reportNo" />
	<input type="hidden" name="pNo" />
	<input type="hidden" name="pExpose" />
</form>

<script>
/* 처리상태칸 미처리일경우 글자색 빨간색으로 설정 */
/* $processStatusTd = $("#processStatusTd:contains('미처리')");
if($processStatusTd){
	$processStatusTd.css('color','red');	
	$processStatusTd.css('font-weight','bold');	
} */

/* 상품노출여부 변경시 체인지 이벤트 */
$(".p_report_expose").change((e) => {
	const $this = $(e.target);
	const reportNo = $this.data("reportNo");
	const pNo = $this.data("pNo");
	const pExpose = $this.val();
	const pExpose_kr = pExpose=="Y"?"노출":"비공개";
	
	const msg = `신고번호[\${reportNo}]의 상품노출설정을 [\${pExpose_kr}]로 하시겠습니까?`;
	
	if(confirm(msg)){
		const $frm = $(document.processReportFrm);
		$frm.find("[name=reportNo]").val(reportNo);
		$frm.find("[name=pNo]").val(pNo);
		$frm.find("[name=pExpose]").val(pExpose);
		$frm.submit();
	}
	else{
		$this.find("[selected]").prop("selected", true);
	}
});




</script>

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>