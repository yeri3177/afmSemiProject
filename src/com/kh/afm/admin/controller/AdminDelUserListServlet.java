package com.kh.afm.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;
import com.kh.afm.common.MvcUtils;
import com.kh.afm.user.model.vo.DelUser;

/**
 * 탈퇴한 회원 목록 보기 
 */
@WebServlet("/admin/delUserList")
public class AdminDelUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 페이징처리
			int cPage = 1;
			int numPerPage = 10;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {
				// 처리코드없음 
			}
			int startRownum = cPage * numPerPage - (numPerPage - 1);
			int endRownum = cPage * numPerPage;
			
			// 업무로직
			List<DelUser> list = adminService.selectAllDelUser(startRownum, endRownum);
			//System.out.println("list@adminUserListServlet = " + list);
			
			// 페이징영역
			int totalContents = adminService.selectDelUserTotalContents(); 
			String url = request.getRequestURI();
			String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
			
			
			// view단 처리
			request.setAttribute("pagebar", pagebar);
			request.setAttribute("list", list);
			request
				.getRequestDispatcher("/WEB-INF/views/admin/delUserList.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
