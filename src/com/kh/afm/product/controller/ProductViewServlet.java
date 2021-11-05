package com.kh.afm.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.product.model.vo.ProductComment;

/**
 * 상품게시판 글 상세보기
 */
@WebServlet("/product/productView")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int no = Integer.parseInt(request.getParameter("pNo"));
			
			//게시글 가져오기
			Product product = productService.selectOneProduct(no);
			
			//게시글 가져오기에 실패시
			if(product == null) {
				request.getSession().setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
				response.sendRedirect(request.getContextPath() + "/product/productList");
				return;
			};
			
			// XSS공격대비 
			// cross-site script공격. 악성코드를 웹페이지삽입하여 클라이언트의 개인정보탈취하는 공격법
			String content = MvcUtils.escapeHtml(product.getpContent());
			
			// 개행문자 br태그 변환처리
			content = MvcUtils.convertLineFeedToBr(content);
			product.setpContent(content);
			
			// 댓글목록 가져오기
			List<ProductComment> commentList = productService.selectCommentList(no);
			System.out.println("commentList@servlet = " + commentList);
			
			//view단 처리위임
			request.setAttribute("product", product);
			request.setAttribute("commentList", commentList);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/product/productView.jsp");
			reqDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
