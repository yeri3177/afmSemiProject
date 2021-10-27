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
		//1. 인코딩처리
		request.setCharacterEncoding("UTF-8");
		
		// 2. 사용자입력값처리
		String userId = request.getParameter("userId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String saveId = request.getParameter("saveId"); 
		System.out.println("userId@servlet = " + userId);
		System.out.println("password@servlet = " + password);
		System.out.println("saveId@servlet = " + saveId);
		
		// 3. 업무로직 실행 
		// a. db에서 memberId와 일치하는 회원 조회
		User user = userService.selectOneUser(userId);
		System.out.println("user@servlet = " + user);
		
		HttpSession session = request.getSession();
		System.out.println(session.getId());

		// b. 리턴된 회원객체에서 비밀번호 일치여부 검사
		if(user != null && password.equals(user.getPassword())) {
			// 로그인 성공
			// session객체에 로그인 정보 기록
			session.setAttribute("loginUser", user);
			session.setAttribute("msg", "로그인 성공했습니다.");
			
			// session유효시간
			// session.setMaxInactiveInterval(60); // 60초
			
			// 아이디저장 #saveId 처리
			// 응답메세지에 Set-cookie헤더값으로 전송
			Cookie cookie = new Cookie("saveId", userId);
			cookie.setPath(request.getContextPath()); // /mvc로 시작하는 경로에 사용할 쿠키
			if(saveId != null) {
				// 체크한 경우
				cookie.setMaxAge(7 * 24 * 60 * 60); // 7일을 초로 지정 
			}
			else {
				// 체크하지 않은 경우
				cookie.setMaxAge(0); // 즉시 삭제
			}
			response.addCookie(cookie);
		}
		else {
			// 로그인 실패
			session.setAttribute("msg", "로그인 실패했습니다.");	
		}
		
		// 4. 응답메세지 작성 : redirect 302
		// 지정한 location주소로 다시 요청할것을 브라우져에게 명시
//		String location = request.getContextPath() + "/"; // /mvc/
		String location = request.getHeader("Referer"); // http://localhost:9090/mvc/board/boardView?no=201
		response.sendRedirect(location);
	}
}
