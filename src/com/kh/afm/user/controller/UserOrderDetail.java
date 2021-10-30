package com.kh.afm.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.user.model.service.UserService;

/**
 * Servlet implementation class UserOrderDetail
 */
@WebServlet("/UserOrderDetail")
public class UserOrderDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

 
	/**
	 *  일반회원 구매내역페이지
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}



}
