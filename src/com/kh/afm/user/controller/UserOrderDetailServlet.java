package com.kh.afm.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.order.model.vo.Order;
import com.kh.afm.user.model.service.UserOrderService;
import com.kh.afm.user.model.vo.User;
import com.kh.afm.user.model.vo.UserOrderDetail;

/**
 * 구매내역페이지
 */
@WebServlet("/user/userorder")
public class UserOrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserOrderService userOrderService = new UserOrderService();

 
	/**
	 *  일반회원 구매내역페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/**
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession(false);
		User loginUser = (User)session.getAttribute("loginUser");
		String userId = loginUser.getUserId();
		
		List<Order> list = userOrderService.userOrder(userId);
		
		UserOrderDetail userorderdetail = new UserOrderDetail();
		
		
	}



}
