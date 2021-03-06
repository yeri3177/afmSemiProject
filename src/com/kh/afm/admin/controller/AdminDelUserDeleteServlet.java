package com.kh.afm.admin.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;

/**
 * 탈퇴회원 데이터 삭제하기 
 */
@WebServlet("/admin/deleteDelUser")
public class AdminDelUserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// encoding처리
			request.setCharacterEncoding("UTF-8");
			
			// 사용자입력값 처리 
			String[] userId_arr = request.getParameterValues("userId");
			System.out.println("userId = " + Arrays.toString(userId_arr));
			
			// 업무로직
			int result = adminService.deleteDelUser(userId_arr);
			
			// 메세지 세션
			String msg = result > 0 ? "탈퇴회원 데이터 "+result+"개 삭제 성공" : "탈퇴회원 데이터 삭제 실패";
			request.getSession().setAttribute("msg", msg);
			
			// 리다이렉트
			response.sendRedirect(request.getContextPath() + "/admin/delUserList");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}	
}
