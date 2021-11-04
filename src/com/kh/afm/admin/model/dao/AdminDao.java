package com.kh.afm.admin.model.dao;

import static com.kh.afm.common.JdbcTemplate.*;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.afm.admin.model.exception.AdminException;
import com.kh.afm.product.model.vo.Attachment;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.product.model.vo.Report;
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
					
					user.setAccount(account); 
				}
				
				list.add(user);
			}
		} catch (SQLException e) {
			throw new AdminException("회원 목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 페이징 - 전체회원수
	 */
	public int selectUserTotalContents(Connection conn) {
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
			throw new AdminException("전체회원수 페이징 오류", e);
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
			throw new AdminException("회원탈퇴 목록 조회 오류", e);
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
					
					user.setAccount(account);
				}
				
				list.add(user);
			}

		} catch (SQLException e) {
			throw new AdminException("회원 검색 조회 오류", e);
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
			throw new AdminException("검색결과 페이징 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	/**
	 * 회원 목록 정렬하기
	 */
	public List<User> sortUser(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<User> list = new ArrayList<>();
		String sql = null;
		String sortType = (String) param.get("sortType");
		String sortKeyword = (String) param.get("sortKeyword");

		if ("asc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserOrderByUserIdAsc");
		} else if ("asc".equals(sortType) && "userName".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserOrderByUserNameAsc");
		} else if ("asc".equals(sortType) && "userRole".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserOrderByUserRoleAsc");
		} else if ("desc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserOrderByUserIdDesc");
		} else if ("desc".equals(sortType) && "userName".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserOrderByUserNameDesc");
		} else if ("desc".equals(sortType) && "userRole".equals(sortKeyword)) {
			sql = prop.getProperty("sortUserOrderByUserRoleDesc");
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
					
					user.setAccount(account);
				}
				list.add(user);
			}

		} catch (SQLException e) {
			throw new AdminException("회원목록 정렬 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
	/**
	 * 상품 목록 정렬하기
	 */
	public List<Product> sortProduct(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		String sql = null;
		String sortType = (String) param.get("sortType");
		String sortKeyword = (String) param.get("sortKeyword");

		
		if ("asc".equals(sortType) && "pNo".equals(sortKeyword)) {
			sql = prop.getProperty("sortProductOrderByProductNoAsc");
		} else if ("asc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortProductOrderByUserIdAsc");
		} else if ("asc".equals(sortType) && "pExpose".equals(sortKeyword)) {
			sql = prop.getProperty("sortProductOrderByProductExposeAsc");
			
			
		} else if ("desc".equals(sortType) && "pNo".equals(sortKeyword)) {
			sql = prop.getProperty("sortProductOrderByProductNoDesc");
		} else if ("desc".equals(sortType) && "userId".equals(sortKeyword)) {
			sql = prop.getProperty("sortProductOrderByUserIdDesc");
		} else if ("desc".equals(sortType) && "pExpose".equals(sortKeyword)) {
			sql = prop.getProperty("sortProductOrderByProductExposeDesc");
		}

		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (int) param.get("start"));
			pstmt.setInt(2, (int) param.get("end"));

			// 2. 쿼리실행 및 ResultSet처리
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Product product = new Product();
				product.setpNo(rset.getInt("p_no"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpTitle(rset.getString("p_title"));
				product.setpContent(rset.getString("p_content"));
				product.setpPost(rset.getString("p_post"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				product.setpExpose(rset.getString("p_expose"));
				product.setpReport(rset.getString("p_report"));
				product.setpRecommend(rset.getInt("p_recommend"));
				
				list.add(product);
			}

		} catch (SQLException e) {
			throw new AdminException("상품 목록 정렬 오류", e);
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
				throw new AdminException("메인페이지 쿼리 조회 오류", e);
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
		//String sql = prop.getProperty("deleteDelUser"); 
		
		// String[] -> String
		String id_str = "";
		for(int i=0; i<userId_arr.length; i++) {
			id_str += (i != userId_arr.length-1) ? (id_str = "'" + userId_arr[i] + "',") : (id_str = "'" + userId_arr[i] + "'");
			//id_str += (i != userId_arr.length-1) ? (id_str = userId_arr[i] + ",") : (id_str =  userId_arr[i] );
		}
		System.out.println("id_str = "+id_str);
		
		String sql = "delete from user_delete where delete_u_id in ( "+id_str+" )";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id_str);
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
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpTitle(rset.getString("p_title"));
				product.setpContent(rset.getString("p_content"));
				product.setpPost(rset.getString("p_post"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				product.setpExpose(rset.getString("p_expose"));
				product.setpReport(rset.getString("p_report"));
				product.setpRecommend(rset.getInt("p_recommend"));
					
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

	/**
	 * 페이징 - 전체 상품수 
	 */
	public int selectProductTotalContents(Connection conn) {
		String sql = prop.getProperty("selectProductTotalContents");
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
			throw new AdminException("상품목록 페이징 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

	/**
	 * 상품 노출여부 변경
	 */
	public int updateProductExpose(Connection conn, int pNo, String pExpose) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateProductExpose"); 
		
		try {
			// 미완성쿼리문 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 셋팅
			pstmt.setString(1, pExpose);
			pstmt.setInt(2, pNo);
			
			// 쿼리실행 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new AdminException("상품 노출여부 변경 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 상품 신고내역 조회
	 */
	public List<Report> selectAllReport(Connection conn, int startRownum, int endRownum) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAllReport");
		ResultSet rset = null;
		List<Report> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, startRownum);
			pstmt.setInt(2, endRownum);

			rset = pstmt.executeQuery();


			while (rset.next()) {
				Report report = new Report();
				report.setReportNo(rset.getInt("report_no"));
				report.setpNo(rset.getInt("p_no"));
				report.setReportTitle(rset.getString("report_title"));
				report.setReportType(rset.getString("report_type"));
				report.setReportContent(rset.getString("report_content"));
				report.setUserId(rset.getString("user_id"));
				report.setReportRegDate(rset.getDate("report_reg_date"));
				report.setReportStatus(rset.getString("report_status"));
					
				list.add(report);
			}

		} catch (SQLException e) {
			throw new AdminException("상품 신고내역 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}

	/**
	 * 페이징 - 전체 신고내역수  
	 */
	public int selectReportTotalContents(Connection conn) {
		String sql = prop.getProperty("selectReportTotalContents");
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
			throw new AdminException("신고내역 페이징 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return totalContents;
	}

	/**
	 * 상품 신고내역 미처리 건수 조회 
	 */
	public int selectReportCnt(Connection conn) {
		String sql = prop.getProperty("selectReportCnt");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int cnt = 0;

		try {
			// 1.PreparedStatment객체 생성 및 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);

			// 2.실행 & ResultSet객체 리턴
			rset = pstmt.executeQuery();

			// 3.ResultSet -> totalContents
			if (rset.next()) {
				cnt = rset.getInt(1);
			}
		} catch (SQLException e) {
			throw new AdminException("신고내역 미처리 건수 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return cnt;
	}

	/**
	 * 상품 검색하기
	 */
	public List<Product> searchProduct(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();
		String sql = null;
		String searchType = (String) param.get("searchType");

		switch (searchType) {
		case "pNo": 
			sql = prop.getProperty("searchProductByProductNo");
			break;
		case "userId":
			sql = prop.getProperty("searchProductByUserId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "pCategory":
			sql = prop.getProperty("searchProductByProductCategory");
			break;
		case "pExpose":
			sql = prop.getProperty("searchProductByProductExpose");
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
				Product product = new Product();
				product.setpNo(rset.getInt("p_no"));
				product.setUserId(rset.getString("p_user_id"));
				product.setpRegDate(rset.getDate("p_reg_date"));
				product.setpTitle(rset.getString("p_title"));
				product.setpContent(rset.getString("p_content"));
				product.setpPost(rset.getString("p_post"));
				product.setpPrice(rset.getInt("p_price"));
				product.setpCnt(rset.getInt("p_cnt"));
				product.setpCategory(rset.getString("p_category"));
				product.setpExpose(rset.getString("p_expose"));
				product.setpReport(rset.getString("p_report"));
				product.setpRecommend(rset.getInt("p_recommend"));
				
				list.add(product);
			}
			
		} catch (SQLException e) {
			throw new AdminException("상품 검색 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}



	/**
	 * 페이징 - 상품 검색된 수  
	 */
	public int searchProductCount(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int totalContents = 0;

		String sql = null;
		String searchType = (String) param.get("searchType");
		switch (searchType) {
		case "pNo":
			sql = prop.getProperty("searchProductCountByProductNo");
			break;
		case "userId":
			sql = prop.getProperty("searchProductCountByUserId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "pCategory":
			sql = prop.getProperty("searchProductCountByProductCategory");
			break;
		case "pExpose":
			sql = prop.getProperty("searchProductCountByProductExpose");
			break;
		}

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			rset = pstmt.executeQuery();
			if (rset.next())
				totalContents = rset.getInt(1);
		} catch (SQLException e) {
			throw new AdminException("상품 검색 페이징 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return totalContents;
	}

	/**
	 * 상품신고테이블의 처리상태 변경
	 */
	public int processReport(Connection conn, int reportNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("processReport"); 
		
		try {
			// 미완성쿼리문 객체생성
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문 셋팅
			pstmt.setInt(1, reportNo);
			
			// 쿼리실행 
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new AdminException("상품 신고 처리상태 변경 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/**
	 * 회원별 주소목록 찾기 
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
			throw new AdminException("회원 주소목록 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
	}
	
}
