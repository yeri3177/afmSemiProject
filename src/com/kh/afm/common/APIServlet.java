package com.kh.afm.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.servlet.ServletException;	
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class APIServlet
 */
@WebServlet("/apiServlet")
public class APIServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public APIServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml;charset=UTF-8");
		BufferedReader br = null;
		try {
			String urlstr = "https://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList"
					+ "&p_product_cls_code=02"
					+ "&p_country_code=1101"
					+ "&p_regday=2021-11-01"
					+ "&p_convert_kg_yn=Y"
					+ "&p_item_category_code=100"
					+ "&p_cert_key=e079b288-97e8-4b06-82ad-f5312bf146b4"
					+ "&p_cert_id=2109"
					+ "&p_returntype=xml";
			//https://www.kamis.or.kr/service/price/xml.do?action=dailyPriceByCategoryList&p_product_cls_code=02&p_country_code=1101&p_regday=2021-11-01&p_convert_kg_yn=Y&p_item_category_code=100&p_cert_key=e079b288-97e8-4b06-82ad-f5312bf146b4&p_cert_id=2109&p_returntype=xml
			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));
			String result = "";
			String line;
			while ((line = br.readLine()) != null) {
				result = result + line + "\n";
				//System.out.println("호출 되니?"+result);
			}
			response.getWriter().append(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}


