package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.csboard.model.vo.Csboard;

/**
 * Servlet implementation class CsboardUpdateServlet
 */
@WebServlet("/csboard/csboardUpdate")
public class CsboardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// CsboardUpdateServlet은 CsboardService에 의존하고 있다.
	private CsboardService csboardService = new CsboardService(); // 의존 객체

	/**
	 * update form 페이지 요청!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		// 사용자 입력값은 문자열이기때문에 형변환 필요
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 2. 업무로직
		// csboard 하나를 요청.
		Csboard csboard = csboardService.selectOneCsboard(boardNo);
		// csboard가 잘 넘어왔는지 확인.
		System.out.println("CsboardUpdateServlet@requestToForm = " + csboard);
		
		// 3. view단 위임
		request.setAttribute("csboard", csboard);
		request
			.getRequestDispatcher("/WEB-INF/views/csboard/csboardUpdate.jsp")
			.forward(request, response);
	}

	/**
	 * db update 요청!
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String boardPassword = request.getParameter("boardPassword");
		String boardLock = request.getParameter("boardLockYN");
		
		Csboard csboard = new Csboard(boardNo, null, boardTitle, boardContent, null, 0, "N", "N", boardPassword, boardLock, 0, 0, 0);
		
		
		// 2. 업무로직 db 저장 update csboard set board_title = ?, board_content = ? where board_no = ?
		int result = csboardService.updateCsboard(csboard);
		System.out.println("CsboardUpdateServlet@update = " + result);
		String msg = result > 0 ? "게시물 수정 성공!" : "게시물 수정 실패!";
		
		// 3. redirect
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/csboard/csboardView?boardNo=" + boardNo;
		response.sendRedirect(location);
	}

}
