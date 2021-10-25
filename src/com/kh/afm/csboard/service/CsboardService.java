package com.kh.afm.csboard.service;

import static com.kh.afm.common.JdbcTemplate.*;
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

}
