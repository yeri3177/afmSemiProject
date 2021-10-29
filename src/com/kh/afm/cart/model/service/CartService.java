package com.kh.afm.cart.model.service;

import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.rollback;
import static com.kh.afm.common.JdbcTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.cart.model.dao.CartDao;
import com.kh.afm.cart.model.vo.Cart;

public class CartService {
	
	private CartDao cartDao = new CartDao();

	public List<Cart> selectAllList(String userId) {
		Connection conn = getConnection();
		List<Cart> list = cartDao.selectAllList(conn, userId);
		close(conn);
		
		return list;
	}

	public int deleteAllCart(String userId) {
		Connection conn = getConnection();
		int result = cartDao.deleteAllCart(conn, userId);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int cartUpdate(String cartNo, String productQuantity) {
		Connection conn = getConnection();
		int result = cartDao.cartUpdate(conn, cartNo, productQuantity);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int deleteOneItem(String cartNo) {
		Connection conn = getConnection();
		int result = cartDao.deleteOneItem(conn, cartNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int cartInsert(String userId, Cart cart) {
		Connection conn = getConnection();
		int result = cartDao.cartInsert(conn, cart);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}


}
