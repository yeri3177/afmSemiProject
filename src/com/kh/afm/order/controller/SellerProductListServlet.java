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
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.user.model.vo.User;

/**
 * 로그인한 판매자가 판매하는 상품 리스트 조회
 */
@WebServlet("/order/sellerProductList")
public class SellerProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private OrderService orderService = new OrderService ();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인한 세션 정보 
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		System.out.println("userId = " + userId);
		
		// 업무로직
		List<Product> list = orderService.selectSellerProductList(userId);
		System.out.println("list@servlet = " + list);
		
		// view단 처리
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/order/sellerProductList.jsp")				
				.forward(request, response);
		
	}

}
