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
		// user
		String userId = request.getParameter("userId"); 
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String userRole = request.getParameter("userRole");
		System.out.println("userRole = " + userRole);
		
		// address
		String adrName = request.getParameter("adrName");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		// account
		String bankname = request.getParameter("bankname");
		String banknumer = request.getParameter("banknumer");

		// User 객체 생성
		User user = new User(userId, null, email, null, null, phone, null, null, null);
		System.out.println("user : " + user);

		// Address 객체 생성
		Address address = new Address(0, adrName, address1, address2, userId);
		System.out.println("address : " + address);

		// Account 객체 생성
		Account account = new Account(0, bankname, banknumer, userId);
		System.out.println("account : " + account);
		
		// 업무로직 (insert 처리)
		int result = userService.updateUser(user);
		int result2 = userService.updateAddress(address);
		int result3 = userService.updateAccount(account);
		
		String msg = result > 0 ? "회원정보를 성공적으로 수정했습니다." : "회원정보 수정실패했습니다.";
		System.out.println("result = " + result);
		System.out.println("result2 = " + result2);
		System.out.println("result3 = " + result3);
		
		
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

			
			String location = request.getContextPath() + "/user/userDetail";
			response.sendRedirect(location);
		}
}
