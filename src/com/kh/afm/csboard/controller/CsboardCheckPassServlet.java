package com.kh.afm.csboard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.csboard.model.service.CsboardService;

/**
 * Servlet implementation class CsboardCheckPassServlet
 */
@WebServlet("/csboard/csboardCheckPass")
public class CsboardCheckPassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CsboardService csboardService = new CsboardService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 입력값 처리
		
		// 2. 업무로직
		
		// 3. view단 위임
		
	}

}
