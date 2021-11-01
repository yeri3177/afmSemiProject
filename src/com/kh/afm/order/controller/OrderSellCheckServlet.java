package com.kh.afm.order.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.order.model.service.OrderService;

/**
 * 판매내역 order_status 변경 서블릿
 */
@WebServlet("/order/orderSellCheck")
public class OrderSellCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService = new OrderService ();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// encoding 처리
		request.setCharacterEncoding("UTF-8");
		
		// 사용자입력값
		
	}

}
