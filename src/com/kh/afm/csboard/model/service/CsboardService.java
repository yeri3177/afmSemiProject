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
import com.kh.afm.csboard.model.vo.CsboardComment;
import com.kh.afm.product.model.vo.Report;

public class CsboardService {
	
	private CsboardDao csboardDao = new CsboardDao();

	public List<Csboard> selectCsboardList(int start, int end) {
		Connection conn = getConnection();
		List<Csboard> list = csboardDao.selectCsboardList(conn, start, end);
		// 단순 조회기 때문에 transaction 처리는 없다.
		close(conn);
		return list;
	}

	
	/**
	 * 총 게시글 조회 
	 * DQL
	 */
	public int selectTotalContents() {
		int totalContents;
		try {
			Connection conn = getConnection();
			totalContents = csboardDao.selectTotalContents(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return totalContents;
	}

	
	/**
	 * 게시글 등록 
	 * DML
	 */
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

	
	/**
	 * 게시글 상세보기 
	 * DQL
	 */
	public Csboard selectOneCsboard(int boardNo) {
		Csboard csboard;
		try {
			Connection conn = getConnection();
			csboard = csboardDao.selectOneCsboard(conn, boardNo);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return csboard;
	}

	
	/**
	 * 조회수
	 * DML 
	 */
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

	
	/**
	 * 게시물 삭제
	 * DML 
	 */
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

	
	/**
	 * 게시물 찾기
	 * DQL 
	 */
	public List<Csboard> searchCsboard(Map<String, Object> param) {
		List<Csboard> list;
		try {
			Connection conn = getConnection();
			list = csboardDao.searchCsboard(conn, param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	
	/**
	 * 게시물 수정
	 * DML 
	 */
	public int updateCsboard(Csboard csboard) {
		Connection conn = getConnection();
		int result = 0;
		try {
			// 1. 게시글 수정 update board문
			result = csboardDao.updateCsboard(conn, csboard);
			
			// 문제없이 코드가 진행되었다면
			commit(conn);
		} catch (Exception e) {
			// 오류가 발생했다면
			rollback(conn);
			// 예외 발생했을 때 던져주기
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

	
	/**
	 * 댓글조회
	 * DQL 
	 */
	public List<CsboardComment> selectCommentList(int boardNo) {
		List<CsboardComment> commentList;
		try {
			Connection conn = getConnection();
			commentList = csboardDao.selectCommentList(conn, boardNo);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return commentList;
	}

	
	/**
	 * 댓글 등록
	 * DML 
	 */
	public int insertCsboardComment(CsboardComment cbc) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csboardDao.insertCsboardComment(conn, cbc);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}


	/**
	 * 공지사항
	 * DQL 
	 */
	public List<Csboard> selectNoticeList() {
		List<Csboard> noticeList;
		try {
			Connection conn = getConnection();
			noticeList = csboardDao.selectNoticeList(conn);
			close(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		return noticeList;
	}

	
	/**
	 * 댓글 삭제
	 * DML 
	 */
	public int deleteCsboardComment(int cbNo) {
		Connection conn = getConnection();
		int result = 0;
		try {
			result = csboardDao.deleteCsboardComment(conn, cbNo);
			commit(conn);
		} catch(Exception e) {
			rollback(conn);
			throw e;
		}
		
		return result;
	}

	
	/**
	 * 신고하기
	 * DML 
	 */
	public int insertReport(Report report) {
		Connection conn = getConnection();
		int result = csboardDao.insertReport(conn, report);
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return result;
	}


}