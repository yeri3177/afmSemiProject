package com.kh.afm.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 회원 유형 페이지
 */
@WebServlet("/user/userTypeEnroll")
public class UserEnrollTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//view단 연결 
		request
			.getRequestDispatcher("/WEB-INF/views/user/userTypeEnroll.jsp")
			.forward(request, response);
	}
}
