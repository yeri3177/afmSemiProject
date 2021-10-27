package com.kh.afm.index.model.dao;

import static com.kh.afm.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kh.afm.cart.model.dao.CartDao;
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
		StringBuffer result = new StringBuffer();
		String indexXml = result.toString();
		String sql = prop.getProperty("selectNewProduct");
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			result.append("<products>\n");
			while(rset.next()) {
				result.append("<product>\n");
					result.append("<pNo>"+rset.getString("p_no")+"</pNo>\n");
					result.append("<pTitle>"+rset.getString("p_title")+"</pTitle>\n");
					result.append("<pPrice>"+rset.getString("p_price")+"</pPrice>\n");
					result.append("<pUserId>"+rset.getString("p_user_id")+"</pUserId>\n");
					result.append("<pRegDate>"+rset.getString("p_reg_date")+"</pRegDate>\n");
					result.append("<img>"+rset.getString("renamed_filename")+"</img>\n");
				result.append("</product>\n");
			}
			result.append("</products>\n\n");
			System.out.println(result);
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return indexXml;
	}

}
