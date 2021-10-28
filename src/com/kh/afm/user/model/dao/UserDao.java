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
			e.printStackTrace();
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
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			
			// 3.ResultSet -> User
			if(rset.next()) {
				String password = rset.getString("password");
				String userName = rset.getString("user_name");
				String userRole = rset.getString("user_role");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("user_email");
				String phone = rset.getString("phone");
				/* String address = rset.getString("address"); */
				Date enrollDate = rset.getDate("user_enroll_date");
				String userExpose = rset.getString("user_expose"); //회원공개여부
				
				user = new User(userId, userName, email, password, birthday, phone, enrollDate, userRole, userExpose);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원 반납
			close(rset);
			close(pstmt);
		}
		
		return user;
	}

	/**
	 * 회원정보 수정 
	 */
	public int updateUser(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateUser"); 
		System.out.println(sql);
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(sql);
			//쿼리문미완성
			pstmt.setString(1, user.getUserName());
			pstmt.setDate(3, user.getBirthday());
			pstmt.setString(4, user.getUserEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(8, user.getUserId());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
  
    public int deleteUser(Connection conn, String userId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteUser"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, userId);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
