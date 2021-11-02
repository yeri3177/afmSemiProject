package com.kh.afm.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.order.model.service.OrderService;

/**
 * Servlet implementation class OrderDetailCheckServlet
 */
@WebServlet("/order/orderDetailCheck")
public class OrderDetailCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int orderNo = Integer.parseInt(request.getParameter("orderNo"));
		//List<Order> orderList = orderService.orderCheckList(userId);
		System.out.println(orderNo);
		String csv = orderService.orderDetailCheckList(orderNo);
		System.out.println(csv);
		
		response.setContentType("text/orderDetailList; charset=utf-8");
		response.getWriter().append(csv);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
