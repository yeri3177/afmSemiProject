package com.kh.afm.csboard.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.csboard.model.vo.Csboard;

/**
 * Servlet implementation class CsboardBoardFinderServlet
 */
@WebServlet("/csboard/boardFinder")
public class CsboardBoardFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CsboardService csboardService = new CsboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		System.out.println("searchType = " + searchType);
		System.out.println("searchKeyword = " + searchKeyword);
		
		// Map은 toString 처리가 되어 있어서 key, value가 열람 가능하다.
		Map<String, Object> param = new HashMap<>();
		param.put("searchType", searchType);
		param.put("searchKeyword", searchKeyword);
		System.out.println("param@servlet = " + param);
		
		// 2. 업무로직
		// a. select * from user where user_id like '%abc%'
		// b. select * from user where user_name like '%길동%'
		// c. select * from csboard where board_title like '%목%'
		List<Csboard> list = csboardService.searchCsboard(param);
		System.out.println(list);
		
		
		// 3. view단 처리 (html)
		// jsp 참조용
		request.setAttribute("list", list);
		request
			.getRequestDispatcher("/WEB-INF/views/csboard/csboardList.jsp")
			.forward(request, response);
	}

}
