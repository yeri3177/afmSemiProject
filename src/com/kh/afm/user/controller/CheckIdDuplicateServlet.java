package com.kh.afm.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.user.model.service.UserService;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class CheckIdDuplicateServlet
 */
@WebServlet("/user/checkIdDuplicate")
public class CheckIdDuplicateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.encoding
		request.setCharacterEncoding("utf-8");
		
		// 2.사용자입력값처리
		String userId = request.getParameter("userId");
		System.out.println("userId@servlet = " + userId);
		
		// 3.업무로직
		User user = userService.selectOneUser(userId);
		boolean available = user == null;
		System.out.println("available@servlet = " + available);
		
		// 4.view단처리
		request.setAttribute("available", available);
		request
			.getRequestDispatcher("/WEB-INF/views/user/checkIdDuplicate.jsp")
			.forward(request, response);
	}

}
