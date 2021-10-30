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

import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.user.model.vo.Account;
import com.kh.afm.user.model.vo.Address;
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

			while (rset.next()) {
				// 회원객체
				User user = new User();
				user.setUserId(rset.getString("user_id"));
				user.setUserName(rset.getString("user_name"));
				user.setUserEmail(rset.getString("user_email"));
				/* user.setPassword(rset.getString("password")); */
				/* user.setBirthday(rset.getDate("birthday")); */
				user.setPhone(rset.getString("phone"));
				user.setUserEnrollDate(rset.getDate("user_enroll_date"));
				user.setUserRole(rset.getString("user_role"));
				user.setUserExpose(rset.getString("user_expose"));
				
				
				if(rset.getString("user_id") != null) {
					// 계좌
					Account account = new Account();
					account.setAccountNo(rset.getInt("account_no"));
					account.setBankName(rset.getString("bank_name"));
					account.setAccountNumber(rset.getString("account_number"));
					
					// 주소
					Address address = new Address();
					address.setAdrNo(rset.getInt("adr_no"));
					address.setAdrName(rset.getString("adr_name"));
					address.setAdrRoad(rset.getString("adr_road"));
					address.setAdrDetail(rset.getString("adr_detail"));
					
					user.setAccount(account);
					user.setAddress(address);
				}
				
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
			if (rset.next()) {
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

			while (rset.next()) {
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

		switch (searchType) {
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
			while (rset.next()) {
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
				
				if(rset.getString("user_id") != null) {
					// 계좌
					Account account = new Account();
					account.setAccountNo(rset.getInt("account_no"));
					account.setBankName(rset.getString("bank_name"));
					account.setAccountNumber(rset.getString("account_number"));
					
					// 주소
					Address address = new Address();
					address.setAdrNo(rset.getInt("account_no"));
					address.setAdrName(rset.getString("adr_name"));
					address.setAdrRoad(rset.getString("adr_road"));
					address.setAdrDetail(rset.getString("adr_detail"));
					
					user.setAccount(account);
					user.setAddress(address);
				}
				
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
		switch (searchType) {
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
			if (rset.next())
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

		if ("asc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserIdOrderAsc");
		} else if ("asc".equals(sortType) && "userName".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserNameOrderAsc");
		} else if ("asc".equals(sortType) && "userRole".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserRoleOrderAsc");
		} else if ("desc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserIdOrderDesc");
		} else if ("desc".equals(sortType) && "userName".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserNameOrderDesc");
		} else if ("desc".equals(sortType) && "userRole".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserByUserRoleOrderDesc");
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));

			// 2. 쿼리실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			while (rset.next()) {
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
				
				if(rset.getString("user_id") != null) {
					// 계좌
					Account account = new Account();
					account.setAccountNo(rset.getInt("account_no"));
					account.setBankName(rset.getString("bank_name"));
					account.setAccountNumber(rset.getString("account_number"));
					
					// 주소
					Address address = new Address();
					address.setAdrNo(rset.getInt("account_no"));
					address.setAdrName(rset.getString("adr_name"));
					address.setAdrRoad(rset.getString("adr_road"));
					address.setAdrDetail(rset.getString("adr_detail"));
					
					user.setAccount(account);
					user.setAddress(address);
				}
				
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
	 * 메인페이지 쿼리
	 */
	public Map<String, Integer> adminMainQuery (Connection conn, Map<String, Integer> param) {

		// param 키값 4개 
		String key_Arr[] = {"recentlyUserCnt", "allUserCnt", "recentlyProdCnt", "allProdCnt"};
		
		// 개수
		int cnt = 0;
		
		// 향상된 for문 
		for(String key : key_Arr) {
			
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			String sql = null;
			
			if(key == key_Arr[0]) {
				sql = prop.getProperty("countUserWeek");
			}
			else if(key == key_Arr[1]) {
				sql = prop.getProperty("countUserAll");
			}
			else if(key == key_Arr[2]) {
				sql = prop.getProperty("countProductWeek");
			}
			else if(key == key_Arr[3]) {
				sql = prop.getProperty("countProductAll");
			}
			
			try {
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					cnt = rset.getInt(1);
				}
				
				param.put(key, cnt);
				System.out.println("key = " + key + ", cnt = " + cnt);
				
			}
			catch (SQLException e) {
				e.printStackTrace();
			} 
			finally {
				
				close(rset);
				close(pstmt);
			}
		}
		return param;
	}

	/**
	 * 페이징 - 전체탈퇴회원수 
	 */
	public int selectDelUserTotalContents(Connection conn) {
		String sql = prop.getProperty("selectDelUserTotalContents");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;

		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);

			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();

			// 3.ResultSet -> totalContents
			if (rset.next()) {
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
	 * 사용자 공개여부 변경 
	 */
	public int updateUserExpose(Connection conn, String userId, String userExpose) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateUserExpose"); 
		
		try {
			// 미완성쿼리문 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 셋팅
			pstmt.setString(1, userExpose);
			pstmt.setString(2, userId);
			
			// 쿼리실행 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 탈퇴회원 데이터 삭제하기 
	 */
	public int deleteDelUser(Connection conn, String[] userId_arr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteDelUser"); 
		String [] arr_sql = new String[userId_arr.length];
		String sql_val = "";
		
		// 배열값 -> ''로 감싸기
		for(int i=0; i<userId_arr.length; i++) {
			arr_sql[i] = "'" + userId_arr[i] + "'";
		}
		
		sql_val = arr_sql.toString();
		System.out.println("sql_val = "+sql_val);
		
		try {
			// 미완성쿼리문 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 셋팅
			pstmt.setString(1, sql_val);
			
			// 쿼리실행 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


	/**
	 * 상품 목록 조회하기 
	 */
	public List<Product> selectAllProduct(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllProduct");
		ResultSet rset = null;


		List<Product> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);

			rset = pstmt.executeQuery();


			while (rset.next()) {
				// 상품
				Product product = new Product();
				product.setpNo(rset.getInt("p_no"));
				product.setUserId(rset.getString("p_user_id"));
				product.setUserId(rset.getString("p_reg_date"));
				product.setUserId(rset.getString("p_title"));
				product.setUserId(rset.getString("p_content"));
				product.setUserId(rset.getString("p_post"));
				product.setUserId(rset.getString("p_price"));
				product.setUserId(rset.getString("p_cnt"));
				product.setUserId(rset.getString("p_category"));
				product.setUserId(rset.getString("p_expose"));
				product.setUserId(rset.getString("p_report"));
				product.setUserId(rset.getString("p_recommend"));
				
				
				if(rset.getInt("p_no") != 0) {
					
					if(rset.getString("img_flag").equals("Y")) {
						Attachment attach = new Attachment();
						attach.setAttachNo(rset.getInt("attach_no"));
						attach.setpNo(rset.getInt("p_no"));
						attach.setOriginalFileName(rset.getString("original_filename"));
						attach.setRenamedFileName(rset.getString("renamed_filename"));
						attach.setRegDate(rset.getDate("reg_date"));
						attach.setImgFlag(rset.getString("img_flag"));
						
						product.setAttach1(attach);
					}
					
					if(rset.getString("img_flag").equals("N")) {
						Attachment attach = new Attachment();
						attach.setAttachNo(rset.getInt("attach_no"));
						attach.setpNo(rset.getInt("p_no"));
						attach.setOriginalFileName(rset.getString("original_filename"));
						attach.setRenamedFileName(rset.getString("renamed_filename"));
						attach.setRegDate(rset.getDate("reg_date"));
						attach.setImgFlag(rset.getString("img_flag"));
						
						product.setAttach2(attach);
					}
					
				}
				
				
				list.add(product);
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
