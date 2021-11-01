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
import com.kh.afm.product.model.vo.Product;

/**
 * 해당상품의 결제내역 리스트 상세조회 
 * - productNo(상품번호) -> orderDetail 테이블 조회
 * - productNo(상품번호) -> porudct 테이블 조회 (상품명 가져오기)
 */
@WebServlet("/order/sellerOrderDetailList")
public class SellerOrderDetailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 상품번호
		int productNo  = Integer.parseInt(request.getParameter("productNo"));
		
		// 업무로직
		// order_detail 테이블 조회
		List<OrderDetail> orderDetailList = orderService.selectSellerOrderDetailList(productNo);
		
		// product 테이블 조회
		//List<Product> productList = orderService.selectProductTitleList(productNo);
		
		
		// view단 연결
		request.setAttribute("orderDetailList", orderDetailList);
		//request.setAttribute("productList", productList);
		request.getRequestDispatcher("/WEB-INF/views/order/sellerOrderDetailList.jsp")				
			.forward(request, response);
	}

}
