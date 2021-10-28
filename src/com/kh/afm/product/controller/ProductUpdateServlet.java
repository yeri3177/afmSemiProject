package com.kh.afm.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.Product;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/product/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		int no = Integer.parseInt(request.getParameter("pNo"));
		
		// 2. 업무로직
		Product product = productService.selectOneProduct(no);
		System.out.println("product@service = " + product);
		
		// 3. view단 위임
		request.setAttribute("product", product);
		request
			.getRequestDispatcher("/WEB-INF/views/product/productUpdate.jsp")
			.forward(request, response);
		
	}

}
