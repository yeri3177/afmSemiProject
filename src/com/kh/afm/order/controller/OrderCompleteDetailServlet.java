package com.kh.afm.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.order.model.service.OrderService;
import com.kh.afm.order.model.vo.OrderDetail;

/**
 * 구매내역 (order_detail 테이블 조회)
 */
@WebServlet("/order/orderCompleteDetail")
public class OrderCompleteDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 구매번호 
		int orderNo  = Integer.parseInt(request.getParameter("orderNo"));
		System.out.println("orderNo = " + orderNo);
		
		// 업무로직
		List<OrderDetail> orderDetailList = orderService.selectOrderCompleteDetail(orderNo);
		System.out.println("orderDetailList@servlet = " + orderDetailList);
		
		
		// view단 연결
		request.setAttribute("orderDetailList", orderDetailList);
		request.getRequestDispatcher("/WEB-INF/views/order/OrderCompleteDetail.jsp")				
			.forward(request, response);
		
	}
}
