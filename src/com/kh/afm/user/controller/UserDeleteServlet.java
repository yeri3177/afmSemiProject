package com.kh.afm.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.user.model.service.UserService;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/user/userDelete")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();
   
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.사용자 입력값 처리
		String userId = request.getParameter("userId");

		// 2.서비스로직호출
		int result = userService.deleteUser(userId);
		
		// 3.응답처리
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("msg", "성공적으로 회원정보를 삭제했습니다.");
			//location으로 logout페이지 지정
			response.sendRedirect(request.getContextPath() + "/user/logout");
		}
		else {
			session.setAttribute("msg", "회원정보삭제에 실패했습니다.");
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

}
