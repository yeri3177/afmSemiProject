package com.kh.afm.admin.model.service;

import static com.kh.afm.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.afm.admin.model.dao.AdminDao;
import com.kh.afm.product.model.vo.Product;
import com.kh.afm.product.model.vo.Report;
import com.kh.afm.user.model.vo.Address;
import com.kh.afm.user.model.vo.DelUser;
import com.kh.afm.user.model.vo.User;

public class AdminService {
	
	// 어드민DAO
	private AdminDao adminDao = new AdminDao();

	/**
	 * 전체 회원 조회
	 */
	public List<User> selectAllUser(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<User> list = null;
		
		try {
			list = adminDao.selectAllMember(conn, startRownum, endRownum);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}		
		return list;
	}

	/**
	 * 페이징 - 전체회원수 
	 */
	public int selectUserTotalContents() {
		Connection conn = getConnection();
		int totalContent = 0;
		try {
			totalContent = adminDao.selectUserTotalContents(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContent;
	}

	/**
	 * 탈퇴한 전체 회원 조회
	 */
	public List<DelUser> selectAllDelUser(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<DelUser> list = null;
		try {
			list = adminDao.selectAllDelUser(conn, startRownum, endRownum);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}		
		
		return list;
	}

	/**
	 * 회원 검색하기
	 */
	public List<User> searchUser(Map<String, Object> param) {
		Connection conn = getConnection();
		List<User> list = null;
		try {
			list = adminDao.searchUser(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return list;
	}

	/**
	 * 페이징 - 검색결과의 전체회원수
	 */
	public int searchUserCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent;
		try {
			totalContent = adminDao.searchUserCount(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContent;
	}

	/**
	 * 회원 목록 정렬하기
	 */
	public List<User> sortUser(Map<String, Object> param) {
		Connection conn = getConnection();
		List<User> list = null;
		try {
			list = adminDao.sortUser(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return list;
	}
	
	/**
	 * 상품 목록 정렬하기
	 */
	public List<Product> sortProduct(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> list;
		try {
			list = adminDao.sortProduct(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return list;
	}
	
	

	/**
	 * 페이징 - 전체탈퇴회원수 
	 */
	public int selectDelUserTotalContents() {
		Connection conn = getConnection();
		int totalContent;
		try {
			totalContent = adminDao.selectDelUserTotalContents(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContent;
	}

	/**
	 * 사용자 공개여부 변경 
	 */
	public int updateUserExpose(String userId, String userExpose) {
		Connection conn = getConnection();
		int result;
		try {
			result = adminDao.updateUserExpose(conn, userId, userExpose);
			
			if(result>0) 
				commit(conn);
			else 
				rollback(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	/**
	 * 탈퇴회원 데이터 삭제하기 
	 */
	public int deleteDelUser(String[] userId_arr) {
		Connection conn = getConnection();
		int result;
		try {
			result = adminDao.deleteDelUser(conn, userId_arr);
			
			if(result>0) commit(conn);
			else rollback(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	/**
	 * 상품 목록 조회하기 
	 */
	public List<Product> selectAllProduct(int startRownum, int endRownum) {
		Connection conn = getConnection();
		
		List<Product> list;
		try {
			list = adminDao.selectAllProduct(conn, startRownum, endRownum);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return list;
	}

	/**
	 * 페이징 - 전체 상품수 
	 */
	public int selectProductTotalContents() {
		Connection conn = getConnection();
		int totalContent;
		try {
			totalContent = adminDao.selectProductTotalContents(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContent;
	}

	/**
	 * 상품 노출여부 변경
	 */
	public int updateProductExpose(int pNo, String pExpose) {
		Connection conn = getConnection();
		int result;
		try {
			result = adminDao.updateProductExpose(conn, pNo, pExpose);
			
			if(result>0) commit(conn);
			else rollback(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	/**
	 * 상품 신고내역 조회
	 */
	public List<Report> selectAllReport(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<Report> list;
		try {
			list = adminDao.selectAllReport(conn, startRownum, endRownum);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}		
		
		return list;
	}

	/**
	 * 페이징 - 전체 신고내역수  
	 */
	public int selectReportTotalContents() {
		Connection conn = getConnection();
		int totalContent;
		try {
			totalContent = adminDao.selectReportTotalContents(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContent;
	}

	/**
	 * 상품 신고내역 미처리 건수 조회 
	 */
	public int selectReportCnt() {
		Connection conn = getConnection();
		int cnt;
		try {
			cnt = adminDao.selectReportCnt(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}		
		
		return cnt;
	}

	/**
	 * 상품 검색하기
	 */
	public List<Product> searchProduct(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Product> list;
		try {
			list = adminDao.searchProduct(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return list;
	}

	/**
	 * 페이징 - 상품 검색된 수  
	 */
	public int searchProductCount(Map<String, Object> param) {
		Connection conn = getConnection();
		int totalContent;
		try {
			totalContent = adminDao.searchProductCount(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return totalContent;
	}

	/**
	 * 상품신고테이블의 처리상태 변경
	 */
	public int processReport(int reportNo) {
		Connection conn = getConnection();
		int result;
		try {
			result = adminDao.processReport(conn, reportNo);
			
			if(result>0) commit(conn);
			else rollback(conn);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	/**
	 * 회원별 주소목록 찾기 
	 */
	public List<Address> selectUserAddress(String userId) {
		Connection conn = getConnection();
		List<Address> list;
		try {
			list = adminDao.selectUserAddress(conn, userId);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}		
		
		return list;
	}
	
	/**
	 * 메인페이지 쿼리
	 */
	public Map<String, Integer> adminMainQuery(Map<String, Integer> param) {
		Connection conn = getConnection();
		Map<String, Integer> result;
		try {
			result = adminDao.adminMainQuery(conn, param);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}

	/**
	 * 올해 상품 등록수 조회 
	 */
	public int[] adminStatistics(int[] productsMonthCnt) {
		Connection conn = getConnection();
		int[] result;
		try {
			result = adminDao.adminStatistics(conn, productsMonthCnt);
			close(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}
		return result;
	}
}
