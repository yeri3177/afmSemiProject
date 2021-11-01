package com.kh.afm.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.vo.Cart;
import com.kh.afm.order.model.service.OrderService;
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.order.model.vo.OrderDetail;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class OrderProductServlet
 */
@WebServlet("/order/productOrder")
public class OrderProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderService = new OrderService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		int pNo = Integer.parseInt(request.getParameter("ProductNo"));
		int adrNo = Integer.parseInt(request.getParameter("address"));
		int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
		int totalQuantity = Integer.parseInt(request.getParameter("totalQuantity"));
		Order order = new Order(userId, 0, null, totalPrice, adrNo);
		int orderNo = orderService.cartOrderInsert(order);
		if(orderNo != 0) {
			int result = 0;
			OrderDetail orderDetail = new OrderDetail(0, orderNo, pNo, totalQuantity, totalPrice, null);
			result = orderService.cartOrderDetailInsert(orderDetail);
			String msg = result != 0 ? "주문 성공!" : "주문 실패!";
			session.setAttribute("msg", msg);
			String location = request.getContextPath() + "/index.jsp";
			response.sendRedirect(location);
		}
	}

}
