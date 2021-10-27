package com.kh.afm.user.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.afm.user.model.dao.UserDao;
import com.kh.afm.user.model.vo.User;

public class UserService {

	public static final String USER_ROLE = "U";
	public static final String SELLER_ROLE = "S";
	public static final String ADMIN_ROLE = "A";
	
	private UserDao userDao = new UserDao();
	
	public User selectOneUser(String userId) {
		Connection conn = getConnection();
		User user = userDao.selectOneUSER(conn, userId);
		close(conn);
		return user;
	}


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

		public List<User> selectAllUser(int startRownum, int endRownum) {
			Connection conn = getConnection();
			List<User> list = userDao.selectAllUser(conn, startRownum, endRownum);
			close(conn);
			return list;
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

		public int updateMemberRole(String memberId, String memberRole) {
			Connection conn = getConnection();
			int result = userDao.updateUserRole(conn, memberId, memberRole);
			//DML요청은 트랜잭션처리 필수 
			if(result > 0) commit(conn);
			else rollback(conn);
			close(conn);
			return result;
		}

		public List<User> searchUser(Map<String, Object> param) {
			Connection conn = getConnection();
			List<User> list = userDao.searchUser(conn, param);
			close(conn);
			return list;
		}

		public int selectTotalContents() {
			Connection conn = getConnection();
			int totalContent = userDao.selectTotalContents(conn);
			close(conn);
			return totalContent;
		}

		public int searchUserCount(Map<String, Object> param) {
			Connection conn = getConnection();
			int totalContent = userDao.searchUserCount(conn, param);
			close(conn);
			return totalContent;

		}	



}
