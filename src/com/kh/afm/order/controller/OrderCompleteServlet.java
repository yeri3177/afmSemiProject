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
import com.kh.afm.order.model.vo.OrderDetail;
import com.kh.afm.user.model.vo.User;

/**
 * 구매내역 (tb_order 테이블 조회)
 */
@WebServlet("/order/orderComplete")
public class OrderCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인한 세션 정보 
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		System.out.println("userId = " + userId);
		
		// 업무로직
		List<Order> orderList = orderService.selectOrderComplete(userId);
		System.out.println("orderList@servlet = " + orderList);
		
		// view단 처리
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/WEB-INF/views/order/orderComplete.jsp")				
				.forward(request, response);
	}
}
