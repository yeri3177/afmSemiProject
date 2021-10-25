package com.kh.afm.cart.controller;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class CartListServlet
 */
@WebServlet("/cart/cartList")
public class CartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		List<Cart> list = cartService.selectAllList(userId);
	}

}