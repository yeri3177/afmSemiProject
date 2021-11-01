package com.kh.afm.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.product.model.service.ProductService;

/**
 * Servlet implementation class ProductLikeServlet
 */
@WebServlet("/product/productLike")
public class ProductLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int like = Integer.parseInt(request.getParameter("like"));
		like++;
		
		//2. 업무로직
		int result = ProductService.productLike(pNo, like);
		String msg = result > 0 ? "좋아요 성공" : "좋아요 실패";
		
		//3. 응답처리
		request.getSession().setAttribute("Msg", msg);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter().append("<span>" + "좋아요 : " + result + "</span>");
	}

}
