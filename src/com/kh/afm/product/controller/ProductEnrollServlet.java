package com.kh.afm.product.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class ProductEnrollServlet
 */
@WebServlet("/product/productEnroll")
public class ProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 파일업로드
		
		//b. 파일저장경로
		//ServletContext객체로부터 /WebConnect/upload/board 절대경로 참조
		ServletContext application = getServletContext();
		String saveDirectory = application.getRealPath("/upload/product");
		System.out.println("saveDirectory = " + saveDirectory);
		
		//c. 최대파일용량 10mb = 1kb * 1000 * 10
		int maxPostSize = 1024 * 1024 * 10;
		
		//d. 인코딩
		String encoding = "utf-8";
		
		//e. 파일명 재지정 정책 객체
		FileRenamePolicy policy = new MvcFileRenamePolicy();
	}

}
