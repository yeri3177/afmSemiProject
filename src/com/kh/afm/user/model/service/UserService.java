package com.kh.afm.user.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.user.model.dao.UserDao;
import com.kh.afm.user.model.vo.Account;
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
	 * 회원 정보 수정하기 (address 테이블)
	 * @param address : 수정하려는 새 주소 객체
	 * @return int : 수정성공여부 
	 */
	public int updateAddress(Address address) {
		Connection conn = getConnection();
		int result = userDao.updateAddress(conn, address);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 회원 정보 수정하기 (tb_account 테이블)
	 */
	public int updateAccount(Account account) {
		Connection conn = getConnection();
		int result = userDao.updateAccount(conn, account);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}
	
	/**
	 * 회원 정보 수정하기 (tb_user 테이블)
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
	 * 회원가입 (address 테이블 행 추가)
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
	/**
	 * 회원가입 (tb_account 테이블 행 추가)
	 */
	public int insertAccount(Account tb_account) {
		Connection conn = getConnection();
		int result = userDao.insertAccount(conn, tb_account);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 회원의 주소목록 조회
	 * @param userId : 회원아이디
	 * @return List<Address> : 주소 리스트
	 */
	public List<Address> selectUserAddress(String userId) {
		Connection conn = getConnection();
		List<Address> list = userDao.selectUserAddress(conn, userId);
		close(conn);		
		
		return list;
	}

	/**
	 * 주소 삭제하기
	 * @param addressNo : 삭제할 주소번호
	 * @return int : 삭제성공여부
	 */
	public int deleteAddress(int addressNo) {
		Connection conn = getConnection();
		int result = userDao.deleteAddress(conn, addressNo);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	/**
	 * 차단 회원인지 조회하기
	 * @param userId : 로그인한 회원 아이디
	 * @return User : 로그인하려는 회원 객체 
	 */
	public User selectBlockUser(String userId) {
		Connection conn = getConnection();
		User user = userDao.selectBlockUser(conn, userId);
		close(conn);
		return user;
	}
}
