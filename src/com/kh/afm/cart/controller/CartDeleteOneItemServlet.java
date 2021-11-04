package com.kh.afm.cart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.exception.CartException;
import com.kh.afm.cart.model.service.CartService;

/**
 * Servlet implementation class CartDeleteOneItemServlet
 */
@WebServlet("/cart/deleteOneItem")
public class CartDeleteOneItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			HttpSession session = request.getSession(false);
			String cartNo = request.getParameter("cartNo");
			
			int result = cartService.deleteOneItem(cartNo);
			

			if(result>0) {
				session.setAttribute("msg", "성공적으로 삭제했습니다.");
				response.sendRedirect(request.getContextPath() + "/");
			}
			else {
				session.setAttribute("msg", "삭제에 실패했습니다.");
				response.sendRedirect(request.getContextPath() + "/");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 상품삭제 servlet 오류", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
