package com.kh.afm.csboard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Csboard implements Serializable {

	/**
	 * Csboard UID 발급
	 */
	private static final long serialVersionUID = 1L;
	
	// field 생성
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String userId;
	private Date boardRegDate;
	private int boardReadcount;
	private String boardStatus;
	private String boardNotice;
	private String boardPassword;
	private String boardLock;
	private int boardFamily;
	private int boardOrderby;
	private int boardStep;
	
	// 기본 생성자
	public Csboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Csboard(int boardNo, String boardTitle, String boardContent, String userId, Date boardRegDate,
			int boardReadcount, String boardStatus, String boardNotice, String boardPassword, String boardLock,
			int boardFamily, int boardOrderby, int boardStep) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.userId = userId;
		this.boardRegDate = boardRegDate;
		this.boardReadcount = boardReadcount;
		this.boardStatus = boardStatus;
		this.boardNotice = boardNotice;
		this.boardPassword = boardPassword;
		this.boardLock = boardLock;
		this.boardFamily = boardFamily;
		this.boardOrderby = boardOrderby;
		this.boardStep = boardStep;
	}

	// getter, setter
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTiltle() {
		return boardTitle;
	}

	public void setBoardTiltle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getBoardRegDate() {
		return boardRegDate;
	}

	public void setBoardRegDate(Date boardRegDate) {
		this.boardRegDate = boardRegDate;
	}

	public int getBoardReadcount() {
		return boardReadcount;
	}

	public void setBoardReadcount(int boardReadcount) {
		this.boardReadcount = boardReadcount;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public String getBoardNotice() {
		return boardNotice;
	}

	public void setBoardNotice(String boardNotice) {
		this.boardNotice = boardNotice;
	}

	public String getBoardPassword() {
		return boardPassword;
	}

	public void setBoardPassword(String boardPassword) {
		this.boardPassword = boardPassword;
	}

	public String getBoardLock() {
		return boardLock;
	}

	public void setBoardLock(String boardLock) {
		this.boardLock = boardLock;
	}

	public int getBoardFamily() {
		return boardFamily;
	}

	public void setBoardFamily(int boardFamily) {
		this.boardFamily = boardFamily;
	}

	public int getBoardOrderby() {
		return boardOrderby;
	}

	public void setBoardOrderby(int boardOrderby) {
		this.boardOrderby = boardOrderby;
	}

	public int getBoardStep() {
		return boardStep;
	}

	public void setBoardStep(int boardStep) {
		this.boardStep = boardStep;
	}

	// String
	@Override
	public String toString() {
		return "Csboard [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", userId=" + userId + ", boardRegDate=" + boardRegDate + ", boardReadcount=" + boardReadcount
				+ ", boardStatus=" + boardStatus + ", boardNotice=" + boardNotice + ", boardPassword=" + boardPassword
				+ ", boardLock=" + boardLock + ", boardFamily=" + boardFamily + ", boardOrderby=" + boardOrderby
				+ ", boardStep=" + boardStep + "]";
	}

	
}
