package com.kh.afm.csboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.csboard.model.vo.Csboard;
import com.kh.afm.csboard.service.CsboardService;

/**
 * Servlet implementation class CsboardListServlet
 */
@WebServlet("/csboard/csboardList")
public class CsboardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CsboardService csboardService = new CsboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리 cPage numPerPage = 10
		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		
		// 2. 업무로직
		// a. content 영역 - paging query
		int start = cPage * numPerPage - (numPerPage - 1);
		int end = cPage * numPerPage;
		List<Csboard> list = csboardService.selectCsboardList(start, end);
		System.out.println("list@servlet = " + list);
		
		// b. pagebar 영역
		// totalContents, url 준비
		// 총게시글을 조회 --> pagebar를 적절히 설정 할 수 있다.
		int totalContents = csboardService.selectTotalContents();
		// 링크 클릭했을 때 이동할 페이지 제공
		String url = request.getRequestURI();
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
		System.out.println("pagebar@servlet = " + pagebar);
		
		// 3. view단 forwarding
		request.setAttribute("list", list);
		request.setAttribute("pagebar", pagebar);
		request
			.getRequestDispatcher("/WEB-INF/views/csboard/csboardList.jsp")
			.forward(request, response);
		
	}



}
