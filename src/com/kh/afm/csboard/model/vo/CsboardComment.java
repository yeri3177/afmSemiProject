package com.kh.afm.csboard.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class CsboardComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cbNo;
	private int cbLevel; // 댓글 1, 대댓글 2
	private String userId;
	private String cbContent;
	private int cbBoardNo;
	private int cbCommentRef; // 댓글 null, 대댓글인 경우 부모댓글 번호 cbNo
	private Date regDate;
	
	public CsboardComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CsboardComment(int cbNo, int cbLevel, String userId, String cbContent, int cbBoardNo, int cbCommentRef,
			Date regDate) {
		super();
		this.cbNo = cbNo;
		this.cbLevel = cbLevel;
		this.userId = userId;
		this.cbContent = cbContent;
		this.cbBoardNo = cbBoardNo;
		this.cbCommentRef = cbCommentRef;
		this.regDate = regDate;
	}

	public int getCbNo() {
		return cbNo;
	}

	public void setCbNo(int cbNo) {
		this.cbNo = cbNo;
	}

	public int getCbLevel() {
		return cbLevel;
	}

	public void setCbLevel(int cbLevel) {
		this.cbLevel = cbLevel;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCbContent() {
		return cbContent;
	}

	public void setCbContent(String cbContent) {
		this.cbContent = cbContent;
	}

	public int getCbBoardNo() {
		return cbBoardNo;
	}

	public void setCbBoardNo(int cbBoardNo) {
		this.cbBoardNo = cbBoardNo;
	}

	public int getCbCommentRef() {
		return cbCommentRef;
	}

	public void setCbCommentRef(int cbCommentRef) {
		this.cbCommentRef = cbCommentRef;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "CsboardComment [cbNo=" + cbNo + ", cbLevel=" + cbLevel + ", userId=" + userId + ", cbContent="
				+ cbContent + ", cbBoardNo=" + cbBoardNo + ", cbCommentRef=" + cbCommentRef + ", regDate=" + regDate
				+ "]";
	}
		
}