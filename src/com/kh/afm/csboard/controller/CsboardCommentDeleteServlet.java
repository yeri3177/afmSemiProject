package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;

/**
 * Servlet implementation class CsboardCommentDelete
 */
@WebServlet("/csboard/csboardCommentDelete")
public class CsboardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CsboardService csboardService = new CsboardService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 파라미터값 가져오기
			int cbBoardNo = Integer.parseInt(request.getParameter("cbBoardNo"));
			int cbNo = Integer.parseInt(request.getParameter("cbNo"));
			
			System.out.println("cbBoardNo =" + cbBoardNo + ", cbNo = " + cbNo);
			
			// 2. 업무로직 호출
			int result = csboardService.deleteCsboardComment(cbNo);
			String msg = (result > 0) ? "댓글 삭제 성공!" : "댓글 삭제 실패!";
			
			// 3. 받은 결과에 따라 view단 처리 위임.
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/csboard/csboardView?boardNo=" + cbBoardNo;
			response.sendRedirect(location);
		} catch(Exception e) {
			e.printStackTrace();
			throw e; // WAS에게 예외페이지 연결 알림.
		}
	}
}
	
