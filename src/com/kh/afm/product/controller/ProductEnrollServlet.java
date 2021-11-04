package com.kh.afm.product.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
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
 * Servlet implementation class ProductEnrollServlet
 */
@WebServlet("/product/productEnroll")
public class ProductEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
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
			
			MultipartRequest multipartRequest =
					new MultipartRequest(
							request,
							saveDirectory,
							maxPostSize,
							encoding,
							policy
					);
			
			//파일정보 가져오기
			String originalFilename1 = multipartRequest.getOriginalFileName("upFile1");
			String renamedFilename1 = multipartRequest.getFilesystemName("upFile1");
			
			String originalFilename2 = multipartRequest.getOriginalFileName("upFile2");
			String renamedFilename2 = multipartRequest.getFilesystemName("upFile2");	
			
			//1. 사용자 입력값 처리
			String title = multipartRequest.getParameter("pTitle");
			String userId = multipartRequest.getParameter("userId");
			int price = Integer.parseInt(multipartRequest.getParameter("pPrice"));
			int cnt = Integer.parseInt(multipartRequest.getParameter("pCnt"));
			String category = multipartRequest.getParameter("pCategory");
			String post = multipartRequest.getParameter("pPost");
			String content = multipartRequest.getParameter("pContent");
			Product product = new Product(0, userId, null, title, content, post, price, cnt, category, null, null, 0, null, null);
			
			if(multipartRequest.getFile("upFile1") != null) {
				Attachment attach = new Attachment();
				attach.setOriginalFileName(originalFilename1);
				attach.setRenamedFileName(renamedFilename1);
				attach.setImgFlag("Y");
				product.setAttach1(attach);
			}
			
			if(multipartRequest.getFile("upFile2") != null) {
				Attachment attach = new Attachment();
				attach.setOriginalFileName(originalFilename2);
				attach.setRenamedFileName(renamedFilename2);
				attach.setImgFlag("N");
				product.setAttach2(attach);
			}
			
			System.out.println("product@servlet = " + product);
			
			//2. 업무로직
			int result = ProductService.insertProduct(product);
			
			//3. 응답처리 : redirect
			HttpSession session = request.getSession();
			String location = request.getContextPath() + "/product/productList";
			response.sendRedirect(location);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

}
