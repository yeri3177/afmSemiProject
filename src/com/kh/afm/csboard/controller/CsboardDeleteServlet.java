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
 * Servlet implementation class CsboardDeleteServlet
 */
@WebServlet("/csboard/csboardDelete")
public class CsboardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CsboardService csboardService = new CsboardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			// 2. 업무로직
			Csboard csboard = csboardService.selectOneCsboard(boardNo);
			
			int result = csboardService.deleteCsboard(boardNo);
			String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
			
			// 3. 사용자 메세지 및 redirect 처리
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/csboard/csboardList");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
