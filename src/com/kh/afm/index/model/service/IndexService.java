package com.kh.afm.index.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.afm.index.model.dao.IndexDao;
import com.kh.afm.product.model.vo.Product;

public class IndexService {
	private IndexDao indexDao = new IndexDao();

	public String selectNewProduct() {
		Connection conn = getConnection();
		String csv = indexDao.selectNewProduct(conn);
		close(conn);
		return csv;
	}

	public String selectBestProduct() {
		Connection conn = getConnection();
		String csv = indexDao.selectBestProduct(conn);
		close(conn);
		return csv;
	}

	public List<Product> indexSearch(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> list = indexDao.indexSearch(conn, param);
		close(conn);
		return list;
	}

	public int selectSearchContent(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent = indexDao.selectSearchContent(conn, param);
		close(conn);
		return totalContent;
	}
	
}
