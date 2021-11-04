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

import com.kh.afm.cart.model.exception.CartException;
import com.kh.afm.cart.model.vo.Cart;


public class CartDao {
	
private Properties prop = new Properties();
	
	public CartDao() {
		String filepath = CartDao.class.getResource("/sql/cart/cart-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new CartException("장바구니 쿼리 오류", e);
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
				cart.setRenamedFilename(rset.getString("renamed_filename"));
				cart.setProductName(rset.getString("p_title"));
				cart.setProductPrice(rset.getInt("p_price"));
				cart.setProductQuantity(rset.getInt("cart_product_quantity"));
				list.add(cart);
				}
			}catch (SQLException e) {
					e.printStackTrace();
					throw new CartException("장바구니 조회 오류", e);
			}finally {
				close(rset);
				close(pstmt);
			}
		return list;
	}

	public int deleteAllCart(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteAllCart");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartException("장바구니 전체삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int cartUpdate(Connection conn, String cartNo, String productQuantity) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("cartUpdate");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productQuantity);
			pstmt.setString(2, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartException("장바구니 수정 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteOneItem(Connection conn, String cartNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteOneItem");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartException("장바구니 상품삭제 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int cartInsert(Connection conn, Cart cart) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("cartInsert"); 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cart.getProductNo());
			pstmt.setString(2, cart.getUserId());
			pstmt.setInt(3, cart.getProductQuantity());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CartException("장바구니 등록 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
