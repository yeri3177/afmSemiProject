package com.kh.afm.user.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.user.model.service.UserService;
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
		
		// 2.사용자입력값 -> User객체
		String userId = request.getParameter("userId");
		//String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthday = request.getParameter("birthday");

		//생일 월 1자리이면 -> 2자리
		if (birthmonth.length() == 1) {
			birthmonth = "0" + birthmonth;
		}
		// 생일 일 1자리이면 -> 2자리
		if (birthday.length() == 1) {
			birthday = "0" + birthday;
		}

		// 생일값 3개 -> 1개
		String s_birth = birthyear + "-" + birthmonth + "-" + birthday; // 생일 8자리

		// 생일 String타입 -> Date타입
		Date d_birth = null;
		if (s_birth != null && !"".equals(s_birth)) {
			d_birth = Date.valueOf(s_birth);
		}

		// User 객체 생성
		User user = new User(userId, userName, email, null, d_birth, phone, null, UserService.USER_ROLE, null);
		System.out.println("user : " + user);
//
//		// Address 객체 생성
//		Address address = new Address(0, "기본주소", address1, address2, userId);
//		System.out.println("address : " + address);
//
//		// Account 객체 생성
//		Account tb_account = new Account(0, banknum, bankname, userId);
//		System.out.println("account : " + tb_account);
		
		// 업무로직 (insert 처리)
		int result = userService.updateUser(user);
		String msg = result > 0 ? "회원정보를 성공적으로 수정했습니다." : "회원정보 수정실패했습니다.";
		
		// 성공적으로 회원정보를 수정했다면, session의 속성 loginMember객체도 갱신해야 한다.
		if (result > 0) {
			HttpSession session = request.getSession();
			User newUser = userService.selectOneUser(userId);
			session.setAttribute("loginUser", newUser);
			System.out.println("userUpdateSession");
		}
			
			// 4.응답처리 msg속성 저장후 /afm로 redirect할 것.
			HttpSession session = request.getSession();
			session.setAttribute("msg", msg);

			
			String location = request.getContextPath() + "/";
			response.sendRedirect(location);
		}
}
