package com.kh.afm.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.product.model.service.ProductService;

/**
 * Servlet implementation class ProductCommentDeleteServlet
 */
@WebServlet("/product/productCommentDelete")
public class ProductCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int commentNo = Integer.parseInt(request.getParameter("commentNo"));
			int pNo = Integer.parseInt(request.getParameter("pNo"));
			
			int result = productService.deleteProductComment(commentNo);
			String msg = (result > 0) ? "댓글 삭제 성공!" : "댓글 삭제 실패!";
			
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/product/productView?pNo=" + pNo;
			response.sendRedirect(location);
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			throw e; // was에게 예외페이지 연결알림.
		}
		
	}

}
