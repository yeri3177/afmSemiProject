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
 * 해당상품의 결제내역 리스트 상세조회 
 */
@WebServlet("/order/sellerOrderDetailList")
public class SellerOrderDetailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품번호
		int productNo  = Integer.parseInt(request.getParameter("productNo"));
		System.out.println("productNo = " + productNo);
		
		// 업무로직
		List<OrderDetail> list = orderService.selectSellerOrderDetailList(productNo);
		System.out.println("list@servlet = " + list);
		
		
		// view단 연결
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/order/sellerOrderDetailList.jsp")				
			.forward(request, response);
	}

}
