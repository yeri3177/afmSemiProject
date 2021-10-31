package com.kh.afm.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.ProductComment;

/**
 * Servlet implementation class ProductCommentServlet
 */
@WebServlet("/product/productCommentEnroll")
public class ProductCommentEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자입력 -> ProductComment객체
		int pNo = Integer.parseInt(request.getParameter("pNo"));
		int commentLevel = Integer.parseInt(request.getParameter("commentLevel"));
		int commentRef = Integer.parseInt(request.getParameter("commentRef"));
		String writer = request.getParameter("userId");
		String content = request.getParameter("content");
		
		ProductComment pc = new ProductComment(0, pNo, writer, commentLevel, content, commentRef, null);
		System.out.println("pc@servlet = " + pc);
		System.out.println(commentRef);
		
		// 업무로직
		int result = productService.insertProductComment(pc);
		String msg = result > 0 ? "댓글 등록 성공!" : "댓글 등록 실패!";
		
		// 리다이렉트
		request.getSession().setAttribute("msg", msg);
		String location = request.getContextPath() + "/product/productView?pNo=" + pNo;
		response.sendRedirect(location);
		
	}

}
