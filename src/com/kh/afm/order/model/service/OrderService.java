package com.kh.afm.order.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.cart.model.vo.Cart;
import com.kh.afm.order.model.dao.OrderDao;
import com.kh.afm.order.model.vo.Order;
import com.kh.afm.order.model.vo.OrderAddress;
import com.kh.afm.order.model.vo.OrderDetail;

public class OrderService {
	OrderDao orderDao = new OrderDao();

	public List<Cart> cartOrder(String userId) {
		Connection conn = getConnection();
		List<Cart> list = orderDao.cartOrder(conn, userId);
		close(conn);
		
		return list;
	}

	public List<OrderAddress> adrList(String userId) {
		Connection conn = getConnection();
		List<OrderAddress> adrList = orderDao.adrList(conn, userId);
		close(conn);
		return adrList;
	}

	public int cartOrderInsert(Order order) {
		Connection conn = getConnection();
		int orderNo = 0;
		int result = orderDao.cartOrderInsert(conn, order);
		if(result != 0) {
			orderNo = orderDao.lastInsertNo(conn);
		}
		close(conn);
		return orderNo;
	}

	public int cartOrderDetailInsert(OrderDetail orderDetail) {
		Connection conn = getConnection();
		int result = orderDao.cartOrderDetailInsert(conn, orderDetail);
		close(conn);
		return result;
	}

	public int orderSuccess(String userId) {
		Connection conn = getConnection();
		int result = orderDao.orderSuccess(conn, userId);
		close(conn);
		return result;
	}

	public String orderDetailCheckList(int orderNo) {
		Connection conn = getConnection();
		String csv = orderDao.orderDetailCheckList(conn, orderNo);
		close(conn);
		return csv;
	}

	public List<Order> orderCheckList(String userId) {
		Connection conn = getConnection();
		List<Order> orderList = orderDao.orderCheckList(conn, userId);
		close(conn);
		return orderList;
	}

//	public int cartOrderProductCntCheck(int pNo) {
//		Connection conn = getConnection();
//		int pCnt = orderDao.cartOrderProductCntCheck(conn, pNo);
//		close(conn);
//		return pCnt;
//	}

}
