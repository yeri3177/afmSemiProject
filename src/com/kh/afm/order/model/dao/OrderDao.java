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
import com.kh.afm.order.model.exception.OrderException;
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.order.model.vo.OrderAddress;
import com.kh.afm.order.model.vo.OrderDetail;
import com.kh.afm.order.model.vo.OrderJoinAll;
import com.kh.afm.product.model.vo.Product;

public class OrderDao {
	
private Properties prop = new Properties();
	
	public OrderDao() {
		String filepath = OrderDao.class.getResource("/sql/order/order-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
			throw new OrderException("주문 쿼리 오류", e);
		}
	}

	public List<Cart> cartOrder(Connection conn, String userId) {
		List<Cart> list = null;
		try {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("cartOrder");
			list = new ArrayList<>();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("장바구니 주문 오류", e);
		}
		return list;
	}

	public List<OrderAddress> adrList(Connection conn, String userId) {
		List<OrderAddress> adrList = null;
		try {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("adrList");
			adrList = new ArrayList<>();
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("배송지주소 조회 오류", e);
		}
		return adrList;
	}

	public int lastInsertNo(Connection conn) {
		int orderNo = 0;
		try {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("lastInsertNo");
			orderNo = 0;
			
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
				close(rset);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("주문 last Index 조회 오류", e);
		}
		return orderNo;
	}

	public int cartOrderInsert(Connection conn, Order order) {
		int result = 0;
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("주문 처리 오류", e);
		}
		
		return result;
	}

	public int cartOrderDetailInsert(Connection conn, OrderDetail orderDetail) {
		int result = 0;
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("주문상세 처리 오류", e);
		}
		
		return result;
	}

	public int orderSuccess(Connection conn, String userId) {
		int result = 0;
		try {
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("주문 성공 조회 오류", e);
		}
		return result;
	}

	public String orderDetailCheckList(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String csvStr = null;
		try {
			StringBuilder csv = new StringBuilder();
			String sql = prop.getProperty("orderDetailCheckList");
			List<OrderJoinAll> orderDetailList = new ArrayList<>();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, orderNo);
				
				rset = pstmt.executeQuery();
				while (rset.next()) {
					OrderJoinAll orderJoinAll = new OrderJoinAll();
					orderJoinAll.setOrderId(rset.getString("user_id"));
					orderJoinAll.setSellerId(rset.getString("p_user_id"));
					orderJoinAll.setpNo(rset.getInt("p_no"));
					orderJoinAll.setpTitle(rset.getString("p_title"));
					orderJoinAll.setOrderCnt(rset.getInt("p_cnt"));
					orderJoinAll.setOrderPrice(rset.getInt("p_price"));
					orderJoinAll.setOrderNo(rset.getInt("order_no"));
					orderJoinAll.setAccountNo(rset.getInt("account_number"));
					orderJoinAll.setBankName(rset.getString("bank_name"));
					orderDetailList.add(orderJoinAll);
					}
				for(int i = 0; i < orderDetailList.size(); i++){
					csv.append(orderDetailList.get(i));
					if(i != orderDetailList.size() -1)
					csv.append("\n");
				}
				csvStr = csv.toString();
				}catch (SQLException e) {
						e.printStackTrace();
				}finally {
					close(rset);
					close(pstmt);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new OrderException("주문내역 상세조회 오류", e);
		}
		return csvStr;
	}

	public List<Order> orderCheckList(Connection conn, String userId) {
		List<Order> orderList = null;
		try {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = prop.getProperty("orderCheckList");
			orderList = new ArrayList<>();
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				
				rset = pstmt.executeQuery();
				while (rset.next()) {
					Order order = new Order();
					order.setUserId(rset.getString("user_id"));
					order.setOrderNo(rset.getInt("order_no"));
					order.setOrderDate(rset.getDate("order_date"));
					order.setTotalPrice(rset.getInt("total_price"));
					order.setAdrNo(rset.getInt("adr_no"));
					orderList.add(order);
					}
				}catch (SQLException e) {
						e.printStackTrace();
						throw new OrderException("주문내역 조회 오류", e);
				}finally {
					close(rset);
					close(pstmt);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderList;
	}

	/**
	 * 로그인한 회원의 구매내역 찾기 (tb_order 테이블)
	 * @param userId : 로그인한 회원아이디
	 * @return List<Order> : 로그인한 회원의 order 데이터 리스트
	 */
	public List<Order> selectOrderComplete(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderComplete");
		List<Order> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Order order = new Order();
				order.setOrderNo(rset.getInt("order_no"));
				order.setUserId(rset.getString("user_id"));
				order.setOrderDate(rset.getDate("order_date"));
				order.setTotalPrice(rset.getInt("total_price"));

				list.add(order);
			}
			
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 로그인한 회원의 구매내역 찾기 (order_detail 테이블)
	 * @param orderNo : 클릭한 해당 주문번호
	 * @return List<OrderDetail> : 해당 주문번호의 주문상세내역 정보
	 */
	public List<OrderDetail> selectOrderCompleteDetail(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOrderCompleteDetail");
		List<OrderDetail> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orderNo);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderDetailNo(rset.getInt("order_detail_no"));
				orderDetail.setProductNo(rset.getInt("p_no"));
				orderDetail.setOrderNo(rset.getInt("order_no"));
				orderDetail.setpCnt(rset.getInt("p_cnt"));
				orderDetail.setpPrice(rset.getInt("p_price"));
				orderDetail.setPayStatus(rset.getString("order_status"));

				list.add(orderDetail);
			}
			
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	public String productNameCheck(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String str = null;
		String sql = prop.getProperty("productNameCheck");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				str = rset.getString("p_title");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		return str;
	}

	/**
	 * 로그인한 판매자의 상품내역 찾기 (product 테이블)
	 * @param userId : 로그인한 판매자 아이디
	 * @return List<Product> : 상품 리스트
	 */
	public List<Product> selectSellerProductList(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSellerProductList");
		List<Product> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Product product = new Product();
				product.setpNo(rset.getInt("p_no"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpTitle(rset.getString("p_title"));
				product.setpContent(rset.getString("p_content"));
				product.setpPost(rset.getString("p_post"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				product.setpExpose(rset.getString("p_expose"));
				product.setpReport(rset.getString("p_report"));
				product.setpRecommend(rset.getInt("p_recommend"));

				list.add(product);
			}
			
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 클릭한 해당 상품의 결제상세내역 찾기 (order_detail 테이블)
	 * @param productNo : 클릭한 해당 상품번호
	 * @return List<OrderDetail> : 해당 상품의 주문내역 리스트
	 */
	public List<OrderDetail> selectSellerOrderDetailList(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectSellerOrderDetailList");
		List<OrderDetail> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, productNo);
			
			rset = pstmt.executeQuery();
			while (rset.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrderDetailNo(rset.getInt("order_detail_no"));
				orderDetail.setProductNo(rset.getInt("p_no"));
				orderDetail.setOrderNo(rset.getInt("order_no"));
				orderDetail.setpCnt(rset.getInt("p_cnt"));
				orderDetail.setpPrice(rset.getInt("p_price"));
				orderDetail.setPayStatus(rset.getString("order_status"));
				
				list.add(orderDetail);
			}
			
		}catch (SQLException e) {
				e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 판매자의 결제처리상태 확인 처리
	 * @param orderDetailNo : 해당 주문상세번호
	 * @param orderStatus : 결제처리상태
	 * @return 메소드성공여부 
	 */
	public int updateOrderStatus(Connection conn, int orderDetailNo, String orderStatus) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateOrderStatus"); 
		
		try {
			// 미완성쿼리문 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 셋팅
			pstmt.setString(1, orderStatus);
			pstmt.setInt(2, orderDetailNo);
			
			// 쿼리실행 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

//	public int cartOrderProductCntCheck(Connection conn, int pNo) {
//		PreparedStatement pstmt = null;
//		ResultSet rset = null;
//		String sql = prop.getProperty("cartOrderProductCntCheck");
//		int orderNo = 0;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, pNo);
//			
//			rset = pstmt.executeQuery();
//			if(rset.next()) {
//				orderNo = rset.getInt(1);
//			}
//		}catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close(pstmt);
//			close(rset);
//		}
//		return orderNo;
//	}
}
