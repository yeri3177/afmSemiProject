package com.kh.afm.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int pNo;
	private String userId;
	private Date pRegDate;
	private String pTitle;
	private String pContent;
	private String pPost;
	private int pPrice;
	private int pCnt;	
	private String pCategory;
	private String pExpose;
	private String pReport;
	private int pRecommend;
	
	private Attachment attach1;
	private Attachment attach2;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int pNo, String userId, Date pRegDate, String pTitle, String pContent, String pPost, int pPrice,
			int pCnt, String pCategory, String pExpose, String pReport, int pRecommend, Attachment attach1,
			Attachment attach2) {
		super();
		this.pNo = pNo;
		this.userId = userId;
		this.pRegDate = pRegDate;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pPost = pPost;
		this.pPrice = pPrice;
		this.pCnt = pCnt;
		this.pCategory = pCategory;
		this.pExpose = pExpose;
		this.pReport = pReport;
		this.pRecommend = pRecommend;
		this.attach1 = attach1;
		this.attach2 = attach2;
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

	public Date getpRegDate() {
		return pRegDate;
	}

	public void setpRegDate(Date pRegDate) {
		this.pRegDate = pRegDate;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getpPost() {
		return pPost;
	}

	public void setpPost(String pPost) {
		this.pPost = pPost;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public int getpCnt() {
		return pCnt;
	}

	public void setpCnt(int pCnt) {
		this.pCnt = pCnt;
	}

	public String getpCategory() {
		return pCategory;
	}

	public void setpCategory(String pCategory) {
		this.pCategory = pCategory;
	}

	public String getpExpose() {
		return pExpose;
	}

	public void setpExpose(String pExpose) {
		this.pExpose = pExpose;
	}

	public String getpReport() {
		return pReport;
	}

	public void setpReport(String pReport) {
		this.pReport = pReport;
	}

	public int getpRecommend() {
		return pRecommend;
	}

	public void setpRecommend(int pRecommend) {
		this.pRecommend = pRecommend;
	}

	public Attachment getAttach1() {
		return attach1;
	}

	public void setAttach1(Attachment attach1) {
		this.attach1 = attach1;
	}

	public Attachment getAttach2() {
		return attach2;
	}

	public void setAttach2(Attachment attach2) {
		this.attach2 = attach2;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", userId=" + userId + ", pRegDate=" + pRegDate + ", pTitle=" + pTitle
				+ ", pContent=" + pContent + ", pPost=" + pPost + ", pPrice=" + pPrice + ", pCnt=" + pCnt
				+ ", pCategory=" + pCategory + ", pExpose=" + pExpose + ", pReport=" + pReport + ", pRecommend="
				+ pRecommend + ", attach1=" + attach1 + ", attach2=" + attach2 + "]";
	}


	
	
	
}
