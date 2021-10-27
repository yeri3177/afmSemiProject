package com.kh.afm.index.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

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
	
}