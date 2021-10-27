package com.kh.afm.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SellerEnrollServlet
 */
@WebServlet("/user/sellerEnroll")
public class SellerEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * 회원가입폼 페이지 html 요청!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view단 연결
		request
			.getRequestDispatcher("/WEB-INF/views/user/sellerEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * 회원가입 요청! : database insert
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
