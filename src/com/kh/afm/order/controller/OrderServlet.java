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
import com.kh.afm.order.model.exception.OrderException;
import com.kh.afm.order.model.service.OrderService;
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.order.model.vo.OrderDetail;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order/order")
public class OrderServlet extends HttpServlet {
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
		try {
			request.setCharacterEncoding("utf-8");
			HttpSession session = request.getSession(false);
			User loginUser = (User)session.getAttribute("loginUser");
			String userId = loginUser.getUserId();
			List<Cart> list = orderService.cartOrder(userId);
			int adrNo = Integer.parseInt(request.getParameter("address"));
			int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
			Order order = new Order(userId, 0, null, totalPrice, adrNo);
			int orderNo = orderService.cartOrderInsert(order);
			if(orderNo != 0) {
				int result = 0;
				for(Cart orderList : list) {
					int pNo = orderList.getProductNo();
					int pQuantity = orderList.getProductQuantity();
					int pQuantityPrice = (orderList.getProductPrice()*pQuantity);
					OrderDetail orderDetail = new OrderDetail(0, orderNo, pNo, pQuantity, pQuantityPrice, null);
					result += orderService.cartOrderDetailInsert(orderDetail);
				}
				if(result == list.size()) {
					int resultnum = orderService.orderSuccess(userId);
				}
				String msg = result == list.size() ? "주문 성공!" : "주문 실패!";
				session.setAttribute("msg", msg);
				String location = request.getContextPath() + "/order/orderCheck";
				response.sendRedirect(location);
				
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("장바구니 상품 주문 servlet 오류", e);
		}
		
	}

}
