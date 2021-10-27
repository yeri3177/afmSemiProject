package com.kh.afm.product.model.dao;

import static com.kh.afm.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.product.model.exception.ProductException;
import com.kh.afm.product.model.exception.ProductException;

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
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Product product = new Product();
				
				product.setpNo(rset.getInt("p_no"));
				product.setpTitle(rset.getString("p_title"));
				product.setpPrice(rset.getInt("p_price"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpRecommend(rset.getInt("p_recommend"));
				
				if(rset.getString("img_flag") != "N") {
					Attachment attach = new Attachment();
					attach.setAttachNo(rset.getInt("attach_no"));
					attach.setpNo(rset.getInt("p_no"));
					attach.setOriginalFileName(rset.getString("original_filename"));
					attach.setRenamedFileName(rset.getString("renamed_filename"));
					attach.setRegDate(rset.getDate("reg_date"));
					attach.setImgFlag(rset.getString("img_flag"));
			
				product.setAttach1(attach);
				}
				
				list.add(product);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("list@memberDao = " + list );
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

	public int insertProduct(Connection conn, Product product) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertProduct");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getUserId());
			pstmt.setString(2, product.getpTitle());
			pstmt.setString(3, product.getpContent());
			pstmt.setString(4, product.getpPost());
			pstmt.setInt(5, product.getpPrice());
			pstmt.setInt(6, product.getpCnt());
			pstmt.setString(7, product.getpCategory());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int selectLastProductNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastProductNo");
		int pNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery(sql);
			if(rset.next()) {
				pNo = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("게시글번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return pNo;
	}

	public int insertAttachment(Connection conn, Attachment attach) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, attach.getpNo());
			pstmt.setString(2, attach.getOriginalFileName());
			pstmt.setString(3, attach.getRenamedFileName());
			pstmt.setString(4, attach.getImgFlag());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProductException("첨부파일 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
}
