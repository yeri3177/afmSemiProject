package com.kh.afm.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.service.CartService;

/**
 * Servlet implementation class CartUpdateServlet
 */
@WebServlet("/cart/cartUpdate")
public class CartUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		String cartNo = request.getParameter("cartNo");
		String productQuantity = request.getParameter("productQuantity");
		
		int result = cartService.cartUpdate(cartNo, productQuantity);
		

		if(result>0) {
			session.setAttribute("msg", "성공적으로 장바구니를 수정했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		}
		else {
			session.setAttribute("msg", "장바구니 수정에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
