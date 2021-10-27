package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.csboard.model.vo.Csboard;

/**
 * Servlet implementation class CsboardViewServlet
 */
@WebServlet("/csboard/csboardView")
public class CsboardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CsboardService csboardService = new CsboardService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 파라미터 글 번호
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 2. 비즈니스 로직 호출
		
		// 게시글 하나 가져오기
		Csboard csboard = csboardService.selectOneCsboard(boardNo);
		System.out.println("CsboardViewServlet = " + csboard);
		
		// 게시글 가져오기에 실패한 경우
		if(csboard == null) {
			request.getSession().setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			response.sendRedirect(request.getContextPath() + "/csboard/csboardList");
		}
		
		// 3. view단 처리 위임
		request.setAttribute("csboard", csboard);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/csboard/csboardView.jsp");
		System.out.println("CsboardViewSerlvet");
		reqDispatcher.forward(request, response);
	}

}
