package com.kh.afm.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.admin.model.service.AdminService;

/**
 * 사용자 공개여부 변경 서블릿
 */
@WebServlet("/admin/updateUserExpose")
public class AdminUserExposeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 사용자입력값
		String userId = request.getParameter("userId");
		String userExpose = request.getParameter("userExpose");
		
		
		// 업무로직 
		int result = adminService.updateUserExpose(userId, userExpose);
		
		// 메세지 세션
		String msg = result > 0 ? "사용자 공개여부값 "+userExpose+"로 변경 성공" : "사용자 공개여부 변경 실패";
		request.getSession().setAttribute("msg", msg);
		
		// 리다이렉트
		response.sendRedirect(request.getHeader("Referer"));
	}
}
