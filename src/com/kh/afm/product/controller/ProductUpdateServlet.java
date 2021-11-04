package com.kh.afm.product.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.afm.common.MvcFileRenamePolicy;
import com.kh.afm.product.model.service.ProductService;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class ProductUpdateServlet
 */
@WebServlet("/product/productUpdate")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 사용자 입력값 처리
			int no = Integer.parseInt(request.getParameter("pNo"));
			
			// 2. 업무로직
			Product product = productService.selectOneProduct(no);
			System.out.println("product@service = " + product);
			
			// 3. view단 위임
			request.setAttribute("product", product);
			request
				.getRequestDispatcher("/WEB-INF/views/product/productUpdate.jsp")
				.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 0. MultipartRequest 객체
			String saveDirectory = getServletContext().getRealPath("/upload/product");
			int maxPostSize = 1024 * 1024 * 10;
			String encoding = "utf-8";
			FileRenamePolicy policy = new MvcFileRenamePolicy();
			MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
			
			//파일정보
			String originalFilename1 = multipartRequest.getOriginalFileName("upFile1");
			String renamedFilename1 = multipartRequest.getFilesystemName("upFile1");
			
			String originalFilename2 = multipartRequest.getOriginalFileName("upFile2");
			String renamedFilename2 = multipartRequest.getFilesystemName("upFile2");	

			// 1. 사용자입력
			int no = Integer.parseInt(multipartRequest.getParameter("pNo"));
			String title = multipartRequest.getParameter("pTitle");
			String userId = multipartRequest.getParameter("userId");
			int price = Integer.parseInt(multipartRequest.getParameter("pPrice"));
			int cnt = Integer.parseInt(multipartRequest.getParameter("pCnt"));
			String category = multipartRequest.getParameter("pCategory");
			String post = multipartRequest.getParameter("pPost");
			String content = multipartRequest.getParameter("pContent");
			
			Product product = new Product(no, userId, null, title, content, post, price, cnt, category, null, null, 0, null, null);
			
			//첨부파일		
			if(multipartRequest.getFile("upFile1") != null) {
				Attachment attach = new Attachment();
				attach.setpNo(no);
				attach.setOriginalFileName(originalFilename1);
				attach.setRenamedFileName(renamedFilename1);
				attach.setImgFlag("Y");
				product.setAttach1(attach);
			}

			if(multipartRequest.getFile("upFile2") != null) {
				Attachment attach = new Attachment();
				attach.setpNo(no);
				attach.setOriginalFileName(originalFilename2);
				attach.setRenamedFileName(renamedFilename2);
				attach.setImgFlag("N");
				product.setAttach2(attach);
			}

			System.out.println("product@servlet = " + product);
			
			// 2. 업무로직
			int result = ProductService.updateProduct(product);
			String msg = result > 0 ? "게시물 수정 성공함" : "게시물 수정 실패함";
			
			
			// 3. 응답처리 : redirect
			request.getSession().setAttribute("msg", msg);
			String location = request.getContextPath() + "/product/productList";
			response.sendRedirect(location);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
