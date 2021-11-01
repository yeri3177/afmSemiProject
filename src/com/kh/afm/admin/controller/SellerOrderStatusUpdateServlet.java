package com.kh.afm.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.order.model.service.OrderService;

/**
 * 판매자의 결제처리 확인 처리
 */
@WebServlet("/order/updateOrderStatus")
public class SellerOrderStatusUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 2.사용자입력값
		int orderDetailNo = Integer.parseInt(request.getParameter("orderDetailNo"));
		String orderStatus = request.getParameter("orderStatus");
		
		// 3.업무로직 
		int result = orderService.updateOrderStatus(orderDetailNo, orderStatus);
		
		// 4.사용자피드백 메세지   
		String msg = result > 0 ? "결제처리상태 변경에 성공 했습니다." : "결제처리상태 변경에 실패하였습니다.";
		request.getSession().setAttribute("msg", msg);
		
		// 5. 리다이렉트
		String location = request.getHeader("Referer");
		response.sendRedirect(location);
	}
}
