package com.kh.afm.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.order.model.service.OrderService;
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class OrderCheckServlet
 */
@WebServlet("/order/orderCheck")
public class OrderCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		List<Order> orderList = orderService.orderCheckList(userId);
//		List<OrderDetail> orderDetailList = orderService.orderDetailCheckList(userId);
		System.out.println(orderList);
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/WEB-INF/views/order/orderCheck.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
