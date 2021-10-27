package com.kh.afm.csboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CsboardFormServlet
 */

// 단순 JSP forward 연결용 
@WebServlet("/csboard/csboardForm")
public class CsboardFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		// csboardForm.jsp에게 해당하는 html을 만들어달라고 요청
		// getRequestDispatcher
		// : 컨테이너 내에서 request, response 객체를 전송하여 처리할 컴포넌트(jsp 파일등)을 불러오는 메소드로 forward() 메소드와 같이 사용
		request
			.getRequestDispatcher("/WEB-INF/views/csboard/csboardForm.jsp")
			.forward(request, response);
		
	}

}
