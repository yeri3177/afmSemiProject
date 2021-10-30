package com.kh.afm.order.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private int orderNo;
	private Date orderDate;
	private int totalPrice;
	private int adrNo;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String userId, int orderNo, Date orderDate, int totalPrice, int adrNo) {
		super();
		this.userId = userId;
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.adrNo = adrNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getAdrNo() {
		return adrNo;
	}
	public void setAdrNo(int adrNo) {
		this.adrNo = adrNo;
	}
	@Override
	public String toString() {
		return "Order [userId=" + userId + ", orderNo=" + orderNo + ", orderDate=" + orderDate + ", totalPrice="
				+ totalPrice + ", adrNo=" + adrNo + "]";
	}
	
	
}
