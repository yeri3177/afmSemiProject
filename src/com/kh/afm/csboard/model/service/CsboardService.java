package com.kh.afm.csboard.model.service;

import static com.kh.afm.common.JdbcTemplate.close;
import static com.kh.afm.common.JdbcTemplate.commit;
import static com.kh.afm.common.JdbcTemplate.getConnection;
import static com.kh.afm.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

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
		int result = csboardDao.insertCsboard(conn, csboard);
		// transaction처리
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}

	public Csboard selectOneCsboard(int boardNo) {
		Connection conn = getConnection();
		Csboard csboard = csboardDao.selectOneCsboard(conn, boardNo);
		close(conn);
		return csboard;
	}

}