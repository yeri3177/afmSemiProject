package com.kh.afm.admin.model.dao;

import static com.kh.afm.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.afm.user.model.vo.DelUser;
import com.kh.afm.user.model.vo.User;

public class AdminDao {
	
	/**
	 * 프로퍼티즈
	 */
	private Properties prop = new Properties();
	
	/**
	 * AdminDao 생성자 
	 */
	public AdminDao() {
		String filepath = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 전체 회원 조회
	 */
	public List<User> selectAllMember(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllUser"); 
		ResultSet rset = null;
		List<User> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				User user = new User();
				user.setUserId(rset.getString("user_id"));
				user.setUserName(rset.getString("user_name"));
				user.setUserEmail(rset.getString("user_email"));
				user.setPassword(rset.getString("password"));
				user.setBirthday(rset.getDate("birthday"));
				user.setPhone(rset.getString("phone"));
				user.setUserEnrollDate(rset.getDate("user_enroll_date"));
				user.setUserRole(rset.getString("user_role"));
				user.setUserExpose(rset.getString("user_expose"));

				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	/**
	 * 페이징 - 전체회원수 
	 */
	public int selectTotalContents(Connection conn) {
		String sql = prop.getProperty("selectTotalContents");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			
			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();
			
			// 3.ResultSet -> totalContents
			if(rset.next()) {
				totalContents = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContents;
	}


	/**
	 * 탈퇴한 전체 회원 조회
	 */
	public List<DelUser> selectAllDelUser(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllDelUser"); 
		ResultSet rset = null;
		List<DelUser> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DelUser delUser = new DelUser();
				delUser.setDeleteUId(rset.getString("delete_u_id"));
				delUser.setDeleteUName(rset.getString("delete_u_name"));
				delUser.setDeleteUEmail(rset.getString("delete_u_email"));
				delUser.setDeletePassword(rset.getString("delete_password"));
				delUser.setDeleteBirthday(rset.getDate("delete_birthday"));
				delUser.setDeletePhone(rset.getString("delete_phone"));
				delUser.setDeleteUEnroll_date(rset.getDate("delete_u_enroll_date"));
				delUser.setUserRole(rset.getString("user_role"));
				delUser.setUserExpose(rset.getString("user_expose"));
				delUser.setDeleteUDate(rset.getDate("delete_u_date"));

				list.add(delUser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	/**
	  * 회원 검색하기 
	  */
	public List<User> searchUser(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<User> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");
		
		switch(searchType) {
		case "userId":
			sql = prop.getProperty("searchUserByUserId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "userName":
			sql = prop.getProperty("searchUserByUserName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "userRole":
			sql = prop.getProperty("searchUserByUserRole");
			break;
		}
		
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
				user.setUserId(rset.getString("user_id"));
				user.setUserName(rset.getString("user_name"));
				user.setUserEmail(rset.getString("user_email"));
				user.setPassword(rset.getString("password"));
				user.setBirthday(rset.getDate("birthday"));
				user.setPhone(rset.getString("phone"));
				user.setUserEnrollDate(rset.getDate("user_enroll_date"));
				user.setUserRole(rset.getString("user_role"));
				user.setUserExpose(rset.getString("user_expose"));
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}


	/**
	 * 검색결과의 전체회원수
	 */
	public int searchUserCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;
		
		String sql = null;
		String searchType = (String) param.get("searchType");
		switch(searchType) {
		case "userId":
			sql = prop.getProperty("searchUserCountByUserId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%"); 
			break;
		case "userName":
			sql = prop.getProperty("searchUserCountByUserName");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "userRole":
			sql = prop.getProperty("searchUserCountByUserRole");
			break;
		}
		
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


	/**
	 * 회원 정렬하기
	 */
	public List<User> sortUser(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<User> list = new ArrayList<>();
		String sql = null;
		String sortType = (String) param.get("sortType");
		String sortKeyword = (String) param.get("sortKeyword");
		
		if("asc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserIdOrderAsc");
		}
		else if("asc".equals(sortType) && "userName".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserNameOrderAsc");
		}
		else if("asc".equals(sortType) && "userRole".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserRoleOrderAsc");
		}
		else if("desc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserIdOrderDesc");
		}
		else if("desc".equals(sortType) && "userName".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserNameOrderDesc");
		}
		else if("desc".equals(sortType) && "userRole".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserRoleOrderDesc");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));
			
			
			// 2. 쿼리실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
				User user = new User();
				user.setUserId(rset.getString("user_id"));
				user.setUserName(rset.getString("user_name"));
				user.setUserEmail(rset.getString("user_email"));
				user.setPassword(rset.getString("password"));
				user.setBirthday(rset.getDate("birthday"));
				user.setPhone(rset.getString("phone"));
				user.setUserEnrollDate(rset.getDate("user_enroll_date"));
				user.setUserRole(rset.getString("user_role"));
				user.setUserExpose(rset.getString("user_expose"));
				list.add(user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
}
