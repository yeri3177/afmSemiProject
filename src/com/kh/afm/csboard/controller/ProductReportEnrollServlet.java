package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.product.model.vo.Report;

/**
 * Servlet implementation class ProductReportEnrollServlet
 */
@WebServlet("/csboard/productReportEnroll")
public class ProductReportEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CsboardService csboardService = new CsboardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자 입력값 처리
		String title = request.getParameter("title");
		String userId = request.getParameter("user_id");
		String reportType = request.getParameter("reportType");
		String content = request.getParameter("content");
		String reportStatus = request.getParameter("reportStatus");
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		System.out.println(productNo);
		Report report = new Report(0, title, reportType, content, userId, null, reportStatus, productNo);
		
		System.out.println("ProductReportEnrollServlet = " + report);
		
		// 2. 업무로직
		int result = csboardService.insertReport(report);
		String msg = result > 0 ? "신고하기 등록 성공!" : "신고하기 등록 실패!";
		
		// 3. 응답처리 : redirect
		
		String location = request.getContextPath() + "/product/productView?pNo=" + productNo;
		response.sendRedirect(location);
		
		
		
		
	}

}
