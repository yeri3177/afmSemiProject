<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
footer{width:100%;height: 10%;}
.footer-container{background-color:rgb(252, 242, 207);width:100%;height: 10%}
.footer-company-info{display:flex;width:100%;}
.footer-logo{width:30%;display:flex;align-items: center;margin:0 auto;justify-content: space-evenly;}
.footer-info{width:36%;text-align:left;font-size:13px;}
.footer-external-link{width:34%;display:flex;justify-content: space-evenly;align-items: center;}
.footer-info p{margin:5px;}
.footer-logo-img{height:100px;}
.footer-logofont-img{height:50px;vertical-align:middle;padding-top:10%;}
.footer-nav{background-color:rgb(252, 242, 207);padding:0px;margin:auto;display:flex;bottom:17%;left:0;width:100%;}
.footer-nav div{width: 100%;height: 10%;float: left;text-align: center;border-left: 1px black solid;}
.footer-nav div:first-of-type{border-left:none;}
.footer-nav div a {display: block;padding:.4em;text-decoration: none;color: black;font-weight: bold;}
.footer-nav div:hover{background-color:#00C652;}
.footer-nav div:hover > a{color:#FFCD12;}
.external-link-img{height: 40px;}
.footer-logo-link{display:flex;}
</style>
    <footer>
      <div class="footer-container">
      	<div class="footer-nav">
      		<div><a href="">회사소개</a></div>
      		<div><a href="">이용약관</a></div>
      		<div><a href="">고객센터</a></div>
      	</div>
      	<div class="footer-company-info">
	      	<div class="footer-logo">
	      		<a class="footer-logo-link" href="">
		      		<img class="footer-logo-img" src="<%= request.getContextPath() %>/images/common/logo.png" alt="" />
		      		<img class="footer-logofont-img" src="<%= request.getContextPath() %>/images/common/logofont.png" alt="" />
	      		</a>
	      	</div>
	      	<div class="footer-info">
				<p class="company"><strong>(주) afm</strong>		사업자등록번호 : 000-00-00000</p>
				<p class="companyRegistrationNumber">주소 : 서울시 강남구 테헤란로</p>
				<p class="companyAddress">고객센터 : 1000-0000</p>
				<p class="companytel">Copyright &copy; 2021 afm Co. Ltd.</p>
			</div>
			<div class="footer-external-link">
				<a href=""><img class="external-link-img" src="<%= request.getContextPath() %>/images/common/youtube.png" alt="" /></a>
				<a href=""><img class="external-link-img" src="<%= request.getContextPath() %>/images/common/Instagram.jpg" alt="" /></a>
				<a href=""><img class="external-link-img" src="<%= request.getContextPath() %>/images/common/twitter.png" alt="" /></a>
				<a href=""><img class="external-link-img" src="<%= request.getContextPath() %>/images/common/facebook.png" alt="" /></a>
				<a href=""><img class="external-link-img" src="<%= request.getContextPath() %>/images/common/github.png" alt="" /></a>
			</div>
		</div>
      </div>
    </footer>


</body>
</html>