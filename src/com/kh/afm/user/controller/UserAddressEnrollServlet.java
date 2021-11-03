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
 * 주소 추가하기
 */
@WebServlet("/user/addressEnroll")
public class UserAddressEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.encoding처리
		request.setCharacterEncoding("UTF-8");
		
		// 2.사용자입력값
		String userId = request.getParameter("userId");
		String adr_name = request.getParameter("adr_name");
		String adr_road = request.getParameter("adr_road");
		String adr_detail = request.getParameter("adr_detail");
		
		// Address 객체 생성
		Address address = new Address(0, adr_name, adr_road, adr_detail, userId);
		
		// 업무로직 (insert 처리)
		int result = userService.insertAddress(address);
		
		String msg = result > 0 ? "주소 추가 성공" : "주소 추가 실패";
		System.out.println("result_user = " + result);
		
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
		response.sendRedirect(request.getContextPath() + "/user/userAddressList");
	}
}
