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
 * Servlet implementation class ProductFinderServlet
 */
@WebServlet("/product/productFinder")
public class ProductFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 검색유형, 검색키워드
			String searchType = request.getParameter("searchType");
			String searchKeyword = request.getParameter("searchKeyword");
			
			if(searchKeyword.equals("모두보기")) {
				
				// 1.사용자입력값 처리 cPage numPerPage = 10
				int cPage = 1;
				int numPerPage = 7;
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));
				} catch (NumberFormatException e) {
					
				}
				
				// 2.업무로직
				// a.content영역 - paging query
				int start = cPage * numPerPage - (numPerPage -1);
				int end = cPage * numPerPage;
				List<Product> list = productService.selectProductList(start, end);
				System.out.println("list@servlet = " + list);

				// b.pagebar영역
				int totalContents = productService.selectTotalContent();
				String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
				String url = request.getRequestURI() + queryString;
				String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
				
				// 3.view단 forwarding
				request.setAttribute("list", list);
				request.setAttribute("pagebar", pagebar);
				request
					.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
					.forward(request, response);
			}
			else {
				
				// 페이징처리
				int cPage = 1;
				int numPerPage = 7;
				try {
					cPage = Integer.parseInt(request.getParameter("cPage"));
				} catch (NumberFormatException e) {
					
				}
				
				int start = cPage * numPerPage - (numPerPage -1);
				int end = cPage * numPerPage;
				
				// 업무로직 (검색 리스트)
				List<Product> list = productService.selectProductSearchList(start, end, searchKeyword);

				// 페이징 - 검색된수
				System.out.println("searchKeyword@servlet = " + searchKeyword);
				int totalContents = productService.searchProductCount(searchKeyword);
				
				// 검색
				String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
				String url = request.getRequestURI() + queryString;
				String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
				
				// view단 forwarding
				request.setAttribute("list", list);
				request.setAttribute("pagebar", pagebar);
				request
					.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
					.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
