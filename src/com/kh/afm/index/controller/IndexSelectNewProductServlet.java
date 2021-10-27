package com.kh.afm.index.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.index.model.service.IndexService;
import com.kh.afm.product.model.vo.Product;

/**
 * Servlet implementation class IndexSelectNewProductServlet
 */
@WebServlet("/index/selectNewProduct")
public class IndexSelectNewProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndexService indexService = new IndexService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String indexXml = indexService.selectNewProduct();
		response.getWriter().append(indexXml);
	}

}
