package com.kh.afm.order.model.dao;

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
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.order.model.vo.OrderAddress;
import com.kh.afm.order.model.vo.OrderDetail;

public class OrderDao {
	
private Properties prop = new Properties();
	
	public OrderDao() {
		String filepath = OrderDao.class.getResource("/sql/order/order-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Cart> cartOrder(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("cartOrder");
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
			}finally {
				close(rset);
				close(pstmt);
			}
		return list;
	}

	public List<OrderAddress> adrList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("adrList");
		List<OrderAddress> adrList = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderAddress orderAddress = new OrderAddress();
				orderAddress.setUserId(rset.getString("user_id"));
				orderAddress.setAddressNo(rset.getInt("adr_no"));
				orderAddress.setAddressName(rset.getString("adr_name"));
				orderAddress.setAddressRoad(rset.getString("adr_road"));
				orderAddress.setAddressDetail(rset.getString("adr_detail"));
				adrList.add(orderAddress);
				}
			}catch (SQLException e) {
					e.printStackTrace();
			}finally {
				close(rset);
				close(pstmt);
			}
		return adrList;
	}

	public int lastInsertNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("lastInsertNo");
		int orderNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery(sql);
			if(rset.next()) {
				orderNo = rset.getInt(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return orderNo;
	}

	public int cartOrderInsert(Connection conn, Order order) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("cartOrderInsert"); 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, order.getUserId());
			pstmt.setInt(2, order.getTotalPrice());
			pstmt.setInt(3, order.getAdrNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int cartOrderDetailInsert(Connection conn, OrderDetail orderDetail) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("cartOrderDetailInsert"); 
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderDetail.getProductNo());
			pstmt.setInt(2, orderDetail.getOrderNo());
			pstmt.setInt(3, orderDetail.getpCnt());
			pstmt.setInt(4, orderDetail.getpPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int orderSuccess(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("orderSuccess");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
