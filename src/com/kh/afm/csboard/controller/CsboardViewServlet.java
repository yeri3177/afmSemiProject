package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcUtils;
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
		//주회수 증가
		int result = csboardService.updateReadCount(boardNo);
		
		// 게시글 하나 가져오기
		Csboard csboard = csboardService.selectOneCsboard(boardNo);
		System.out.println("CsboardViewServlet = " + csboard);
		
		
		// 게시글 가져오기에 실패한 경우
		if(csboard == null) {
			request.getSession().setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
			response.sendRedirect(request.getContextPath() + "/csboard/csboardList");
		}
		
		// XSS 공격 대비
		// cross-site script 공격. 악성코드를 웹페이지삽입하여 클라이언트의 개인정보를 탈취하는 공격법.
		// <script> --> &lt;script&gt;
		// MvcUtils로 보관
		String BoardContent = MvcUtils.escapeHtml(csboard.getBoardContent()); 
		
		
		// 개행문자 br태그 변환처리
		// \n 자체를 문자로 전달하기 위해서 \\n으로 처리
		// \n --> <br/>로 변환
		// 코드 위치 : 게시글 하나가져오고 view단 처리 위임 전 사이
		// replaceAll() : 원본을 바꾸는 것은 아니다.
		// MvcUtils로 보관
		BoardContent = MvcUtils.convertLineFeedToBr(BoardContent);
		
		csboard.setBoardContent(BoardContent);
		
		
		// 3. view단 처리 위임
		request.setAttribute("csboard", csboard);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/csboard/csboardView.jsp");
		System.out.println("CsboardViewSerlvet");
		reqDispatcher.forward(request, response);
	}

}
