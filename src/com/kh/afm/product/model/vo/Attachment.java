package com.kh.afm.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Attachment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int attachNo;
	private int pNo;
	private String originalFileName;
	private String renamedFileName;
	private Date regDate;
	private String imgFlag;
	
	public Attachment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attachment(int attachNo, int pNo, String originalFileName, String renamedFileName, Date regDate,
			String imgFlag) {
		super();
		this.attachNo = attachNo;
		this.pNo = pNo;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.regDate = regDate;
		this.imgFlag = imgFlag;
	}

	public int getAttachNo() {
		return attachNo;
	}

	public void setAttachNo(int attachNo) {
		this.attachNo = attachNo;
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getImgFlag() {
		return imgFlag;
	}

	public void setImgFlag(String imgFlag) {
		this.imgFlag = imgFlag;
	}

	@Override
	public String toString() {
		return "Attachment [attachNo=" + attachNo + ", pNo=" + pNo + ", originalFileName=" + originalFileName
				+ ", renamedFileName=" + renamedFileName + ", regDate=" + regDate + ", imgFlag=" + imgFlag + "]";
	}
	
	
}
