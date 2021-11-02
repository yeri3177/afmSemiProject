package com.kh.afm.index.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.afm.common.MvcUtils;
import com.kh.afm.index.model.service.IndexService;
import com.kh.afm.product.model.vo.Product;



/**
 * Servlet implementation class IndexSearchServlet
 */
@WebServlet("/index/indexSearch")
public class IndexSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndexService indexService = new IndexService();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int cPage = 1;
		int numPerPage = 10;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			
		}
		String searchKeyword = request.getParameter("search");
		int start = cPage * numPerPage - (numPerPage -1);
		int end = cPage * numPerPage;
		Map<String, Object> param = new HashMap<>();
		param.put("searchKeyword", searchKeyword);
		param.put("start", start);
		param.put("end", end);
		List<Product> list = indexService.indexSearch(param);
		
		
		int totalContents = indexService.selectSearchContent(param);
		String query = String.format("?search=%s", searchKeyword);
		String url = request.getRequestURI() + query;
		String pagebar = MvcUtils.getPagebar(cPage, numPerPage, totalContents, url);
		
		request.setAttribute("list", list);
		request.setAttribute("searchKeyword", searchKeyword);
		request.setAttribute("pagebar", pagebar);
		request.getRequestDispatcher("/WEB-INF/views/indexSearch/indexSearchList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
