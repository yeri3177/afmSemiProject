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
import java.util.Map;
import java.util.Properties;

import com.kh.afm.csboard.model.exception.CsboardException;
import com.kh.afm.csboard.model.vo.Csboard;
import com.kh.afm.csboard.model.vo.CsboardComment;


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
			pstmt.setString(2, csboard.getBoardTitle());
			pstmt.setString(3, csboard.getBoardContent());
			pstmt.setString(4, csboard.getBoardNotice());
			pstmt.setString(5, csboard.getBoardPassword());
			pstmt.setString(6, csboard.getBoardLock());
			
			// DML
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new CsboardException("게시글 등록 오류", e);
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Csboard selectOneCsboard(Connection conn, int boardNo) {
		System.out.println("CsboardDao - selectOneCsboard");
		Csboard csboard = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneCsboard");
		
		try {
			// 미완성 쿼리문을 가지고 객체 생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문 미완성
			pstmt.setInt(1, boardNo);
			// 쿼리문 실행
			// 완성된 쿼리를 가지고 있는 pstmt 실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				csboard = new Csboard();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return csboard;
	}

	public int selectLastCsboardNo(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLastCsboardNo");
		int csboardNo = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				csboardNo = rset.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CsboardException("게시물 번호 조회 오류", e);
		} finally {
			close(rset);
			close(pstmt);
		}
		return csboardNo;
	}

	// DML
	// 조회수
	public int updateReadCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReadCount");
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CsboardException("조회수 1 증가 오류", e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int deleteCsboard(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteCsboard");
		
		try {
			// 미완성 쿼리문을 가지고 객체 생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문 미완성
			pstmt.setInt(1, boardNo);
			
			// 쿼리문 실행 : 완성된 쿼리를 가지고 있는 pstmt 실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 조회
	// DQL
	public List<Csboard> searchCsboard(Connection conn, Map<String, Object> param) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		// 0행이 조회되도 ArrayList<>() 객체는 returen되어야 한다.
		List<Csboard> list = new ArrayList<>();
		String sql = null;
		// Object는 switch 계산식 안에 들어올 수 없으므로 밖으로 빼서 가져온다.
		String searchType = (String) param.get("searchType");
		switch(searchType) {
		case "userId":
			sql = prop.getProperty("searchCsboardByUserId");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "boardNo":
			sql = prop.getProperty("searchCsboardByBoardNo");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		case "boardTitle":
			sql = prop.getProperty("searchCsboardByBoardTitle");
			param.put("searchKeyword", "%" + param.get("searchKeyword") + "%");
			break;
		}
		System.out.println("sql@dao = " + sql);
		
		try {
			// 1. PreparedStatement 객체 생성 & 미완성쿼리 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (String) param.get("searchKeyword"));
			
			// 2. 쿼리실행 및 ResultSet 처리
			rset = pstmt.executeQuery();
			while(rset.next()) {
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
				list.add(csboard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 3. 자원반납
			close(rset);
			close(pstmt);
		}
		return list;
	}

	
	// 게시판 수정하기
	// DML
	public int updateCsboard(Connection conn, Csboard csboard) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateCsboard");
		
		try {
			// 미완성 쿼리문을 가지고 객체 생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문 미완성
			pstmt.setString(1, csboard.getBoardTitle());
			pstmt.setString(2, csboard.getBoardContent());
			pstmt.setString(3, csboard.getBoardPassword());
			pstmt.setString(4, csboard.getBoardLock());
			pstmt.setInt(5, csboard.getBoardNo());
			
			// 쿼리문 실행 : 완성된 쿼리를 가지고 있는 pstmt 실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<CsboardComment> selectCsboardList(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		List<CsboardComment> commentList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			// 미완성 쿼리 값 대입
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				CsboardComment cbc = new CsboardComment();
				cbc.setCbNo(rset.getInt("cb_no"));
				cbc.setCbLevel(rset.getInt("cb_level"));
				cbc.setUserId(rset.getString("user_id"));
				cbc.setCbContent(rset.getString("cb_content"));
				cbc.setCbBoardNo(rset.getInt("cb_board_no"));
				cbc.setCbCommentRef(rset.getInt("cb_comment_ref"));
				cbc.setRegDate(rset.getDate("reg_date"));
				// 객체가 하나 만들어진 것을 commentList에다 하나씩 더해준다.
				commentList.add(cbc);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return commentList;
	}

	// DML
	public int insertCsboardComment(Connection conn, CsboardComment cbc) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertCsboardComment");
		
		try {
			// 미완성 쿼리문을 가지고 객체 생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문 미완성
			pstmt.setInt(1, cbc.getCbLevel());
			pstmt.setString(2, cbc.getUserId());
			pstmt.setString(3, cbc.getCbContent());
			pstmt.setInt(4, cbc.getCbBoardNo());
			pstmt.setObject(5, cbc.getCbCommentRef() == 0 ? null : cbc.getCbCommentRef());
			
			// 쿼리문 실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new CsboardException("댓글 등록 오류!", e);
		} finally {
			close(pstmt);
		}
		return result;
	}


	public List<Csboard> selectNoticeList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectNoticeList");
		List<Csboard> noticeList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);

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
				noticeList.add(csboard);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		return noticeList;
	}

}
