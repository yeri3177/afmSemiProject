<%@page import="com.kh.afm.cart.model.vo.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>장바구니</title>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/cart.css"/>
<script>
document.addEventListener('DOMContentLoaded', function(){
    document.querySelector('.basketrowcmd a:nth-child(2)').addEventListener('click', function(){
        basket.delAllItem();
    });
    document.querySelectorAll('.basketcmd a').forEach(
        function(item, idx){
            item.addEventListener('click', function(){
                basket.delItem(idx);
            });
        }
    );
    document.querySelectorAll('.updown').forEach(
        function(item, idx){
            item.querySelector('input').addEventListener('keyup', function(){
                basket.changePNum(idx);
            });
            item.children[1].addEventListener('click', function(){

                basket.changePNum(idx);
            });
            item.children[2].addEventListener('click', function(){
                basket.changePNum(idx);
            });
        }
    );
    document.querySelectorAll('a[href="#"]').forEach(function(item){
        item.setAttribute('href','javascript:void(0)');
    });
});

let basket = {
    totalCount: 0, 
    totalPrice: 0,
    delAllItem: function(){
        document.querySelectorAll('.row.data').forEach(function (item) {
            item.remove();
          });
          $.ajax({
			url: "<%= request.getContextPath() %>/cart/cartDeleteAll",
			dataType: "text",
			success:(this.totalCount = 0,this.totalPrice = 0,this.updateUI()),
			error(xhr,textStatus,err){
				console.log(xhr,textStatus,err);
			}
		})
    },
    reCalc: function(){
        this.totalCount = 0;
        this.totalPrice = 0;
        document.querySelectorAll(".p_num").forEach(function (item) {
            if(item.parentElement.parentElement.parentElement.previousElementSibling.firstElementChild.firstElementChild.checked == true){
                var count = parseInt(item.getAttribute('value'));
                this.totalCount += count;
                var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
                this.totalPrice += count * price;
            }
        }, this);
    },
    updateUI: function () {
        document.querySelector('#sum_p_num').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';
    },
	    changePNum: function (i) {
	        var item = document.querySelector('input[name=p_num'+i+']');
	        var inputcartNo = document.querySelector('input[name=cartNo'+i+']');
	        var cartNo = inputcartNo.getAttribute('value');
	        var p_num = parseInt(item.getAttribute('value'));
	        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
	        
	        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }
	
	        item.setAttribute('value', newval);
	        item.value = newval;
	
	        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
	        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
 			$.ajax({
			url: "<%= request.getContextPath() %>/cart/cartUpdate",
			data: {'cartNo':cartNo,'productQuantity':newval},
			dataType: "text",
			success:(this.reCalc(),this.updateUI()),
			error(xhr,textStatus,err){
				console.log(xhr,textStatus,err);
			}
		})
	    },
	    checkItem: function () {
	        this.reCalc();
	        this.updateUI();
	    },
	    delItem: function (i) {
	        event.target.parentElement.parentElement.parentElement.remove();
	        $.ajax({
				url: "<%= request.getContextPath() %>/cart/deleteOneItem",
				data: {'cartNo':i},
				dataType: "text",
				success:(this.reCalc(),this.updateUI()),
				error(xhr,textStatus,err){
					console.log(xhr,textStatus,err);
				}
			})
	    }
}

Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};
</script>
<%
List<Cart> list = (List<Cart>)request.getAttribute("list");
%>
<h2 class="cartheaderh2">장바구니</h2>
<%
	if(list.isEmpty()){
%>
		<h1 class="cartisEmpty">텅</h1>
        <h3 class="cartisEmptyh3">장바구니가 비어있습니다.</h3>
<%
	}else{
%>
    <form name="orderform" id="orderform" method="post" class="orderform" action="<%= request.getContextPath() %>/order/cartOrder">
    
            <input type="hidden" name="cmd" value="order">
            <div class="basketdiv" id="basket">
                <div class="row head">
                    <div class="subdiv">
                        <div class="check">선택</div>
                        <div class="img">이미지</div>
                        <div class="pname">상품명</div>
                    </div>
                    <div class="subdiv">
                        <div class="basketprice">가격</div>
                        <div class="num">수량</div>
                        <div class="sum">합계</div>
                    </div>
                    <div class="subdiv">
    
                        <div class="basketcmd">삭제</div>
                    </div>
                    <div class="split"></div>
                </div>
             
	<%
	int sum = 0;
	int sumcount = 0;
	int i = 1;
		for(Cart _cart : list){
			sum += (_cart.getProductPrice() * _cart.getProductQuantity());
			sumcount += _cart.getProductQuantity();
	%>
	<div class="row data" <% if(i%2 != 0){%>style="background-color:#f4f4f4;"<% } %>>
                    <div class="subdiv">
                        <div class="check"><input type="checkbox" name="buy" value="260" checked onclick="javascript:basket.checkItem(<%= i %>);">&nbsp;</div>
                    	<input type="hidden" value="<%= _cart.getCartNo() %>" name="cartNo<%= i %>"/>
                        <div class="img"><img src="<%= request.getContextPath() %>/upload/product/<%= _cart.getRenamedFilename() %>" width="60"></div>
                        <div class="pname"><span><%= _cart.getProductName() %></span></div>
                    </div>
                    <div class="subdiv">
                        <div class="basketprice"><input type="hidden" name="p_price" id="p_price<%= i %>" class="p_price" value="<%= _cart.getProductPrice() %>"><%= _cart.getProductPrice() %>원</div>
                        <div class="num">
                            <div class="updown<%= i %>">
                                <input type="text" name="p_num<%= i %>" id="p_num<%= i %>" size="2" maxlength="4" class="p_num" value="<%= _cart.getProductQuantity() %>" onkeyup="javascript:basket.changePNum(<%= i %>);">
                                <span onclick="javascript:basket.changePNum(<%= i %>);"><i class="fas fa-arrow-alt-circle-up up"></i></span>
                                <span onclick="javascript:basket.changePNum(<%= i %>);"><i class="fas fa-arrow-alt-circle-down down"></i></span>
                            </div>
                        </div>
                        <div class="sum"><%= _cart.getProductPrice() * _cart.getProductQuantity() %>원</div>
                    </div>
                    <div class="subdiv">
                        <div class="basketcmd"><a href="javascript:void(0)" class="abutton" onclick="javascript:basket.delItem(<%= _cart.getCartNo() %>);">삭제</a></div>
                    </div>
                </div>
	<% i++;} %>
            <div class="right-align basketrowcmd">
            <p>&nbsp;</p>
                <a href="javascript:void(0)" class="abutton" onclick="javascript:basket.delAllItem();">장바구니비우기</a>
            </div>
            <div class="bigtext right-align sumcount" id="sum_p_num">상품갯수: <%= sumcount %>개</div>
            <div class="bigtext right-align box blue summoney" id="sum_p_price">합계금액: <%= sum %>원</div>
            <div id="goorder" class="">
	        <div class="clear"></div>
                <p class="cartInfo">선택 체크박스는 금액 계산용 입니다.</p>
                <div class="buttongroup center-align cmd">
                <button type="button" id="btnProductList" class="cmd" onclick="location.href='<%= request.getContextPath() %>/product/productList';">상품목록</button>
                <button type="submit">전체 상품 주문</button>
	        </div>
    	</div>
    	</div>
        </form>
<% } %>



<%@ include file="/WEB-INF/views/common/footer.jsp" %>