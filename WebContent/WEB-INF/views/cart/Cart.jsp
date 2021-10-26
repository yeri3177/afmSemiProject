<%@page import="com.kh.afm.cart.model.vo.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>장바구니</title>

<%@ include file="/WEB-INF/views/common/header.jsp" %>


<script>
//이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function(){
    // "선택 상품 삭제" 버튼 클릭
    document.querySelector('.selectDelete').addEventListener('click', function(){
        cart.delCheckedItem();
    });
    // "장바구니 비우기" 버튼 클릭
    document.querySelector('.deleteAll').addEventListener('click', function(){
        cart.delAllItem();
    });
    // 장바구니 행 "삭제" 버튼 클릭
    document.querySelectorAll('.btnCartDelete').forEach(
        function(item){
            item.addEventListener('click', function(){
                cart.delItem();
            });
        }
    );   
    // 수량변경 - 이벤트 델리게이션으로 이벤트 종류 구분해 처리
    document.querySelectorAll('.cartcountupdown').forEach(
        function(item, idx){
            //수량 입력 필드 값 변경
            item.querySelector('input').addEventListener('keyup', function(){
                cart.changePNum(idx+1);
            });
            //수량 증가 화살표 클릭
            item.children[1].addEventListener('click', function(){
                cart.changePNum(idx+1);
            });
            //수량 감소 화살표 클릭
            item.children[2].addEventListener('click', function(){
                cart.changePNum(idx+1);
            });
        }
    );
    //앵커 # 대체해 스크롤 탑 차단
    document.querySelectorAll('a[href="#"]').forEach(function(item){
        item.setAttribute('href','javascript:void(0)');
    });
});
let cart = {
	    totalCount: 0, //전체 갯수 변수
	    totalPrice: 0, //전체 합계액 변수
	    //체크한 장바구니 상품 비우기
	    delCheckedItem: function(){
	        document.querySelectorAll("input[name=buy]:checked").forEach(function (item) {
	            item.parentElement.parentElement.parentElement.remove();
	        });
	        //AJAX 서버 업데이트 전송
	        //전송 처리 결과가 성공이면
	        this.reCalc();
	        this.updateUI();
	    },
	    //장바구니 전체 비우기
	    delAllItem: function(){
	        document.querySelectorAll('.cartdata').forEach(function (item) {
	            item.remove();
	          });
	          //AJAX 서버 업데이트 전송
	          //전송 처리 결과가 성공이면
	          this.totalCount = 0;
	          this.totalPrice = 0;
	          this.reCalc();
	          this.updateUI();
	    },
	    //재계산
	    reCalc: function(){
	        this.totalCount = 0;
	        this.totalPrice = 0;
	        document.querySelectorAll(".p_num").forEach(function (item) {
	            var count = parseInt(item.getAttribute('value'));9999
	            this.totalCount += count;
	            var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
	            this.totalPrice += count * price;
	        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
	    },
	    //화면 업데이트
	    updateUI: function () {
	        document.querySelector('#cartpcount').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';
	        document.querySelector('#cartAllPrice').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';
	    },
	    //개별 수량 변경
	    changePNum: function (pos) {
	        var item = document.querySelector('input[name=p_num'+pos+']');
	        var p_num = parseInt(item.getAttribute('value'));
	        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
	        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }
	        item.setAttribute('value', newval);
	        item.value = newval;
	        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
	        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
	        //AJAX 업데이트 전송
	        //전송 처리 결과가 성공이면
	        this.reCalc();
	        this.updateUI();
	    },
	    //상품 삭제
	    delItem: function () {
	        event.target.parentElement.parentElement.parentElement.remove();
	    }
	}
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d)/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};
</script>
<%
	List<Cart> list = (List<Cart>)request.getAttribute("list");
%>
<h2>장바구니</h2>
<%
	if(list == null){
%>

    <!-- list null일때... -->
    <!-- list에 자료가 아무것도 없다면 -->
        <h3>장바구니가 비어있습니다.</h3>
        
<%
	}else{
%>

    <!-- list null이 아닐때, 즉 자료가 있을때 -->
    <!-- form을 실행한다.  -->
        <form id="cartform" name="cartform" class="cartform" method="post"
        action="/cart/update.do">
        <div class="cart">
            <div class="cartheader">
            <div class="cartcheck">선택</div>
            <div class="cartimg">이미지</div>
            <div class="cartproductname">상품명</div>
            <div class="cartprice">가격</div>
            <div class="cartcount">수량</div>
            <div class="cartsum">합계</div>
            <div class="basketcmd">&nbsp;</div>
            </div>
	<%
	int sum = 0;
	int sumcount = 0;
		for(Cart _cart : list){
			sum += (_cart.getProductPrice() * _cart.getProductQuantity());
			sumcount += _cart.getProductQuantity();
	%>
			<div class="cartdata">
	            <div class="cartcheck"><input type="checkbox" name="buy" value="260" checked="">&nbsp;</div>
	            <div class="cartimg"><img src="<%= request.getContextPath() %>/images/<%= //attach value %>" width="60"></div>
				<div class="cartproductname"><span><%= _cart.getProductName() %></span></div>
	            <div class="cartprice"><input type="hidden" name="p_price" id="p_price1" class="p_price" value="<%= _cart.getProductPrice() %>"><%= _cart.getProductPrice() %>원</div>
	            <div class="cartcount">
	                <!-- "장바구니 수량 변경" -->
	                <div class="cartcountupdown">
	                    <input type="text" name="p_num1" id="p_num1" size="2" maxlength="4" class="p_num" value="<%= _cart.getProductQuantity() %>">
	                    <span><i class="fas fa-arrow-alt-circle-up up"></i></span>
	                    <span><i class="fas fa-arrow-alt-circle-down down"></i></span>
	                </div>
	            </div>
	            <div class="sum"><%= _cart.getProductPrice() * _cart.getProductQuantity() %>원</div>
	            <div class="cartDelete"><a href="#" class="btnCartDelete">삭제</a></div>
	        </div>
	    </div>
    
	<%
		}//forEach문 끝
	%>
                <!-- "장바구니 기능 버튼" -->
	    <div class="cartcontrolAll">
	        <a href="#" class="selectDelete">선택상품삭제</a>
	        <a href="#" class="deleteAll">장바구니비우기</a>
	    </div>
    <!-- "장바구니 전체 합계 정보" -->
	    <div class="cartpcount" id="cartpcount">상품갯수: <%= sumcount %>개</div>
	    <div class="cartAllPrice" id="cartAllPrice">합계금액: <%= sum %>원</div>
        <div id="cartcheckbuy" class="">
	        <div class="clear"></div>
	        <div class="buttongroup">
	            <a href="#">선택한 상품 주문</a>
	        </div>
    	</div>
        </form>
<% } %>
<button type="button" id="btnProductList">상품목록</button>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>