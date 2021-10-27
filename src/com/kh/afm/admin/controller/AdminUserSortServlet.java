package com.kh.afm.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.admin.model.service.AdminService;
import com.kh.afm.common.MvcUtils;
import com.kh.afm.user.model.vo.User;

/**
 * 회원 정렬
 */
@WebServlet("/admin/userSort")
public class AdminUserSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService(); 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 정렬유형, 정렬키워드 가져오기 
		String sortType = request.getParameter("sortType");
		String sortKeyword = request.getParameter("sortKeyword");
		
		// 검색결과 페이징 처리
		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			// 처리코드 없음
		}
		int start = cPage * numPerPage - (numPerPage - 1);
		int end = cPage * numPerPage;
		
		// 업무로직
		Map<String, Object> param = new HashMap<>();
		param.put("sortType", sortType);
		param.put("sortKeyword", sortKeyword);
		param.put("start", start);
		param.put("end", end);
		System.out.println("param@sortservlet = " + param);
		
		// 검색결과 리스트 
		List<User> list = adminService.sortUser(param);
		System.out.println("정렬결과 sortlist" + list);
		
		// 페이지바
		int totalContents = adminService.selectTotalContents();
		String queryString = String.format("?sortType=%s&sortKeyword=%s", sortType, sortKeyword);
		String url = request.getRequestURI() + queryString; 
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
		System.out.println("pagebar@SortServlet = " + pagebar);
		
		// view단처리
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/admin/userList.jsp")
			.forward(request, response);
	}
}
