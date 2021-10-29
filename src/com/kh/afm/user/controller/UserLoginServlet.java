package com.kh.afm.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.user.model.service.UserService;
import com.kh.afm.user.model.vo.User;

@WebServlet("/user/userLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	//로그인페이지요청
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view단 연결
		request
			.getRequestDispatcher("/WEB-INF/views/user/userLogin.jsp")
			.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. encoding처리 
		request.setCharacterEncoding("utf-8");
		
		// 2. 사용자입력값
		String userId = request.getParameter("userId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String saveId = request.getParameter("saveId");
		
		// 3. 업무로직 실행
		// a. db에서 memberId와 일치하는 회원 조회 
		User user = userService.selectOneUser(userId);	
		HttpSession session = request.getSession();
		
		// b. 리턴된 회원객체에서 비밀번호 일치여부 검사 
		if(user != null && password.equals(user.getPassword())) {
			
			// 로그인 성공 session객체에 로그인 정보 기록
			session.setAttribute("loginUser", user);
			session.setAttribute("msg", "로그인 성공했습니다.");
			
			// 아이디저장 쿠키
			Cookie cookie = new Cookie("saveId", userId);
			cookie.setPath(request.getContextPath()); 
			if(saveId != null) {
				// 체크한 경우
				cookie.setMaxAge(7*24*60*60); // 7일
			}
			else {
				// 체크하지 않은 경우
				cookie.setMaxAge(0); // 즉시삭제 
			}
			response.addCookie(cookie);
		} 
		else {
			// 로그인 실패 
			request.setAttribute("msg", "로그인 실패했습니다.");
		}
		
		// 4. 응답메세지 작성 :redirect302
		String location = request.getContextPath() + "/";
		response.sendRedirect(location);
	}
}
