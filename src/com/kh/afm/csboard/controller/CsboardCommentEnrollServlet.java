package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.csboard.model.vo.CsboardComment;

/**
 * Servlet implementation class CsboardCommentEnrollServlet
 */
@WebServlet("/csboard/csboardCommentEnroll")
public class CsboardCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CsboardService csboardService = new CsboardService();
	
	/**
	 * 등록 후 /csboard/csbaordView?boardNo=현재 게시글 번호 리다이렉트
	 * 
	 * dao에서 commentRef필드는 setObject를 사용할 것.
	 * - 0이면 null이 대입
	 * - n이면 n이 대입
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력 -> CsboardComment 객체
		int cbNo = Integer.parseInt(request.getParameter("cbBoardNo"));
		int cbLevel = Integer.parseInt(request.getParameter("cbLevel"));
		int cbCommentRef = Integer.parseInt(request.getParameter("cbCommentRef"));
		String userId = request.getParameter("userId");
		String cbContent = request.getParameter("cbConent");
		
		CsboardComment cbc = new CsboardComment(0, cbLevel, userId, cbContent, cbLevel, cbCommentRef, null);
		System.out.println("CsboardCommentEnrollServlet = " + cbc);
		
		// 2. 업무로직
		int result = csboardService.insertCsboardComment(cbc);
		String msg = result > 0 ? "댓글 등록 성공!" : "댓글 등록 실패!";
		
		// 3. redirect
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/csboard/csboardView?no=" + cbNo;
		response.sendRedirect(location);
		
	}

}
