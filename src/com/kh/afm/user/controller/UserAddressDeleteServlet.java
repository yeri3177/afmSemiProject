package com.kh.afm.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.user.model.service.UserService;

/**
 * 주소삭제 서블릿
 */
@WebServlet("/user/addressDelete")
public class UserAddressDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자 입력값 처리
		int addressNo = Integer.parseInt(request.getParameter("addressNo")); 

		// 2.서비스로직호출
		int result = userService.deleteAddress(addressNo);
		
		// 3.응답처리
		String msg = (result > 0) ? "주소 삭제 성공" : "주소 삭제 실패";
		System.out.println("result_user = " + result);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath() + "/user/userAddressList");
	}
}
