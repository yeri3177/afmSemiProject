package com.kh.afm.cart.model.dao;

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

import com.kh.afm.cart.model.vo.Cart;


public class CartDao {
	
private Properties prop = new Properties();
	
	public CartDao() {
		String filepath = CartDao.class.getResource("/sql/cart/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Cart> selectAllList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllList");
		List<Cart> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Cart cart = new Cart();
				cart.setUserId(rset.getString("user_id"));
				cart.setCartNo(rset.getInt("cart_no"));
				cart.setProductNo(rset.getInt("p_no"));
				cart.setAttachNo(rset.getInt("attach_no"));
				cart.setProductPrice(rset.getInt("p_price"));
				cart.setProductQuantity(rset.getInt("cart_product_quantity"));
				list.add(cart);
				}
			}catch (SQLException e) {
					e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		return list;
	}

}
