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
import java.util.Properties;

import com.kh.afm.user.model.vo.User;

public class AdminDao {
	
	/**
	 * 프로퍼티즈
	 */
	private Properties prop = new Properties();
	
	/**
	 * MemberDao 생성자 
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
		System.out.println("sql = " + sql);
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
				user.setBirthday(rset.getString("birthday"));
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
}
