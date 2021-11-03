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
		
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println(searchType);
		System.out.println(searchKeyword);
		
		if(searchKeyword.equals("모두보기")) {
			System.out.println("모두보기선택");
			
			// 1.사용자입력값 처리 cPage numPerPage = 10
			int cPage = 1;
			int numPerPage = 10;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch (NumberFormatException e) {
				
			}
			System.out.println("cPage = " + cPage);
			
			// 2.업무로직
			// a.content영역 - paging query
			int start = cPage * numPerPage - (numPerPage -1);
			int end = cPage * numPerPage;
			List<Product> list = productService.selectProductList(start, end);
			System.out.println("list@servlet = " + list);

			// b.pagebar영역
			// totalContents, url 준비
			int totalContents = productService.selectTotalContent(start, end);
			String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
			String url = request.getRequestURI() + queryString;
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
			System.out.println("pagebar@servlet = " + pagebar);
			
			// 3.view단 forwarding
			request.setAttribute("list", list);
			request.setAttribute("pagebar", pagebar);
			request
				.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
				.forward(request, response);
		}
		else {
			System.out.println("그 외 선택");
			
			// 1.사용자입력값 처리 cPage numPerPage = 10
						int cPage = 1;
						int numPerPage = 10;
						try {
							cPage = Integer.parseInt(request.getParameter("cPage"));
						} catch (NumberFormatException e) {
							
						}
						System.out.println("cPage = " + cPage);
						
						// 2.업무로직
						// a.content영역 - paging query
						int start = cPage * numPerPage - (numPerPage -1);
						int end = cPage * numPerPage;
						List<Product> list = productService.selectProductSearchList(start, end, searchKeyword);
						System.out.println("list@servlet = " + list);

						// b.pagebar영역
						// totalContents, url 준비
						int totalContents = productService.selectTotalContent(start, end);
						String queryString = String.format("?searchType=%s&searchKeyword=%s", searchType, searchKeyword);
						String url = request.getRequestURI() + queryString;
						String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
						System.out.println("pagebar@servlet = " + pagebar);
						
						// 3.view단 forwarding
						request.setAttribute("list", list);
						request.setAttribute("pagebar", pagebar);
						request
							.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
							.forward(request, response);
		}
	}

}
