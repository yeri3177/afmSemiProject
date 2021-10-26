package com.kh.afm.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 관리자 - 상품목록 페이지 
 */
@WebServlet("/admin/productList")
public class AdminProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request
			.getRequestDispatcher("/WEB-INF/views/admin/productList.jsp")
			.forward(request, response);
		
	}
}
