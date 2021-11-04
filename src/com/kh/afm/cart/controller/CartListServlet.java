package com.kh.afm.cart.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.exception.CartException;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			
			HttpSession session = request.getSession(false);
			User loginUser = (User)session.getAttribute("loginUser");
			String userId = loginUser.getUserId();
			
			List<Cart> list = cartService.selectAllList(userId);
			
			request.setAttribute("list", list);
			request.getRequestDispatcher("/WEB-INF/views/cart/Cart.jsp").forward(request, response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 조회 servlet 오류", e);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 조회 servlet 오류", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 조회 servlet 오류", e);
		}
	}
}
