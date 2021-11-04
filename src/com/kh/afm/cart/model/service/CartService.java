package com.kh.afm.cart.model.service;

import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.rollback;
import static com.kh.afm.common.JdbcTemplate.close;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.cart.model.dao.CartDao;
import com.kh.afm.cart.model.exception.CartException;
import com.kh.afm.cart.model.vo.Cart;

public class CartService {
	
	private CartDao cartDao = new CartDao();

	public List<Cart> selectAllList(String userId) {
		List<Cart> list = null;;
		try {
			Connection conn = getConnection();
			list = cartDao.selectAllList(conn, userId);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 조회 service 오류", e);
		}
		
		return list;
	}

	public int deleteAllCart(String userId) {
		int result = 0;
		try {
			Connection conn = getConnection();
			result = cartDao.deleteAllCart(conn, userId);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 전체삭제 service 오류", e);
		}
		return result;
	}

	public int cartUpdate(String cartNo, String productQuantity) {
		int result = 0;
		try {
			Connection conn = getConnection();
			result = cartDao.cartUpdate(conn, cartNo, productQuantity);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 수정 service 오류", e);
		}
		return result;
	}

	public int deleteOneItem(String cartNo) {
		int result = 0;
		try {
			Connection conn = getConnection();
			result = cartDao.deleteOneItem(conn, cartNo);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 조회 service 오류", e);
		}
		return result;
	}

	public int cartInsert(String userId, Cart cart) {
		int result = 0;
		try {
			Connection conn = getConnection();
			result = cartDao.cartInsert(conn, cart);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CartException("장바구니 등록 service 오류", e);
		}
		return result;
	}


}
