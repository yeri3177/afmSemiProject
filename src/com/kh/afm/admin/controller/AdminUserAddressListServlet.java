package com.kh.afm.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;
import com.kh.afm.user.model.vo.Address;

/**
 * 회원별 주소 목록 찾기 
 */
@WebServlet("/admin/userAddressList")
public class AdminUserAddressListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 찾으려는 회원아이디 
			String userId = request.getParameter("userId");
			System.out.println("주소userId = " + userId);
			
			// 업무로직
			List<Address> addressList = adminService.selectUserAddress(userId);
			System.out.println("addressList = "+addressList);
			
			// view단 처리
			request.setAttribute("addressList", addressList);
			request.getRequestDispatcher("/WEB-INF/views/admin/userAddressList.jsp")				
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
