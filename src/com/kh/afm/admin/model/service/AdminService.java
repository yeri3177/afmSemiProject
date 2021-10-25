package com.kh.afm.admin.model.service;

import static com.kh.afm.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.afm.admin.model.dao.AdminDao;
import com.kh.afm.user.model.vo.User;

public class AdminService {
	
	// 어드민DAO
	private AdminDao adminDao = new AdminDao();

	/**
	 * 전체 회원 조회
	 */
	public List<User> selectAllUser(int startRownum, int endRownum) {
		Connection conn = getConnection();
		List<User> list = adminDao.selectAllMember(conn, startRownum, endRownum);
		close(conn);		
		
		return list;
	}

	/**
	 * 페이징 - 전체회원수 
	 */
	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContent = adminDao.selectTotalContents(conn);
		close(conn);
		return totalContent;
	}
}
