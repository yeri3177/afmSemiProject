package com.kh.afm.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.user.model.service.UserService;
import com.kh.afm.user.model.vo.User;

/**
 * Servlet implementation class UserUpdatePasswordServlet
 */
@WebServlet("/user/updatePassword")
public class UserUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/user/updatePassword.jsp")
		   .forward(request, response);
	}

	/**
	 * POST /user/updatePassword
	 * db정보 update
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String location = request.getContextPath();
		String msg = null;
		int result = 0;
		
		//1. 사용자 입력값 처리 : 기존비밀번호/신규비밀번호 암호화처리 필수
		String oldPassword = MvcUtils.getEncryptedPassword(request.getParameter("oldPassword"));
		String newPassword = MvcUtils.getEncryptedPassword(request.getParameter("newPassword"));
		
		//2. 기존비밀번호 비교 : session의 loginMember객체 이용할 것
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		if (oldPassword.equals(loginUser.getPassword())) {

			// 3. 업무로직 : 기존비밀번호가 일치한 경우만 신규비밀번호로 업데이트한다.
			loginUser.setPassword(newPassword);
			result = userService.updatePassword(loginUser);
			msg = (result > 0) ? "비밀번호를 성공적으로 변경했습니다." : "비밀번호를 변경에 실패했습니다.";
			location += "/user/userDetail";
		} else {
			msg = "비밀번호가 일치하지 않습니다.";
			location += "/user/updatePassword";
		}
		
		//4. 사용자경고창 및 리다이렉트 처리
		// 기존비밀번호가 일치하지 않았다면, "비밀번호가 일치하지 않습니다." 안내 & /afm/user/updatePassword 리다이렉트
		// 기존비밀번호가 일치하고, 신규비밀번호 변경에 성공했다면, "비밀번호를 성공적으로 변경했습니다." 안내 &
		// /afm/user/userDetail 리다이렉트
		session.setAttribute("msg", msg);
		response.sendRedirect(location);
		
	}

}
