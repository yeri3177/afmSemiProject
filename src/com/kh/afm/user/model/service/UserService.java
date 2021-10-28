package com.kh.afm.user.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.afm.user.model.dao.UserDao;
import com.kh.afm.user.model.vo.Address;
import com.kh.afm.user.model.vo.User;

public class UserService {

	public static final String USER_ROLE = "U";
	public static final String SELLER_ROLE = "S";
	public static final String ADMIN_ROLE = "A";
	
	private UserDao userDao = new UserDao();
	
	/**
	 * 로그인
	 */
	public User selectOneUser(String userId) {
		Connection conn = getConnection();
		User user = userDao.selectOneUser(conn, userId);
		close(conn);
		return user;
	}

	/**
	 * 회원가입 (User 테이블 행 추가)
	 */
	public int insertUser(User user) {
		Connection conn = getConnection();
		int result = userDao.insertUser(conn, user);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	/**
	 * 회원 수정하기 (회원수정)
	 */
	 public int updateUser(User user) {
			Connection conn = getConnection();
			int result = userDao.updateUser(conn, user);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
			return result;
		}
	 

	    public int deleteUser(String userId) {
			Connection conn = getConnection();
			int result = userDao.deleteUser(conn, userId);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
			return result;
		}


	    public int updatePassword(User user) {
			Connection conn = getConnection();
			int result = userDao.updatePassword(conn, user);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
			return result;
		}

		public int selectTotalContents() {
			Connection conn = getConnection();
			int totalContent = userDao.selectTotalContents(conn);
			close(conn);
			return totalContent;
		}

		/**
		 * 회원가입 (Address 테이블 행 추가)
		 */
		public int insertAddress(Address address) {
			Connection conn = getConnection();
			int result = userDao.insertAddress(conn, address);
			if(result>0)
				commit(conn);
			else 
				rollback(conn);
			close(conn);
			return result;
		}

}
