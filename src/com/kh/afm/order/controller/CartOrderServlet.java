package com.kh.afm.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.vo.Cart;
import com.kh.afm.order.model.service.OrderService;
import com.kh.afm.order.model.vo.OrderAddress;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class CartOrderServlet
 */
@WebServlet("/order/cartOrder")
public class CartOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		List<Cart> list = orderService.cartOrder(userId);
		List<OrderAddress> adrList = orderService.adrList(userId);
		
		request.setAttribute("list", list);
		request.setAttribute("adrList", adrList);
		request.getRequestDispatcher("/WEB-INF/views/order/cartOrder.jsp").forward(request, response);
	}

}
