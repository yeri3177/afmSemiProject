package com.kh.afm.index.model.dao;

import static com.kh.afm.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.afm.cart.model.dao.CartDao;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;

public class IndexDao {
private Properties prop = new Properties();
	
	public IndexDao() {
		String filepath = CartDao.class.getResource("/sql/index/index-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String selectNewProduct(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		String csvStr = null;
		StringBuilder csv = new StringBuilder();
		String sql = prop.getProperty("selectNewProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
					Product product = new Product();
					
					product.setpNo(rset.getInt("p_no"));
					product.setpTitle(rset.getString("p_title"));
					product.setpPrice(rset.getInt("p_price"));
					product.setUserId(rset.getString("p_user_id"));
					product.setpRegDate(rset.getDate("p_reg_date"));
					product.setpRecommend(rset.getInt("p_recommend"));
					
					if(rset.getString("img_flag").equals("Y")) {
						Attachment attach = new Attachment();
						attach.setAttachNo(rset.getInt("attach_no"));
						attach.setpNo(rset.getInt("p_no"));
						attach.setOriginalFileName(rset.getString("original_filename"));
						attach.setRenamedFileName(rset.getString("renamed_filename"));
						attach.setRegDate(rset.getDate("reg_date"));
						attach.setImgFlag(rset.getString("img_flag"));
						
						product.setAttach1(attach);
					
					list.add(product);
				}
			}
			for(int i = 0; i < list.size(); i++){
				csv.append(list.get(i));
				if(i != list.size() -1)
				csv.append("\n");
			}
			csvStr = csv.toString();

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return csvStr;
	}

	public String selectBestProduct(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		String csvStr = null;
		StringBuilder csv = new StringBuilder();
		String sql = prop.getProperty("selectBestProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
					Product product = new Product();
					
					product.setpNo(rset.getInt("p_no"));
					product.setpTitle(rset.getString("p_title"));
					product.setpPrice(rset.getInt("p_price"));
					product.setUserId(rset.getString("p_user_id"));
					product.setpRegDate(rset.getDate("p_reg_date"));
					product.setpRecommend(rset.getInt("p_recommend"));
					
					if(rset.getString("img_flag").equals("Y")) {
						Attachment attach = new Attachment();
						attach.setAttachNo(rset.getInt("attach_no"));
						attach.setpNo(rset.getInt("p_no"));
						attach.setOriginalFileName(rset.getString("original_filename"));
						attach.setRenamedFileName(rset.getString("renamed_filename"));
						attach.setRegDate(rset.getDate("reg_date"));
						attach.setImgFlag(rset.getString("img_flag"));
						
						product.setAttach1(attach);
					
					list.add(product);
				}
			}
			for(int i = 0; i < list.size(); i++){
				csv.append(list.get(i));
				if(i != list.size() -1)
				csv.append("\n");
			}
			csvStr = csv.toString();

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return csvStr;
	}

	public List<Product> indexSearch(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		String sql = prop.getProperty("indexSearch");
		param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			pstmt.setInt(2, (int) param.get("start"));
			pstmt.setInt(3, (int) param.get("end"));
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Product product = new Product();
				product.setpNo(rset.getInt("p_no"));
				product.setpTitle(rset.getString("p_title"));
				product.setpPrice(rset.getInt("p_price"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpRecommend(rset.getInt("p_recommend"));
				
				if(rset.getString("img_flag").equals("Y")) {
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
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}

	public int selectSearchContent(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		String sql = prop.getProperty("indexSearchCount");
		param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

}
