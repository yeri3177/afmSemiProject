package com.kh.afm.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.Product;

/**
 * 상품 목록 조회하기
 */
@WebServlet("/product/productList")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {			
			
			// 페이징 처리
			int cPage = 1;
			int numPerPage = 7;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				
			}
			
			int start = cPage * numPerPage - (numPerPage -1);
			int end = cPage * numPerPage;
			
			// pagebar 영역
			int totalContents = productService.selectTotalContent();
			String url = request.getRequestURI();
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
			
			// 업무로직
			List<Product> list = productService.selectProductList(start, end);
			
			// view단 forwarding
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request
				.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
