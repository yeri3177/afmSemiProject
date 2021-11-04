package com.kh.afm.order.model.service;

import static com.kh.afm.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.afm.cart.model.vo.Cart;
import com.kh.afm.order.model.dao.OrderDao;
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.order.model.vo.OrderAddress;
import com.kh.afm.order.model.vo.OrderDetail;
import com.kh.afm.product.model.vo.Product;

public class OrderService {
	OrderDao orderDao = new OrderDao();

	public List<Cart> cartOrder(String userId) {
		List<Cart> list = null;
		try {
			Connection conn = getConnection();
			list = orderDao.cartOrder(conn, userId);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public List<OrderAddress> adrList(String userId) {
		List<OrderAddress> adrList = null;
		try {
			Connection conn = getConnection();
			adrList = orderDao.adrList(conn, userId);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adrList;
	}

	public int cartOrderInsert(Order order) {
		int orderNo = 0;
		try {
			Connection conn = getConnection();
			int result = orderDao.cartOrderInsert(conn, order);
			if(result != 0) {
				orderNo = orderDao.lastInsertNo(conn);
			}
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderNo;
	}

	public int cartOrderDetailInsert(OrderDetail orderDetail) {
		int result = 0;
		try (Connection conn = getConnection()) {
			result = orderDao.cartOrderDetailInsert(conn, orderDetail);
			close(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int orderSuccess(String userId) {
		int result = 0;
		try {
			Connection conn = getConnection();
			result = orderDao.orderSuccess(conn, userId);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String orderDetailCheckList(int orderNo) {
		String csv = "";
		try {
			Connection conn = getConnection();
			csv = orderDao.orderDetailCheckList(conn, orderNo);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csv;
	}

	public List<Order> orderCheckList(String userId) {
		List<Order> orderList = null;
		try {
			Connection conn = getConnection();
			orderList = orderDao.orderCheckList(conn, userId);
			close(conn);
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
	public List<Order> selectOrderComplete(String userId) {
		Connection conn = getConnection();
		List<Order> list = orderDao.selectOrderComplete(conn, userId);
		close(conn);
		return list;
	}

	/**
	 * 로그인한 회원의 구매내역 찾기 (order_detail 테이블)
	 * @param orderNo : 클릭한 해당 주문번호
	 * @return List<OrderDetail> : 해당 주문번호의 주문상세내역 정보
	 */
	public List<OrderDetail> selectOrderCompleteDetail(int orderNo) {
		Connection conn = getConnection();
		List<OrderDetail> list = orderDao.selectOrderCompleteDetail(conn, orderNo);
		close(conn);
		return list;
	}

	public String productNameCheck(int productNo) {
		Connection conn = getConnection();
		String str = orderDao.productNameCheck(conn, productNo);
		close(conn);
		return str;
	}
	
	/**
	 * 로그인한 판매자의 상품내역 찾기 (product 테이블)
	 * @param userId : 로그인한 판매자 아이디
	 * @return List<Product> : 상품 리스트
	 */
	public List<Product> selectSellerProductList(String userId) {
		Connection conn = getConnection();
		List<Product> list = orderDao.selectSellerProductList(conn, userId);
		close(conn);
		return list;
	}

	/**
	 * 클릭한 해당 상품의 결제상세내역 찾기 (order_detail 테이블)
	 * @param productNo : 클릭한 해당 상품번호
	 * @return List<OrderDetail> : 해당 상품의 주문내역 리스트
	 */
	public List<OrderDetail> selectSellerOrderDetailList(int productNo) {
		Connection conn = getConnection();
		List<OrderDetail> list = orderDao.selectSellerOrderDetailList(conn, productNo);
		close(conn);
		return list;
	}

	/**
	 * 판매자의 결제처리상태 확인 처리
	 * @param orderDetailNo : 해당 주문상세번호
	 * @param orderStatus : 결제처리상태
	 * @return 메소드성공여부 
	 */
	public int updateOrderStatus(int orderDetailNo, String orderStatus) {
		Connection conn = getConnection();
		int result = orderDao.updateOrderStatus(conn, orderDetailNo, orderStatus);
		
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		
		return result;
	}

//	public int cartOrderProductCntCheck(int pNo) {
//		Connection conn = getConnection();
//		int pCnt = orderDao.cartOrderProductCntCheck(conn, pNo);
//		close(conn);
//		return pCnt;
//	}

}
