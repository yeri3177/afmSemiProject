<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/footer.css"/>
</body>
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



</html>