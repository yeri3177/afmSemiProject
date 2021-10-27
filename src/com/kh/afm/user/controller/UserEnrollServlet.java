package com.kh.afm.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.user.model.service.UserService;

/**
 * 회원가입 기능
 */
@WebServlet("/user/userEnroll")
public class UserEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
       

	/**
	 * 회원가입폼 페이지 html 요청!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view단 연결
		request
			.getRequestDispatcher("/WEB-INF/views/user/userEnroll.jsp")
			.forward(request, response);
		
	}

	/**
	 * 회원가입 요청! : database insert
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
