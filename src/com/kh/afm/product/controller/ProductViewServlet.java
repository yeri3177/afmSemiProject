package com.kh.afm.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.Product;

/**
 * Servlet implementation class ProductViewServlet
 */
@WebServlet("/product/productView")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int no = Integer.parseInt(request.getParameter("pNo"));
			
			//게시글 가져오기
			Product product = productService.selectOneProduct(no);
			System.out.println("product@servlet = " + product);
			
			//게시글 가져오기에 실패시
			if(product == null) {
				request.getSession().setAttribute("msg", "조회한 게시글이 존재하지 않습니다.");
				response.sendRedirect(request.getContextPath() + "/product/productList");
				return;
			};
			
			//view단 처리위임
			request.setAttribute("product", product);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/product/productView.jsp");
			reqDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
