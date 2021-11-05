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
 * Servlet implementation class ProductSelectCategory
 */
@WebServlet("/product/selectCategory")
public class ProductSelectCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pCategory = request.getParameter("pValue");
			String all = "모두보기";
			
			if(pCategory.equals(all)) {
				System.out.println("모두보기");
				System.out.println(pCategory);
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
				String csvStr = productService.selectProductAllCategory(start, end, pCategory);
				System.out.println("csvStr@servlet = " + csvStr);
				
				// b.pagebar영역
				// totalContents, url 준비
				int totalContents = productService.selectTotalContent();
				String url = request.getRequestURI();
				String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
				System.out.println("pagebar@servlet = " + pagebar);
				
				// 3.view단 forwarding
//			request.setAttribute("csvStr", csvStr);
//			request.setAttribute("pagebar", pagebar);
//			request
//			.getRequestDispatcher("/WEB-INF/views/product/productList.jsp")
//			.forward(request, response);
				response.setContentType("text/orderDetailList; charset=utf-8");
				response.getWriter().append(csvStr);
				
				
			}
			else {
				System.out.println("다른거");
				
				System.out.println(pCategory);
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
				String csvStr = productService.selectProductCategory(start, end, pCategory);
				System.out.println("csvStr@servlet = " + csvStr);
				
				// b.pagebar영역
				// totalContents, url 준비
				int totalContents = productService.selectTotalContent();
				String url = request.getRequestURI();
				String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
				System.out.println("pagebar@servlet = " + pagebar);
				
				// 3.view단 forwarding
				response.setContentType("text/orderDetailList; charset=utf-8");
				response.getWriter().append(csvStr);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}


		

}
