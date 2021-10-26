package com.kh.afm.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.service.CartService;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class CartDeleteAllServlet
 */
@WebServlet("/cart/cartDeleteAll")
public class CartDeleteAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		int result = cartService.deleteAllCart(userId);
		

		if(result>0) {
			session.setAttribute("msg", "성공적으로 장바구니를 삭제했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		}
		else {
			session.setAttribute("msg", "장바구니삭제에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

}