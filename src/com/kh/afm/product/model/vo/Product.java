package com.kh.afm.product.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int pNo;
	private String pTitle;
	private int pPrice;
	private String pWriter;
	private Date pRegDate;
	private int pRecommend;
	
	public Product(int pNo, String pTitle, int pPrice, String pWriter, Date pRegDate, int pRecommend) {
		super();
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.pPrice = pPrice;
		this.pWriter = pWriter;
		this.pRegDate = pRegDate;
		this.pRecommend = pRecommend;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getpNo() {
		return pNo;
	}

	public void setpNo(int pNo) {
		this.pNo = pNo;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public int getpPrice() {
		return pPrice;
	}

	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}

	public String getpWriter() {
		return pWriter;
	}

	public void setpWriter(String pWriter) {
		this.pWriter = pWriter;
	}

	public Date getpRegDate() {
		return pRegDate;
	}

	public void setpRegDate(Date pRegDate) {
		this.pRegDate = pRegDate;
	}

	public int getpRecommend() {
		return pRecommend;
	}

	public void setpRecommend(int pRecommend) {
		this.pRecommend = pRecommend;
	}

	@Override
	public String toString() {
		return "Product [pNo=" + pNo + ", pTitle=" + pTitle + ", pPrice=" + pPrice + ", pWriter=" + pWriter
				+ ", pRegDate=" + pRegDate + ", pRecommend=" + pRecommend + "]";
	}
	
	
	
}
