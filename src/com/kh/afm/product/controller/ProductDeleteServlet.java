package com.kh.afm.product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.Product;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/product/productDelete")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값
			int no = Integer.parseInt(request.getParameter("pNo"));
			
			// 2. 업무로직
			Product product = productService.selectOneProduct(no);
			//첨부파일 삭제
//		if(product != null && product.getAttach1() != null); {
//			ServletContext application = getServletContext();
//			String saveDirectory = application.getRealPath("/upload/product");
//			String filename = product.getAttach1().getRenamedFileName();
//			File delFile = new File(saveDirectory, filename);
//			boolean result = delFile.delete();
//			System.out.printf("첨부파일1[%s] 삭제여부 : %b%n", filename, result);
//			
//		}
//		
//		if(product != null && product.getAttach2() != null); {
//			ServletContext application = getServletContext();
//			String saveDirectory = application.getRealPath("/upload/product");
//			String filename = product.getAttach2().getRenamedFileName();
//			File delFile = new File(saveDirectory, filename);
//			boolean result = delFile.delete();
//			System.out.printf("첨부파일1[%s] 삭제여부 : %b%n", filename, result);
//			
//		}
			//attachment삭제
			int result1 = productService.deleteAttachmentY(no);
			System.out.println(result1);
			int result2 = productService.deleteAttachmentN(no);
			System.out.println(result2);
			
			//product삭제
			int result = productService.deleteProduct(no);
			String msg = result > 0 ? "게시물 삭제 성공!" : "게시물 삭제 실패!";
			
			// 3. 사용자메세지 및 redirect처리
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect(request.getContextPath() + "/product/productList");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
