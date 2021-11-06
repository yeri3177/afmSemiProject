package com.kh.afm.csboard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.csboard.model.service.CsboardService;
import com.kh.afm.csboard.model.vo.Csboard;
import com.kh.afm.csboard.model.vo.CsboardComment;

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
		// 읽음 여부 확인(cookie)
		// 쿠키 가져오기
		Cookie[] cookies = request.getCookies();
		// 게시글을 읽었습니까?
		boolean hasRead = false;
		// csboardValue 변수 생성
		String csboardValue = "";
		// null일 때 : 완전 첫 방문일 때
		if(cookies != null) {
			for(Cookie c : cookies) {
				// cookie에서 name과 value를 가져온다.
				String name = c.getName();
				String value = c.getValue();
				System.out.println("??");
				System.out.println(name + " : " + value);
				
				// csboard라는 cookie가 있냐
				if("csboard".equals(name)) {
					csboardValue = value;
					// 현재 게시글 읽음 여부
					// boardNo를 포함하고 있는가?
					if(value.contains("|" + boardNo + "|")) {
						hasRead = true;
					}
					break;
				}
			}
		}
		
		// %b : boolean 출력
		// %s : 문자형 출력
		// %n : 줄 바꿈
		System.out.printf("hasRead = %b, csboardValue = %s%n", hasRead, csboardValue);
		
		// 게시글을 처음 읽는 경우
		if(!hasRead) {
			// 게시글 Cookie
			// 뭐를 읽었는지 cookie에 담아둔다.
			// boardNo : 현재 게시글 번호
			Cookie cookie = new Cookie("csboard", csboardValue + "|" + boardNo + "|");
			// cookie 유효기간
			cookie.setMaxAge(365 * 24 * 60 * 60);
			// 언제만 쓸거냐? /csboard/csboardView 쓸 때만
			// 해당 요청시만 cookie 전송 / 결국 브라우저에 저장할 것이니까.
			cookie.setPath(request.getContextPath() + "/csboard/csboardView"); 
			response.addCookie(cookie);
			
			//주회수 증가
			int result = csboardService.updateReadCount(boardNo);
		}
		
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
		
		// 댓글 목록 가져오기
		List<CsboardComment> commentList = csboardService.selectCommentList(boardNo);
		//System.out.println("commentList@servlet = " + commentList);
		
		// 3. view단 처리 위임
		// view단에 전달하기 위해 속성으로 저장
		request.setAttribute("csboard", csboard);
		request.setAttribute("commentList", commentList);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/csboard/csboardView.jsp");
		System.out.println("CsboardViewSerlvet");
		reqDispatcher.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
