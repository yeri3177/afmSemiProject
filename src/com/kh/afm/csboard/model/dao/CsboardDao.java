package com.kh.afm.csboard.model.dao;

import static com.kh.afm.common.JdbcTemplate.close;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.afm.csboard.model.vo.Csboard;

public class CsboardDao {
	
	private Properties prop = new Properties();
	
	/**
	 * prop객체에 buildpath로 배포된 /sql/csboard/csboard-query.properties 불러오기
	 */
	public CsboardDao() {
		// url 객체가 return되므로 getPath()를 불러온다.
		String filepath = CsboardDao.class.getResource("/sql/csboard/csboard-query.properties").getPath();
		// 위 절대주소를 가지고 prop.load한다.
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 게시물 조회
	// DQL
	public List<Csboard> selectCsboardList(Connection conn, int start, int end) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCsboardList");
//		System.out.println("sql = " + sql);
		List<Csboard> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rset = pstmt.executeQuery();
			
			// 여러행이기때문에 while 반복문 필수
			while(rset.next()) {
				// 한 행씩 vo로 담아낸다.
				// 테이블 record 1개가 VO 객체 1개로 변환이 된다.
				Csboard csboard = new Csboard();
				csboard.setBoardNo(rset.getInt("board_no"));
				csboard.setUserId(rset.getString("user_id"));
				csboard.setBoardTitle(rset.getString("board_title"));
				csboard.setBoardContent(rset.getString("board_content"));
				csboard.setBoardRegDate(rset.getDate("board_reg_date"));
				csboard.setBoardReadcount(rset.getInt("board_readcount"));
				csboard.setBoardStatus(rset.getString("board_status"));
				csboard.setBoardNotice(rset.getString("board_noticeYN"));
				csboard.setBoardPassword(rset.getString("board_password"));
				csboard.setBoardLock(rset.getString("board_lockYN"));
				csboard.setBoardFamily(rset.getInt("board_family"));
				csboard.setBoardOrderby(rset.getInt("board_orderby"));
				csboard.setBoardStep(rset.getInt("board_step"));
				
				// 그리고 이 csboard를 list에 추가
				list.add(csboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		
		return list;
	}

	// 게시물 총 개수 조회
	// DQL
	public int selectTotalContents(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		int totalContents = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
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

	// DML
	public int insertCsboard(Connection conn, Csboard csboard) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertCsboard");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, csboard.getUserId());
			pstmt.setString(2, csboard.getBoardPassword());
			pstmt.setString(3, csboard.getBoardTitle());
			pstmt.setString(4, csboard.getBoardContent());
//			pstmt.setString(5, csboard.getBoardLock());
			
			// DML
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}


}
