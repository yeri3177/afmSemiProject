package com.kh.afm.product.model.service;

import static com.kh.afm.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.product.model.dao.ProductDao;
import com.kh.afm.product.model.vo.Product;

public class ProductService {

	private ProductDao productDao = new ProductDao();
	
	public List<Product> selectProductList(int start, int end) {
		
		Connection conn = getConnection();
		List<Product> list = productDao.selectProductList(conn, start, end);
		close(conn);
		return list;
	}

	public int selectTotalContent(int start, int end) {
		Connection conn = getConnection();
		int totalContents = productDao.selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

}
