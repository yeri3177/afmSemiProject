package com.kh.afm.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;

/**
 * 신고내역페이지 - 상품노출수정칸 변경
 */
@WebServlet("/admin/processReport")
public class AdminProcessReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// encoding처리
			request.setCharacterEncoding("UTF-8");
			
			// 사용자입력값
			int reportNo = Integer.parseInt(request.getParameter("reportNo"));
			int pNo = Integer.parseInt(request.getParameter("pNo"));
			String pExpose = request.getParameter("pExpose");
			String pExpose_kr = "Y".equals(pExpose) ? "노출" : "비공개";
			
			System.out.println("reportNo = " + reportNo);
			System.out.println("pNo = " + pNo);
			System.out.println("pExpose = " + pExpose);
			
			
			// 업무로직 
				// product_report에서 report_status값 수정(N -> Y)
			int result1 = adminService.processReport(reportNo);
			System.out.println("result1 = " + result1);
				// product에서 p_expose값 수정 (AdminProductExposeUpdateServlet의 업무로직 재사용)
			int result2 = adminService.updateProductExpose(pNo, pExpose);
			System.out.println("result2 = " + result2);
			
			// 메세지 세션
			String msg = (result1 > 0 && result2 > 0)? 
					"상품신고처리완료! 상품노출값을 ["+pExpose_kr+"]로 변경 성공" : "상품 신고처리 프로세스 실패";
			request.getSession().setAttribute("msg", msg);
			
			// 리다이렉트
			response.sendRedirect(request.getHeader("Referer"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
