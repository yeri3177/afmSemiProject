package com.kh.afm.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;
import com.kh.afm.user.model.vo.User;

/**
 * 관리자페이지 - 사용자관리 - 회원 목록 
 */
@WebServlet("/admin/userList")
public class AdminUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 어드민서비스 객체
	private AdminService adminService = new AdminService(); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 페이징처리

		
		// 업무로직
		List<User> list = adminService.selectAllUser();
		System.out.println("list@adminUserListServlet = " + list);
		
		// 페이징영역
		
		// 뷰단 처리
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/userList.jsp")
			.forward(request, response);	
		
		
		
	}
}
