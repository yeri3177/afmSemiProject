package com.kh.afm.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ProductComment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private int commentNo;
	private int pNo;
	private String userId;
	private int commentLevel;
	private String commentContent;
	private int commentRef;
	private Date regdate;
	
	public ProductComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductComment(int commentNo, int pNo, String userId, int commentLevel, String commentContent,
			int commentRef, Date regdate) {
		super();
		this.commentNo = commentNo;
		this.pNo = pNo;
		this.userId = userId;
		this.commentLevel = commentLevel;
		this.commentContent = commentContent;
		this.commentRef = commentRef;
		this.regdate = regdate;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCommentLevel() {
		return commentLevel;
	}

	public void setCommentLevel(int commentLevel) {
		this.commentLevel = commentLevel;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getCommentRef() {
		return commentRef;
	}

	public void setCommentRef(int commentRef) {
		this.commentRef = commentRef;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "ProductComment [commentNo=" + commentNo + ", pNo=" + pNo + ", userId=" + userId + ", commentLevel="
				+ commentLevel + ", commentContent=" + commentContent + ", commentRef=" + commentRef + ", regdate="
				+ regdate + "]";
	}
	

}
