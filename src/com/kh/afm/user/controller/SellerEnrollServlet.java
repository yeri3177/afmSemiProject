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
import com.kh.afm.user.model.vo.Address;
import com.kh.afm.user.model.vo.User;

/**
 * 회원가입 기능(판매자 회원)
 */
@WebServlet("/user/sellerEnroll")
public class SellerEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	/**
	 * 회원가입폼 페이지 html 요청!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// view단 연결
		request
			.getRequestDispatcher("/WEB-INF/views/user/sellerEnroll.jsp")
			.forward(request, response);
	}

	/**
	 * 회원가입 요청! : database insert
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 2.사용자입력값 -> User객체
		String userId = request.getParameter("userId");
		String password = MvcUtils.getEncryptedPassword(request.getParameter("password"));
		
		String emailId = request.getParameter("emailId");
		String emailAddress = request.getParameter("emailAddress");
		
		String userName = request.getParameter("userName");	
		String phone = request.getParameter("phone");
		
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthday = request.getParameter("birthday");
		
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		//이메일값 2개 합치기 (아이디+주소)
		System.out.println("emailId = " + emailId);
		System.out.println("emailAddress = " + emailAddress);
		String email = emailId+"@"+emailAddress;
				
		if(birthyear!=null && birthyear!=null && birthyear!=null) {
			//생일 월 1자리이면 -> 2자리
			if(birthmonth.length()==1) {
				birthmonth = "0"+birthmonth;
			}
			//생일 일 1자리이면 -> 2자리
			if(birthday.length()==1) {
				birthday = "0"+birthday;
			}
			
			// 생일값 3개 -> 1개
			String s_birth = birthyear+"-"+birthmonth+"-"+birthday; // 생일 8자리 
			
			// 생일 String타입 -> Date타입 
			Date d_birth = null;
			if(s_birth != null && !"".equals(s_birth)) {
				d_birth = Date.valueOf(s_birth);
			}
			
			// User 객체 생성
			User user = new User(userId, userName, email, password, d_birth, phone, null, UserService.SELLER_ROLE, null);
			System.out.println("user : " + user);
			
		}

		// User 객체 생성
		User user = new User(userId, userName, email, password, null, phone, null, UserService.SELLER_ROLE, null);
		System.out.println("user : " + user);
		
		// Account 객체 생성
		Address address = new Address(0, "기본주소", address1, address2, userId);
		System.out.println("address : " + address);
		
		// 업무로직 (insert 처리)
		int result = userService.insertUser(user);
		System.out.println("insertUser_result : " + result);
		
		int result2 = userService.insertAddress(address);
		System.out.println("insertAddress_result2 : " + result2);
		
		String msg = result > 0 ? "성공적으로 회원가입했습니다." : "회원가입 실패했습니다.";
		
		// 응답처리
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		// 리다이렉트 
		String location = request.getContextPath() + "/";
		response.sendRedirect(location);
			}
	
}
