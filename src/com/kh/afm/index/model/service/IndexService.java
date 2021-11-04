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
		String csv = "";
		try {
			Connection conn = getConnection();
			csv = indexDao.selectNewProduct(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csv;
	}

	public String selectBestProduct() {
		String csv ="";
		try {
			Connection conn = getConnection();
			csv = indexDao.selectBestProduct(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csv;
	}

	public List<Product> indexSearch(Map<String, Object> param) {
		List<Product> list = null;
		try {
			Connection conn = getConnection();
			list = indexDao.indexSearch(conn, param);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public int selectSearchContent(Map<String, Object> param) {
		int totalContent = 0;
		try {
			Connection conn = getConnection();
			totalContent = indexDao.selectSearchContent(conn, param);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalContent;
	}
	
}
