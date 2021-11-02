package com.kh.afm.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.service.CartService;
import com.kh.afm.cart.model.vo.Cart;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class CartInsert
 */
@WebServlet("/cart/cartInsert")
public class CartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	private Cart cart = new Cart();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));
		
		cart = new Cart(userId, 0, productNo, null,null, 0, productQuantity);
		
		int result = cartService.cartInsert(userId, cart);
		

		if(result>0) {
			session.setAttribute("msg", "성공적으로 장바구니애 담았습니다.");
			response.sendRedirect(request.getContextPath() + "/product/productList");
		}
		else {
			session.setAttribute("msg", "장바구니 담기에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/product/productList");
		}
	}

}
