package com.kh.afm.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;

/**
 * 상품 노출여부 변경 서블릿
 */
@WebServlet("/admin/updateProductExpose")
public class AdminProductExposeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 사용자입력값
		String pNo = request.getParameter("pNo");
		String pExpose = request.getParameter("pExpose");
		String pExpose_kr = "Y".equals(pExpose) ? "노출" : "비공개";
		
		// 업무로직 
		int result = adminService.updateProductExpose(pNo, pExpose);
		System.out.println("상품노출여부result = " + result);
		
		// 메세지 세션
		String msg = result > 0 ? "상품노출여부값 ["+pExpose_kr+"]로 변경 성공" : "상품노출여부값 변경 실패";
		request.getSession().setAttribute("msg", msg);
		
		// 리다이렉트
		response.sendRedirect(request.getHeader("Referer"));
	}
}
