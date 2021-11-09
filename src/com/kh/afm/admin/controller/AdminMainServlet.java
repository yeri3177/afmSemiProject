package com.kh.afm.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;

/**
 * 관리자 메인 페이지 
 */
@WebServlet("/admin/adminMain")
public class AdminMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 간략 4개 검색
			Map<String, Integer> summary = new HashMap<>();
			
			// 월별 상품수
			int[] productsMonthCnt = new int[12];
			
			
			// 업무로직
			summary = adminService.adminMainQuery(summary);
			productsMonthCnt = adminService.adminStatistics(productsMonthCnt);
			
			
			// view단 연결 
			request.setAttribute("summary", summary);
			request.setAttribute("productsMonthCnt", productsMonthCnt);
			request
				.getRequestDispatcher("/WEB-INF/views/admin/adminMain.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}	
	}
}