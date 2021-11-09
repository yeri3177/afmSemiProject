<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/admin/adminHeader.jsp" %>
<%
	Map<String, Integer> summary = (Map<String, Integer>) request.getAttribute("summary");
	int[] productsMonthCnt =  (int[])request.getAttribute("productsMonthCnt");
%>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<style>

</style>

<section id="adminmain-container">
	
	<%-- <table id="main-tbl">
		<tr>
			<td class="tdbox">
				<div>최근 일주일간 가입한 회원수</div>
				
				<div class="text2">
					<%= summary.get("recentlyUserCnt") %>
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="tdbox">
				<div>전체 회원수</div>
				
				<div class="text2">
					<%= summary.get("allUserCnt") %>
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="tdbox">
				<div>최근 일주일간 추가한 상품수</div>
				
				<div class="text2">
					<%= summary.get("recentlyProdCnt") %>
				</div>
			</td>
		</tr>
		
		<tr>
			<td class="tdbox">
				<div>전체 상품수</div>
				
				<div class="text2">
					<%= summary.get("allProdCnt") %>
				</div>
			</td>
		</tr>
	
	</table> --%>

	<h2 class="summary-h2">afm 회원수&상품수</h2>
	<div id="summary-div">
		<div>
			<div class="summary-data">
				<%= summary.get("recentlyUserCnt") %>
			</div>
			<br>
			<div class="summary-text">
				일주일간<br /> 
				가입 회원수
			</div>
		</div>
		
		<div>
			<div class="summary-data">
				<%= summary.get("allUserCnt") %>
			</div>
			<br>
			<div class="summary-text">
				전체<br />회원수
			</div>
		</div>
		
		<div>
			<div class="summary-data">
				<%= summary.get("recentlyProdCnt") %>
			</div>
			<br>
			<div class="summary-text">
				일주일간<br />
				등록 상품수
			</div>
		</div>
		
		<div>
			<div class="summary-data">
				<%= summary.get("allProdCnt") %>
			</div>
			<br>
			<div class="summary-text">
				전체<br />상품수
			</div>
		</div>
	</div>
	
	<div>
		<h2 id="myChart-h2">2021년 상품 등록수</h2>
  		<canvas id="myChart"></canvas>
	</div>

<script>
/**
 * 차트 
 */
var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, {
    type: 'line', 
    data: {
        labels: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
        datasets: [{
            label: '수량',
            backgroundColor: [
              '#568A35',
              '#FFCD4A',
              '#FC5230'
            ],
            data: [
            	${productsMonthCnt[0]}, 
            	${productsMonthCnt[1]}, 
            	${productsMonthCnt[2]}, 
            	${productsMonthCnt[3]}, 
            	${productsMonthCnt[4]}, 
            	${productsMonthCnt[5]}, 
            	${productsMonthCnt[6]}, 
            	${productsMonthCnt[7]}, 
            	${productsMonthCnt[8]}, 
            	${productsMonthCnt[9]}, 
            	${productsMonthCnt[10]}, 
            	${productsMonthCnt[11]},
            	${productsMonthCnt[12]}
            ]
        }]
    },
    options: {
		/* responsive: false, */
		scales: {
			yAxes: [{
				ticks: {
					min: 0,
					max: 10,
					stepSize : 1
					/* fontSize : 14, */
				}
			}]
		}
	}
});
</script>

</section>	

<%@ include file="/WEB-INF/views/admin/adminFooter.jsp" %>		

