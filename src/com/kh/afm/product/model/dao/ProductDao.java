package com.kh.afm.product.model.dao;

import static com.kh.afm.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.afm.product.model.vo.Product;

public class ProductDao {

	private Properties prop = new Properties();
	
	public ProductDao() {
		String filepath = ProductDao.class.getResource("/sql/product/product-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Product> selectProductList(Connection conn, int start, int end) {
		
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectProductList");
		List<Product> list = new ArrayList<>();
		
		try {
			System.out.println(start);
			System.out.println(end);
			System.out.println(22);
			pstmt = conn.prepareStatement(sql);
			System.out.println(33);
			pstmt.setInt(1, start);
			System.out.println(44);
			pstmt.setInt(2, end);
			System.out.println(55);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product product = new Product();
				product.setpNo(rset.getInt("p_no"));
				product.setpTitle(rset.getString("p_title"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpWriter(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpRecommend(rset.getInt("p_recommend"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	public int selectTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

	
}
