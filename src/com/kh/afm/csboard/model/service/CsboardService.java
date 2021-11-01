package com.kh.afm.csboard.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.afm.csboard.model.dao.CsboardDao;
import com.kh.afm.csboard.model.vo.Csboard;

public class CsboardService {
	
	private CsboardDao csboardDao = new CsboardDao();

	public List<Csboard> selectCsboardList(int start, int end) {
		Connection conn = getConnection();
		List<Csboard> list = csboardDao.selectCsboardList(conn, start, end);
		// 단순 조회기 때문에 transaction 처리는 없다.
		close(conn);
		return list;
	}

	// 총 게시글 조회
	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = csboardDao.selectTotalContents(conn);
		close(conn);
		return totalContents;
	}

	public int insertCsboard(Csboard csboard) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			// board 테이블 추가
			result = csboardDao.insertCsboard(conn, csboard);
			
			// 생성된 board_no 가져오기
			int csboardNo = csboardDao.selectLastCsboardNo(conn);
			System.out.println("insertCsboard@service = " + csboardNo);
			
			// csboard 객체에 set -> servlet에서 참조
			csboard.setBoardNo(csboardNo);
			
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			result = 0;
		}
					
		close(conn);
		return result;
	}

	public Csboard selectOneCsboard(int boardNo) {
		Connection conn = getConnection();
		Csboard csboard = csboardDao.selectOneCsboard(conn, boardNo);
		close(conn);
		return csboard;
	}

	// DML
	public int updateReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = csboardDao.updateReadCount(conn, boardNo);
		
		// transaction
		if(result > 0) commit(conn);
		else rollback(conn);
		
		// 자원반납
		close(conn);
		return result;
	}

	// 게시물 삭제
	// DML
	public int deleteCsboard(int boardNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 오류가 나면 catch절로 이동
			// 오류가 없으면 result == 0으로 if절 작동
			result = csboardDao.deleteCsboard(conn, boardNo);
			if(result == 0)
				throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다. : " + boardNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e; // controller가 예외처리를 결정할 수 있도록 넘김.
		} finally {
			close(conn);
		}
		return result;
	}

	// 게시물 찾기
	// DQL
	public List<Csboard> searchCsboard(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Csboard> list = csboardDao.searchCsboard(conn, param);
		return list;
	}





}