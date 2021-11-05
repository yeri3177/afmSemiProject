package com.kh.afm.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
		
		try {
			//1. 사용자입력값 처리
			int pNo = Integer.parseInt(request.getParameter("pNo"));
			int like = Integer.parseInt(request.getParameter("like"));
			
//		//좋아요 누름 확인(cookie)
//		Cookie[] cookies = request.getCookies();
//		boolean hasClick = false;
//		String productValue = "";
//		
//		if(cookies != null) {
//			for(Cookie c : cookies) {
//				String name = c.getName();
//				String value = c.getValue();
//				System.out.println(name + " : " + value);
//				
//				if("product".equals(name)) {
//					productValue = value;
//					//좋아요 누름 여부
//					if(value.contains("|" + pNo + "|")) {
//						hasClick = true;
//					}
//					break;
//					
//				}
//			}
//		}
//		
//		System.out.printf("hasClick = %b, productValue = %s%n", hasClick, productValue);
//		
//		// 처음 좋아요 누르는 경우
//		if(!hasClick) {
//			// 좋아요 cookie
//			Cookie cookie = new Cookie("product", productValue + "|" + pNo +"|");
//			cookie.setMaxAge(365 * 24 * 60 * 60);
//			cookie.setPath(request.getContextPath() + "/product/productLike"); // 해당 요청시만 cookie전송
//			response.addCookie(cookie);
//		
//			//좋아요 증가
//			like++;
//		}
			
			like++;
			//2. 업무로직
			int result = ProductService.productLike(pNo, like);
			String msg = result > 0 ? "좋아요 성공" : "좋아요 실패";
			
			//3. 응답처리
			request.getSession().setAttribute("Msg", msg);
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter().append("<img class='top-tags' src='/afmSemiProject/images/product/tag5.png' />" + "<span>" + result + "</span>");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
