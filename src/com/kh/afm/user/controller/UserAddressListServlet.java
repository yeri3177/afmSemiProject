package com.kh.afm.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.user.model.service.UserService;
import com.kh.afm.user.model.vo.Address;
import com.kh.afm.user.model.vo.User;

/**
 * 내정보보기에서 자신의 주소 목록 조회 
 */
@WebServlet("/user/userAddressList")
public class UserAddressListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인한 회원 
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		// 업무로직
		List<Address> addressList = userService.selectUserAddress(loginUser.getUserId());
		System.out.println("addressList = "+addressList);
		
		// view단 처리
		request.setAttribute("addressList", addressList);
		request.getRequestDispatcher("/WEB-INF/views/user/userAddressList.jsp")				
				.forward(request, response);

	}

}
