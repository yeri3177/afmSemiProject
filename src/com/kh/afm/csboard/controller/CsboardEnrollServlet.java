package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.csboard.model.vo.Csboard;

/**
 * Servlet implementation class CsboardEnrollServlet
 */
@WebServlet("/csboard/csboardEnroll")
public class CsboardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CsboardService csboardService = new CsboardService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 사용자입력값 처리
		String userId = request.getParameter("user_id");
		String boardPassword = request.getParameter("board_password");
		String boardTitle = request.getParameter("board_title");
		String boardContent = request.getParameter("board_content");
		String boardNotice = request.getParameter("boardNotice_yn");
		String boardLock = request.getParameter("boardLock_yn");
		Csboard csboard = new Csboard(0, userId, boardTitle, boardContent, null, 0, null, boardNotice, boardPassword, boardLock, 0, 0, 0);
		
		System.out.println("CsboardEnrollServlet@servlet = " + csboard);
		
		// 2. 업무로직 (db에 저장)
		int result = csboardService.insertCsboard(csboard);
//		String msg = result > 0 ? "게시물 등록 성공!" : "게시물 등록 실패!";
		
		System.out.println("CsboardEnrollServlet@servlet = " + csboard);
		
		// 3. 응답 처리 : redirect(DML이기 때문에)
		HttpSession session = request.getSession();
//		session.setAttribute("msg", msg);
		String location = request.getContextPath() + "/csboard/csboardList";
		// "/csboard/csboardView?boardNo=" + csboard.getBoardNo()
		response.sendRedirect(location);
	}

}
