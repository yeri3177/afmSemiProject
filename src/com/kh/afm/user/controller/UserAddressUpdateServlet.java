package com.kh.afm.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.user.model.service.UserService;
import com.kh.afm.user.model.vo.Address;

/**
 * 주소 수정하기 
 */
@WebServlet("/user/addressUpdate")
public class UserAddressUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();   

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 수정 입력값 
		String userId = request.getParameter("userId"); 
		int addressNo = Integer.parseInt(request.getParameter("addressNo")); 
		String addressName = request.getParameter("addressName"); 
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		// 콘솔 값 확인
		System.out.println("userId = " + userId);
		System.out.println("addressNo = " + addressNo);
		System.out.println("addressName = " + addressName);
		System.out.println("address1 = " + address1);
		System.out.println("address2 = " + address2);
		
		// Address 객체 생성
		Address address = new Address(addressNo, addressName, address1, address2, userId);
		
		// 업무로직
		int result = userService.updateAddress(address);
		
		String msg = (result > 0) ? "주소 변경 성공" : "주소 변경 실패";
		System.out.println("result_user = " + result);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath() + "/user/userAddressList");
	}

}
