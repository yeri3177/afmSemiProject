package com.kh.afm.user.model.dao;

import static com.kh.afm.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.afm.user.model.exception.UserException;
import com.kh.afm.user.model.vo.Account;
import com.kh.afm.user.model.vo.Address;
import com.kh.afm.user.model.vo.User;

public class UserDao {
	
private Properties prop = new Properties();
	
	public UserDao() {
		String filepath = UserDao.class.getResource("/sql/user/user-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 회원가입 (User 테이블 행 추가)
	 */
	public int insertUser(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertUser"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserName());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setDate(5, user.getBirthday());
			pstmt.setString(6, user.getPhone());
			/* getUserEnrollDate */ //디폴트값
			pstmt.setString(7, user.getUserRole());
			/* getUserExpose */ //디폴트값
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("회원가입 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 로그인
	 */
	public User selectOneUser(Connection conn, String userId) {
		String sql = prop.getProperty("selectOneUser");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		Account account = null;
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			
			// 3.ResultSet -> User
			if(rset.next()) {
				//tb_user테이블 정보
				String password = rset.getString("password");
				String userName = rset.getString("user_name");
				String userRole = rset.getString("user_role");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("user_email");
				String phone = rset.getString("phone");
				Date enrollDate = rset.getDate("user_enroll_date");
				String userExpose = rset.getString("user_expose"); 
				
				//tb_account 정보
				int account_no = rset.getInt("account_no");
				String account_number = rset.getString("account_number");
				String bank_name = rset.getString("bank_name");
				
				// 계좌정보 셋팅 
				account = new Account(account_no, account_number, bank_name, userId);
				
				// 회원정보 셋팅 
				user = new User(userId, userName, email, password, birthday, phone, enrollDate, userRole, userExpose, account, null);
			}
		} catch (SQLException e) {
			throw new UserException("로그인 오류", e);
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return user;
	}

	/**
	 * 회원 정보 수정하기 (tb_user 테이블)
	 */
	public int updateUser(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateUser"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			
			//쿼리문미완성
			pstmt.setString(1, user.getUserEmail());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getUserId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("회원정보 수정 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 회원 정보 수정하기 (address 테이블)
	 */
	public int updateAddress(Connection conn, Address address) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAddress"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			
			//쿼리문미완성
			pstmt.setString(1, address.getAdrName());
			pstmt.setString(2, address.getAdrRoad());
			pstmt.setString(3, address.getAdrDetail());
			pstmt.setInt(4, address.getAdrNo());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("주소 수정 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 회원 정보 수정하기 (tb_account 테이블)
	 */
	public int updateAccount(Connection conn, Account account) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAccount"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			
			//쿼리문미완성
			pstmt.setString(1, account.getAccountNumber());
			pstmt.setString(2, account.getBankName());
			pstmt.setString(3, account.getUserId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("계좌 수정 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}
  /**
   * 탈퇴하기
   */
    public int deleteUser(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteUser"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("회원 탈퇴 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
    /**
	 * 비밀번호 수정 
	 */
    public int updatePassword(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUserId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("비밀번호 수정 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}


    /**
	 * 페이징 - 검색결과의 전체회원수
	 */
	public int selectTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			throw new UserException("페이징 처리 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}

	/**
	 * 회원가입 (Address 테이블 행 추가)
	 */
	public int insertAddress(Connection conn, Address address) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertAddress"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성
			pstmt = conn.prepareStatement(query);
			
			//쿼리문미완성
			pstmt.setString(1, address.getUserId());
			pstmt.setString(2, address.getAdrName());
			pstmt.setString(3, address.getAdrRoad());
			pstmt.setString(4, address.getAdrDetail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("주소 테이블 추가 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	/**
	 * 회원가입 (Account 테이블 행 추가)
	 */
	public int insertAccount(Connection conn, Account account) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertAccount"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성
			pstmt = conn.prepareStatement(query);
			
			//쿼리문미완성
			pstmt.setString(1, account.getUserId());
			pstmt.setString(2, account.getAccountNumber());
			pstmt.setString(3, account.getBankName());
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("계좌 테이블 추가 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 회원의 주소목록 조회
	 * @param userId : 회원아이디
	 * @return List<Address> : 주소 리스트
	 */
	public List<Address> selectUserAddress(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectUserAddress");
		ResultSet rset = null;
		List<Address> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();


			while (rset.next()) {
				Address address = new Address();
				address.setAdrNo(rset.getInt("adr_no"));
				address.setUserId(rset.getString("user_id"));
				address.setAdrName(rset.getString("adr_name"));
				address.setAdrRoad(rset.getString("adr_road"));
				address.setAdrDetail(rset.getString("adr_detail"));

				list.add(address);
			}

		} catch (SQLException e) {
			throw new UserException("회원 주소 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 주소 삭제하기
	 */
	public int deleteAddress(Connection conn, int addressNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteAddress"); 

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, addressNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new UserException("주소 삭제 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 차단 회원인지 조회하기
	 */
	public User selectBlockUser(Connection conn, String userId) {
		String sql = prop.getProperty("selectBlockUser");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		User user = null;
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			
			// 3.ResultSet -> User
			if(rset.next()) {
				
				// 회원정보 셋팅 
				user = new User();
			}
		} catch (SQLException e) {
			throw new UserException("차단 회원 조회 오류", e);
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return user;
	}
}
