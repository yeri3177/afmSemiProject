package com.kh.afm.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.user.model.service.UserService;
import com.kh.afm.user.model.vo.Account;
import com.kh.afm.user.model.vo.Address;
import com.kh.afm.user.model.vo.User;


@WebServlet("/user/userUpdate")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/**
	 * 회원정보 수정
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 2.사용자입력값 
		// user 테이블에 관한 사용자 입력값 받아오기
		String userId = request.getParameter("userId"); 
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String userRole = request.getParameter("userRole");
		System.out.println("userRole = " + userRole);
		
		// User 객체 생성
		User user = new User(userId, null, email, null, null, phone, null, null, null);
		System.out.println("user : " + user);
		
		// 업무로직
		int result_user = userService.updateUser(user);
		
		
		
		//계좌수정 성공여부
		int result_account = 0;
		
		if("S".equals(userRole)) {
			// account 테이블에 관한 사용자 입력값 받아오기 
			String bankname = request.getParameter("bankname");
			String banknumer = request.getParameter("banknumer");
			
			// Account 객체 생성
			Account account = new Account(0, bankname, banknumer, userId);
			System.out.println("account : " + account);
		
			// 업무로직
			result_account = userService.updateAccount(account);
		
		}
		
		
		// 업데이트 성공 메세지
		String msg = (result_user >0 && result_account > 0) ? 
						"회원정보를 성공적으로 수정했습니다." : "회원정보 수정실패했습니다.";
		System.out.println("result_user = " + result_user);
		System.out.println("result_account = " + result_account);
		
		
		
		// 성공적으로 회원정보를 수정했다면, session의 속성 loginMember객체도 갱신해야 한다.
		if (result_user >0 && result_account > 0) {
			HttpSession session = request.getSession();
			User newUser = userService.selectOneUser(userId);
			session.setAttribute("loginUser", newUser);
			System.out.println("userUpdateSession");
		}
			
		// 4.응답처리 msg속성 저장후 /afm로 redirect할 것.
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);

		
		String location = request.getContextPath() + "/user/userDetail";
		response.sendRedirect(location);
	}
}
