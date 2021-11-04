package com.kh.afm.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.order.model.service.OrderService;
import com.kh.afm.order.model.vo.OrderAddress;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class ProductOrderServlet
 */
@WebServlet("/product/productOrder")
public class ProductOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(false);
			User loginUser = (User)session.getAttribute("loginUser");
			String userId = loginUser.getUserId();
			
			// 1. 사용자 입력값 처리
			int productNo = Integer.parseInt(request.getParameter("productNo"));
			String productName = orderService.productNameCheck(productNo);
			String productRenamedFilename = request.getParameter("productRenamedFilename");
			int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
			List<OrderAddress> adrList = orderService.adrList(userId);
			// 2. 응답 처리
			request.setAttribute("adrList", adrList);
			request.setAttribute("productName", productName);
			request.setAttribute("productNo", productNo);
			request.setAttribute("productRenamedFilename", productRenamedFilename);
			request.setAttribute("productPrice", productPrice);
			request.setAttribute("productQuantity", productQuantity);
			request
				.getRequestDispatcher("/WEB-INF/views/order/Order.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
