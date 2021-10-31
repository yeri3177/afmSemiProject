package com.kh.afm.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductOrderServlet
 */
@WebServlet("/product/productOrder")
public class ProductOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 처리
		int productNo = (int)request.getAttribute("productNo");
		String productRenamedFilename = (String)request.getAttribute("productRenamedFilename");
		String productPrice = (String)request.getAttribute("productPrice");
		String productQuantity = (String)request.getAttribute("productQuantity");
		
		// 2. 응답 처리
		request
			.getRequestDispatcher("/WEB-INF/views/order/Order.jsp")
			.forward(request, response);
	}

}
