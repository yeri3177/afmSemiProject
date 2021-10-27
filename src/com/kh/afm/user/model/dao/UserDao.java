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
	
	public int insertUser(Connection conn, User user) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertUser"); 
		
		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserRole());
			pstmt.setString(6, user.getBirthday());
			pstmt.setString(7, user.getUserEmail());
			pstmt.setString(8, user.getPhone());
			
			
			
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

	
	public User selectOneUSER(Connection conn, String userId) {
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
				String birthday = rset.getString("birthday");
				String email = rset.getString("email");
				String phone = rset.getString("phone");
				String address = rset.getString("address");
				Date enrollDate = rset.getDate("enroll_date");
				String userExpose = rset.getString("user_expose"); //회원공개여부
				
				user = new User(userId, userName,  email, password, birthday, phone, enrollDate, userRole, userExpose, address );
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
			pstmt.setString(3, user.getBirthday());
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

	public List<User> selectAllUser(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllMember"); 
		// select * from (select row_number() over(order by enroll_date desc) rnum, m.* from member m ) m where rnum between ? and ?
		ResultSet rset = null;
		List<User> list = new ArrayList<>();
		
		try {
			// 1.pstmt객체생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			// 2.쿼리실행
			rset = pstmt.executeQuery();
			// 3.rset -> list
			while(rset.next()) {
				User user = new User();
				user.setUserId(rset.getString("user_id"));
				user.setPassword(rset.getString("password"));
				user.setUserName(rset.getString("user_name"));
				user.setUserRole(rset.getString("user_role"));
				user.setBirthday(rset.getString("birthday"));
				user.setUserEmail(rset.getString("email"));
				user.setPhone(rset.getString("phone"));
				user.setUserEnrollDate(rset.getDate("enroll_date"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}

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

    public int updateUserRole(Connection conn, String userId, String userRole) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateUserRole"); 

		try {
			//PreparedStatement객체생성 및 쿼리 값대입
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userRole);
			pstmt.setString(2, userId);

			//Statement객체 실행 : DML은 executeUpdate()
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<User> searchUser(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<User> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		switch(searchType) {
		case "userId":
			sql = prop.getProperty("searchMemberByMemberId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "memberName":
			sql = prop.getProperty("searchUserByUserName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "gender":
			sql = prop.getProperty("searchUserByGender");
			break;
		}
		System.out.println("sql@dao = " + sql);
		
		try {
			// 1. PreparedStatement객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			pstmt.setInt(2, (int) param.get("start"));
			pstmt.setInt(3, (int) param.get("end"));
			
			// 2. 쿼리실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				User user = new User();
				user.setUserId(rset.getString("member_id"));
				user.setPassword(rset.getString("password"));
				user.setUserName(rset.getString("member_name"));
				user.setUserRole(rset.getString("member_role"));
				user.setBirthday(rset.getString("birthday"));
				user.setUserEmail(rset.getString("email"));
				user.setPhone(rset.getString("phone"));
				user.setUserEnrollDate(rset.getDate("enroll_date"));
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}

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

	public int searchUserCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		switch(searchType) {
		case "userId":
			sql = prop.getProperty("searchMemberCountByMemberId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "userName":
			sql = prop.getProperty("searchMemberCountByMemberName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		}
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
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





}
