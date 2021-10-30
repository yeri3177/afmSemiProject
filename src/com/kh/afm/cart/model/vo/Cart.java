package com.kh.afm.cart.model.vo;

import java.io.Serializable;

public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private int cartNo;
	private int productNo;
	private String renamedFilename;
	private String productName;
	private int productPrice;
	private int productQuantity;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(String userId, int cartNo, int productNo, String renamedFilename, String productName, int productPrice,
			int productQuantity) {
		super();
		this.userId = userId;
		this.cartNo = cartNo;
		this.productNo = productNo;
		this.renamedFilename = renamedFilename;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getRenamedFilename() {
		return renamedFilename;
	}
	public void setRenamedFilename(String renamedFilename) {
		this.renamedFilename = renamedFilename;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	@Override
	public String toString() {
		return "Cart [userId=" + userId + ", cartNo=" + cartNo + ", productNo=" + productNo + ", renamedFilename="
				+ renamedFilename + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productQuantity=" + productQuantity + "]";
	}
	
	
	
}
