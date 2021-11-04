package com.kh.afm.cart.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.cart.model.exception.CartException;
import com.kh.afm.cart.model.service.CartService;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class CartDeleteAllServlet
 */
@WebServlet("/cart/cartDeleteAll")
public class CartDeleteAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartService cartService = new CartService();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
				session.setAttribute("msg", "장바구니 삭제에 실패했습니다.");
				response.sendRedirect(request.getContextPath() + "/");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 전체삭제 servlet 오류", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 전체삭제 servlet 오류", e);
		}
	}

}
