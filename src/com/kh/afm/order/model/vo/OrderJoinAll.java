package com.kh.afm.order.model.vo;

import java.io.Serializable;

public class OrderJoinAll implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String sellerId;
	private int pNo;
	private String pTitle;
	private int orderCnt;
	private int orderPrice;
	private int orderNo;
	private int accountNo;
	private String bankName;
	
	public OrderJoinAll() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderJoinAll(String orderId, String sellerId, int pNo, String pTitle, int orderCnt, int orderPrice,
			int orderNo, int accountNo, String bankName) {
		super();
		this.orderId = orderId;
		this.sellerId = sellerId;
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.orderCnt = orderCnt;
		this.orderPrice = orderPrice;
		this.orderNo = orderNo;
		this.accountNo = accountNo;
		this.bankName = bankName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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
	public int getOrderCnt() {
		return orderCnt;
	}
	public void setOrderCnt(int orderCnt) {
		this.orderCnt = orderCnt;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Override
	public String toString() {
		return "OrderJoinAll [orderId=" + orderId + ", sellerId=" + sellerId + ", pNo=" + pNo + ", pTitle=" + pTitle
				+ ", orderCnt=" + orderCnt + ", orderPrice=" + orderPrice + ", orderNo=" + orderNo + ", accountNo="
				+ accountNo + ", bankName=" + bankName + "]";
	}

}
